package org.backbone.orm.search;

/**
 * @author bianliang (05/22/2017)
 */
public class EquivalentComparator implements Comparator {

    private String name;
    private Object value;

    public EquivalentComparator(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
