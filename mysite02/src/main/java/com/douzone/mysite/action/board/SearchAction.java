package com.douzone.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.repository.PageRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.PageVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class SearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String kind = request.getParameter("kind");
		String kwd = request.getParameter("kwd");
		int totalRow = 0;
		PageVo pageVo = new PageVo();
		List<BoardVo> list = null;

		if(kind.equals("title")) {
			list = new BoardRepository().searchTitle(kwd);
			totalRow = new PageRepository().countSearchBoard(kwd);
		}
		if(kind.equals("name")) {
			list = new BoardRepository().searchWriter(kwd);
			totalRow = new PageRepository().countSearchName(kwd);
		}
		
		pageVo.setTotalRow(totalRow);
		pageVo.page();
		
		request.setAttribute("searchPageVo", pageVo);
		request.setAttribute("ListOrSearch", 1);
		request.setAttribute("searchList", list);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/board/list.jsp");
		rd.forward(request, response);
	}

}
