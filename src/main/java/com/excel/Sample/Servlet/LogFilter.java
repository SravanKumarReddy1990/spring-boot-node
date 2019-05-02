package com.excel.Sample.Servlet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

// Implements Filter class
public class LogFilter implements Filter  {
	private FilterConfig config;
   public void  init(FilterConfig config) 
                         throws ServletException{
	   this.config=config;
	   String testParam = config.getInitParameter("initialPath"); 
	      System.out.println("Test Param: " + testParam); 
   }

public void  doFilter(ServletRequest request, 
                 ServletResponse response,
                 FilterChain chain) 
                 throws java.io.IOException, ServletException {
	   HttpServletRequest req = (HttpServletRequest) request;
	String urlPath=req.getRequestURI();
	String projectName=req.getContextPath();
	urlPath=urlPath.replaceAll(projectName, "");
	String[] u=urlPath.split("/");
//	for(String folder:u){
//		System.out.println(folder);
//	}
	 String initialPath = config.getInitParameter("initialPath"); 
	 String endPath = config.getInitParameter("endPath"); 
//	 String[] initialPathu=initialPath.split("/");
//		for(String folder:initialPathu){
//			System.out.println(folder);
//		}
	
	
	chain.doFilter(request, response);
	
	
   }
   public void destroy( ){
    
   }
}