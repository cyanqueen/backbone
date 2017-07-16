package org.backbone.orm.search.comparator;

/**
 * @author bianliang (07/16/2017)
 * @since 0.0.1
 */
public abstract class SingleComparator extends LogicalComparator {

    private Object value;

    public SingleComparator(String name, Object value, LogicalOperator lo) {
        super(name, lo);
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
