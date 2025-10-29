package controller;

import java.util.ArrayList;

import model.StockDAO;
import model.StockDTO;
import view.View;

public class Controller {
	
	private View view;
	private StockDAO model;

	
	public Controller(){
		view = new View();
		model = new StockDAO();
	}
	
	public void startApp() {
		int PK=1011;
		
		
		while(true) {
			view.printMenu();
			
			int command = view.getMenuNum();
			
			// 프로그램 종료
			if(command == 0) {
				 view.printTurnOff();
				break;
			}
			// 종목 추가하기
			else if (command == 1) {
				StockDTO stockDTO = this.view.getStockName();
				// 만약 이미 존재하는 주식 이름이라면
				if(this.model.selectOne(stockDTO) != null) {
					view.printFail();
					continue;
				}
				stockDTO.setId(PK++);
				// 가격 DTO에 넣기
				stockDTO.setPrice(view.getStockPrice().getPrice());
				// 만약 중복이 아니라면 주가 입력받기
				// 성공하면
				if(!this.model.insert(stockDTO)) {
					this.view.printFail();
					continue;
				}
				// 성공하면
				this.view.printSuccess();
			} 
			// 전체 종목 출력
			else if (command == 2) {
				// 컨디션 지금은 필요 없어서 DTO만 만들어놓기
				StockDTO stockDTO = new StockDTO();
				
				this.view.printAllStock(this.model.selectAll(stockDTO));
			} 
			// 종목명 검색
			else if (command == 3) {
				// 컨디션 지금은 필요 없어서 DTO만 만들어놓기
				StockDTO stockDTO = new StockDTO();;
				
				ArrayList<StockDTO> datas = this.model.selectAll(stockDTO);
				
				// 종목 이름들어있는 DTO 만들기
				stockDTO = this.view.getStockNameFromList(datas);
				
				// DTO에 주가도 함께 넣어주기
				stockDTO = this.model.selectOne(stockDTO);
				
				this.view.printStock(stockDTO);

			} 
			// 종목 제거
			else if (command == 4) {
				
				// 깡통 DTO 만들기
				StockDTO stockDTO = new StockDTO();
						
				// 전체 출력하기
				this.view.printAllStock(this.model.selectAll(stockDTO));
				
				// 이름 넣은 DTO 받기
				stockDTO = this.view.getStockNameFromList(this.model.selectAll(stockDTO));
				
				// 삭제에 실패했다면
				if(!this.model.delete(stockDTO)){
					this.view.printFail();
					continue;
				}
				// 삭제에 성공했다면
				this.view.printSuccess();
			} 
			// 종목 주가 변경
			else if (command == 5) {

				// 깡통 DTO 만들기
				StockDTO stockDTO = new StockDTO();
						
				// 전체 출력하기
				this.view.printAllStock(this.model.selectAll(stockDTO));
				
				// 이름 넣은 DTO 받기
				stockDTO = this.view.getStockNameFromList(this.model.selectAll(stockDTO));
				
				// 동일한 이름의 종목이 존재하지 않는다면
				if(this.model.selectOne(stockDTO)==null) {
					this.view.printFail();
				}
				// 동일한 이름의 종목이 존재한다면
				StockDTO priceStockDTO = this.view.getStockPrice();
				stockDTO.setPrice(priceStockDTO.getPrice());
				
				// 업데이트에 실패했다면
				if(!this.model.update(stockDTO)) {
					this.view.printFail();
					continue;
				}
				this.view.printSuccess();
			}			
		}
	}
}
