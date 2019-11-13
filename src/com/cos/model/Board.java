package com.cos.model;

import java.sql.Timestamp;

import com.mysql.cj.x.protobuf.MysqlxCrud.CreateView;

public class Board {
	private int id;
	private int userId;
	private String title;
	private String content;
	
	private int readCount;
	private Timestamp createDate;
	private String previewImg; //db와 상관없음. 보드에 디비객체추가
	private User user=new User();
	
	public Board() {

	}

	public Board(int id, int userId, String title, String content, int readCount, Timestamp createDate,
			String previewImg) {
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.readCount = readCount;
		this.createDate = createDate;
		this.previewImg = previewImg;
	}

	public int getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getPreviewImg() {
		return previewImg;
	}

	public void setPreviewImg(String previewImg) {
		this.previewImg = previewImg;
	}

	




}