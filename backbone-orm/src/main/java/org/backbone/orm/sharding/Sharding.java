package org.backbone.orm.sharding;

/**
 * @author bianliang (05/14/2017)
 */
public interface Sharding {
    String getDatabaseName(Object o);
    String getTableName(Object o);
}
