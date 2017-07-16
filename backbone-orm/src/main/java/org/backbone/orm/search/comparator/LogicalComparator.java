package org.backbone.orm.search.comparator;

/**
 * @author bianliang (07/16/2017)
 * @since 0.0.1
 */
public abstract class LogicalComparator implements Comparator {

    private String name;

    private LogicalOperator lo;

    public LogicalComparator(String name, LogicalOperator lo) {
        this.name = name;
        this.lo = lo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LogicalOperator getLogicalOperator() {
        return lo;
    }

    public void setLogicalOperator(LogicalOperator lo) {
        this.lo = lo;
    }
}
