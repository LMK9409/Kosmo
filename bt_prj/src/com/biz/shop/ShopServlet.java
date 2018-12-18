package com.biz.shop;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class ShopServlet
 */
@WebServlet("/shop")
public class ShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		ShopDAO dao = new ShopDAO();
//	      ArrayList<ShopVO> list = dao.select();
//	      System.out.println(list);
//	      request.setAttribute("SHOP_LIST", list);
//	      request.getRequestDispatcher("index.jsp").forward(request, response);
//	     
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String asd =request.getParameter("key");
		System.out.println(asd);
		Gson gson= new Gson();
		ShopVO svo=gson.fromJson(asd, ShopVO.class);
		System.out.println(svo.getLat());
		System.out.println(svo.getLng());
		ShopDAO dao = new ShopDAO();


		ArrayList<ShopVO> list = dao.selectAll(svo);
		response.setContentType("application/json; encoding=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String res=gson.toJson(list);
		PrintWriter out =response.getWriter();
		out.println(res);
	}

}
