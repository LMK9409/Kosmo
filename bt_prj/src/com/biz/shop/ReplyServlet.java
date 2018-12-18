package com.biz.shop;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

/**
 * Servlet implementation class ReplyServlet
 */
@WebServlet("/Reply")
public class ReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess=request.getSession();
		String sessid=sess.getAttribute("SESS_ID").toString();
		System.out.println(sessid);
		String parmval =request.getParameter("key");
		System.out.println(parmval);
		
		Gson gson= new Gson();
		ReplyVO svo=gson.fromJson(parmval, ReplyVO.class);
		svo.setRegid(sessid);

		ShopDAO dao = new ShopDAO();
		int insert= dao.insertReply(svo);
		ArrayList<ReplyVO> rlist =dao.selectReply(svo);
		
		
		
		response.setContentType("application/json; encoding=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String res=gson.toJson(rlist);
		PrintWriter out =response.getWriter();
		out.println(res);
	}

}
