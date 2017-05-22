package org.backbone.orm;

import org.backbone.core.bean.PersistableEntity;
import org.backbone.orm.search.Search;

import java.util.List;

/**
 * @author bianliang (05/09/2017)
 */
public interface EntityAccessor {

    <T extends PersistableEntity> List<T> query(Search<T> search);

    <T extends PersistableEntity> T queryOne(Search<T> search);

    <T extends PersistableEntity> T getInclude(PersistableEntity entity, String ... includes);

    <T extends PersistableEntity> T getExclude(PersistableEntity entity, String ... excludes);

    int update(PersistableEntity entity);

    int update(PersistableEntity entity, Search<? extends PersistableEntity> search);

    Long save(PersistableEntity entity);

    List<Long> batchSave(List<? extends PersistableEntity> list);

    int delete(PersistableEntity entity);

    int delete(Search<? extends PersistableEntity> search);
}
