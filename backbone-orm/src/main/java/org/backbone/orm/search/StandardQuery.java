package org.backbone.orm.search;

import org.backbone.core.bean.PersistableEntity;
import org.backbone.orm.search.comparator.Comparator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bianliang (05/21/2017)
 */
public class StandardQuery<T extends PersistableEntity> implements Query {

    private Class<T> beanType;
    private int limit;
    private List<String> includeFields;
    private List<String> excludeFields;
    private List<Comparator> comparators;
    private Sort sort;

    public StandardQuery() {
        this.comparators = new ArrayList<Comparator>();
    }

    @Override
    public Class<T> getBeanType() {
        return beanType;
    }

    @Override
    public Query<T> setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    public int getLimit() {
        return limit;
    }

    @Override
    public Query<T> setExcludeFields(List excludeFields) {
        this.excludeFields = excludeFields;
        return this;
    }

    @Override
    public Query<T> setIncludeFields(List includeFields) {
        this.includeFields = includeFields;
        return this;
    }

    @Override
    public Query<T> addComparator(Comparator comparator) {
        if (comparator != null) comparators.add(comparator);
        return this;
    }

    @Override
    public Query<T> setBeanType(Class beanType) {
        this.beanType = beanType;
        return this;
    }

    @Override
    public List<String> getIncludeFields() {
        return this.includeFields;
    }

    @Override
    public List<String> getExcludeFields() {
        return this.excludeFields;
    }

    @Override
    public Query<T> setSort(Sort sort) {
        this.sort = sort;
        return this;
    }

    @Override
    public Sort getSort() {
        return this.sort;
    }


}
