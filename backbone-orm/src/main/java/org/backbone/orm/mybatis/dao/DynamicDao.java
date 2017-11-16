package org.backbone.orm.mybatis.dao;

import org.apache.ibatis.session.SqlSession;
import org.backbone.core.bean.PersistableEntity;
import org.backbone.orm.mybatis.DatabaseRouter;
import org.backbone.orm.mybatis.DynamicMapper;
import org.backbone.orm.parser.MysqlQueryParser;
import org.backbone.orm.parser.SQLParameter;
import org.backbone.orm.parser.QueryParser;
import org.backbone.orm.search.Query;
import org.backbone.orm.search.QueryBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author bianliang (05/07/2017)
 */
public class DynamicDao implements DatabaseRouterDao, InitializingBean {

    private DatabaseRouter router;

    private QueryParser searchParser = new MysqlQueryParser();

    @Required
    public void setRouter(DatabaseRouter router) {
        this.router = router;
    }

    @Override
    public DatabaseRouter getDatabaseRouter() {
        return this.router;
    }

    @Override
    public <T extends PersistableEntity> List<T> query(Query<T> query) {
        SQLParameter<T> sqlParameter = searchParser.parse(query);
        SqlSession sqlSession = this.getSqlSession(sqlParameter);
        DynamicMapper<T> mapper = (DynamicMapper<T>) this.getMapper(query.getBeanType(), sqlSession);
        return mapper.query(sqlParameter);
    }

    @Override
    public <T extends PersistableEntity> T queryOne(Query<T> query) {
        if (query.getLimit() > 1) query.setLimit(1);
        List<T> list = this.query(query);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public <T extends PersistableEntity> T getInclude(PersistableEntity entity, String... includes) {
        Query<T> query = QueryBuilder.custom((Class<T>) entity.getClass())
                .addEquivalent("id", entity.getId())
                .includeFields(includes)
                .build();
        return this.queryOne(query);
    }

    @Override
    public <T extends PersistableEntity> T getExclude(PersistableEntity entity, String... excludes) {
        Query<T> query = QueryBuilder.custom((Class<T>) entity.getClass())
                .addEquivalent("id", entity.getId())
                .excludeFields(excludes)
                .build();
        return this.queryOne(query);
    }

    @Override
    public int update(PersistableEntity entity) {
        SqlSession sqlSession = this.getSqlSession(entity);
        DynamicMapper<PersistableEntity> mapper = this.getMapper(entity.getClass(), sqlSession);
        return mapper.update(entity);
    }

    @Override
    public int update(PersistableEntity entity, Query<? extends PersistableEntity> query) {
        SQLParameter<PersistableEntity> sqlParameter = (SQLParameter<PersistableEntity>) searchParser.parse(query);
        sqlParameter.put(DynamicMapper.PARAM_ENTITY_NAME, entity);
        SqlSession sqlSession = this.getSqlSession(sqlParameter);
        DynamicMapper<PersistableEntity> mapper = this.getMapper(entity.getClass(), sqlSession);
        return mapper.updateBySearch(sqlParameter);
    }

    @Override
    public Long save(PersistableEntity entity) {
        return null;
    }

    @Override
    public List<Long> batchSave(List<? extends PersistableEntity> list) {
        return null;
    }

    @Override
    public int delete(PersistableEntity entity) {
        return 0;
    }

    @Override
    public int delete(Query<? extends PersistableEntity> query) {
        return 0;
    }

    private DynamicMapper<PersistableEntity> getMapper(Class<?> beanType, SqlSession sqlSession) {
        return null;
    }

    private SqlSession getSqlSession(Object o) {
        // 强制使用master
        return getDatabaseRouter().getMasterSqlSession();
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
