package com.biz.google;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GoogleSignInServlet
 */
@WebServlet("/google_auth")
public class GoogleSignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String googleURL="https://accounts.google.com/o/oauth2/v2/auth";
		String client_id="467969851194-d927d4s9ugp8jeu4tdng7a3ioftknufu.apps.googleusercontent.com";
		String redirect_uri="http://localhost/google_redirect";
		String scope="https://www.googleapis.com/auth/calendar.readonly";
		String 	access_type="offline";
		String 	response_type="code";
		
	
		 Map<String,Object> map = new HashMap<>();
		 map.put("scope", scope);
		 map.put("access_type", access_type);
		 map.put("include_granted_scopes",true);
		 map.put("redirect_uri", redirect_uri);
		 map.put("response_type",response_type);
		 map.put("client_id", client_id);

	        StringBuffer buffer = new StringBuffer();
	        for (Map.Entry<String,Object> keyval : map.entrySet()) {
	            if (buffer.length() != 0) buffer.append('&');
	            buffer.append(keyval.getKey());
	            buffer.append('=');
	            buffer.append(String.valueOf(keyval.getValue()));
	        }
	        System.out.println(googleURL+"?"+buffer);
	   byte[] postDataBytes = buffer.toString().getBytes();
	
		  
	  
		URL url = new URL(googleURL); 
	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();           
	    connection.setDoOutput(true); 
	    connection.setInstanceFollowRedirects(false); 
	    connection.setRequestMethod("POST"); 
	    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
	    connection.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
	    connection.setRequestProperty("charset", "UTF-8");
	    connection.connect();
	    connection.getOutputStream().write(postDataBytes); //http body에 write = post
	    connection.getOutputStream().flush();
	    connection.getOutputStream().close();

	    
	    
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String line="";
        if( (line = in.readLine()) != null) {
        	System.out.print(line);
        }

       
	}

	
	
}

