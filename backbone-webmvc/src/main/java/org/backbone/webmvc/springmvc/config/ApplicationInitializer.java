package org.backbone.webmvc.springmvc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;
import java.util.Collection;
import java.util.Map;

/**
 * ApplicationInitializer
 *
 * @author mayanjun
 * @author bianliang (04/08/2017)
 */
public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationInitializer.class);

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        int majorVersion = servletContext.getMajorVersion();
        int minorVersion = servletContext.getMinorVersion();
        LOG.info("Container servlet version is {}.{}", majorVersion, minorVersion);

        int effectiveMajorVersion = servletContext.getEffectiveMajorVersion();
        int effectiveMinorVersion = servletContext.getEffectiveMinorVersion();
        LOG.info("Application servlet effective version is {}.{}", effectiveMajorVersion, effectiveMinorVersion);

        Map<String, ? extends ServletRegistration> map = servletContext.getServletRegistrations();
        Collection<? extends ServletRegistration> registrations =  map.values();

        for (ServletRegistration registration : registrations) {
            String className = registration.getClassName();
            if (DispatcherServlet.class.getCanonicalName().equals(className)) {
                LOG.info("DispatcherServlet has been initialized");
                return;
            }
        }
        super.onStartup(servletContext);
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter filter1 = new CharacterEncodingFilter();
        filter1.setEncoding("utf-8");
        filter1.setForceEncoding(true);

        HttpPutFormContentFilter filter2 = new HttpPutFormContentFilter();
        HiddenHttpMethodFilter filter3 = new HiddenHttpMethodFilter();

        LOG.info("Adding filter to dispatcher servlet[CharacterEncodingFilter,HttpPutFormContentFilter,HiddenHttpMethodFilter]");
        return new Filter[]{filter1, filter2, filter3};
    }

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        AnnotationConfigWebApplicationContext applicationContext = (AnnotationConfigWebApplicationContext) super.createRootApplicationContext();
        if (applicationContext != null)
            applicationContext.setDisplayName("Root WebMvcApplicationConfig");
        return applicationContext;
    }

    @Override
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext applicationContext = (AnnotationConfigWebApplicationContext) super.createServletApplicationContext();
        if (applicationContext != null)
            applicationContext.setDisplayName("DispatcherServlet WebMvcApplicationConfig");
        return applicationContext;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{WebMvcApplicationConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{ServletApplicationConfig.class};
    }
}
