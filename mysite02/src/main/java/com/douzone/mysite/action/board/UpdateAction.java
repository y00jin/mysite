package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String no = request.getParameter("no");
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		
		BoardVo vo = new BoardVo();
		vo.setNo(Integer.parseInt(no));
		vo.setTitle(title);
		vo.setContents(contents);
		
		new BoardRepository().updateBoard(vo);
		
		response.sendRedirect(request.getContextPath() + "/board");
	
	}

}
