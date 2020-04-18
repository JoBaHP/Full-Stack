/**
 * 
 */
package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * @author jovan https://github.com/JoBaHP
 *                 https://gitlab.com/JoBaHP
 *    define just few users in Spring config file instead of extracting user information from
 *    persistence engines so we have user details loaded in application memory
 */
//extending WebSecurityConfigurerAdapte class allows us to 
//configure Spring Security and override the default methods
 
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SpringSecurityConfiguration_InMemory extends WebSecurityConfigurerAdapter{
	
	//creating two users (user and admin) and their roles (USER and ADMIN) and passwords 
	//admin user has both the USER and ADMIN roles user has only the USER role
	//configureGlobal method configure in-memory authentication
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth)
	 throws Exception {auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
	 auth.inMemoryAuthentication().withUser("admin").password("password").roles("USER", "ADMIN");
	}
	
	//mapping roles to URLs
	//using the anyMatchers method to map the URL pattern and HttpMethod to a specific role
	//disabled cross-site request forgery to restrict any end user from executing unwanted actions
	 @Override
  protected void configure(HttpSecurity http) throws Exception {http.httpBasic().realmName("User Registration System")
           .and().authorizeRequests().antMatchers("/login/login.html", "/template/home.html","/").permitAll()
           .anyRequest().authenticated().and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
 }
}
