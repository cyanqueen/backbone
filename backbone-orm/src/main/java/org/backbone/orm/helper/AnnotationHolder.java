package org.backbone.orm.helper;

import org.backbone.core.annotation.Column;
import org.backbone.core.annotation.PrimaryKey;

import java.lang.reflect.Field;

/**
 * Holds annotations on fields.
 *
 * @author bianliang (06/04/2017)
 */
public class AnnotationHolder {

    private Field field;

    private Column column;

    private PrimaryKey primaryKey;

    private String ognl;

    public AnnotationHolder() {}

    public AnnotationHolder(Field field, Column column, PrimaryKey primaryKey, String ognl) {
        this.field = field;
        this.column = column;
        this.primaryKey = primaryKey;
        this.ognl = ognl;
    }

    public String getOgnl() {
        return ognl;
    }

    public void setOgnl(String ognl) {
        this.ognl = ognl;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public PrimaryKey getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(PrimaryKey primaryKey) {
        this.primaryKey = primaryKey;
    }
}
