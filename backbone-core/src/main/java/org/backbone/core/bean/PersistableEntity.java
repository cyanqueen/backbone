package org.backbone.core.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bianliang (05/09/2017)
 */
public interface PersistableEntity extends Serializable {

    Long getId();

    void setId(Long id);

    Date getCreateTime();

    void setCreateTime(Date createTime);

    Date getUpdateTime();

    void setUpdateTime(Date updateTime);

}
