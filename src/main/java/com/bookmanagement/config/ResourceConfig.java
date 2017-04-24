package com.bookmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class ResourceConfig {
	 @Bean
	    public ResourceBundleMessageSource messageSource() {
	        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
	        source.setBasename("validation");
	        source.setUseCodeAsDefaultMessage(true);
	        return source;
	    } 
}
