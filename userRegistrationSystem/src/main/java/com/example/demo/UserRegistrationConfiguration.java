/**
 * 
 */
package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @author jovan https://github.com/JoBaHP
 *                 https://gitlab.com/JoBaHP
 */
// Read the properties file so that properties from RestValidationHandler
// can be used during the ValidationError instance creation
// meta-annotation and indicates that this configuration class can have
// one or more @Bean methods
// will allow to alter a msg prop file without restarting the application
@Configuration
public class UserRegistrationConfiguration {
	
	//configures to support msgs from prop
	//loads a msg prop file and resolves msg key from properties file
	@Bean(name="messageSource")
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageBundle =  
				     new ReloadableResourceBundleMessageSource();
	//method takes an argument as classpath:messages/messages path of the property file
		 messageBundle.setBasename("classpath:messages/messages");
		 messageBundle.setDefaultEncoding("UTF-8");
         return messageBundle;
	}

}
