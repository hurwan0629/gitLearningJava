package class05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Delete {
public static void delete(int id) {
		
		String driverName = "oracle.jdbc.driver.OracleDriver";
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "C##HURWAN";
		String password = "1234";
		
		Connection conn=null;;
		
		String SQL_DELETE = "DELETE FROM PRODUCT WHERE PRODUCT_ID = ?";
		
		
		try {
			// 1. 드라이버 로드(적재)
			// 드라이버 설치한다고 생각하면 됨
			Class.forName(driverName);
//			System.out.println("드라이버 불러오기 성공");

			// 2. DB 연결
			// url이 뭐지?
			// conn 은 user계정 자체? 아니면 연결자? 라고 생각하면 될듯
			conn = DriverManager.getConnection(url, user, password);
//			System.out.println("DB 연결 성공");
			
			
			
			// DB 사용 : data를 read, write ▶ 서비스(CRUD) 수행

			PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE);
			pstmt.setInt(1, id);
			
			// iterator과 매우 흡사한 구조를 내부적으로 가지고있음
			int rs = pstmt.executeUpdate();
			if(rs>0) {
				System.out.println("삭제 성공!");
			}
			else {
				System.out.println("삭제 실패...");
			}
			
		}
		// driverName 가 존재하지 않는경우 잡아주는 에러
		catch (ClassNotFoundException e) {
			System.out.println("드라이버가 불러오기 실패");
			e.printStackTrace();
		}catch (SQLException e) {
			System.out.println("DB 연결 실패");
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
