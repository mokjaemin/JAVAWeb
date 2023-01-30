<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<%
	String msg = "다시입력하세요";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String userID = request.getParameter("userID");
	if (userID.length() == 0){
%>
		<jsp:forward page='login.jsp' >
			<jsp:param name="msg" value="<%= msg %>" />
		</jsp:forward>
<%
	}
%>
<h1>환영합니다. <%= userID %></h1>
</body>
</html>