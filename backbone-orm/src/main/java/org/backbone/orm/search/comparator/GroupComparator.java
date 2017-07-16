package org.backbone.orm.search.comparator;

/**
 * @author bianliang (07/16/2017)
 * @since 0.0.1
 */
public class GroupComparator extends LogicalComparator {

    public static final String START = "(";
    public static final String END = ")";

    private boolean start;

    public GroupComparator(boolean start, LogicalOperator lo) {
        super("", lo);
        this.start = start;
    }

    @Override
    public String getExpression() {
        return this.start ? START : END;
    }
}
