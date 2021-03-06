package com.biz.member;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * Servlet implementation class MemberEditServlet
 */
@WebServlet("/edit")
public class MemberEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if(session.getAttribute("SESS_ID")==null)
		{
			response.sendRedirect("login.jsp");
			return;
		}
		
		String sessId =session.getAttribute("SESS_ID").toString();
		MemberDAO db = new MemberDAO();
		MemberVO mvo=new MemberVO();
		mvo.setUserId(sessId);
		mvo=db.select(mvo);
		request.setAttribute("KEY_MVO",mvo);
		request.getRequestDispatcher("/profile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String saveDirectory="c:/uploads/profile";
		String encoding = "UTF-8";
		FileRenamePolicy policy =new DefaultFileRenamePolicy();
		int maxPostSize = 10000000;
		MemberDAO db = new MemberDAO();
		MemberVO mvo= new MemberVO();
		HttpSession sess=request.getSession();
		String oldId=sess.getAttribute("SESS_ID").toString();
		mvo.setUserId(oldId);
		mvo=db.select(mvo);
		MultipartRequest mrequest  = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
         
         //1.파일 카피 인풋/아웃풋 스트림
         //2.중복 리네임까지 해줬음
         System.out.println("asd");
		String username = mrequest.getParameter("user_name");
		String email = mrequest.getParameter("user_email");
		String userpw = mrequest.getParameter("user_pw");
		
		String orginPname=mrequest.getOriginalFileName("pname");//파일명
		String sysPname = mrequest.getFilesystemName("pname");//리네임된 파일명
		System.out.println(orginPname);
		System.out.println(sysPname);
		if(orginPname!=null )
		{

		File pfile =mrequest.getFile("pname");
		String sysfname= pfile.getName();//파일 객체를 이용한 파일명
		long attachFileSize=  pfile.length();//파일크기
		String attachFileContentType=mrequest.getContentType("pname");

	
		
		
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
		}
		
		mvo.setUserName(username);
		mvo.setUserPw(userpw);
		mvo.setUserEmail(email);
		
		int res =db.update(mvo);
		if(res!=0)
		{

			response.sendRedirect("login.jsp");
		}else
		{
			response.sendRedirect("404.jsp");
		}
		
	}

}
