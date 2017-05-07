package org.backbone.easyweb.orm.mybatis;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;

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
}
