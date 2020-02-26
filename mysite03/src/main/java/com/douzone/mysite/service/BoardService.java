package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	public List<BoardVo> getBoard() {
		return boardRepository.findAll();
	}
	
	public BoardVo getView(Long no) {
		return boardRepository.findBoardByNo(no);
	}

	public int write(BoardVo vo) {
		return boardRepository.write(vo);
	}
	
	public void updateHit(Long no) {
		boardRepository.updateHit(no);
	}
	
	public int delete(Long no) {
		return boardRepository.delete(no);
	}
	
	public boolean modify(BoardVo boardVo) {
		int count = boardRepository.modify(boardVo);
		return count == 1;
	}

	public BoardVo findBoardByNo(Long no) {
		return boardRepository.findBoardByNo(no);
	}
	
}
