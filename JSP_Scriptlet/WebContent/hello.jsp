<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	String name = "재민";
	public String getName(){return name;} 
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선언문 실습</title>
</head>
<body>
	<h1>안녕하세요 <%= name %></h1>
</body>
</html>