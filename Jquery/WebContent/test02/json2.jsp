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
		$.ajax({
			type : "post", // get방식으로 전달
			async : false, // 동기 방식으로 전달
			url : "http://localhost:8090/Jquery/json2", // 해당 url에 전달
			success : function(data, textStatus){ // 성공시실행, data는 전달받은 데이터
				alert(data);
				var jsonInfo = JSON.parse(data);
				memberInfo = ""; 
				for(var i in jsonInfo.members){
					memberInfo += jsonInfo.members[i].name + "<br>";
					memberInfo += jsonInfo.members[i].age + "<br>";
				}
				$("#message").html(memberInfo);
			},
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