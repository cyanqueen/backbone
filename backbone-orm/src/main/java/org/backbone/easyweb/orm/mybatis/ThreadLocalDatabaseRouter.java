package org.backbone.easyweb.orm.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * @author bianliang (05/07/2017)
 */
public class ThreadLocalDatabaseRouter implements DatabaseRouter, InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(ThreadLocalDatabaseRouter.class);
    private static final String MASTER_DATABASE = "master";

    private Map<String, SqlSession> sqlSessionMap;

    private SqlSession masterSqlSession;

    private ThreadLocal<String> databaseThreadLocal;

    public ThreadLocalDatabaseRouter() {
        databaseThreadLocal = new ThreadLocal<String>();
    }

    @Required
    public void setMasterSqlSession(SqlSession masterSqlSession) {
        Assert.notNull(masterSqlSession, "数据库路由参数:masterSqlSession不能为空");
        this.masterSqlSession = masterSqlSession;
    }

    @Required
    public void setSqlSessionMap(Map<String, SqlSession> sqlSessionMap) {
        Assert.notEmpty(sqlSessionMap, "数据库路由参数:sqlSessionMap不能为空");
        this.sqlSessionMap = sqlSessionMap;
    }

    @Override
    public SqlSession getMasterSqlSession() {
        return this.masterSqlSession;
    }

    @Override
    public SqlSession getDatabaseSqlSession() {
        String databaseName = getUsingDatabase();
        return sqlSessionMap.get(databaseName);
    }

    public String getUsingDatabase() {
        return databaseThreadLocal.get();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        databaseThreadLocal.set(MASTER_DATABASE);
    }
}
