<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 결과 출력2</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	String user_id = request.getParameter("user_id");
	String user_pw = request.getParameter("user_pw");
%>
<%
	if(user_id == null || user_id.length() == 0){
%>
	아이디를 입력하세요.<br>
	<a href = '/JSP_Scriptler/login.html'>로그인 하기</a>
<%
	}else{
%>
	<h1>아이디 : <%= user_id %></h1>
	<h1>비밀번호 : <%= user_pw %></h1>
<%
	}
%>
</body>
</html>