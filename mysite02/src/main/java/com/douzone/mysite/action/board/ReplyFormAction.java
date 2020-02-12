package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class ReplyFormAction implements Action {
	
//	http://localhost:8088/mysite02/board?a=replyform&no=4&gNo=2&oNo=1&depth=0
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String no = request.getParameter("no");
		String groupNo = request.getParameter("gNo");
		String orderNo = request.getParameter("oNo");
		String depth = request.getParameter("depth");
		
		request.setAttribute("no", no);
		request.setAttribute("groupNo", groupNo);
		request.setAttribute("orderNo", orderNo);
		request.setAttribute("depth", depth);
		
		WebUtil.forward("/WEB-INF/views/board/reply.jsp", request, response);
	}

}
