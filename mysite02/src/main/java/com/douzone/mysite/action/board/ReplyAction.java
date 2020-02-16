package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;

public class ReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String authUserNo = request.getParameter("authUserNo");
		String no = request.getParameter("no");
		String groupNo = request.getParameter("groupNo");
		String orderNo = request.getParameter("orderNo");
		String depth = request.getParameter("depth");
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		
		BoardVo vo = new BoardVo();
		vo.setNo(Integer.parseInt(no));
		vo.setGroupNo(Integer.parseInt(groupNo));
		vo.setOrderNo(Integer.parseInt(orderNo));
		vo.setUserNo(Integer.parseInt(authUserNo));
		vo.setDepth(Integer.parseInt(depth));
		vo.setTitle(title);
		vo.setContents(contents);
		
		new BoardRepository().updateOrderNo(Integer.parseInt(orderNo));
		new BoardRepository().insertInView(vo);
		
		response.sendRedirect(request.getContextPath() + "/board?a=list&no=1");
		
		
	}

}
