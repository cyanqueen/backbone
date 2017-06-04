package org.backbone.core.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bianliang (05/09/2017)
 */
public abstract class EntityBean implements PersistableEntity {

    private static final long serialVersionUID = 4316289291606068159L;

    private Long id;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
