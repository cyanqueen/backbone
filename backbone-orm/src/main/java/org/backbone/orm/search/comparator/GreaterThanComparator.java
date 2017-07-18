package org.backbone.orm.search.comparator;

/**
 * @author bianliang (07/16/2017)
 * @since 0.0.1
 */
public class GreaterThanComparator extends SingleComparator {

    private boolean include;

    public GreaterThanComparator(String name, Object value, LogicalOperator lo) {
        this(name, value, false, lo);
    }

    public GreaterThanComparator(String name, Object value, boolean include, LogicalOperator lo) {
        super(name, value, lo);
        this.include = include;
        if (name == null) throw new IllegalArgumentException("name can't be null");
        if (value == null) throw new IllegalArgumentException("value can't be null");
    }


    @Override
    public String getExpression() {
        if (include) return "#{name}>=#{value}";
        return "#{name}>#{value}";
    }
}
