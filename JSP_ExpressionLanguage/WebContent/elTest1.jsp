<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
   	isELIgnored="false"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현언어에서 사용되는 데이터들</title>
</head>
<body>
	<h1>여러가지 데이터 출력</h1>
	<h1>
		기본 출력 <br>
		\${"안녕하세요"} : ${"안녕하세요"} <br>
		\${100 + 1} : ${100 + 1 } <br>
		\${"10" + 1} : ${"10" + 1 } <br>
		\${null + 1} : ${null + 1 } <br>
			<!-- // 안됨 -->
			<%-- \${"hello" + 1} : ${"hello" + 1 } <br> --%>
			<%-- \${"hello" + "world"} : ${"hello" + "world" } <br> --%>
		<br>
		사칙연산<br>
		\${10 mod 10} : ${10 mod 10} <br>
	</h1>
</body>
</html>