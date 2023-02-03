<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	List dataList = new ArrayList();
	dataList.add("1");
	dataList.add("2");
	dataList.add("3");
%>
<c:set var="id" value="mok" scope="page"/>
<c:set var="age" value="${27}" scope="page"/>
<c:set var="list" value="<%=dataList %>" scope="page"/>
<%-- <c:set var="pageContext" value="${pageContext.request.contextPath}"/> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>c:set태그</h1>
	<h1>${id}</h1>
	<h1>${age}</h1>
	<%-- <a href="${pageContext}/memberForm.jsp">실험</a> --%>
	<br>
	<br>
	<h1>c:if태그</h1>
	<c:if test="${27==27}">
		<h1>true</h1>
	</c:if>
	<br>
	<br>
	<h1>c:choose태그</h1>
	<c:choose>
		<c:when test="${empty name}">
			<h1>hello</h1>
		</c:when>
		<c:otherwise>
			<h1>bye</h1>
		</c:otherwise>
	</c:choose>
	<br>
	<br>
	<h1>c:forEach태그</h1>
	<c:forEach var="data" items="${list}">
		${data}<br>
	</c:forEach>
	<br>
	<br>
	<h1>c:out태그</h1>
	<c:out value="${address}" default="없음" />
</body>
</html>