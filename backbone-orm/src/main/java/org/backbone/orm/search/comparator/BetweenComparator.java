package org.backbone.orm.search.comparator;

/**
 * @author bianliang (07/16/2017)
 * @since 0.0.1
 */
public class BetweenComparator extends BinaryComparator {

    public BetweenComparator(String name, Object value1, Object value2, LogicalOperator lo) {
        super(name, value1, value2, lo);
        if (name == null) throw new IllegalArgumentException("name can't be null");
        if (value1 == null) throw new IllegalArgumentException("value1 can't be null");
        if (value2 == null) throw new IllegalArgumentException("value2 can't be null");
    }

    @Override
    public String getExpression() {
        return "#{name} BETWEEN #{value1} AND #{value2}";
    }
}
