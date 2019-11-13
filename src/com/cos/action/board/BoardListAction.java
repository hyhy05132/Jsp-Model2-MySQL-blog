package com.cos.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.action.Action;
import com.cos.dao.BoardDao;
import com.cos.model.Board;
import com.cos.util.Utils;

public class BoardListAction implements Action {

	private final static String TAG = "BoardListAction:";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("page") == null)
			return;

		int page = Integer.parseInt(request.getParameter("page"));

		// page<=0 혹은 page>maxNum 버튼비활성화

		if (page <= 0) {
			page = 1;

		}

		BoardDao bDao = new BoardDao();
		List<Board> boards = null;
		List<Board> hotBoards = bDao.findOrderByReadCountDesc();

		// serach와 목록을 분기
		if (request.getParameter("search") == null || request.getParameter("search").equals("")) {
			boards = bDao.findAll(page);// 페이징 하기
			request.setAttribute("search", null);
		} else {
			String search = request.getParameter("search");
			System.out.println("search : "+search);
			boards = bDao.findAll(page, search);
			request.setAttribute("search", search);
		}

		Utils.setPreviewImg(boards); // 이미지를 프리뷰이미지에 저장
		Utils.setPreviewContent(boards);// 이미지 태그 제거
		Utils.setPreviewImg(hotBoards);//

		request.setAttribute("boards", boards);
		request.setAttribute("hotBoards", hotBoards);

		RequestDispatcher dis = request.getRequestDispatcher("/board/list.jsp");
		dis.forward(request, response);

	}

}
