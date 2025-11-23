package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JDBCUtil;
import model.dto.ReplyDTO;

public class ReplyDAO {
	private static final String SELECT_ALL= "SELECT R.RID, R.CONTENT, R.WRITER, R.BID, M.NAME "
			+ "FROM REPLY R LEFT JOIN MEMBER M ON R.WRITER=M.MID WHERE BID=? ORDER BY RID DESC";
	private static final String INSERT = 						//CONTENT, WRITER, BID
			"INSERT INTO REPLY VALUES((SELECT NVL(MAX(RID),10000) FROM REPLY)+1,?,?,?)";

	private static final String UPDATE="UPDATE REPLY SET CONTENT=? WHERE RID=?";
	private static final String UPDATE_DELMEM="UPDATE REPLY SET WRITER=NULL WHERE WRITER=?";
	
	private static final String DELETE="DELETE FROM REPLY WHERE RID=?";
	
	
	
	public ArrayList<ReplyDTO> selectAll(ReplyDTO replyDTO){
		ArrayList<ReplyDTO> datas = new ArrayList<>();
		Connection conn = JDBCUtil.connect();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(SELECT_ALL);
			pstmt.setInt(1, replyDTO.getBid());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ReplyDTO data = new ReplyDTO();
				data.setBid(rs.getInt("BID"));
				data.setContent(rs.getString("CONTENT"));
				data.setRid(rs.getInt("RID"));
				data.setWriter(rs.getString("WRITER"));
				data.setWriterName(rs.getString("NAME"));
				datas.add(data);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		JDBCUtil.disconnect(conn, pstmt);
		
		return datas;
	}
	private ReplyDTO selectOne(ReplyDTO replyDTO){
		return null;
	}

	public boolean insert(ReplyDTO replyDTO){
		
		Connection conn = JDBCUtil.connect();
		PreparedStatement pstmt= null;
		try {
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, replyDTO.getContent());
			pstmt.setString(2, replyDTO.getWriter());
			pstmt.setInt(3, replyDTO.getBid());
			int rs = pstmt.executeUpdate();
			if(rs<=0) {
				return false;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		JDBCUtil.disconnect(conn, pstmt);
		
		return true;
	}
	public boolean update(ReplyDTO replyDTO){
		Connection conn = JDBCUtil.connect();
		PreparedStatement pstmt= null;
		try {
			if(replyDTO.getCondition().equals("UPDATE_REPLY")) {
				pstmt = conn.prepareStatement(UPDATE);
				pstmt.setString(1, replyDTO.getContent());
				pstmt.setInt(2, replyDTO.getRid());
				int rs = pstmt.executeUpdate();
				if(rs<=0) {
					return false;
				}
			}
			else if(replyDTO.getCondition().equals("UPDATE_DELMEM")) {
				pstmt = conn.prepareStatement(UPDATE_DELMEM);
				pstmt.setString(1, replyDTO.getWriter());
				int rs = pstmt.executeUpdate();
				if(rs<=0) {
					return false;
				}
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		JDBCUtil.disconnect(conn, pstmt);
		
		return true;
	}
	public boolean delete(ReplyDTO replyDTO){
		Connection conn = JDBCUtil.connect();
		PreparedStatement pstmt= null;
		try {
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, replyDTO.getRid());
			int rs = pstmt.executeUpdate();
			if(rs<=0) {
				return false;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		JDBCUtil.disconnect(conn, pstmt);
		
		return true;
	}
}
