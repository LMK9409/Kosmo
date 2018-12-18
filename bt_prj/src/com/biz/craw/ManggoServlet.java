package com.biz.craw;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biz.craw.YTNcraw.YTNVO;
import com.google.gson.Gson;

/**
 * Servlet implementation class ManggoServlet
 */
@WebServlet("/Manggo")
public class ManggoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManggoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		ManggoCraw soup = new ManggoCraw();
		String url = "https://www.mangoplate.com/top_lists/1172_guobaorou";
		String selector="#contents_list > ul > li";
		ArrayList<ManggoVO> resList= soup.mangCrawling(url, selector);
		System.out.println(resList.size() + "건 클롤링");
		
		PrintWriter out =response.getWriter();
	
		for(ManggoVO res: resList)
		{
			System.out.println(res.getImg());
			out.print("<img width=50 height=50 src=\""+res.getImg()+"\">");
			out.println(res.getTitle());
			out.println(res.getPlace()+"<br>");
			out.println(res.getContent());
			out.print("<br><br><br>");
		}
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<ManggoVO> resList=null;
		response.setContentType("text/html; charset=UTF-8");
		ManggoCraw soup = new ManggoCraw();
		String url = "https://www.mangoplate.com/top_lists/1172_guobaorou";
		String selector="#contents_list > ul > li";
		
		
		
		resList= soup.mangCrawling(url, selector);
		
		
		response.setContentType("application/json; encoding=UTF-8");
		PrintWriter out =response.getWriter();

		Gson gson = new Gson();
		String resjson=gson.toJson(resList);
		System.out.println("post call...");
		System.out.println(resjson);
		out.println(resjson);
		
	}

}
