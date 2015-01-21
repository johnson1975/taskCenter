package com.chaojidaogou.taskcenter.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * Created by Johnson on 2014/10/16.
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.chaojidaogou.taskcenter.web")
public class WebConfig extends WebMvcConfigurationSupport {
    private static final String PROP_FILE_ENCODING = "GBK";

    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping handlerMapping = super.requestMappingHandlerMapping();
        handlerMapping.setRemoveSemicolonContent(false);
        return handlerMapping;
    }

    @Override
    @Bean
    public HandlerMapping resourceHandlerMapping() {
        return super.resourceHandlerMapping();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer loadProperties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        Resource[] resources = new ClassPathResource[]{new ClassPathResource("config.properties")};
        configurer.setLocations(resources);
        configurer.setFileEncoding(PROP_FILE_ENCODING);
        configurer.setIgnoreUnresolvablePlaceholders(true);
        return configurer;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("locale/messages");
        messageSource.setDefaultEncoding("GBK");
        return messageSource;
    }
}