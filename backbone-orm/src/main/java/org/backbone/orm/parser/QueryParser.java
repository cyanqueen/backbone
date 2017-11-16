package org.backbone.orm.parser;

import org.backbone.orm.search.Query;

import java.io.Serializable;

/**
 * @author bianliang (05/14/2017)
 */
public interface QueryParser {

    <T extends Serializable> SQLParameter<T> parse(Query<T> query);
}
