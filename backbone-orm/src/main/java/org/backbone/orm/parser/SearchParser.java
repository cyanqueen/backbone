package org.backbone.orm.parser;

import org.backbone.orm.search.Search;

import java.io.Serializable;

/**
 * @author bianliang (05/14/2017)
 */
public interface SearchParser {

    <T extends Serializable> SQLParameter<T> parse(Search<T> search);
}
