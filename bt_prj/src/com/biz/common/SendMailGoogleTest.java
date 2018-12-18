package com.biz.common;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//------------------------------------------------------------------------
//https://www.google.com/settings/security/lesssecureapp
//	https://support.google.com/accounts/answer/6009563
// https://accounts.google.com/DisplayUnlockCaptcha
//534-5.7.14
//------------------------------------------------------------------------


import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendMailGoogleTest
{
	private final String PASS ="ansrud12";
	private final String FROM="ktm2413@gmail.com" ;
	private final String HOST = "smtp.gmail.com";
	public static void main(String [] args)
	{ 
		SendMailGoogleTest asd = new SendMailGoogleTest();
		asd.sendMail();
	}
	public void sendMail() {
		// Sender's email ID needs to be mentioned
		// Recipient's email ID needs to be mentioned.

		String to = "ktm2413@naver.com";

		// Get system properties
		Properties properties = System.getProperties();
		// Setup mail server
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", HOST);
		properties.put("mail.smtp.user", FROM);
		properties.put("mail.smtp.PASSword", PASS);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		try{
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(FROM));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO,
					new InternetAddress(to));

			// Set Subject: header field
			message.setSubject("여어!");


			//message.setText(htmlStr);
			StringBuffer html=new StringBuffer();
			html.append("<!DOCTYPE html>");	
			html.append("<html>");	
			html.append("<head>");	
			html.append("<meta charset=\"UTF-8\">");
			html.append("<title>\"Insert title here\"</title>");
			html.append("</head>");
			html.append("<body>");
			html.append("<table>");
			html.append("<tr>");
			html.append("<td><img src=\"http://www.ikosmo.co.kr/userfile/board/1449557805277737.jpg\"></td>");	
			html.append("</tr>");	
			html.append("<tr>");	
			html.append("<td>비밀번호 확인</td>");	
			html.append("</tr>");
			html.append("</table>");
			html.append("</body>");	
			html.append("</html>");	
			message.setContent(html.toString(), "text/html");
			// Send message
			Transport transport = session.getTransport("smtp");
			transport.connect(HOST, FROM, PASS);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			System.out.println("Sent message successfully....");
		}catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

}


//import java.util.Properties;
//
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PASSwordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//public class SendMailGoogleTest {
//
//	public static void main(String[] args) {
//
//		final String username = "coms.korea@gmail.com";
//		final String PASSword = "dd";
//
//		Properties props = new Properties();
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.host", "smtp.gmail.com");
//		props.put("mail.smtp.port", "587");
//
//		Session session = Session.getInstance(props,
//		  new javax.mail.Authenticator() {
//			protected PASSwordAuthentication getPASSwordAuthentication() {
//				return new PASSwordAuthentication(username, PASSword);
//			}
//		  });
//
//		try {
//
//			Message message = new MimeMessage(session);
//			message.setFrom(new InternetAddress("koms.korea@gmail.com"));
//			message.setRecipients(Message.RecipientType.TO,
//				InternetAddress.parse("koms.korea@gmail.com"));
//			message.setSubject("Testing Subject");
//			message.setText("Dear Mail Crawler No spam to my email, please!");
//
//			Transport.send(message);
//
//			System.out.println("Done");
//
//		} catch (MessagingException e) {
//			throw new RuntimeException(e);
//		}
//	}
//}

//import java.util.Properties;  
//import javax.mail.*;  
//import javax.mail.internet.*;  
//
//public class SendMailGoogleTest {
//	//http://blog.naver.com/PostView.nhn?blogId=choda100&logNo=220848908601
//	
//	
//	public static void main(String[] args) {  
//
//
//		/* Outgoing Mail (SMTP) Server
//		       requires TLS : smtp.gmail.com (use authentication)
//		       Use Authentication: Yes
//		       Use STARTTLS: Yes(some clients call this SSL)
//		 Port: 465 or 587
//		 Account Name : admin@xxx.co.kr
//		 Email Address : admin@xxx.co.kr
//		 PASSword : xxxxxx
//		 */
//		final String user="coms.korea@gmail.com";  
//		final String PASSword="dd";  
//
//		String from = "coms.korea@gmail.com";
//		String to = "coms.korea@gmail.com";   
//
//		//Get the session object  
//		Properties props = new Properties();  
//		props.put("mail.smtp.host", "smtp.gmail.com");
//		props.put("mail.smtp.auth", "true");  
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.port", "587");
//		
//
//		Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {  
//			protected PASSwordAuthentication getPASSwordAuthentication() {  
//				return new PASSwordAuthentication(user,PASSword);  
//			}  
//		});  
//
//		//Compose the message  15*25  
//		try {  
//			MimeMessage message = new MimeMessage(session);  
//			message.setFrom(new InternetAddress(from));  
//			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
//			message.setSubject("Send MAIL TEST......kosmo jp5");  
//			message.setText("This is simple program of sending email using JavaMail API");  
//
//			//send the message  
//			Transport.send(message);  
//
//			System.out.println("======================");  
//
//		} catch (MessagingException e) {e.printStackTrace();}  
//	}  
//}  