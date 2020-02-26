package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	
	@Autowired
	private SqlSession sqlSession;

	// 리스트
	public List<BoardVo> findAll() {
		List<BoardVo> result = sqlSession.selectList("board.findAll");
		return result;
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

	
	/*
	public List<BoardVo> searchTitle(String searchTitle) {
		
		List<BoardVo> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();

			String sql = "select b.no,b.title,b.contents,b.hit,b.reg_date,b.g_no,b.o_no,b.depth,a.no,a.name " + 
					"from user a,board b where a.no = b.user_no and b.title like ? order by g_no desc,o_no asc";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+searchTitle+"%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				int hit = rs.getInt(4);
				String regDate = rs.getString(5);
				int groupNo = rs.getInt(6);
				int orderNo = rs.getInt(7);
				int depth = rs.getInt(8);
				int userNo = rs.getInt(9);
				String userName = rs.getString(10);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setGroupNo(groupNo);
				vo.setOrderNo(orderNo);
				vo.setDepth(depth);
				vo.setUserNo(userNo);
				vo.setUserName(userName);
				
				result.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public List<BoardVo> searchWriter(String searchWriter) {
		
		List<BoardVo> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();

			String sql = "select b.no,b.title,b.contents,b.hit,b.reg_date,b.g_no,b.o_no,b.depth,a.no,a.name " + 
					"from user a,board b where a.no = b.user_no and a.name like ? order by g_no desc,o_no asc";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+searchWriter+"%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				int hit = rs.getInt(4);
				String regDate = rs.getString(5);
				int groupNo = rs.getInt(6);
				int orderNo = rs.getInt(7);
				int depth = rs.getInt(8);
				int userNo = rs.getInt(9);
				String userName = rs.getString(10);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setGroupNo(groupNo);
				vo.setOrderNo(orderNo);
				vo.setDepth(depth);
				vo.setUserNo(userNo);
				vo.setUserName(userName);
				
				result.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public void updateOrderNo(int orderNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();

			String sql = "update board set o_no = (o_no+1) where ? < o_no;";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1,orderNo);

			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error : " + e);
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
	}
	
	public void updateMinOrderNo(int orderNo, int groupNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();

			String sql = "update board set o_no = (o_no-1) where ? < o_no and g_no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1,orderNo);
			pstmt.setInt(2,groupNo);

			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error : " + e);
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
	}
	
	public boolean insertInView(BoardVo boardVo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();

			String sql = "insert into board values(null,?,?,0,now(),?,?+1,?+1,?)";
			
//			insert into board values(null,'라면','뭔소리',0,now(),1,o_no+1,depth+1,3);
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContents());
			pstmt.setInt(3, boardVo.getGroupNo());
			pstmt.setInt(4, boardVo.getOrderNo());
			pstmt.setInt(5, boardVo.getDepth());
			pstmt.setInt(6, boardVo.getUserNo());

			int count = pstmt.executeUpdate();

			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error : " + e);
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
		return result;
	}
	
		
	
	
	public int selectDepth(int ono, int gno) {
		int depth = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();

			String sql = "select depth from board where o_no=? and g_no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, ono);
			pstmt.setInt(2, gno);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				depth = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return depth;
	}
	
	
	
	public int selectMaxOrderNo(int gno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int maxOrderNo = 0;

		try {
			conn = dataSource.getConnection();

			String sql = "select max(o_no) from board where contents='' and g_no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, gno);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				maxOrderNo = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return maxOrderNo;
	}
	
	public boolean updateDeleteBoard(int maxOrderNo, int depth) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();

			String sql = "delete from board where o_no < ? and contents = ''  and depth = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, maxOrderNo+1);
			pstmt.setInt(2, depth-1);
			
			int count = pstmt.executeUpdate();

			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("error : " + e);
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
		return result;
	}
	
	public boolean updateBoard(int boardNo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();

			String sql = "update board set contents='' where no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, boardNo);
			
			int count = pstmt.executeUpdate();

			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("error : " + e);
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
		return result;
	}

	*/
}
