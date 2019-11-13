<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>에이잭스 테스트</h1>
	<hr>

	<div id="demo">
		<h2>1</h2>

	</div>
	<button type="button" onclick="loadDoc()">Change Count</button>

	<script>
		var reply = {
			id : null,
			boardId : 1,
			userId : 3,
			content : 'input태그에 적힌값을 들고 온다',
			createDate : null
		}
		console.log(reply);
		var replyString = JSON.stringify(reply);
		console.log(replyString);
		function loadDoc() {
			var xhttp = new XMLHttpRequest();
			//콜백함수 -> xttp.send 된뒤 요청받은 서버에서 응답이 정상적으로 처리되면 콜백
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {

					console.log(this.responseText);
					
					var jsonData = JSON.parse(this.responseText);
					console.log(jsonData);

					document.querySelector("#demo").innerHTML = jsonData.name
							+ " " + jsonData.sal;
				}
			};
			xhttp.open("POST", "http://localhost:8000/blog/test", true);
			xttp.setRequestHeader("Content-type", "application/json");
			xhttp.send(replyString);
		}
	</script>

</body>
</html>