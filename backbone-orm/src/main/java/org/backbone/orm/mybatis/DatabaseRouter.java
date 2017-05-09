package org.backbone.orm.mybatis;

import org.apache.ibatis.session.SqlSession;

/**
 * @author bianliang (05/07/2017)
 */
public interface DatabaseRouter {
    SqlSession getMasterSqlSession();

    SqlSession getDatabaseSqlSession();

    String getUsingDatabase();
}
