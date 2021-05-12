package com.pet.dog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.boot.autoconfigure.security.SecurityProperties;



@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER - 10)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Override
    protected void configure(HttpSecurity http) throws Exception 
    {
        http
         .csrf().disable()
         .antMatcher("/**")
         .authorizeRequests()
         .antMatchers("/dog").hasRole("ADMIN")
         .antMatchers("/dog/**").hasRole("ADMIN")
         .antMatchers("/dogs").hasAnyRole("USER","ADMIN")
         .anyRequest().authenticated()
         .and()
         .httpBasic();
    }
  
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) 
            throws Exception 
    {
        auth.inMemoryAuthentication()
            .withUser("bharathi")
            .password("{noop}password")
            .roles("USER")
            .and()
            .withUser("admin")
            .password("{noop}password")
            .roles("ADMIN");
    }
}
