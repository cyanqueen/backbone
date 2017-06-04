package org.backbone.core.annotation;

import java.lang.annotation.*;

/**
 * 标识实体与表的对应关系
 *
 * @author bianliang (05/29/2017)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Table {

    /**
     * 数据库表名,默认情况表名为t_className
     *
     * @return
     */
    String value() default "";

    /**
     * 索引
     *
     * @return
     */
    Index[] indexes() default {};

    /**
     * 注释
     *
     * @return
     */
    String comment() default "";


    String charset() default "utf8";

    /**
     * 是否临时表
     *
     * @return
     */
    boolean isTemporary() default false;

    String engine() default "";

    /**
     * "IF NOT EXISTS"语句默认起作用
     *
     * @return
     */
    boolean ifNotExists() default true;




}
