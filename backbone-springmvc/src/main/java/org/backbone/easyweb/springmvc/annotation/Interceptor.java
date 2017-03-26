package org.backbone.easyweb.springmvc.annotation;

import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.annotation.*;

/**
 * This annotation used to annotate an annatation serve as an interceptor configuration.
 * @author bianliang (02/09/2017)
 * @since 0.0.1
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE})
public @interface Interceptor {

    /**
     * Class instance of {@linkplain HandlerInterceptor}
     * @return interceptor class
     */
    Class<? extends HandlerInterceptor> value();

    /**
     * Set if load an interceptor instantiate from IOC container instead of new one
     * @return return true if you want to load an instantiate from IOC
     */
    boolean loadFromContainer() default false;

    /**
     * Processor will load an interceptor instantiate from IOC container by name if this value is specified, otherwise by class.
     * @return Interceptor beanId defined in IOC container
     */
    String beanId() default "";

}
