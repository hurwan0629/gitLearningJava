package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.MemberDTO;

public class MemberDAO {
	private ArrayList<MemberDTO> selectAll(MemberDTO memberDTO){
		return null;
	}
	public MemberDTO selectOne(MemberDTO memberDTO){
		String SQL_SELECT_ONE_JOIN = 
				"SELECT MEMBER_PK,MEMBER_ID, MEMBER_NAME, MEMBER_PASSWORD, MEMBER_ADDRESS, MEMBER_PHONE_NUMBER, MEMBER_ROLE "
				+ "FROM TBL_MEMBER WHERE MEMBER_ID = ? ";//회원가입시 아이디 중복확인
		String SQL_SELECT_ONE_LOGIN = 
				"SELECT MEMBER_PK,MEMBER_ID, MEMBER_NAME, MEMBER_PASSWORD, MEMBER_ADDRESS, MEMBER_PHONE_NUMBER, MEMBER_ROLE "
				+ "FROM TBL_MEMBER WHERE MEMBER_ID = ? AND MEMBER_PASSWORD=?";//로그인    
		
		MemberDTO data = null; // selectOne의 반환값

		String driverName = "oracle.jdbc.driver.OracleDriver";

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "C##HRUWAN";
		String password = "1234";

		Connection conn = null;

		try {
			// 1. 드라이버 로드(적재)
			Class.forName(driverName);

			// 2. DB 연결
			conn = DriverManager.getConnection(url, user, password);

			PreparedStatement pstmt = null;
			if(memberDTO.getCondition().equals("JOIN")) {//회원가입중
				// 3. DB 사용 : data를 read,write ▶ 서비스(CRUD) 수행
				pstmt = conn.prepareStatement(SQL_SELECT_ONE_JOIN);
				pstmt.setString(1, memberDTO.getMemberId()); // MID 파라미터의 물음표
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					data = new MemberDTO();
					data.setMemberId(rs.getString("MEMBER_ID"));
					data.setMemberName(rs.getString("MEMBER_NAME"));
					data.setMemberId(rs.getString("MEMBER_ADDRESS"));
					data.setMemberId(rs.getString("MEMBER_PHONE_NUMBER"));
					data.setMemberRole(rs.getString("MEMBER_ROLE"));
				}
			}
			else if(memberDTO.getCondition().equals("LOGIN")) {
				// 3. DB 사용 : data를 read,write ▶ 서비스(CRUD) 수행
				pstmt = conn.prepareStatement(SQL_SELECT_ONE_LOGIN);
				pstmt.setString(1, memberDTO.getMemberId()); // MID 파라미터의 물음표
				pstmt.setString(2, memberDTO.getMemberPassword()); // PASSWD 파라미터의 물음표
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					data = new MemberDTO();
					data.setMemberId(rs.getString("MEMBER_ID"));
					data.setMemberName(rs.getString("MEMBER_NAME"));
					data.setMemberId(rs.getString("MEMBER_ADDRESS"));
					data.setMemberId(rs.getString("MEMBER_PHONE_NUMBER"));
					data.setMemberRole(rs.getString("MEMBER_ROLE"));
				}
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 4. DB 연결 해제
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		

		return data;
	}
	public boolean update(MemberDTO memberDTO){//회원탈퇴 업데이트

		String driverName = "oracle.jdbc.driver.OracleDriver";

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "C##HRUWAN";
		String password = "1234";

		Connection conn = null;

		String SQL_UPDATE = "UPDATE TBL_MEMBER SET MEMBER_ID = NULL WHERE MEMBER_ID = ? AND MEMBER_PASSWORD = ?";

		try {
			// 1. 드라이버 로드(적재)
			Class.forName(driverName);

			// 2. DB 연결
			conn = DriverManager.getConnection(url, user, password);

			// 3. DB 사용 : data를 read,write ▶ 서비스(CRUD) 수행
			PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE);
			pstmt.setString(1, memberDTO.getMemberId());
			pstmt.setString(2, memberDTO.getMemberPassword());
			int result = pstmt.executeUpdate(); // CUD(INSERT,UPDATE,DELETE)
			if(result <= 0) {
				return false;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				// 4. DB 연결 해제
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return true;//성공 true 실패 false
	}
	public boolean insert(MemberDTO memberDTO){//회원가입, 등급은 무조건 user
	

		String driverName = "oracle.jdbc.driver.OracleDriver";

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "C##HRUWAN";
		String password = "1234";

		Connection conn = null;

		String SQL_INSERT = "INSERT INTO TBL_MEMBER VALUES(?,?,?,?,?,'USER')";

		try {
			// 1. 드라이버 로드(적재)
			Class.forName(driverName);

			// 2. DB 연결
			conn = DriverManager.getConnection(url, user, password);

			// 3. DB 사용 : data를 read,write ▶ 서비스(CRUD) 수행
			PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT);
			pstmt.setString(1, memberDTO.getMemberId());
			pstmt.setString(2, memberDTO.getMemberName());
			pstmt.setString(3, memberDTO.getMemberPassword());
			pstmt.setString(4, memberDTO.getMemberAddress());
			pstmt.setString(5, memberDTO.getMemberPhoneNumber());
			int result = pstmt.executeUpdate(); // CUD(INSERT,UPDATE,DELETE)
			if(result <= 0) {
				return false;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				// 4. DB 연결 해제
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return true; //insert 성공시 true, 실패시 false 반환
	}
	private boolean delete(MemberDTO memberDTO) {
		return false;
	}
}




