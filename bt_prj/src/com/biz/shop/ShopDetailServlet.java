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
 * Servlet implementation class ShopDetailServlet
 */
@WebServlet("/shop_detail")
public class ShopDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json; encoding=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String asd =request.getParameter("sseq");
		ShopDAO dao = new ShopDAO();
		ShopVO res=new ShopVO();
		res.setSseq(Integer.parseInt(asd));
		res = dao.selectShopInfo(res.getSseq());
		res.setPlist(dao.selectShopPic(res.getSseq()));
		request.setAttribute("SVO",res);
		ArrayList<ShopPicVO> dsa = new ArrayList<ShopPicVO>();
//		dsa=res.getPvo();
//		if(dsa.size()<=0)
//		{
//			dsa.get(0).setPname("000.png");
//			res.getPvo().get(0).setPname("000.png");
//		}
		for(ShopPicVO a:dsa)
		{
			System.out.println(a.getPname());
		}
		request.setAttribute("PVO",dsa);
		request.getRequestDispatcher("/shop_detail.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
