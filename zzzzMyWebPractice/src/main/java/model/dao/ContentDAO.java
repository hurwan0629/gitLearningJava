package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JDBCUtil;
import model.dto.ContentDTO;

public class ContentDAO {
	private static final String INSERT = "INSERT INTO CONTENT VALUES(CONTENT_SEQ.NEXTVAL, ?)";
	private static final String SELECT = "SELECT CONTENT FROM CONTENT WHERE CONTENT_PK=?"; 
	private static final String SELECT_ALL = "SELECT CONTENT_PK, CONTENT FROM CONTENT ORDER BY CONTENT_PK DESC";
	
	public ContentDTO selectOne(ContentDTO contentDTO) {
		ContentDTO data = new ContentDTO();
		Connection conn = JDBCUtil.connect();
		PreparedStatement pstmt=null;
		
		try {
			pstmt = conn.prepareStatement(SELECT);
			pstmt.setInt(1, contentDTO.getCpk());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String content = rs.getString("CONTENT");
				data.setContent(content);
			}
		} catch (SQLException e) {
			return data;
		}
		return data;
	}

	public ArrayList<ContentDTO> selectAll(ContentDTO contentDTO) {
		ArrayList<ContentDTO> datas = new ArrayList<>();
		Connection conn = JDBCUtil.connect();
		PreparedStatement pstmt=null;
		
		try {
			pstmt = conn.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String content = rs.getString("CONTENT");
				int cpk = rs.getInt("CONTENT_PK");
				ContentDTO data = new ContentDTO();
				data.setContent(content);
				data.setCpk(cpk);
				datas.add(data);
			}
		} catch (SQLException e) {
			return datas;
		}
		return datas;
	}

	public boolean insert(ContentDTO contentDTO) {

		Connection conn = JDBCUtil.connect();
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, contentDTO.getContent());
			int rs = pstmt.executeUpdate();
			if (rs <= 0) {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	private boolean delete(ContentDTO contentDTO) {
		return false;
	}

	private boolean update(ContentDTO contentDTO) {
		return false;
	}

}
