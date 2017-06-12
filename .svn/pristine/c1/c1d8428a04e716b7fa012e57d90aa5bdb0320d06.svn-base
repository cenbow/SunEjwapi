package com.huaao.sunejwapi.common.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
 
public class URLFilter implements Filter {   
    public void doFilter(ServletRequest servletRequest, ServletResponse       
                              servletResponse, FilterChain filterChain) throws    
                                        IOException,ServletException   
    {   
       HttpServletRequest request = (HttpServletRequest) servletRequest;   
       HttpServletResponse response = (HttpServletResponse) servletResponse;   
       if (request.getAttribute("rflag") == null && request.getParameter("method") !=null) { 
    	   request.setAttribute("rflag", "1");
    	   String[] apiMethod = request.getParameter("method").split("\\.");
           request.getRequestDispatcher("/"+apiMethod[0]+"/"+apiMethod[1]).forward(request, response);   
           return;   
       }   
       filterChain.doFilter(servletRequest, servletResponse);   
    }   
   

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}   
}   
