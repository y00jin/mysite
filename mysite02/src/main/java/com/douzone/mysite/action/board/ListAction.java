package com.douzone.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.PageRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.PageVo;
import com.douzone.web.action.Action;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		PageVo pageVo = new PageVo();
		
//		int thisPage = pageVo.getThisPage();
		
		int totalRow = new PageRepository().countBoard();
		pageVo.setTotalRow(totalRow);
		pageVo.page();
		
//		List<BoardVo> list = new BoardRepository().findAll();
		String no = request.getParameter("no");
		pageVo.setThisPage(Integer.parseInt(no));
		List<BoardVo> list;
		if(no == null) {
			list = new PageRepository().findAllByNo(1, pageVo.getDisplayRow());
		} else {
			list = new PageRepository().findAllByNo(Integer.parseInt(no), pageVo.getDisplayRow());
		}
	
		request.setAttribute("ListOrSearch", 0);
		request.setAttribute("boardList", list);
		request.setAttribute("pageNo", no);
		request.setAttribute("pageVo", pageVo);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/board/list.jsp");
		rd.forward(request, response);
	}

}
