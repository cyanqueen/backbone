package org.backbone.webmvc.springmvc.annotation;

import org.backbone.webmvc.springmvc.interceptor.AccessLogInterceptor;

import java.lang.annotation.*;

/**
 * @author bianliang (04/11/2017)
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Interceptor(value = AccessLogInterceptor.class)
public @interface PrintAccessLog {
}
