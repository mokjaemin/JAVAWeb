<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page = "image1.jsp" flush="true">
	<jsp:param name="name" value="테스트"/>
	<jsp:param name="ImgName" value="testImage1.png"/>
</jsp:include>

<h1>테스트 2</h1>
</body>
</html>