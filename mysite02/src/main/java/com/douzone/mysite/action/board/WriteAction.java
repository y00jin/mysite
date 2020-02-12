package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String userNo = request.getParameter("authUserNo");
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		
		BoardVo vo = new BoardVo();
		vo.setUserNo(Integer.parseInt(userNo));
		vo.setTitle(title);
		vo.setContents(contents);
		
		new BoardRepository().insertInList(vo);
		
		response.sendRedirect(request.getContextPath() + "/board");
		
	}

}
