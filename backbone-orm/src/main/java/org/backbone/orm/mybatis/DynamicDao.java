package org.backbone.orm.mybatis;

import org.backbone.core.bean.PersistableEntity;
import org.backbone.core.search.Search;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

/**
 * @author bianliang (05/07/2017)
 */
public class DynamicDao implements DatabaseRouterDao, InitializingBean {

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
    public <T extends PersistableEntity> List<T> query(Search<T> search) {
        return null;
    }

    @Override
    public <T extends PersistableEntity> T queryOne(Search<T> search) {
        return null;
    }

    @Override
    public <T extends PersistableEntity> T getInclude(T t, String... includes) {
        return null;
    }

    @Override
    public <T extends PersistableEntity> T getExclude(T t, String... excludes) {
        return null;
    }

    @Override
    public int update(PersistableEntity entity) {
        return 0;
    }

    @Override
    public int update(PersistableEntity entity, Search<? extends PersistableEntity> search) {
        return 0;
    }

    @Override
    public Long save(PersistableEntity entity) {
        return null;
    }

    @Override
    public int delete(PersistableEntity entity) {
        return 0;
    }

    @Override
    public int delete(Search<? extends PersistableEntity> search) {
        return 0;
    }
}
