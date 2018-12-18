package com.biz.member;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biz.common.Ch99DBManager;
import com.biz.shop.ShopDAO;
import com.biz.shop.ShopVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		//response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String saveDirectory="c:/uploads/profile";
		String encoding = "UTF-8";
		FileRenamePolicy policy =new DefaultFileRenamePolicy();
		int maxPostSize = 10000000;
         

		MultipartRequest mrequest  = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
         
         //1.파일 카피 인풋/아웃풋 스트림
         //2.중복 리네임까지 해줬음
         
		String username = mrequest.getParameter("user_name");
		String userid = mrequest.getParameter("user_id");
		String email = mrequest.getParameter("user_email");
		String userpw = mrequest.getParameter("user_pw");
	

		String orginPname=mrequest.getOriginalFileName("pname");//파일명
		String sysPname = mrequest.getFilesystemName("pname");//리네임된 파일명

		File pfile =mrequest.getFile("pname");
		String sysfname= pfile.getName();//파일 객체를 이용한 파일명
		long attachFileSize=  pfile.length();//파일크기
		String attachFileContentType=mrequest.getContentType("pname");

		MemberVO mvo= new MemberVO();
		
		
		Enumeration formNames = mrequest.getFileNames();
		while(formNames.hasMoreElements())
		{

			String inputNames=(String)formNames.nextElement();
			String originPnames=mrequest.getOriginalFileName(inputNames);

			if (originPnames!=null) {
				String sysPnames = mrequest.getFilesystemName(inputNames);//리네임된 파일명
				File pfiles =mrequest.getFile(inputNames);
				long attachFileSizes=pfiles.length();
				String attachFileContentTypes=mrequest.getContentType(inputNames);
				mvo.setPname(originPnames);
				mvo.setSysname(sysPnames);


			}
		}
		
		mvo.setUserName(username);
		mvo.setUserId(userid);
		mvo.setUserPw(userpw);
		mvo.setUserEmail(email);
		MemberDAO db = new MemberDAO();
		int res =db.insert(mvo);
		if(res!=0)
		{
			response.sendRedirect("index.jsp");
		}else
		{
			response.sendRedirect("404.jsp");
		}
		
		
		



	
		
		
	}

}
