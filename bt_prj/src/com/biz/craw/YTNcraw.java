package com.biz.craw;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.security.*;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.*;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Base;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class YTNcraw {
	
	

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


	public ArrayList<YTNVO> ytnCrawling(String url, String selector) {
		ArrayList<YTNVO> list = new ArrayList<YTNVO>();
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
		Connection conn=Jsoup.connect(url);
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
			for(Element elm : elms) {
				YTNVO vo  = new YTNVO();
				Elements elTitle = elm.select("dt a");
				Elements asd=elm.select("dd.vertical p.ph a img");
				System.out.println("http://www.ytn.co.kr"+elTitle.attr("href"));
				System.out.println(elTitle.text());
				System.out.println(asd.attr("src"));
				vo.setImg(asd.attr("src"));
				vo.setUrl("http://www.ytn.co.kr"+elTitle.attr("href"));
				vo.setTitle(elTitle.text());
				
				Elements elmsContent = elm.select("dd.text");
				for(Element elmCont : elmsContent) {
					System.out.println(elmCont.text());
					vo.setContent(elmCont.text());
				}
				System.out.println();
				list.add(vo);

				//				String sql = "insert into ytn values(ytn_seq.nextval, ?,?,?)";
				//				pstmt = conn.prepareStatement(sql);
				//				pstmt.setString(1,vo.getUrl());
				//				pstmt.setString(2,vo.getTitle());
				//				pstmt.setString(3,vo.getContent());
				//				res = pstmt.executeUpdate();

			}
			//db.dbClose(conn, pstmt);



			//			Elements elms = doc.select
			//				("div#ytn_list_v2014 dl.photo_list dt a");
			//			for(Element elm : elms) {
			//				System.out.println("http://www.ytn.co.kr"
			//						+elm.attr("href"));
			//				System.out.println(elm.text());
			//			}

			//File f = new File("C:\\jp\\workspace_java\\java_prj\\src\\com\\kosmo\\ch15\\crow.txt");

			//			java.lang.Object
			//			java.io.Writer
			//			java.io.OutputStreamWriter
			//			java.io.FileWriter

			//			java.lang.Object
			//			java.io.Writer
			//			java.io.BufferedWriter


			//			java.lang.Object
			//			java.io.OutputStream
			//			java.io.FileOutputStream

			//			OuputStreamWriter 1byte --> 2byte
			//			FileWriter 1byte --> 2byte

			//-----------------------------------
			//			
			//			byte[] byteArr = xxxxx.html().getBytes();
			//			FileOutputStream fos = new FileOutputStream(f);
			//			BufferedOutputStream bos = new BufferedOutputStream(fos);
			//			bos.write(byteArr);
			//			bos.flush();

			//			FileOutputStream fos = new FileOutputStream(f);
			//			OutputStreamWriter osw = new OutputStreamWriter(fos);
			//			BufferedWriter br = new BufferedWriter(osw);
			//			br.write(xxxxx.html());
			//			br.flush();

			//			FileWriter fw = new FileWriter(f);
			//			BufferedWriter br = new BufferedWriter(fw);
			//			br.write(xxxxx.html());
			//			br.flush();


			//			FileOutputStream fos 
			//				= new FileOutputStream(f);
			//			PrintStream out = new PrintStream(fos);
			//			out.println(doc.html());
			//			
			//			System.out.println(doc.html().length());  //222666
			//			System.out.println(f.length()); 			//223869
			//			

			//http://partnerjun.tistory.com/42

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	

	class YTNVO {
		private int seq;
		private String url;
		private String title;
		private String content;
		private String img;
		
		public String getImg() {
			return img;
		}
		public void setImg(String img) {
			this.img = img;
		}
		public int getSeq() {
			return seq;
		}
		public void setSeq(int seq) {
			this.seq = seq;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
	}
}
