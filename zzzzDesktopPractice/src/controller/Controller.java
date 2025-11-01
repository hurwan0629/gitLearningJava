package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.StudentDAO;
import model.StudentDTO;

/**
 * 그냥 view 안만들었습니다.
 * 너무 힘들어서...
 */


public class Controller {
	
	private StudentDAO model;
	private Scanner scanner = new Scanner(System.in);
	public Controller() {
		StudentDAO.prepare();

		model = new StudentDAO();
	}

	public void startApp() {
		
		while(true) {
			
			System.out.println("1. 학생 ID로 검색");
			System.out.println("2. 학생 전체 검색");
			System.out.println("3. 학생 추가");
			System.out.println("4. 학생 삭제");
			System.out.println("5. 학생 점수 수정");
			System.out.println("0. 프로그램 종료");
			System.out.print(" >>> ");
			int command = this.scanner.nextInt();
			this.scanner.nextLine();
			
			if(command == 0) {
				System.out.println("프로그램 종료...");
				break;
			}
			else if(command == 1) {
				StudentDTO studentDTO = new StudentDTO();
				studentDTO.setStudentCondition("SELECTBYID");
				
				StudentDTO data = this.model.selectOne(studentDTO);
				
				System.out.println(data);
			}
			else if(command == 2) {
				ArrayList<StudentDTO> datas = new ArrayList<>();
				
				StudentDTO studentDTO = new StudentDTO();
				studentDTO.setStudentCondition("ALL");
				
				datas = this.model.selectAll(studentDTO);
				
				for(StudentDTO data:datas) {
					System.out.println(data);
				}
			}
			else if(command == 3) {
				StudentDTO studentDTO = new StudentDTO();
				
				System.out.print("학생의 이름을 입력해주세요 >>> ");
				studentDTO.setStudentName(this.scanner.next());
				this.scanner.nextLine();
				
				System.out.print("학생의 나이를 입력해주세요 >>> ");
				studentDTO.setStudentAge(this.scanner.nextInt());
				this.scanner.nextLine();
				
				System.out.print("학생의 점수를 입력해주세요 >>> ");
				studentDTO.setStudentScore(this.scanner.nextInt());
				this.scanner.nextLine();
				
				model.insert(studentDTO);
			}
			else if(command == 4) {
				StudentDTO studentDTO = new StudentDTO();
				
				System.out.print("삭제할 학생의 ID를 입력해주세요 >>> ");
				studentDTO.setStudentId(this.scanner.nextInt());
				this.scanner.nextLine();
				
				model.delete(studentDTO);
			}
			else if (command == 5) {
				StudentDTO studentDTO = new StudentDTO();

				System.out.print("변경할 학생의 ID를 입력해주세요 >>> ");
				studentDTO.setStudentId(this.scanner.nextInt());
				this.scanner.nextLine();
				
				System.out.print("학생의 변경후 점수를 입력해주세요 >>> ");
				studentDTO.setStudentScore(this.scanner.nextInt());
				this.scanner.nextLine();
				
				model.update(studentDTO);
			}
		}
		
		
	}
}
