package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JDBCUtil;
import model.dto.MemberDTO;

public class MemberDAO {
	private ArrayList<MemberDTO> selectAll(MemberDTO memberDTO){
		return null;
	}
	public MemberDTO selectOne(MemberDTO memberDTO){
		/*ID 중복확인*/String SQL_SELECT_ONE_JOIN = "SELECT MEMBER_ID FROM TBL_MEMBER WHERE MEMBER_ID = ?";
		/*로그인-->>*/String SQL_SELECT_ONE_LOGIN = "SELECT MEMBER_PASSWORD, MEMBER_ID, MEMBER_NAME, MEMBER_ADDRESS, MEMBER_PHONE_NUMBER, MEMBER_ROLE FROM TBL_MEMBER WHERE MEMBER_ID = ? AND MEMBER_PASSWORD=?";   
		/*회원탈퇴시 비밀번호 확인 부분->>*/ // LOGIN하고 같은기능
		
		
		Connection conn = JDBCUtil.connect();
		PreparedStatement pstmt = null;
		MemberDTO data = null; // selectOne의 반환값(담기지 않으면 null반환)

		try {
			if(memberDTO.getCondition().equals("JOIN")) {//회원가입중
				pstmt = conn.prepareStatement(SQL_SELECT_ONE_JOIN);
				pstmt.setString(1, memberDTO.getMemberId());	
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					data = new MemberDTO(); 
					data.setMemberId(rs.getString("MEMBER_ID"));
				}
			}
			else if(memberDTO.getCondition().equals("LOGIN")) {
				System.out.println("moded.dao.MemberDAO.selectOne condition:LOGIN [로그]");
				// 3. DB 사용 : data를 read,write ▶ 서비스(CRUD) 수행
				pstmt = conn.prepareStatement(SQL_SELECT_ONE_LOGIN);
				pstmt.setString(1, memberDTO.getMemberId()); 
				pstmt.setString(2, memberDTO.getMemberPassword()); 
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					data = new MemberDTO(); 
					data.setMemberId(rs.getString("MEMBER_ID"));
					data.setMemberName(rs.getString("MEMBER_NAME"));
					data.setMemberPassword(rs.getString("MEMBER_PASSWORD"));
					data.setMemberAddress(rs.getString("MEMBER_ADDRESS"));
					data.setMemberPhoneNumber(rs.getString("MEMBER_PHONE_NUMBER"));
					data.setMemberRole(rs.getString("MEMBER_ROLE"));
				}
			}
//			else if(memberDTO.getCondition().equals("QUIT")) {
//				// 3. DB 사용 : data를 read,write ▶ 서비스(CRUD) 수행
//				pstmt = conn.prepareStatement(SQL_SELECT_ONE_QUIT);
//				pstmt.setString(1, memberDTO.getMemberId());
//				ResultSet rs = pstmt.executeQuery();
//				if (rs.next()) {
//					data = new MemberDTO(); 
//					data.setMemberId(rs.getString("MEMBER_ID"));
//					data.setMemberName(rs.getString("MEMBER_NAME"));
//					data.setMemberPassword(rs.getString("MEMBER_PASSWORD"));
//					data.setMemberAddress(rs.getString("MEMBER_ADDRESS"));
//					data.setMemberPhoneNumber(rs.getString("MEMBER_PHONE_NUMBER"));
//					data.setMemberRole(rs.getString("MEMBER_ROLE"));
//				}
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.disconnect(conn, pstmt);
		}		
		return data;
	}
	public boolean update(MemberDTO memberDTO){//회원탈퇴 업데이트
		
		String SQL_UPDATE = "UPDATE TBL_MEMBER SET MEMBER_ID = NULL WHERE MEMBER_ID = ? AND MEMBER_PASSWORD = ?";

		Connection conn = JDBCUtil.connect();
		PreparedStatement pstmt = null;
		
		try {
			// 3. DB 사용 : data를 read,write ▶ 서비스(CRUD) 수행
			pstmt = conn.prepareStatement(SQL_UPDATE);
			pstmt.setString(1, memberDTO.getMemberId());
			pstmt.setString(2, memberDTO.getMemberPassword());
			int result = pstmt.executeUpdate(); // CUD(INSERT,UPDATE,DELETE)
			if(result <= 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(conn, pstmt);
		}

		return true;//성공 true 실패 false
	}
	public boolean insert(MemberDTO memberDTO){//회원가입, 등급은 무조건 user
		
		String SQL_INSERT = "INSERT INTO TBL_MEMBER VALUES(MEMBER_SEQ.NEXTVAL,?,?,?,?,?,'USER')";
		
		Connection conn = JDBCUtil.connect();
		PreparedStatement pstmt = null;

		try {
			// 3. DB 사용 : data를 read,write ▶ 서비스(CRUD) 수행
			pstmt = conn.prepareStatement(SQL_INSERT);
			pstmt.setString(1, memberDTO.getMemberId());
			pstmt.setString(2, memberDTO.getMemberName());
			pstmt.setString(3, memberDTO.getMemberPassword());
			pstmt.setString(4, memberDTO.getMemberAddress());
			pstmt.setString(5, memberDTO.getMemberPhoneNumber());
			int result = pstmt.executeUpdate(); // CUD(INSERT,UPDATE,DELETE)
			if(result <= 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(conn, pstmt);
		}

		return true; //insert 성공시 true, 실패시 false 반환
	}
	private boolean delete(MemberDTO memberDTO) {
		return false;
	}
}