package com.cos.test;

import org.junit.jupiter.api.Test;

import com.cos.model.Comment;
import com.google.gson.Gson;

public class GsonTest {
	
	@Test
	public void gsonTest1() {
		Comment comment= new Comment();
		comment.setId(1);
		comment.setUserId(5);
		comment.setBoardId(10);
		comment.setContent("댓글");
		comment.setCreateDate(null);
		comment.getResponseData().setStatus("ok");
		comment.getUser().setUsername("ssar");
		
		Gson gson =new Gson();
		String commentjson =gson.toJson(comment);
		System.out.println(commentjson);
		
	}

}
