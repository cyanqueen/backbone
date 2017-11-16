package org.backbone.orm.mybatis;

import org.apache.ibatis.annotations.Param;
import org.backbone.orm.parser.SQLParameter;

/**
 * @author bianliang (05/13/2017)
 */
public class DynamicQueryBuilder {

    public String buildUpdate(@Param(DynamicMapper.PARAM_ENTITY_NAME) Object bean) {

        return null;
    }

    public String buildQuery(@Param(DynamicMapper.PARAM_ENTITY_NAME) SQLParameter<?> sqlParameter) {
        String sql = sqlParameter.getSQL();
        return sql;
    }

}
