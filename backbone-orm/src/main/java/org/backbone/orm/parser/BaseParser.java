package org.backbone.orm.parser;

import org.backbone.core.annotation.Table;
import org.backbone.core.util.SqlUtils;
import org.backbone.orm.helper.AnnotationHelper;
import org.backbone.orm.helper.AnnotationHolder;
import org.backbone.orm.search.Search;
import org.backbone.orm.search.Sort;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author bianliang (05/14/2017)
 */
public abstract class BaseParser implements SearchParser {

    private Map<Class<?>, Map<String, String>> FIELDS_CACHE = new ConcurrentHashMap<Class<?>, Map<String, String>>();

    @Override
    public <T extends Serializable> SQLParameter parse(Search<T> search) {
        SQLParameter<T> sqlParameter = new SQLParameter(search);
        sqlParameter.setResultType(search.getBeanType());
        parseSelectClause(sqlParameter, search);
        parseFrom(sqlParameter, search);
        parseWhereClause(sqlParameter, search);
        parseOrderBy(sqlParameter, search);
        parseLimitClause(sqlParameter, search);
        return sqlParameter;
    }

    protected void parseSelectClause(SQLParameter sqlParameter, Search<? extends Serializable> search) {
        Collection<String> selectFields = getSelectFields(search);
        String selectClause = listToString(selectFields, ",");
        sqlParameter.setSelectClause(selectClause);
    }

    protected Collection<String> getSelectFields(Search<? extends Serializable> search) {
        Class<?> beanType = search.getBeanType();
        Collection<String> allFields0 = getAllFields(beanType);

        Collection<String> allFields = new ArrayList<String>(allFields0);

        List<String> includeFields = search.getIncludeFields();
        if (includeFields != null && includeFields.size() > 0) {
            Map<String, String> fs = FIELDS_CACHE.get(beanType);
            Collection<String> fields = new ArrayList<String>();
            for (String includeField : includeFields) {
                String field = fs.get(includeField);
                if (field != null) {
                    fields.add(includeField);
                }
            }
            allFields = fields;
        }

        List<String> excludeFields = search.getExcludeFields();
        if (excludeFields != null && excludeFields.size() > 0) {
            Collection<String> excludeParsedFields = new ArrayList<String>();
            for (String name : excludeFields) {
                String parsedName = getSelectField(name, search, false);
                excludeParsedFields.add(parsedName);
            }
            allFields.removeAll(excludeParsedFields);
        }
        return allFields;
    }

    private String getSelectField(String field, Search<? extends Serializable> search, boolean inWhere) {
        Class<?> beanType = search.getBeanType();
        AnnotationHolder ah = AnnotationHelper.getAnnotationHolder(field, beanType);
        if (ah == null) throw new IllegalArgumentException(" Can't find field `"+ field +"` in class["+ beanType +"]");

        String name = ah.getField().getName();
        if (!inWhere) {
            String ref = ah.getColumn().referenceField();
            if (SqlUtils.isNotBlank(ah.getOgnl())) {
                name = "`" + name + "` as `" + ah.getOgnl() + "." + ah.getField().getName() + "`";
            } else if (SqlUtils.isNotBlank(ref)) {
                name = "`" + name + "` as `" + ah.getField().getName() + "." + ref + "`";
            }
        }

        return name;
    }

    private Collection<String> getAllFields(Class<?> beanType) {
        Map<String, String> map = FIELDS_CACHE.get(beanType);
        if (map != null) return map.values();

        map = new HashMap<String, String>();
        List<AnnotationHolder> holders = AnnotationHelper.getAnnotationHolders(beanType);
        if (holders != null && holders.size() > 0) {
            for (int i = 0; i < holders.size(); i++) {
                AnnotationHolder holder = holders.get(i);
                String as = holder.getField().getName();
                String name = "`" + AnnotationHelper.getColumnName(holder) + "`";
                String ognl = holder.getOgnl();
                String ref = holder.getColumn().referenceField();
                if (!SqlUtils.isBlank(ognl)) {
                    as = ognl + "." + as;
                    name = name + " AS `" + as + "`";
                } else if (!SqlUtils.isBlank(ref)) {
                    String refAs = holder.getField().getName() + "." + ref;
                    name = name + " AS `" + refAs + "`";
                    map.put(refAs, name);
                }
                map.put(as, name);
            }
            FIELDS_CACHE.put(beanType, map);
        }
        return map.values();
    }

    protected String listToString(Collection<String> selectFields, String sep) {
        if (selectFields != null && selectFields.size() > 0) {
            String[] fields = selectFields.toArray(new String[selectFields.size()]);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < fields.length; i++) {
                sb.append(fields[i]);
                if (i < fields.length - 1) sb.append(sep);
            }
            return sb.toString();
        }
        return "*";
    }

    protected void parseFrom(SQLParameter sqlParameter, Search search) {
        Class<?> beanType = search.getBeanType();
        String tableName = AnnotationHelper.getTableName(beanType);
        sqlParameter.setTableName(tableName);
    }

    protected void parseWhereClause(SQLParameter sqlParameter, Search search) {
        String whereClause = "";
        Class beanType = search.getBeanType();
        sqlParameter.setWhereClause(whereClause);
    }

    protected void parseLimitClause(SQLParameter sqlParameter, Search search) {
        StringBuilder limitClause = new StringBuilder();
        int limit = search.getLimit();
        if (limit > 0) limitClause.append(" LIMIT ").append(limit);
        sqlParameter.setLimitClause(limitClause.toString());
    }

    protected void parseOrderBy(SQLParameter sqlParameter, Search search) {
        String sortClause = null;
        Sort sort = search.getSort();
        if (sort != null) {
            sortClause = " ORDER BY " + sort.getName();
            if (sort.getDirection() != null) sortClause = sortClause + sort.getDirection().name();
        }
        sqlParameter.setOrderByClause(sortClause);
    }
}
