<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    %>
<%
	request.setCharacterEncoding("UTF-8");
	/* String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String name = request.getParameter("name");
	String email = request.getParameter("email"); */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 출력</title>
</head>
<body>
<%-- 	<h1><%=id %></h1>
	<h1><%=pwd %></h1>
	<h1><%=name %></h1>
	<h1><%=email %></h1> --%>
	<br>
	<h1>${param.id}</h1>
	<h1>${param.pwd}</h1>
	<h1>${param.name}</h1>
	<h1>${param.email}</h1>
</body>
</html>