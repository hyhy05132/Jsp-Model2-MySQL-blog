package com.cos.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
 *  dml(insert,update,delete)close
 */

public class DBClose {
	public static void close(Connection conn, PreparedStatement pstmt) {
		try {
			conn.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

/*
 *  dml(insert,update,delete)close
 */

	public static void close(Connection conn, PreparedStatement pstmt,ResultSet rs) {
		try {
			conn.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
