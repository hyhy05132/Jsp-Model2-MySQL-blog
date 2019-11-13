<%@page import="java.io.PrintWriter"%>
<%@page import="com.cos.util.SHA256"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//코드값 받기
String code = request.getParameter("code");




//디비에서 아이디값으로 이메일과 솔트 가져오기(가정)

//리턴받은 코드 값이랑 센드코드값을 비교해서 동일하면 
boolean rightCode = 
			SHA256.getEncrypt("codingspecialist@naver.com", "cos").equals(code) ? true : false;



PrintWriter script = response.getWriter();


if(rightCode == true){
	
	
	if(rightCode == true){
		//디비에 이메일체크 칼럼 업데이트 해주기 1
		script.println("<script>");
		script.println("alert('이메일 인증에 성공하였습니다.')");
		script.println("location.href='login.jsp'");
		script.println("</script>");
	} else{
		script.println("<script>");
		script.println("alert('이메일 인증을 실패하였습니다.')");
		script.println("location.href='error.jsp'");
		script.println("</script>");
	}



}



//인증 완료 로그인 페이지 이동

//미인증 에러 페이지 이동
%>