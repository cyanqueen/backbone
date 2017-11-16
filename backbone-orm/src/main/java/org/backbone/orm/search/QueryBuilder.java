package org.backbone.orm.search;

import org.backbone.core.bean.PersistableEntity;
import org.backbone.orm.search.comparator.EquivalentComparator;
import org.backbone.orm.search.comparator.LogicalOperator;
import org.springframework.util.Assert;

import java.util.Arrays;

/**
 * @author bianliang (05/21/2017)
 */
public class QueryBuilder<T extends PersistableEntity> {

    private Query<T> query;

    private QueryBuilder(Class<T> beanType) {
        Assert.notNull(beanType, "beanType can not be null!");
        query = new StandardQuery<T>();
        query.setBeanType(beanType);
    }


    public static <T extends PersistableEntity> QueryBuilder<T> custom(Class<T> beanType) {
        return new QueryBuilder<T>(beanType);
    }

    public QueryBuilder<T> limit(int limit) {
        query.setLimit(limit);
        return this;
    }

    public Query<T> build() {
        return query;
    }

    public QueryBuilder<T> addEquivalent(String name, Object value) {
        query.addComparator(new EquivalentComparator(name, value, LogicalOperator.AND));
        return this;
    }

    public QueryBuilder<T> addEquivalent(String name, Object value, boolean reserve) {
        query.addComparator(new EquivalentComparator(name, value, reserve, LogicalOperator.AND));
        return this;
    }

    public QueryBuilder<T> includeFields(String... includes) {
        query.setIncludeFields(Arrays.asList(includes));
        return this;
    }

    public QueryBuilder<T> excludeFields(String... excludes) {
        query.setExcludeFields(Arrays.asList(excludes));
        return this;
    }

    public QueryBuilder<T> orderBy(String name, SortDirection sortDirection) {
        query.setSort(new Sort(name, sortDirection));
        return this;
    }
}
