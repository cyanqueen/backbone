package org.backbone.orm.mybatis;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.backbone.orm.parser.SQLParameter;

import java.io.Serializable;
import java.util.List;

/**
 * @author bianliang (05/10/2017)
 */
public interface DynamicMapper<T extends Serializable> {

    String PARAM_ENTITY_NAME = "bean";

    @UpdateProvider( type = DynamicSearchBuilder.class, method = "buildUpdate")
    int update(@Param(PARAM_ENTITY_NAME) T bean);

    @UpdateProvider(type = DynamicSearchBuilder.class, method = "buildSearchUpdate")
    int updateBySearch(@Param(PARAM_ENTITY_NAME) SQLParameter<T> sqlParameter);

    @SelectProvider(type = DynamicSearchBuilder.class, method = "buildQuery")
    List<T> query(@Param(PARAM_ENTITY_NAME) SQLParameter<T> sqlParameter);
}
