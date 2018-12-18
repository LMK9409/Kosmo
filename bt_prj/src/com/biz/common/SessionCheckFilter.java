package com.biz.common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class SessionCheckFilter implements Filter
{
  private ArrayList<String> checkingURLList;
  public void init(FilterConfig config) throws ServletException
  {
    String configUncheckingURLs = config.getInitParameter("CKECKING");
    StringTokenizer token = new StringTokenizer(configUncheckingURLs, ",");
 
	  checkingURLList = new ArrayList<String>();
 
    while ( token.hasMoreTokens() )
    {
    	
    	checkingURLList.add(token.nextToken());
    }
  }
 
 
  public void doFilter(ServletRequest req, ServletResponse res,
      FilterChain chain) throws IOException, ServletException
  {
 
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;
    String requestURL = request.getServletPath();
    System.out.println(requestURL);
    
    if ( checkingURLList.contains(requestURL) ||requestURL.indexOf("/admin/") !=-1)
    {
        HttpSession session = request.getSession();
        //인증검사 Authentication 검사
             if ( session == null || session.getAttribute("SESS_ID") == null )
             {
               //System.out.println("## Filter Session Null - " + url);
               response.sendRedirect("/login.jsp");
             }
             else
             {
           	  //인가 검사  Authorization 검사
           	  String gubun=session.getAttribute("SESS_GUBUN").toString();
           	  if(gubun.equals("u") )
           	  {
           		 if( requestURL.indexOf("/admin/") !=-1) {
           			 response.sendRedirect("/401.jsp");
           		 }
           		  
           	  }
               chain.doFilter(req, res);
             }
    }
    else
    {
    	chain.doFilter(req, res);

    }
  }
 

  public void destroy()
  {
  }
}