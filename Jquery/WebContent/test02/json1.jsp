<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-Latest.min.js"></script>
<script type="text/javascript">
	function fn_process(){ // 버튼 클릭시
		var _jsonInfo = '{"name" : "목재민", "age" : "27", "gender" : "man", "nickname" : "goodone"}';
		$.ajax({
			type : "post", // get방식으로 전달
			async : false, // 동기 방식으로 전달
			data :{jsonInfo: _jsonInfo},
			url : "http://localhost:8090/Jquery/json", // 해당 url에 전달
			dataType : "json", // 전송 받을 데이터 형식
			error : function(data, textStatus){ // 에러시 실행
				alert(textStatus);
				alert("에러가 발생했습니다.");
			},
			complete : function(data, textStatus){ // 작업 완료시
				/* alert("작업을 완료했습니다."); */
			}
		});
	}
</script>
</head>
<body>
<input type="button" id="btnDuplicate" value="정보출력" onClick="fn_process()"/><br>
<div id="message"></div>
</body>
</html>