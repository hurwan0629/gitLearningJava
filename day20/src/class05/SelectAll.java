package class05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectAll {
	public static void selectAll() {

		String driverName = "oracle.jdbc.driver.OracleDriver";
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "C##HURWAN";
		String password = "1234";
		
		Connection conn=null;;
		
		String SQL_SELECT_ALL = "SELECT * FROM PRODUCT ORDER BY PRODUCT_ID ASC";
		
		try {
			// 1. 드라이버 로드(적재)
			// 드라이버 설치한다고 생각하면 됨
			Class.forName(driverName);

			// 2. DB 연결
			// url이 뭐지?
			// conn 은 user계정 자체? 아니면 연결자? 라고 생각하면 될듯
			conn = DriverManager.getConnection(url, user, password);
			
			// DB 사용 : data를 read, write ▶ 서비스(CRUD) 수행
			
			PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			
			System.out.println("=== PRODUCT LIST ===");
			while(rs.next()) {
				int id = rs.getInt("PRODUCT_ID");
				String name = rs.getString("NAME");
				int price = rs.getInt("PRICE");
				int count = rs.getInt("COUNT");
				System.out.println("id: " + id + ", name: " + name + ", price: " + price + ", count: " + count);
			}
			System.out.println("====================");
		} 
		// driverName 가 존재하지 않는경우 잡아주는 에러
		catch (ClassNotFoundException e) {
			System.out.println("드라이버가 불러오기 실패");
			e.printStackTrace();
		}catch (SQLException e) {
			System.out.println("SQL 연결 또는 구문 에러");
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
