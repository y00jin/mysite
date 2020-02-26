package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "")
	public String index(Model model) {
		List<BoardVo> list = boardService.getBoard();
		model.addAttribute("list",list);
		return "board/index";
	}
	
	@RequestMapping(value = "/view/{no}")
	public String view(Model model,@PathVariable("no") Long no) {
		model.addAttribute("no",no);
		BoardVo content = boardService.getView(no);
		model.addAttribute("content", content);
		return "board/view";
	}
	
}
