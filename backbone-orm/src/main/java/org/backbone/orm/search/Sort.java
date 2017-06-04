package org.backbone.orm.search;

import java.io.Serializable;

/**
 * @author bianliang (05/26/2017)
 */
public class Sort implements Serializable {

    private String name;
    private SortDirection direction;


    public Sort(String name, SortDirection direction) {
        this.name = name;
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SortDirection getDirection() {
        return direction;
    }

    public void setDirection(SortDirection direction) {
        this.direction = direction;
    }
}
