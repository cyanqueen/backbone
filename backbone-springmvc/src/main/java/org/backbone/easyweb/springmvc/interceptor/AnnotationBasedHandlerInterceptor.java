package org.backbone.easyweb.springmvc.interceptor;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author bianliang (04/12/2017)
 */
 public abstract class AnnotationBasedHandlerInterceptor implements HandlerInterceptor, Ordered {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
