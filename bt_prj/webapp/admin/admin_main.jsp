<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
관리자 페이지.....

<%
String nm=session.getAttribute("SESS_NAME").toString();
String gbn=session.getAttribute("SESS_GUBUN").toString();


out.println(nm+","+gbn);
%>
</body>
</html>