package org.backbone.orm.parse;

import org.backbone.core.search.Search;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author bianliang (05/14/2017)
 */
public class SQLParameter<T extends Serializable> extends HashMap implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(SQLParameter.class);

    private String selectClause;
    private String tableNames;
    private String whereClause;
    private String groupClause;
    private String limitClause;

    private Class<T> resultType;
    private Search<T> search;

    public SQLParameter(Search<T> search) {
        super();
        this.search = search;
    }

    public String getSQL() {
        StringBuffer sql =  new StringBuffer("select ")
                .append(selectClause)
                .append(" from ")
                .append(tableNames)
                .append(whereClause);
        if (groupClause != null) sql.append(groupClause);
        if (limitClause != null) sql.append(limitClause);

        LOG.debug("Preparing to execute sql statement is:{}", sql);
        return sql.toString();
    }

    public Class<T> getResultType() {
        return resultType;
    }

    public void setResultType(Class<T> resultType) {
        this.resultType = resultType;
    }

    public Search<T> getSearch() {
        return search;
    }

    public void setSearch(Search<T> search) {
        this.search = search;
    }

    public String getSelectClause() {
        return selectClause;
    }

    public void setSelectClause(String selectClause) {
        this.selectClause = selectClause;
    }

    public String getTableNames() {
        return tableNames;
    }

    public void setTableNames(String tableNames) {
        this.tableNames = tableNames;
    }

    public String getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    public String getGroupClause() {
        return groupClause;
    }

    public void setGroupClause(String groupClause) {
        this.groupClause = groupClause;
    }

    public String getLimitClause() {
        return limitClause;
    }

    public void setLimitClause(String limitClause) {
        this.limitClause = limitClause;
    }

    @Override
    public String toString() {
        return "SQLParameter:" + getSQL();
    }
}
