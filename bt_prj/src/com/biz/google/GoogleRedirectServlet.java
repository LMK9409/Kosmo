package com.biz.google;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GoogleCallBackServlet
 */
@WebServlet("/google_redirect")
public class GoogleRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String googleURL="https://accounts.google.com/o/oauth2/v2/auth";
		URL url = new URL(googleURL); 
	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();           
	    connection.connect();

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String line="";
        if( (line = in.readLine()) != null) {
        	System.out.print(line);
        }
       
				
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		"http%3A%2F%2Flocalhost%2Fgoogle_redirect";
//		"https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fcalendar.readonly";
		String googleURL="https://accounts.google.com/o/oauth2/v2/auth";
		URL url = new URL(googleURL); 
	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();           
	    connection.connect();

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String line="";
        if( (line = in.readLine()) != null) {
        	System.out.print(line);
        }
        

	}

}
