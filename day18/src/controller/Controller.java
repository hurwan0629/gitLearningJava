package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.MelonDAO;
import model.MelonDTO;

public class Controller {
	private MelonDAO mDAO;
	
	public Controller() {
		this.mDAO = new MelonDAO();
	}
	public void startApp() {
		while(true) {
			
			// 커맨드 입력받기
			System.out.print(" 메뉴 입력 >>> ");
			int command = new Scanner(System.in).nextInt();
			
			if(command == 0) {
				break;
			}
			else if(command == 1) {
				ArrayList<MelonDTO> datas = mDAO.selectAll(null);
				for(MelonDTO data:datas) {
					System.out.println(data);
				}
			}
		}
	}
}
