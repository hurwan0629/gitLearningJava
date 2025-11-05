package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JDBCUtil;
import model.dto.ReplyDTO;

public class ReplyDAO {
	
	private static final String SELECT_ALL = "SELECT * FROM REPLY WHERE BID = ? ORDER BY RID DESC";
	
	static final String INSERT = "INSERT INTO REPLY(RID, CONTENT, MID, BID) VALUES((SELECT NVL(MAX(RID), 1000) FROM REPLY)+1, ?, ?,?)";
	
	public ArrayList<ReplyDTO> selectAll(ReplyDTO replyDTO){
		ArrayList<ReplyDTO> datas = new ArrayList<>();
		
		// 1,2
		Connection conn = JDBCUtil.connect();
		
		// 3
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(SELECT_ALL);
			pstmt.setInt(1, replyDTO.getBid());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// 데이터를 생성
				ReplyDTO data = new ReplyDTO();
				data.setRid(rs.getInt("RID"));
				data.setContent(rs.getString("CONTENT"));
				data.setMid(rs.getString("MID"));
				data.setBid(rs.getInt("BID"));
				data.setRegdate(rs.getDate("REGDATE"));
				
				// 배열 리스트에 생성한 데이터를 추가
				datas.add(data);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			// 4
			JDBCUtil.disconnect(conn, pstmt);
		}
		return datas;
		
	}
	private ReplyDTO selectOne(ReplyDTO replyDTO){
		return null;
	}
	
	public boolean insert(ReplyDTO replyDTO){
		Connection conn = JDBCUtil.connect();

		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, replyDTO.getContent());
			pstmt.setString(2, replyDTO.getMid());
			pstmt.setInt(3, replyDTO.getBid());
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
	private boolean update(ReplyDTO replyDTO){
		return false;
	}
	private boolean delete(ReplyDTO replyDTO){
		return false;
	}
}
