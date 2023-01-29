<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isErrorPage = "true"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	toString 내용 :  <br>
	<%= exception.toString() %> <br>
	getMessage 내용 : <br>
	<%= exception.getMessage() %> <br>
	printStackTrace 내용 :  <br>
	<% exception.printStackTrace(); %> <br>
	숫자만 이용가능합니다.
</body>
</html>