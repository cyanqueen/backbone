package org.backbone.orm.mybatis;

import org.backbone.core.bean.StandardEntity;
import org.backbone.core.search.Search;
import org.backbone.orm.EntityAccessor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

/**
 * @author bianliang (05/07/2017)
 */
public class DynamicDao implements EntityAccessor, DatabaseRouterDao, InitializingBean {

    private DatabaseRouter router;

    @Required
    public void setRouter(DatabaseRouter router) {
        this.router = router;
    }

    @Override
    public DatabaseRouter getDatabaseRouter() {
        return this.router;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public <T extends StandardEntity> List<T> query(Search<T> search) {
        return null;
    }

    @Override
    public <T extends StandardEntity> T queryOne(Search<T> search) {
        return null;
    }

    @Override
    public <T extends StandardEntity> T getInclude(T t, String... includes) {
        return null;
    }

    @Override
    public <T extends StandardEntity> T getExclude(T t, String... excludes) {
        return null;
    }

    @Override
    public <T extends StandardEntity> int update(T t) {
        return 0;
    }

    @Override
    public <T extends StandardEntity> int update(T t, Search<T> search) {
        return 0;
    }

    @Override
    public <T extends StandardEntity> Long save(T t) {
        return null;
    }

    @Override
    public <T extends StandardEntity> int delete(T t) {
        return 0;
    }

    @Override
    public <T extends StandardEntity> int delete(Search<T> search) {
        return 0;
    }
}
