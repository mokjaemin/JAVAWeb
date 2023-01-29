<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String name = (String) session.getAttribute("name");
	String address = (String) session.getAttribute("address");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>세션 내장 객체 2</title>
</head>
<body>
	이름은 : <%= name %> <br>
	주소는 : <%= address %> 
</body>
</html>