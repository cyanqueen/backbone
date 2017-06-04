package org.backbone.core.annotation;

import java.lang.annotation.*;

/**
 * Component
 *
 * @author bianliang (06/04/2017)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Component {
    String[] excludes() default {};
}
