package com.biz.common;

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
 * Servlet implementation class EmpServlet
 */
@WebServlet("/emp")
public class PagingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//		response.getWriter().append("Served at: ").append(request.getContextPath());
		Ch99JDBCImpl d = new Ch99JDBCImpl();
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();
		//		String a =request.getParameter("col");
		//		String b=request.getParameter("val");
		list=d.empList();
		if(list.isEmpty()==false)
		{
			request.setAttribute("KEY_LIST",list);
			request.getRequestDispatcher("/style_test.jsp").forward(request, response);
		}
	
		
		System.out.println("post call...");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//		doGet(request, response);
		Ch99JDBCImpl d = new Ch99JDBCImpl();
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();
		String a =request.getParameter("searchColumn");
		String b=request.getParameter("searchStr");
		list=d.empList(a,b);
		response.setContentType("application/json; encoding=UTF-8");
		Gson gson = new Gson();
		String gsonStr=gson.toJson(list);
//		request.setAttribute("KEY_LIST",list);
//		request.getRequestDispatcher("/emp_result.jsp").forward(request, response);
		System.out.println("post call...");
		System.out.println(gsonStr);
		PrintWriter out=response.getWriter();
		out.println(gsonStr);
	}

}

