package org.backbone.core.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bianliang (05/09/2017)
 */
public class StandardEntity implements Serializable {

    private Date createTime;

    private Date updateTime;

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
