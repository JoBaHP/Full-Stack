/**
 * 
 */
package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.demo.service.UserInfoDetailsService;

/**
 * @author jovan https://github.com/JoBaHP
 *                 https://gitlab.com/JoBaHP
 *                 
 *  default behavior and securing a URI               
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled= true)
public class SpringSecurityConfiguration_Database extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserInfoDetailsService userInfoDetailsService;
	
	// overridden WebSecurityConfigurerAdapter’s
	//configure method, which takes AuthenticationManagerBuilder as a parameter
	//AuthenticationManagerBuilder is a helper class that implements the Builder pattern 
	//and provides a way of assembling an AuthenticationManager

	@Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder)throws Exception {
		authenticationManagerBuilder.userDetailsService(userInfoDetailsService);
	}
	
	//disabled CSRF for HTTP methods a type of security vulnerability 
	//where by a malicious web site forces users to execute unwanted commands on another web site
	//in which they are currently authenticated and is highly recommended 
	//when requests are submitted by a user via a browser
	 @Override
     protected void configure(HttpSecurity http) throws Exception {
		 http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
		 .antMatchers("/api/user/**").authenticated().and().httpBasic().realmName("User Registration System")
		  .and()
          .csrf()
                  .disable();
	 }

}
