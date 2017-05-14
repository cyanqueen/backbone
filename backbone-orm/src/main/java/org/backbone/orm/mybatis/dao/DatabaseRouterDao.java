package org.backbone.orm.mybatis.dao;

import org.backbone.orm.EntityAccessor;
import org.backbone.orm.mybatis.DatabaseRouter;

/**
 * @author bianliang (05/07/2017)
 */
public interface DatabaseRouterDao extends EntityAccessor {

    DatabaseRouter getDatabaseRouter();
}
