package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.exception.UserRepositoryException;
import com.douzone.mysite.vo.UserVo;

@Repository
public class UserRepository {

	@Autowired
	private DataSource dataSource;
	
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

	public UserVo updateNamePasswordGender(UserVo vo) {
		UserVo userVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();

			String sql = "update user set name = ?, password = ?, gender = ? where email = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getGender());
			pstmt.setString(4, vo.getEmail());

			pstmt.executeUpdate();
			
			userVo = vo;
			
		} catch (SQLException e) {
			throw new UserRepositoryException(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userVo;
	}

}
