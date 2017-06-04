package org.backbone.core.annotation;

import org.backbone.core.enums.IndexType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表示索引
 *
 * @author bianliang (05/29/2017)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface Index {

    /**
     * 索引名称
     *
     * @return
     */
    String value() default "";

    /**
     * 索引字段
     *
     * @return
     */
    IndexColumn[] columns() default {};

    /**
     * 索引类型
     *
     * @return
     */
    IndexType type() default IndexType.NULL;

}
