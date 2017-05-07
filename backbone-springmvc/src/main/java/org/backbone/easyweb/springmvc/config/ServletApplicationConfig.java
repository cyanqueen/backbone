package org.backbone.easyweb.springmvc.config;

import org.backbone.easyweb.springmvc.annotation.ScanIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;

/**
 * @author mayanjun
 * @author bianliang (04/13/2017)
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "${"+ WebPropertyNames.SPRING_SCAN_PACKAGES +":"+ Global.DEFAULT_SPRING_SCAN_PACKAGES +"}"
    , excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = ScanIgnore.class)
    )
@ImportResource(locations = "classpath:${"+ WebPropertyNames.SPRING_SCAN_XML +":"+ Global.DEFAULT_SPRING_SCAN_XML+"}")
@ScanIgnore
public class ServletApplicationConfig {

    private static final Logger LOG = LoggerFactory.getLogger(ServletApplicationConfig.class);

    @Value("#{"+ Global.BEAN_NAME_ROOT_PROPS +"['"+ WebPropertyNames.SPRING_SCAN_PACKAGES +"']}")
    private String scanPackages;

    @Value("#{"+ Global.BEAN_NAME_ROOT_PROPS +"['"+ WebPropertyNames.SPRING_SCAN_XML+"']}")
    private String scanXml;

    /**
     * Print root properties config
     */
    @PostConstruct
    public void init() {
        LOG.info("spring.scan.packages=" + (scanPackages == null ? "(default)" + Global.DEFAULT_SPRING_SCAN_PACKAGES : scanPackages));
        LOG.info("spring.scan.xml=" + (scanXml == null ? "(default)" + Global.DEFAULT_SPRING_SCAN_XML : scanXml));
    }
}
