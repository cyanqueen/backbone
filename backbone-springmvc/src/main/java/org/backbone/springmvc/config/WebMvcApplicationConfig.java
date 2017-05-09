package org.backbone.springmvc.config;

import org.backbone.springmvc.annotation.ScanIgnore;
import org.backbone.springmvc.interceptor.AnnotationBasedProcessorInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mayanjun
 * @author bianliang (04/09/2017)
 */
@Configuration
@PropertySource(value = {Global.MVC_CONFIG_LOCATION}, ignoreResourceNotFound = true)
@ScanIgnore
public class WebMvcApplicationConfig extends WebMvcConfigurerAdapter implements ResourceLoaderAware {

    private static final Logger LOG = LoggerFactory.getLogger(WebMvcApplicationConfig.class);

    private ResourceLoader resourceLoader;

    @Bean(name = Global.BEAN_NAME_ROOT_PROPS)
    public PropertiesFactoryBean getPropertiesFactoryBean() {
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        Resource[] resources = getPropsResources(resourceLoader);
        bean.setLocations(resources);
        return bean;
    }

    protected Resource[] getPropsResources(ResourceLoader resourceLoader) {
        PropertySource sourceAnnotation = WebMvcApplicationConfig.class.getAnnotation(PropertySource.class);
        String[] props = sourceAnnotation.value();
        if (props.length > 0) {
            List<Resource> list = new ArrayList<Resource>(1);
            for (String prop : props) {
                Resource resource = resourceLoader.getResource(prop);
                if (resource.exists())
                    list.add(resource);
            }
            if (!CollectionUtils.isEmpty(list)) {
                Resource[] resources = new Resource[list.size()];
                list.toArray(resources);
                //print log
                printLog(Global.BEAN_NAME_ROOT_PROPS, resources);

                return resources;
            }
       }
        return null;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getAnnotationBasedProcessorInterceptor());
        LOG.info("AnnotationBasedInterceptor init");
    }

    public void printLog(String name, Resource[] resources) {
        StringBuilder sb = new StringBuilder("Properties are using now: " + name + "[");
        for (int i = 0; i <resources.length;i ++) {
            try {
                sb.append(resources[i].getURL());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (i < resources.length - 1)
                sb.append(",");
        }
        sb.append("]");
        LOG.info(sb.toString());
    }

    @Bean
    public AnnotationBasedProcessorInterceptor getAnnotationBasedProcessorInterceptor() {
        return new AnnotationBasedProcessorInterceptor();
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

}
