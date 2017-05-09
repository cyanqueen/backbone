package org.backbone.orm;

import org.backbone.core.bean.PersistableEntity;
import org.backbone.core.search.Search;

import java.util.List;

/**
 * @author bianliang (05/09/2017)
 */
public interface EntityAccessor {

    <T extends PersistableEntity> List<T> query(Search<T> search);

    <T extends PersistableEntity> T queryOne(Search<T> search);

    <T extends PersistableEntity> T getInclude(T t, String ... includes);

    <T extends PersistableEntity> T getExclude(T t, String ... excludes);

    int update(PersistableEntity entity);

    int update(PersistableEntity entity, Search<? extends PersistableEntity> search);

    Long save(PersistableEntity entity);

    int delete(PersistableEntity entity);

    int delete(Search<? extends PersistableEntity> search);
}
