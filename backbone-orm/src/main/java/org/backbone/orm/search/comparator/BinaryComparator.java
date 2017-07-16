package org.backbone.orm.search.comparator;

/**
 * @author bianliang (07/16/2017)
 * @since 0.0.1
 */
public abstract class BinaryComparator extends LogicalComparator {

    private Object value1;
    private Object value2;

    public BinaryComparator(String name, Object value1, Object value2, LogicalOperator lo) {
        super(name, lo);
        this.value1 = value1;
        this.value2 = value2;
    }

    public Object getValue1() {
        return value1;
    }

    public void setValue1(Object value1) {
        this.value1 = value1;
    }

    public Object getValue2() {
        return value2;
    }

    public void setValue2(Object value2) {
        this.value2 = value2;
    }
}
