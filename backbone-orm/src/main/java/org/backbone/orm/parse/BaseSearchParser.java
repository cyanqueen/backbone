package org.backbone.orm.parse;

import org.backbone.core.search.Search;

import java.io.Serializable;

/**
 * @author bianliang (05/14/2017)
 */
public abstract class BaseSearchParser implements SearchParser {

    @Override
    public <T extends Serializable> SQLParameter parse(Search<T> search) {
        SQLParameter<T> sqlParameter = new SQLParameter(search);
        sqlParameter.setResultType(search.getBeanType());
        parseSelectClause(sqlParameter, search);
        parseTableNamesClause(sqlParameter, search);
        parseWhereClause(sqlParameter, search);
        parseGroupClause(sqlParameter, search);
        parseLimitClause(sqlParameter, search);
        return sqlParameter;
    }

    protected <T extends Serializable> void parseLimitClause(SQLParameter<T> sqlParameter, Search<T> search) {
        String limitClause = null;

        sqlParameter.setLimitClause(limitClause);
    }

    protected <T extends Serializable> void parseGroupClause(SQLParameter<T> sqlParameter, Search<T> search) {
        String groupClause = null;

        sqlParameter.setGroupClause(groupClause);
    }

    protected <T extends Serializable> void parseWhereClause(SQLParameter<T> sqlParameter, Search<T> search) {
        String whereClause = "";

        sqlParameter.setWhereClause(whereClause);
    }

    protected <T extends Serializable> void parseTableNamesClause(SQLParameter<T> sqlParameter, Search<T> search) {
        String tableNames = "";

        sqlParameter.setTableNames(tableNames);
    }

    protected <T extends Serializable> void parseSelectClause(SQLParameter<T> sqlParameter, Search<T> search) {
        String selectClause = "";

        sqlParameter.setSelectClause(selectClause);
    }
}
