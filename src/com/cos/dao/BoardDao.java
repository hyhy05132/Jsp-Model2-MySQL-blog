package com.cos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cos.model.Board;
import com.cos.util.DBClose;

public class BoardDao {

	// 싱글톤으로 만들어야 하는데 그냥함.

	private Connection conn; // 마이에스큐엘 과 연결핌욜
	private PreparedStatement pstmt;// 쿼리문을 작성 -실행하기 위해필요
	private ResultSet rs; // 결과를 보관할 커서

	// 수정
	public int update(Board board) {

		final String SQL = "UPDATE board SET title = ? , content = ? , createDate = now() WHERE id = ?";

		conn = DBConn.getConnection();
		try { 
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getId());
			int result = pstmt.executeUpdate();// 변경된 튜플의 개수를 리턴

			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);

		}
		return -1;
	}

	// 인기리스트 3건 가져오기

	public List<Board> findOrderByReadCountDesc() {
		final String SQL = "SELECT * FROM board ORDER BY readCount DESC limit 3";
		conn = DBConn.getConnection();

		try {
			List<Board> boards = new ArrayList<>();

			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();

			while (rs.next()) { // 알에스 넥스트 커서이동 리턴값 불린
				Board board = new Board();
				board.setId(rs.getInt("id"));
				board.setUserId(rs.getInt("userId"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setReadCount(rs.getInt("readCount"));
				board.setCreateDate(rs.getTimestamp("createDate"));

				boards.add(board);

			}
			return boards;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return null;
	}

	// 조회수 증가
	public int incresaeReadCount(int id) {

		final String SQL = "UPDATE board SET readCount=readCount+1 WHERE id = ?";

		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);

			pstmt.setInt(1, id);

			int result = pstmt.executeUpdate();// 변경된 튜플의 개수를 리턴

			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);

		}
		return -1;
	}

	public int save(Board board) {

		final String SQL = "INSERT INTO board(userId,title,content,createDate)VALUES(?,?,?,now())";

		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, board.getUserId());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			int result = pstmt.executeUpdate();// 변경된 튜플의 개수를 리턴

			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);

		}
		return -1;
	}
	// 리스트보기 SELECT *FROM board ORDER BY id DESC

	public List<Board> findAll() {
		final String SQL = "SELECT * FROM board ORDER BY id DESC";
		conn = DBConn.getConnection();

		try {
			List<Board> boards = new ArrayList<>();

			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();

			while (rs.next()) { // 알에스 넥스트 커서이동 리턴값 불린
				Board board = new Board();
				board.setId(rs.getInt("id"));
				board.setUserId(rs.getInt("userId"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setReadCount(rs.getInt("readCount"));
				board.setCreateDate(rs.getTimestamp("createDate"));

				boards.add(board);

			}
			return boards;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return null;
	
	}
	

	public List<Board> findAll(int page) {
		final String SQL = "select * from board b, user u where b.userId= u.id order by b.id desc limit ?,3";
		conn = DBConn.getConnection();

		try {
			List<Board> boards = new ArrayList<>();

			pstmt = conn.prepareStatement(SQL);

			pstmt.setInt(1, (page - 1) * 3);

			rs = pstmt.executeQuery();

			while (rs.next()) { // 알에스 넥스트 커서이동 리턴값 불린
				Board board = new Board();
				board.setId(rs.getInt("b.id"));
				board.setUserId(rs.getInt("b.userId"));
				board.setTitle(rs.getString("b.title"));
				board.setContent(rs.getString("b.content") + " ");
				board.setReadCount(rs.getInt("b.readCount"));
				board.setCreateDate(rs.getTimestamp("b.createDate"));

				// 2보드에 유저객체에 유저네임 저장(추후 :유저프로파일)
				board.getUser().setUsername(rs.getString("u.username"));
				boards.add(board);

			}
			return boards;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return null;
	}
	public List<Board> findAll(int page,String search) {
		
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT * from board b, user u ");
		sb.append("where b.userId=u.id and ");
		sb.append("(b.content like ? or b.title like ?) ");
		sb.append("order by b.id desc limit ?,3");
		
		final String SQL= sb.toString();
		conn = DBConn.getConnection();

		try {
			List<Board> boards = new ArrayList<>();

			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,"%"+search+"%");
			pstmt.setString(2,"%"+search+"%");
			pstmt.setInt(3, (page - 1) * 3);

			rs = pstmt.executeQuery();

			while (rs.next()) { // 알에스 넥스트 커서이동 리턴값 불린
				Board board = new Board();
				board.setId(rs.getInt("b.id"));
				board.setUserId(rs.getInt("b.userId"));
				board.setTitle(rs.getString("b.title"));
				board.setContent(rs.getString("b.content") + " ");
				board.setReadCount(rs.getInt("b.readCount"));
				board.setCreateDate(rs.getTimestamp("b.createDate"));

				// 2보드에 유저객체에 유저네임 저장(추후 :유저프로파일)
				board.getUser().setUsername(rs.getString("u.username"));
				board.getUser().setUserProfile(rs.getString("u.userProfile"));
				boards.add(board);

			}
			return boards;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return null;
	}

	private PreparedStatement setString(int i, String string) {
		// TODO Auto-generated method stub
		return null;
	}

	// 상세보기 SELECT *FROM board WHERE id =?
	public Board findById(int id) {
		final String SQL = "select * from board b, user u where b.userId = u.id and b.id = ?";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 알에스 넥스트 커서이동 리턴값 불린
				Board board = new Board();
				board.setId(rs.getInt("b.id"));
				board.setUserId(rs.getInt("b.userId"));
				board.setTitle(rs.getString("b.title"));
				board.setContent(rs.getString("b.content"));
				board.setReadCount(rs.getInt("b.readCount"));
				board.setCreateDate(rs.getTimestamp("b.createDate"));
				board.getUser().setUsername(rs.getString("u.username"));
				board.getUser().setUserProfile(rs.getString("u.userProfile"));
				return board;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return null;

	}

	public int delete(int id) {

		final String SQL = "DELETE FROM board WHERE id = ?";

		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);

			int result = pstmt.executeUpdate();// 변경된 튜플의 개수를 리턴

			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);

		}
		return -1;
	}
}
