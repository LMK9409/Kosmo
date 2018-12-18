package com.biz.craw;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class ManggoCraw {


private final static String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";
    
    // SSL 우회 등록
    public static void makeCertNsetSSL() throws NoSuchAlgorithmException, KeyManagementException {
    	
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
        	public X509Certificate[] getAcceptedIssuers() {
        			return null;
            	}
            	public void checkClientTrusted(X509Certificate[] certs, String authType) {
            	}
            	public void checkServerTrusted(X509Certificate[] certs, String authType) {
            	}
        	}         
        };
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultHostnameVerifier(
            new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            }
        );
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }



	public ArrayList<ManggoVO> mangCrawling(String url, String selector) {
		ArrayList<ManggoVO> list = new ArrayList<ManggoVO>();
		//Connection.class
		//public interface Connection
		//Connection$Response.class
		//
		//       interface Response extends Base
		if(url.indexOf("https://") >= 0){
			try {
				makeCertNsetSSL();
				SSLContext sc = SSLContext.getInstance("SSL");
			} catch (KeyManagementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		
		Connection.Response response;
		int count = 0;
		
		try {
			response = Jsoup.connect(url)
					.method(Connection.Method.GET)
					.execute();

			System.out.println(response.statusMessage());
			System.out.println(response.statusCode());
			

			Document doc = response.parse();
			//System.out.println(doc.html());

			
			Elements elms = doc.select(selector);
			count  = elms.size();
			
			//conn = db.dbConn();
			for(Element elm:elms)
			{
				
				ManggoVO vo  = new ManggoVO();
				Elements pelTitle = elm.select("div figure figcaption div span a h3");
				
				Elements pimg=elm.select("div > figure > a > div > img");
				System.out.println(pimg.attr("src"));
				Elements pplace=elm.select("div figure figcaption div p.etc");
				Elements pcon=elm.select("div > div > p.short_review");
				Elements title = elm.select("div > figure > figcaption > div > span > a");
	            
				vo.setUrl("https://www.mangoplate.com"+title.attr("href"));
				vo.setImg(pimg.attr("data-original"));
				vo.setTitle(pelTitle.text());
				vo.setPlace(pplace.text());
				vo.setContent(pcon.text());
				
				list.add(vo);
			}
			
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<ManggoVO> mangCrawling(String url, String selector,int top) {
		ArrayList<ManggoVO> list = new ArrayList<ManggoVO>();
		//Connection.class
		//public interface Connection
		//Connection$Response.class
		//
		//       interface Response extends Base
		if(url.indexOf("https://") >= 0){
			try {
				makeCertNsetSSL();
				SSLContext sc = SSLContext.getInstance("SSL");
			} catch (KeyManagementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		
		Connection.Response response;
		int count = 0;
		
		try {
			response = Jsoup.connect(url)
					.method(Connection.Method.GET)
					.execute();

			System.out.println(response.statusMessage());
			System.out.println(response.statusCode());
			

			Document doc = response.parse();
			//System.out.println(doc.html());

			
			Elements elms = doc.select(selector);
			count  = elms.size();
			
			//conn = db.dbConn();
			
			for(int i = 0 ; i<top;i++)
			{
				Element elm=elms.get(i);
				ManggoVO vo  = new ManggoVO();
				Elements pelTitle = elm.select("div figure figcaption div span a h3");
				
				Elements pimg=elm.select("div > figure > a > div > img");
				System.out.println(pimg.attr("src"));
				Elements pplace=elm.select("div figure figcaption div p.etc");
				Elements pcon=elm.select("div > div > p.short_review");
				Elements title = elm.select("div > figure > figcaption > div > span > a");
	            
				vo.setUrl("https://www.mangoplate.com"+title.attr("href"));
				vo.setImg(pimg.attr("data-original"));
				vo.setTitle(pelTitle.text());
				vo.setPlace(pplace.text());
				vo.setContent(pcon.text());
				
				list.add(vo);
			}
			
			
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
}
