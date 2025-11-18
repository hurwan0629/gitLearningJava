package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JDBCUtil;

public class MemberDAO {
	private static final String SELECT_ONE="SELECT * FROM MEMBER WHERE MID=? AND MPW=?";
	
	private ArrayList<MemberDTO> selectAll(MemberDTO memberDTO){
		return null;
	}
	public MemberDTO selectOne(MemberDTO memberDTO){
		System.out.println("selectOne");
		MemberDTO data = null;

		Connection conn = null;

		PreparedStatement pstmt = null;
		try {
			conn=JDBCUtil.connect();
			pstmt = conn.prepareStatement(SELECT_ONE);
			pstmt.setString(1, memberDTO.getMid());
			pstmt.setString(2, memberDTO.getMpw());
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("탐색 성공");
				data = new MemberDTO();
				data.setMid(rs.getString("MID"));
				data.setMpw(rs.getString("MPW"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(conn, pstmt);
		}
		return data;
	}
	private boolean insert(MemberDTO memberDTO){
		return false;
	}
	private boolean update(MemberDTO memberDTO){
		return false;
	}
	private boolean delete(MemberDTO memberDTO){
		return false;
	}
}
