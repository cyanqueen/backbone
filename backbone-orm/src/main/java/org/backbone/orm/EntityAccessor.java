package org.backbone.orm;

import org.backbone.core.bean.PersistableEntity;
import org.backbone.orm.search.Query;

import java.util.List;

/**
 * @author bianliang (05/09/2017)
 */
public interface EntityAccessor {

    <T extends PersistableEntity> List<T> query(Query<T> query);

    <T extends PersistableEntity> T queryOne(Query<T> query);

    <T extends PersistableEntity> T getInclude(PersistableEntity entity, String ... includes);

    <T extends PersistableEntity> T getExclude(PersistableEntity entity, String ... excludes);

    int update(PersistableEntity entity);

    int update(PersistableEntity entity, Query<? extends PersistableEntity> query);

    Long save(PersistableEntity entity);

    List<Long> batchSave(List<? extends PersistableEntity> list);

    int delete(PersistableEntity entity);

    int delete(Query<? extends PersistableEntity> query);
}
