package com.cos.action.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.action.Action;
import com.cos.dao.UserDao;
import com.cos.model.User;
import com.cos.util.SHA256;
import com.cos.util.Script;

import mail.SendMail;



public class UserJoinAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//목적 : form태그에 있는 name값을 받아서 DB에 insert하고 나서 페이지 이동
		
		//null값 처리하기(나중에), 유효성 검사(나중에)
		String username = request.getParameter("username");
		String rawpassword = request.getParameter("password");
		String email = request.getParameter("email"); 
		String address = request.getParameter("address");
		String password = SHA256.getEncrypt(rawpassword, "cos");
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password); //Encryption(암호화) 해야됨
		user.setEmail(email);
		user.setAddress(address);
		
		//DAO 연결하기
		UserDao dao = new UserDao();
		int result = dao.save(user);
		
		if(result == 1) {
			String resultMsg = SendMail.sendMail(email, "cos", username);
			
			System.out.println("인증메일 발송 결과 : " + resultMsg);
			
			response.sendRedirect("/blog/index.jsp");

		}else {
			Script.back(response);
		}
		
	}
}
