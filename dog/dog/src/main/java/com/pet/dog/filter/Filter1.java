package com.pet.dog.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Filter1 implements Filter {

	@Override
	public void doFilter(ServletRequest servletRequest , ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		System.out.println("filter 1 called");
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication auth = securityContext.getAuthentication();
		System.out.println(auth.getName());
		chain.doFilter(servletRequest , servletResponse);
	}
	
}
