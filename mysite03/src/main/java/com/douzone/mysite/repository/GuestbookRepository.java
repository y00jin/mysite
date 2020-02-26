package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GuestbookVo;

@Repository
public class GuestbookRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insert(GuestbookVo guestbookVo) {
		return sqlSession.insert("guestbook.insert", guestbookVo);
	}

	public int delete(GuestbookVo guestbookVo) {
		return sqlSession.delete("guestbook.delete", guestbookVo);
	}
	
	public List<GuestbookVo> findAll() {
		List<GuestbookVo> result = sqlSession.selectList("guestbook.findAll");
		return result;
	}
}
