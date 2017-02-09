package org.backbone.easyweb.springmvc.annotation;

import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.annotation.*;

/**
 * @author bianliang (02/09/2017)
 * @since 0.0.1
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE})
public @interface Interceptor {

    boolean loadFromContainer() default false;

    Class<? extends HandlerInterceptor> value();

}
