package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	
	@Autowired
	private SqlSession sqlSession;

	// 리스트
	public List<BoardVo> findList(String keyword, String check, Integer page, Integer size) {
		Map<String, Object> param = new HashMap<>();
        param.put("check", check);
        param.put("keyword", keyword);
        param.put("startIndex", (page-1)*size);
        param.put("size", size);
		return sqlSession.selectList("board.findList", param);
	}

	// view
	public BoardVo findBoardByNo(Long boardNo) {
		BoardVo boardVo = sqlSession.selectOne("board.viewFinder", boardNo);
		return boardVo;
	}
	
	public void updateHit(Long boardNo) {
		sqlSession.update("board.updateHit",boardNo);
	}
	
	// write
	public int write(BoardVo boardVo) {
		return sqlSession.insert("board.write",boardVo);
	}
	
	// delete
	public int delete(Long no) {
		return sqlSession.delete("board.delete",no);
	}
	
	public int modify(BoardVo vo) {
		return sqlSession.update("board.modify", vo);
	}

	public void updateOrderNo(Long orderNo) {
		sqlSession.update("board.updateOrderNo", orderNo);
	}
	
	public int getTotalCount( String keyword, String check ) {
		Map<String, Object> map = new HashMap<>();
		map.put("check", check);
		map.put("keyword", keyword);
		return sqlSession.selectOne( "board.totalCount", map );
	}

}
