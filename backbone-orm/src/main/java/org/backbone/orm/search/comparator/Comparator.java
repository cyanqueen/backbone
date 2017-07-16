package org.backbone.orm.search.comparator;

import java.io.Serializable;

/**
 * @author bianliang (05/22/2017)
 */
public interface Comparator extends Serializable {

    LogicalOperator DEFAULT_LOGICAL_OPERATOR = LogicalOperator.AND;

    String getName();

    LogicalOperator getLogicalOperator();

    String getExpression();
}
