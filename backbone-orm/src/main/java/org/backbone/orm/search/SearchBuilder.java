package org.backbone.orm.search;

import org.backbone.core.bean.PersistableEntity;
import org.backbone.orm.search.comparator.Comparator;
import org.backbone.orm.search.comparator.EquivalentComparator;
import org.springframework.util.Assert;

import java.util.Arrays;

/**
 * @author bianliang (05/21/2017)
 */
public class SearchBuilder<T extends PersistableEntity> {

    private Search<T> search;

    private SearchBuilder(Class<T> beanType) {
        Assert.notNull(beanType, "beanType can not be null!");
        search = new StandardSearch<T>();
        search.setBeanType(beanType);
    }


    public static <T extends PersistableEntity> SearchBuilder<T> custom(Class<T> beanType) {
        return new SearchBuilder<T>(beanType);
    }

    public SearchBuilder<T> limit(int limit) {
        search.setLimit(limit);
        return this;
    }

    public Search<T> build() {
        return search;
    }

    public SearchBuilder<T> addEquivalent(String name, Object value) {
        search.addComparator(new EquivalentComparator(name, value, Comparator.DEFAULT_LOGICAL_OPERATOR));
        return this;
    }

    public SearchBuilder<T> addEquivalent(String name, Object value, boolean reserve) {
        search.addComparator(new EquivalentComparator(name, value, Comparator.DEFAULT_LOGICAL_OPERATOR, reserve));
        return this;
    }

    public SearchBuilder<T> includeFields(String... includes) {
        search.setIncludeFields(Arrays.asList(includes));
        return this;
    }

    public SearchBuilder<T> excludeFields(String... excludes) {
        search.setExcludeFields(Arrays.asList(excludes));
        return this;
    }

    public SearchBuilder<T> orderBy(String name, SortDirection sortDirection) {
        search.setSort(new Sort(name, sortDirection));
        return this;
    }
}
