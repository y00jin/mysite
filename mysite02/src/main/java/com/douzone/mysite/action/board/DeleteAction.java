package com.douzone.mysite.action.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.web.action.Action;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String no = request.getParameter("no");
		String ono = request.getParameter("ono");
		String gno = request.getParameter("gno");
		String depth = request.getParameter("dep");
		
		response.setContentType("text/html; charset=UTF-8");
		
//		new BoardRepository().deleteBoard(Integer.parseInt(no));
		
		int deleteNoDepth = new BoardRepository().selectDepth(Integer.parseInt(ono), Integer.parseInt(gno));
		int compareNoDepth = new BoardRepository().selectDepth(Integer.parseInt(ono)+1, Integer.parseInt(gno));
		
		System.out.println(deleteNoDepth + " / " + compareNoDepth);
		System.out.println(no);
		
		if(compareNoDepth == -1) {
			new BoardRepository().deleteBoard(no);
			new BoardRepository().updateMinOrderNo(Integer.parseInt(ono),Integer.parseInt(gno));
			int maxOrderNo = new BoardRepository().selectMaxOrderNo(Integer.parseInt(gno));
			new BoardRepository().updateDeleteBoard(maxOrderNo,Integer.parseInt(depth));
		}
		if(compareNoDepth == deleteNoDepth) {
			new BoardRepository().deleteBoard(no);
			new BoardRepository().updateMinOrderNo(Integer.parseInt(ono),Integer.parseInt(gno));
			int maxOrderNo = new BoardRepository().selectMaxOrderNo(Integer.parseInt(gno));
			new BoardRepository().updateDeleteBoard(maxOrderNo,Integer.parseInt(depth));
		}
		if(deleteNoDepth != compareNoDepth) {
			new BoardRepository().updateBoard(Integer.parseInt(no));
		}
		if(deleteNoDepth < compareNoDepth) {
			new BoardRepository().updateBoard(Integer.parseInt(no));
		}
		if(deleteNoDepth > compareNoDepth) {
			new BoardRepository().deleteBoard(no);
			new BoardRepository().updateMinOrderNo(Integer.parseInt(ono),Integer.parseInt(gno));
			int maxOrderNo = new BoardRepository().selectMaxOrderNo(Integer.parseInt(gno));
			new BoardRepository().updateDeleteBoard(maxOrderNo,Integer.parseInt(depth));
		}
		
		response.sendRedirect(request.getContextPath() + "/board?a=list&no=1");
	}

}
