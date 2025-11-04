package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JDBCUtil;
import model.dto.BoardDTO;

public class BoardDAO {
	private static final String SELECT_ALL = //"SELECT * FROM BOARD ORDER BY BID DESC"; 
			"SELECT B.BID BID, B.TITLE TITLE, B.CONTENT CONTENT, B.MID MID, B.BCOUNT BCOUNT, M.NAME, NAME "
			+ "FROM BOARD B LEFT OUTER JOIN MEMBER M ON M.MID = B.MID ORDER BY B.BID DESC";
	private static final String SELECT_ALL_TITLE = //"SELECT * FROM BOARD WHERE TITLE LIKE ? ORDER BY BID DESC";
			"SELECT B.BID BID, B.TITLE TITLE, B.CONTENT CONTENT, B.MID MID, B.BCOUNT BCOUNT, M.NAME NAME "
			+ "FROM BOARD B LEFT OUTER JOIN MEMBER M ON M.MID = B.MID WHERE B.TITLE LIKE ? ORDER BY B.BID DESC";
	private static final String SELECT_ALL_MID = //"SELECT * FROM BOARD WHERE MID = ? ORDER BY BID DESC";
			"SELECT B.BID BID, B.TITLE TITLE, B.CONTENT CONTENT, B.MID MID, B.BCOUNT BCOUNT,M.NAME NAME "
			+ "FROM BOARD B INNER JOIN MEMBER M ON M.MID = B.MID WHERE M.MID = ? ORDER BY B.BID DESC";
	private static final String SELECT_ALL_BCOUNT = //"SELECT * FROM BOARD ORDER BY BCOUNT DESC, BID DESC";
			"SELECT B.BID BID, B.TITLE TITLE, B.CONTENT CONTENT, B.MID MID, B.BCOUNT BCOUNT, M.NAME NAME "
			+ "FROM BOARD B LEFT OUTER JOIN MEMBER M ON M.MID = B.MID ORDER BY B.BCOUNT DESC, B.BID DESC";
	private static final String SELECT_ALL_NAME = // 찾는 이름만 같은 모든 글 출력
			"SELECT B.BID BID, B.TITLE TITLE, B.CONTENT CONTENT, B.MID MID, B.BCOUNT BCOUNT,M.NAME NAME"
			+ "FROM BOARD B INNER JOIN MEMBER M ON M.MID = B.MID WHERE M.NAME = ? ORDER BY B.BID DESC";

	private static final String SELECT_ONE = "SELECT * FROM BOARD WHERE BID = ?";

	private static final String INSERT = "INSERT INTO BOARD(BID,TITLE,CONTENT,MID) VALUES((SELECT NVL(MAX(BID),100) FROM BOARD)+1,?,?,?)";
	private static final String DELETE = "DELETE FROM BOARD WHERE BID = ?";

	private static final String UPDATE_BCOUNT = "UPDATE BOARD SET BCOUNT = BCOUNT+1 WHERE BID = ?";
	private static final String UPDATE_TITLE = "UPDATE BOARD SET TITLE = ? WHERE BID = ?";
	private static final String UPDATE_CONTENT = "UPDATE BOARD SET CONTENT = ? WHERE BID = ?";



	public ArrayList<BoardDTO> selectAll(BoardDTO boardDTO){
		ArrayList<BoardDTO> datas = new ArrayList<BoardDTO>();

		// 1,2 ▶ 함수화,모듈화,컴포넌트화
		Connection conn = JDBCUtil.connect();

		// 3
		PreparedStatement pstmt = null;
		try {
			if(boardDTO.getCondition().equals("ALL")) {
				pstmt = conn.prepareStatement(SELECT_ALL);
			}
			else if(boardDTO.getCondition().equals("TITLE")) {
				pstmt = conn.prepareStatement(SELECT_ALL_TITLE);
				pstmt.setString(1, "%" + boardDTO.getTitle() + "%");
			}
			else if(boardDTO.getCondition().equals("MID")) {
				pstmt = conn.prepareStatement(SELECT_ALL_MID);
				pstmt.setString(1, boardDTO.getMid());
			}
			else if(boardDTO.getCondition().equals("BCOUNT")) {
				pstmt = conn.prepareStatement(SELECT_ALL_BCOUNT);
			}
			else if(boardDTO.getCondition().equals("NAME")) {
				pstmt = conn.prepareStatement(SELECT_ALL_NAME);
				pstmt.setString(1, boardDTO.getWriter());
			}
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO data = new BoardDTO();
				data.setBid(rs.getInt("BID"));
				data.setTitle(rs.getString("TITLE"));
				data.setContent(rs.getString("CONTENT"));
				data.setMid(rs.getString("MID"));
				data.setWriter(rs.getString("NAME"));
				data.setBcount(rs.getInt("BCOUNT"));
				datas.add(data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4 ▶ 함수화,모듈화,컴포넌트화
			JDBCUtil.disconnect(conn, pstmt);
		}

		return datas;
	}
	public BoardDTO selectOne(BoardDTO boardDTO){
		BoardDTO data = null;

		Connection conn = JDBCUtil.connect();

		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(SELECT_ONE);
			pstmt.setInt(1, boardDTO.getBid());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				data = new BoardDTO();
				data.setBid(rs.getInt("BID"));
				data.setTitle(rs.getString("TITLE"));
				data.setContent(rs.getString("CONTENT"));
				data.setMid(rs.getString("MID"));
				data.setBcount(rs.getInt("BCOUNT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(conn, pstmt);
		}

		return data;
	}

	public boolean insert(BoardDTO boardDTO){
		Connection conn = JDBCUtil.connect();

		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, boardDTO.getTitle());
			pstmt.setString(2, boardDTO.getContent());
			pstmt.setString(3, boardDTO.getMid());
			int result = pstmt.executeUpdate();
			if(result <= 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(conn, pstmt);
		}

		return true;
	}
	public boolean update(BoardDTO boardDTO){
		Connection conn = JDBCUtil.connect();

		PreparedStatement pstmt = null;
		try {
			if(boardDTO.getCondition().equals("UPDATE_BCOUNT")) {
				pstmt = conn.prepareStatement(UPDATE_BCOUNT);
				pstmt.setInt(1, boardDTO.getBid());
			}
			else if(boardDTO.getCondition().equals("UPDATE_CONTENT")) {
				pstmt = conn.prepareStatement(UPDATE_CONTENT);
				pstmt.setString(1, boardDTO.getContent());
				pstmt.setInt(2, boardDTO.getBid());
			}
			else if(boardDTO.getCondition().equals("UPDATE_TITLE")) {
				pstmt = conn.prepareStatement(UPDATE_TITLE);
				pstmt.setString(1, boardDTO.getTitle());
				pstmt.setInt(2, boardDTO.getBid());
			}
			int result = pstmt.executeUpdate();
			if(result <= 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(conn, pstmt);
		}

		return true;
	}
	public boolean delete(BoardDTO boardDTO){
		Connection conn = JDBCUtil.connect();

		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, boardDTO.getBid());
			int result = pstmt.executeUpdate();
			if(result <= 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(conn, pstmt);
		}

		return true;
	}
}
