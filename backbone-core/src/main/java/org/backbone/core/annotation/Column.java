package org.backbone.core.annotation;

import org.backbone.core.enums.DataType;

import java.lang.annotation.*;

/**
 * @author bianliang (06/01/2017)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Column {

    /**
     * 数据库列名
     */
    String value() default "#";

    /**
     * 数据类型
     */
    DataType dataType() default DataType.VARCHAR;

    /**
     * 是否无符号
     */
    boolean unsigned() default false;

    /**
     * 默认值
     */
    String defaultValue() default "";

    /**
     * 长度
     */
    String length() default "";

    /**
     * 是否可以为空
     */
    boolean notNull() default true;

    /**
     * 注释
     */
    String comment() default "";

    /**
     * 外键 ，例如定义如下列：<br/>
     * <pre>
     *     @ Column(type = DataType.BIGINT, comment = "项目ID", referenceField = "id")
     *     private Subject subject;
     * </pre>
     * 那么将会生成选择语句: subject AS `subject.id`
     * @return
     */
    String referenceField() default "";


}
