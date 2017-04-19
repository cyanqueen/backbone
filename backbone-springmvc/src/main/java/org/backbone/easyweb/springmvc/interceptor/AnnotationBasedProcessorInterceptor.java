package org.backbone.easyweb.springmvc.interceptor;

import org.apache.commons.lang.StringUtils;
import org.backbone.easyweb.springmvc.annotation.Interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author bianliang (03/24/2017)
 * @since 0.0.1
 */
public class AnnotationBasedProcessorInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(AnnotationBasedProcessorInterceptor.class);

    private Map<Method, List<HandlerInterceptor>> interceptorCaches;

    public AnnotationBasedProcessorInterceptor() {
        interceptorCaches = new ConcurrentHashMap<Method, List<HandlerInterceptor>>(
                new IdentityHashMap<Method, List<HandlerInterceptor>>()
        );
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        List<? extends HandlerInterceptor> interceptors = getInterceptors(request, handler);
        if (!CollectionUtils.isEmpty(interceptors)) {
            for (HandlerInterceptor interceptor : interceptors) {
                boolean ret = interceptor.preHandle(request, response, handler);
                if (!ret) return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        List<? extends HandlerInterceptor> interceptors = getInterceptors(request, handler);
        if (!CollectionUtils.isEmpty(interceptors)) {
            for (HandlerInterceptor interceptor : interceptors) {
                interceptor.postHandle(request, response, handler, modelAndView);
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        List<? extends HandlerInterceptor> interceptors = getInterceptors(request, handler);
        if (!CollectionUtils.isEmpty(interceptors)) {
            for (HandlerInterceptor interceptor : interceptors) {
                interceptor.afterCompletion(request, response, handler, ex);
            }
        }
    }

    private List<? extends HandlerInterceptor> getInterceptors(HttpServletRequest request, Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;

            Class<?> c = hm.getBeanType();
            Method method = hm.getMethod();

            List<? extends HandlerInterceptor> interceptors = interceptorCaches.get(method);
            if (interceptors == null) {
                interceptors = searchInterceptors(c, method, request);
            }
            return interceptors;
        }
        return null;
    }

    private List<? extends HandlerInterceptor> searchInterceptors(Class<?> clazz, Method method, HttpServletRequest request) {
        // search class
        Annotation[] annotations = clazz.getAnnotations();
        List<HandlerInterceptor> list = instantiateInterceptors(request, annotations);

        // search method
        List<HandlerInterceptor> mlist = instantiateInterceptors(request, method.getAnnotations());

        if (!mlist.isEmpty()) {
            list.addAll(mlist);
        }

        // sort interceptors
        Collections.sort(list, new Comparator<HandlerInterceptor>() {
            @Override
            public int compare(HandlerInterceptor o1, HandlerInterceptor o2) {
                int s1 = 0;
                int s2 = 0;
                if (o1 instanceof Ordered) s1 = ((Ordered) o1).getOrder();
                if (o2 instanceof Ordered) s2 = ((Ordered) o2).getOrder();
                return s1 > s2 ? 1 : (s1 < s2 ? -1 : 0);
            }
        });
        LOG.info("Sorted interceptor:" + method.getName() + ",list=" + list);

        // print log
        if (!list.isEmpty()) {
            StringBuilder log = new StringBuilder("Handler interceptors : class=" + clazz.getSimpleName() + ",method=" + method + "[");
            for (int i = 0; i < list.size(); i++) {
                log.append(list.get(i).getClass().getCanonicalName());
                if (i < list.size() - 1)
                    log.append(",");
            }
            log.append("]");
            LOG.info(log.toString());
        }

        interceptorCaches.put(method, list);
        return list;
    }

    private List<HandlerInterceptor> instantiateInterceptors(HttpServletRequest request, Annotation[] annotations) {
        List<HandlerInterceptor> interceptors = new ArrayList<HandlerInterceptor>();
        for (Annotation anno : annotations) {
            if (anno.annotationType().isAnnotationPresent(Interceptor.class)) {
                Interceptor ince = anno.annotationType().getAnnotation(Interceptor.class);
                Class<? extends HandlerInterceptor> clz = ince.value();
                HandlerInterceptor inc = null;
                String from = "new";
                String by = "class";
                ApplicationContext applicationContext = getApplicationContext(request);
                if (ince.loadFromContainer()) {
                    String beanId = ince.beanId();
                    Object incObj = null;
                    if (StringUtils.isNotBlank(beanId)) {
                        try {
                            incObj = applicationContext.getBean(beanId);
                            by = "name";
                        } catch (Exception e) {
                            LOG.warn("Load interceptor from IOC by name failed : beanId=" + beanId);
                        }
                    }
                    if (incObj == null) {
                        try {
                            LOG.info("Attempt to load interceptor from IOC by class:{}", clz);
                            incObj = applicationContext.getBean(clz);
                        } catch (Exception e) {
                            LOG.warn("Load interceptor from IOC by class failed : class=" + clz);
                        }
                    }
                    inc = (HandlerInterceptor) incObj;
                    from = "spring";
                } else {
                    try {
                        inc = clz.newInstance();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                if (inc != null) {
                    interceptors.add(inc);
                    LOG.info("Interceptor instantiated : " + inc.getClass().getCanonicalName() + (", from " + from + " by " + by));
                }

            }
        }
        return interceptors;
    }

    private ApplicationContext getApplicationContext(HttpServletRequest request) {
        return (ApplicationContext) request.getSession().getServletContext().getAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.dispatcher");
    }
}
