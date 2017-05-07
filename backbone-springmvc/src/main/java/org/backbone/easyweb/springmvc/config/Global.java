package org.backbone.easyweb.springmvc.config;

/**
 * @author bianliang (04/09/2017)
 */
public class Global {

    /**
     * 默认spring mvc配置文件
     */
    public static final String MVC_CONFIG_LOCATION = "classpath:web.properties";

    /**
     * {@link org.springframework.beans.factory.config.PropertiesFactoryBean} 的bean名称，包含web.properties中的属性
     */
    public static final String BEAN_NAME_ROOT_PROPS = "rootProps";

    /**
     * 默认spring扫描路径
     */
    public static final String DEFAULT_SPRING_SCAN_PACKAGES = "org.backbone";

    /**
     * 默认spring扫描的bean
     */
    public static final String DEFAULT_SPRING_SCAN_XML = "spring/spring-config-*.xml";

}
