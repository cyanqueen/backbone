package org.backbone.orm.search.comparator;

/**
 * @author bianliang (05/22/2017)
 */
public class EquivalentComparator extends SingleComparator {

    private boolean reverse;

    public EquivalentComparator(String name, Object value, LogicalOperator lo) {
        this(name, value, lo, false);
    }

    public EquivalentComparator(String name, Object value, LogicalOperator lo, boolean reverse) {
        super(name, value, lo);
        this.reverse = reverse;
        if (name == null) throw new IllegalArgumentException("name can't be null");
        if (value == null) throw new IllegalArgumentException("value can't be null");
    }

    @Override
    public String getExpression() {
        if (reverse) return "#{name}!=#{value}";
        return "#{name}=#{value}";
    }
}
