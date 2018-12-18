package com.biz.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/login")
public class LoginLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginLogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    //로그아웃처리 doGet
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("logout");
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("index.jsp");
	}
	//로그인 처리 doPost
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession();
		session.removeAttribute("SESS_NAME");
		session.removeAttribute("SESS_GUBUN");
		session.removeAttribute("SESS_ID");
		
		
		System.out.println("로그인");
		String userid = request.getParameter("userid");
		String userpw = request.getParameter("userpw");
		System.out.println(userid+userpw);
		String userGubun = "";
		MemberDAO db = new MemberDAO(); 
		MemberVO mvo = new MemberVO();
		
		mvo.setUserId(userid);
		mvo.setUserPw(userpw);
		
		System.out.println(mvo.getUserId());
		
		mvo=db.select(mvo);
		
		System.out.println(mvo.getUserEmail());
		
		if(mvo.getUserGubun()==null){
			response.sendRedirect("404.jsp");
			
		}else {
			
			
			session.setAttribute("SESS_NAME", mvo.getUserName());
			session.setAttribute("SESS_GUBUN",mvo.getUserGubun());
			session.setAttribute("SESS_ID",mvo.getUserId());
			

			if(mvo.getUserGubun().equals("u")){
				response.sendRedirect("index.jsp");
//				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			else{	
				if(mvo.getUserGubun().equals("a")){
					response.sendRedirect("/admin/index.jsp");
//					request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
				}
			}
		}
	}
}
