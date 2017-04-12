package org.backbone.easyweb.springmvc.annotation;

import org.backbone.easyweb.springmvc.interceptor.PrintAccessLogInterceptor;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author bianliang (04/11/2017)
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Interceptor(value = PrintAccessLogInterceptor.class, loadFromContainer = true)
public @interface PrintAccessLog {
}
