package org.backbone.orm.parser;

import org.backbone.orm.search.Search;
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
    private String tableName;
    private String whereClause;
    private String orderByClause;
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
                .append(tableName)
                .append(whereClause);
        if (orderByClause != null) sql.append(orderByClause);
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

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
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
