package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.UserVo;

@Repository
public class UserRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public int insert(UserVo vo) {
		return sqlSession.insert("user.insert", vo);
	}

	public UserVo findByEmailAndPassword(UserVo vo) {
		return sqlSession.selectOne("user.findByEmailAndPassword", vo);
	}
	
	public UserVo findByEmailAndPassword(String email, String password) {
		Map<String, Object> map = new HashMap<>();
		map.put("e", email);
		map.put("p", password);
		return sqlSession.selectOne("user.findByEmailAndPassword2", map);
	}

	public UserVo findByNo(Long authUserNo) {
		return sqlSession.selectOne("user.findByNo", authUserNo);
	}

	public UserVo find(String email) {
		return sqlSession.selectOne("user.find", email);
	}

	public int update(UserVo vo) {
		return sqlSession.update("user.update", vo);
	}

}
