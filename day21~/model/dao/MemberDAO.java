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
		String SQL_SELECT_ONE_LOGIN = "SELECT * FROM MEMBER WHERE MID=? AND PASSWD=?";
		String SQL_SELECT_ONE_JOIN = "SELECT * FROM MEMBER WHERE MID=?";

		///// LOGIN
		// 전체배열중에서
		// [아이디],[비번] 확인해서 데이터 반환
		///// JOIN
		// 전체배열중에서
		// [아이디]가 존재하는지 확인

		MemberDTO data = null; // selectOne의 반환값

		String driverName = "oracle.jdbc.driver.OracleDriver";

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "C##HURWAN";
		String password = "1234";

		Connection conn = null;

		try {
			// 1. 드라이버 로드(적재)
			Class.forName(driverName);

			// 2. DB 연결
			conn = DriverManager.getConnection(url, user, password);

			PreparedStatement pstmt = null;
			if(memberDTO.getCondition().equals("JOIN")) {
				// 3. DB 사용 : data를 read,write ▶ 서비스(CRUD) 수행
				pstmt = conn.prepareStatement(SQL_SELECT_ONE_JOIN);
				pstmt.setString(1, memberDTO.getMid()); // MID 파라미터의 물음표
			}
			else if(memberDTO.getCondition().equals("LOGIN")) {
				// 3. DB 사용 : data를 read,write ▶ 서비스(CRUD) 수행
				pstmt = conn.prepareStatement(SQL_SELECT_ONE_LOGIN);
				pstmt.setString(1, memberDTO.getMid()); // MID 파라미터의 물음표
				pstmt.setString(2, memberDTO.getPasswd()); // PASSWD 파라미터의 물음표
			}
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				data = new MemberDTO();
				data.setMid(rs.getString("MID"));
				data.setPasswd(rs.getString("PASSWD"));
				data.setName(rs.getString("NAME"));
				data.setMrole(rs.getString("MROLE"));
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

	public boolean insert(MemberDTO memberDTO){
		// [아이디],[비번],[이름]
		// 등급은 무조건 사용자등급

		String driverName = "oracle.jdbc.driver.OracleDriver";

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "C##HURWAN";
		String password = "1234";

		Connection conn = null;

		String SQL_INSERT = "INSERT INTO MEMBER VALUES(?,?,?,'USER')";

		try {
			// 1. 드라이버 로드(적재)
			Class.forName(driverName);

			// 2. DB 연결
			conn = DriverManager.getConnection(url, user, password);

			// 3. DB 사용 : data를 read,write ▶ 서비스(CRUD) 수행
			PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT);
			pstmt.setString(1, memberDTO.getMid());
			pstmt.setString(2, memberDTO.getPasswd());
			pstmt.setString(3, memberDTO.getName());
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

		return true;
	}
	public boolean update(MemberDTO memberDTO){
		// 전체배열중에서
		// [아이디]가 있는지 확인
		// 그 정보의 [이름]변경

		String driverName = "oracle.jdbc.driver.OracleDriver";

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "C##HURWAN";
		String password = "1234";

		Connection conn = null;

		String SQL_UPDATE = "UPDATE MEMBER SET NAME = ? WHERE MID = ?";

		try {
			// 1. 드라이버 로드(적재)
			Class.forName(driverName);

			// 2. DB 연결
			conn = DriverManager.getConnection(url, user, password);



			// 3. DB 사용 : data를 read,write ▶ 서비스(CRUD) 수행
			PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE);
			pstmt.setString(1, memberDTO.getName());
			pstmt.setString(2, memberDTO.getMid());
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

		return true;
	}
	public boolean delete(MemberDTO memberDTO){
		// 전체배열중에서
		// [아이디]가있는지 확인후 삭제

		String driverName = "oracle.jdbc.driver.OracleDriver";

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "C##HURWAN";
		String password = "1234";

		Connection conn = null;

		String SQL_DELETE = "DELETE FROM MEMBER WHERE MID=?";

		try {
			// 1. 드라이버 로드(적재)
			Class.forName(driverName);

			// 2. DB 연결
			conn = DriverManager.getConnection(url, user, password);

			// 3. DB 사용 : data를 read,write ▶ 서비스(CRUD) 수행
			PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE);
			pstmt.setString(1, memberDTO.getMid());
			int result = pstmt.executeUpdate(); // CUD(INSERT,UPDATE,DELETE)
			if(result <=0) {
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

		return true;
	}
}
