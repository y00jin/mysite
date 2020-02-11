package com.douzone.mysite.action.guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.web.action.Action;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<GuestbookVo> list = new GuestbookRepository().findAll();

		String no = request.getParameter("no");
		String password = request.getParameter("password");

		for (GuestbookVo vo : list) {
			if (Integer.toString(vo.getNo()).equals(no)) {
				if (vo.getPassword().equals(password)) {
					GuestbookVo deleteVo = new GuestbookVo();
					deleteVo.setNo(Integer.parseInt(no));
					new GuestbookRepository().delete(deleteVo);
					response.sendRedirect(request.getContextPath() + "/guestbook?a=list");
					break;
				} else {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('잘못된 비밀번호입니다.'); location.href='" +request.getContextPath()+ "/guestbook?a=deleteform';</script>");
					out.flush();
					break;
				}
			}
		}
	}

}
