package org.backbone.orm.search.comparator;

import java.lang.reflect.Array;
import java.util.Collection;

/**
 * @author bianliang (07/16/2017)
 * @since 0.0.1
 */
public class InComparator extends SingleComparator {

    private boolean reverse;

    public InComparator(String name, Object value, LogicalOperator lo) {
        this(name, value, false, lo);
    }

    public InComparator(String name, Object value, boolean reverse, LogicalOperator lo) {
        super(name, value, lo);
        this.reverse = reverse;
        if (name == null) throw new IllegalArgumentException("name can't be null");
        if (value == null) throw new IllegalArgumentException("value can't be null");
        if (!value.getClass().isArray() && !(value instanceof Collection)) {
            throw new IllegalArgumentException("value must be array or collection!");
        }
        int length;
        if (value.getClass().isArray()) {
            length = Array.getLength(value);
        } else {
            length = ((Collection<?>)value).size();
        }
        if (length == 0) throw new IllegalArgumentException("Array must not be empty");
    }

    @Override
    public String getExpression() {
        if (reverse) return "#{name} NOT IN (#{value})";
        return "#{name} IN (#{value})";
    }
}
