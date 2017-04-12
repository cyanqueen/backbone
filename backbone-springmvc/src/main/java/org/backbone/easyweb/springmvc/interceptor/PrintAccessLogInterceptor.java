package org.backbone.easyweb.springmvc.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author bianliang (04/12/2017)
 */
public class PrintAccessLogInterceptor extends AnnotationBasedHandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(PrintAccessLogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOG.info("Start print access log");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }
}
