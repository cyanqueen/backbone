package org.backbone.orm.search;

import java.io.Serializable;
import java.util.List;

/**
 * @author bianliang (05/09/2017)
 */
public interface Search<T extends Serializable> extends Serializable {

    Class<T> getBeanType();

    Search<T> setLimit(int limit);

    int getLimit();

    Search<T> setIncludeFields(List<String> includeFields);

    Search<T> setExcludeFields(List<String> excludeFields);

    Search<T> addComparator(Comparator comparator);

    Search<T> setBeanType(Class<T> beanType);

    List<String> getIncludeFields();

    List<String> getExcludeFields();

    Search<T> setSort(Sort sort);

    Sort getSort();
}
