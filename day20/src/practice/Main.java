package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		String driverName = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "C##HURWAN";
		String password = "1234";
		Connection conn = null;
		try {
			// 드라이버 로드
			Class.forName(driverName);
			System.out.println("로드 성공");
			
			// 드라이버 실행
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("드라이버 실행 성공");
			
			System.out.println("쿼리문을 입력하시오");
			
			
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM PRODUCT ORDER BY PRODUCT_ID ASC");
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				System.out.println("ID: " + result.getInt("PRODUCT_ID"));
				System.out.println("NAME: " + result.getString("NAME"));
				System.out.println("PRICE: " + result.getInt("PRICE"));
				System.out.println("COUNT: " + result.getInt("COUNT"));
			}
			System.out.println("====================");
			
			
			
			System.out.println("You can only INSERT, UPDATE, DELETE");
			String SQL = new Scanner(System.in).nextLine();
			pstmt = conn.prepareStatement(SQL);
			int rs = pstmt.executeUpdate();
			if(rs <= 0) {
				System.out.println("Nothing changed");
			}
			else {
				System.out.println(rs + " changed");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			System.out.println("SQL ERROR CATCHED \nTRY AGAING");
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
