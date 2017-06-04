package org.backbone.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表示索引行属性
 *
 * @author bianliang (05/29/2017)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface IndexColumn {

    /**
     * 索引字段名称
     *
     * @return
     */
    String value();

    /**
     * 索引字段长度
     *
     * @return
     */
    int length() default 0;
}
