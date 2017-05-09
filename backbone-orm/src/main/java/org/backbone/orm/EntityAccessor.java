package org.backbone.orm;

import org.backbone.core.bean.StandardEntity;
import org.backbone.core.search.Search;

import java.util.List;

/**
 * @author bianliang (05/09/2017)
 */
public interface EntityAccessor {

    <T extends StandardEntity> List<T> query(Search<T> search);
    <T extends StandardEntity> T queryOne(Search<T> search);

    <T extends StandardEntity> T getInclude(T t, String ... includes);
    <T extends StandardEntity> T getExclude(T t, String ... excludes);

    <T extends StandardEntity> int update(T t);
    <T extends StandardEntity> int update(T t, Search<T> search);

    <T extends StandardEntity> Long save(T t);

    <T extends StandardEntity> int delete(T t);
    <T extends StandardEntity> int delete(Search<T> search);
}
