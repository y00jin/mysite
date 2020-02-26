package com.douzone.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "")
	public String index(Model model) {
		List<BoardVo> list = boardService.getBoard();
		model.addAttribute("list", list);
		return "board/index";
	}

	@RequestMapping(value = "/view/{no}")
	public String view(Model model, @PathVariable("no") Long no) {
		model.addAttribute("no", no);
		BoardVo content = boardService.getView(no);
		boardService.updateHit(no);
		model.addAttribute("content", content);
		return "board/view";
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(HttpSession session) {
		//////////////////////////// 접근제어////////////////////////////
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null)
			return "redirect:/";
		///////////////////////////////////////////////////////////////
		return "board/write";
	}

	@RequestMapping(value = "/reply/{no}", method = RequestMethod.GET)
	public String reply(HttpSession session, @PathVariable("no") Long no, Model model) {
		////////////////////////////접근제어////////////////////////////
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null)
			return "redirect:/";
		///////////////////////////////////////////////////////////////
		BoardVo rootVo = boardService.findBoardByNo(no);
		model.addAttribute("rootVo",rootVo);
		return "board/write";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(HttpSession session, BoardVo vo) {
		//////////////////////////// 접근제어////////////////////////////
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null)
			return "redirect:/";
		///////////////////////////////////////////////////////////////
		boardService.write(vo);
		return "redirect:/board";
	}

	@RequestMapping(value = "/delete/{no}")
	public String delete(HttpSession session, @PathVariable("no") Long no) {
		////////////////////////////접근제어////////////////////////////
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null)
		return "redirect:/";
		///////////////////////////////////////////////////////////////
		boardService.delete(no);
		return "redirect:/board";
	}

	@RequestMapping(value = "/modify/{no}", method = RequestMethod.GET)
	public String modify(HttpSession session, @PathVariable("no") Long no, Model model) {
		////////////////////////////접근제어////////////////////////////
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null)
			return "redirect:/";
		///////////////////////////////////////////////////////////////
		BoardVo content = boardService.findBoardByNo(no);
		model.addAttribute("content", content);
		return "board/modify";
	}

	@RequestMapping(value = "/modify/{no}", method = RequestMethod.POST)
	public String modify(HttpSession session, BoardVo vo) {
		////////////////////////////접근제어////////////////////////////
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null)
		return "redirect:/";
		///////////////////////////////////////////////////////////////
		boardService.modify(vo);
		return "redirect:/board";
	}
}
