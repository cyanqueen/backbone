package org.backbone.core.search;

import java.io.Serializable;

/**
 * @author bianliang (05/09/2017)
 */
public interface Search<T extends Serializable> extends Serializable {

    Class<T> getBeanType();
}
