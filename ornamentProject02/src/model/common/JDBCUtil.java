package model.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Util류 클래스들이 대체적으로 static(객체와 무관하게) 메서드를 로직으로 품고있어서 메서드만 호출하여 사용하는 경우가 多
public class JDBCUtil {
	/*
	private static final String driverName = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String user = "C##HURWAN";
	private static final String password = "1234";
	 */
	
	private static final String driverName = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String user = "C##HURWAN";
	private static final String password = "1234";
	
	
	// 1,2 ▶ 함수화,모듈화,컴포넌트화
	public static Connection connect() {
		Connection conn = null;
		try {
			// 1. 드라이버 로드(적재)
			Class.forName(driverName);
			// 2. DB 연결
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return conn;
	}
	
	// 4 ▶ 함수화,모듈화,컴포넌트화
	public static void disconnect(Connection conn,PreparedStatement pstmt) {
		try {
			// 4. DB 연결 해제
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}