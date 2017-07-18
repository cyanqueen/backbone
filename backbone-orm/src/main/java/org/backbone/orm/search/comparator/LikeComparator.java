package org.backbone.orm.search.comparator;

/**
 * @author bianliang (07/16/2017)
 * @since 0.0.1
 */
public class LikeComparator extends SingleComparator {

    private boolean reverse;

    public LikeComparator(String name, Object value, LogicalOperator lo) {
        this(name, value, false, lo);
    }

    public LikeComparator(String name, Object value, boolean reverse, LogicalOperator lo) {
        super(name, value, lo);
        this.reverse = reverse;
        if (name == null) throw new IllegalArgumentException("name can't be null");
        if (value == null) throw new IllegalArgumentException("value can't be null");
    }

    @Override
    public String getExpression() {
        if (reverse) return "#{name} NOT LIKE #{value}";
        return "#{name} LIKE #{value}";
    }
}
