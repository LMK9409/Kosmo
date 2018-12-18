package com.biz.craw;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biz.craw.YTNcraw;
import com.biz.craw.YTNcraw.YTNVO;

/**
 * Servlet implementation class YTNCrawServlet
 */
@WebServlet("/ytn")
public class YTNCrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		YTNcraw soup = new YTNcraw();
		String url = "http://www.ytn.co.kr/photo/photo_list_1406.html";
		String selector="div#ytn_list_v2014 dl.photo_list";
		ArrayList<YTNVO> resList= soup.ytnCrawling(url, selector);
		System.out.println(resList.size() + "건 클롤링");
		
		PrintWriter out =response.getWriter();
		
		for(YTNVO res : resList)
		{
			out.print("<img width=50 height=50 src=\""+res.getImg()+"\">");
			out.println(res.getTitle());
			out.print("<br><br>");
		}
		 
//		request.getRequestDispatcher("index.jsp").forward(request, response);


	}

}
