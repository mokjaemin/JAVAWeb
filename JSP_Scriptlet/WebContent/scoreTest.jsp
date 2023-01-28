<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	int score = Integer.parseInt(request.getParameter("score"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학점 출력</title>
</head>
<body>
<h1>시험점수 : <%= score %></h1>
<%
	if(score > 90){
%>
		<h1>A학점입니다.</h1>
<%
	}else{
%>
		<h1>B학점입니다.</h1>
<%
	}
%>
</body>
</html>