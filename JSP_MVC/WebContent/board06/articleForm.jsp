<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  request.setCharacterEncoding("UTF-8");
%> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  /> 
<head>
<meta charset="UTF-8">
<title>글쓰기창</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
   function readURL(input) {
      if (input.files && input.files[0]) {
	      var reader = new FileReader();
	      reader.onload = function (e) {
	        $('#preview').attr('src', e.target.result);
          }
         reader.readAsDataURL(input.files[0]);
      }
  }  
  function backToList(obj){ // 현재 위치를 전달 받음
    obj.action="${contextPath}/board/listArticles.do"; // 현재 위치를 해당 경로로 변경
    obj.submit();
  }

</script>
 <title>새글 쓰기 창</title>
</head>
<body>
<h1 style="text-align:center">새글 쓰기</h1>
	<!-- 	글스기 누르면 addArticle.do전 -->
  <form name="articleForm" method="post"   action="${contextPath}/board/addArticle.do"   enctype="multipart/form-data">
    <table border=0 align="center">
     <tr>
	   <td align="right">글제목: </td>
	   <td colspan="2"><input type="text" size="67"  maxlength="500" name="title" /></td>
	 </tr>
	 <tr>
		<td align="right" valign="top"><br>글내용: </td>
		<td colspan=2><textarea name="content" rows="10" cols="65" maxlength="4000"></textarea> </td>
     </tr>
     <tr>
        <td align="right">이미지파일 첨부:  </td>
        <!-- 현재 파일을 함수에 전달  -->
	     <td> <input type="file" name="imageFileName"  onchange="readURL(this);" /></td>
         <td><img  id="preview" src="#"   width=200 height=200/></td>
	 </tr>
	 <tr>
	    <td align="right"> </td>
	    <td colspan="2">
	   	   <!-- 글쓰기 누르면 form 형태에 따라 값 전달 -->
	       <input type="submit" value="글쓰기" />
	       <!-- 현재 위치를 함수에 전달  -->
	       <input type=button value="목록보기"onClick="backToList(this.form)" />
	    </td>
     </tr>
    </table>
  </form>
</body>
</html>