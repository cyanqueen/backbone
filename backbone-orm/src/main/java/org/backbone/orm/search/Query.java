package org.backbone.orm.search;

import org.backbone.orm.search.comparator.Comparator;

import java.io.Serializable;
import java.util.List;

/**
 * @author bianliang (05/09/2017)
 */
public interface Query<T extends Serializable> extends Serializable {

    Class<T> getBeanType();

    Query<T> setLimit(int limit);

    int getLimit();

    Query<T> setIncludeFields(List<String> includeFields);

    Query<T> setExcludeFields(List<String> excludeFields);

    Query<T> addComparator(Comparator comparator);

    Query<T> setBeanType(Class<T> beanType);

    List<String> getIncludeFields();

    List<String> getExcludeFields();

    Query<T> setSort(Sort sort);

    Sort getSort();
}
