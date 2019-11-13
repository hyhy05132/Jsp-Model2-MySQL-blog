package com.cos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cos.model.User;
import com.cos.util.DBClose;

public class MailDao {
	private MailDao() {
	}

	private static MailDao instance = new MailDao();

	public static MailDao getInstance() {
		return instance;
	}

	private Connection conn; // 마이에스큐엘 과 연결핌욜
	private PreparedStatement pstmt;// 쿼리문을 작성 -실행하기 위해필요
	private ResultSet rs; // 결과를 보관할 커서
	
	public User findAll() {
		final String SQL = "SELECT * FROM adminInfo";
		conn = DBConn.getConnection();

		try {
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				User user = new User();
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));

				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}

		return null;
	}
}
