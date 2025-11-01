package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentDAO {
	private static String driverName = "oracle.jdbc.driver.OracleDriver";

	private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String user = "C##HURWAN";
	private static String password = "1234";

	private static Connection conn = null;

	private static Scanner scanner = new Scanner(System.in);

	private ArrayList<StudentDTO> datas;
	
	
	public static void prepare() {
		try {
			Class.forName(StudentDAO.driverName);
			System.out.println("드라이버 로딩 완료");

			conn = DriverManager.getConnection(StudentDAO.url, StudentDAO.user, StudentDAO.password);
			System.out.println("드라이버 연결 완료");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
			return;
		} catch (SQLException e) {
			System.out.println("드라이버 연결 실패");
			e.printStackTrace();
			return;
		}
	}
	
	// condition: 
	//	SELECTBYID: ID로 검색
	public static StudentDTO selectOne(StudentDTO studentDTO) {
		StudentDTO data = new StudentDTO();
		if(studentDTO.getStudentCondition().equals("SELECTBYID")) {
			String SQL_SELECT_ONE = "SELECT STUDENT_ID, NAME, AGE, SCORE FROM "
					+ "STUDENT WHERE STUDENT_ID = ?";
			try {
				PreparedStatement pstmt = StudentDAO.conn.prepareStatement(SQL_SELECT_ONE);
				
				// 문자 예외처리 생략
				System.out.print("STUDENT_ID = ");
				int student_id = StudentDAO.scanner.nextInt();
				// ▼ 이거 쓸필요 없나?
				StudentDAO.scanner.nextLine();
				
				pstmt.setInt(1, student_id);
				
				ResultSet rs = pstmt.executeQuery();

				// data 저장
				while(rs.next()){
					data.setStudentId(rs.getInt("STUDENT_ID"));
					data.setStudentAge(rs.getInt("AGE"));
					data.setStudentScore(rs.getInt("SCORE"));
					data.setStudentName(rs.getString("NAME"));
				}
			} catch (SQLException e) {
				System.out.println("명령어 구문 문제");
				e.printStackTrace();
			}
		}
		
		return data;
	}
	
	// condition: 
	//	ALL: 전체선택
	public static ArrayList<StudentDTO> selectAll(StudentDTO studentDTO) {
		ArrayList<StudentDTO> datas = new ArrayList<>();
		
		if(studentDTO.getStudentCondition().equals("ALL")) {
			
			String SQL_SELECT_ONE = "SELECT STUDENT_ID, NAME, AGE, SCORE FROM "
					+ "STUDENT ORDER BY STUDENT_ID ASC";
			try {
				PreparedStatement pstmt = StudentDAO.conn.prepareStatement(SQL_SELECT_ONE);
				
				ResultSet rs = pstmt.executeQuery();

				// datas 에저장
				while(rs.next()){
					StudentDTO data = new StudentDTO();
					data.setStudentId(rs.getInt("STUDENT_ID"));
					data.setStudentAge(rs.getInt("AGE"));
					data.setStudentScore(rs.getInt("SCORE"));
					data.setStudentName(rs.getString("NAME"));
					
					datas.add(data);
				}
			} catch (SQLException e) {
				System.out.println("명령어 구문 문제");
				e.printStackTrace();
			}
		}
		return datas;
	}

	public static boolean insert(StudentDTO studentDTO) {
		
		String SQL_INSERT = "INSERT INTO STUDENT VALUES(STUDENT_ID_SEQ.NEXTVAL, ?, ?, ?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT);
			
			pstmt.setString(1, studentDTO.getStudentName());
			pstmt.setInt(2, studentDTO.getStudentAge());
			pstmt.setInt(3, studentDTO.getStudentScore());
			
			int rs = pstmt.executeUpdate();
			
			if(rs <= 0) {
				System.out.println("미확인 오류 / 추가 실패");
				return false;
			}
			System.out.println("생성 성공!");
			
		} catch (SQLException e) {
			System.out.println("명령어 구문 문제");
			e.printStackTrace();
		}
		return true;
	}

	public static boolean update(StudentDTO studentDTO) {
		
		String SQL_UPDATE = "UPDATE STUDENT SET SCORE = ? WHERE STUDENT_ID = ?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE);
			
			pstmt.setInt(1, studentDTO.getStudentScore());
			pstmt.setInt(2, studentDTO.getStudentId());
			
			int rs = pstmt.executeUpdate();
			
			if(rs <= 0) {
				System.out.println("미확인 오류 / 업데이트 실패");
				return false;
			}
			System.out.println("변경 성공!");
			
		} catch (SQLException e) {
			System.out.println("명령어 구문 문제");
			e.printStackTrace();
		}
		return true;
	}

	public static boolean delete(StudentDTO studentDTO) {
		String SQL_DELETE = "DELETE FROM STUDENT WHERE STUDENT_ID = ?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE);
			
			pstmt.setInt(1, studentDTO.getStudentId());
			
			int rs = pstmt.executeUpdate();
			
			if(rs <= 0) {
				System.out.println("미확인 오류 / 삭제 실패");
				return false;
			}
			System.out.println("삭제 성공!");
		} catch (SQLException e) {
			System.out.println("명령어 구문 문제");
			e.printStackTrace();
		}
		
		return false;
	}
}
