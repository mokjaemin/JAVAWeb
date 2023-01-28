<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	String name = "재민";
	public String getName(){return name;} 
%>    
<% String age = request.getParameter("age"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현식 실습</title>
</head>
<body>
	<h1>안녕하세요 <%= name %></h1>
	<h1>나이 <%= age %></h1>
	<h1>키 <%= 180 %></h1>
	<h1>나이 +10 =  <%= Integer.parseInt(age) + 10 %></h1>
</body>
</html>