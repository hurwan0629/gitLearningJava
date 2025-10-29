package model;

import java.util.ArrayList;

public class StockDAO {
	private ArrayList<StockDTO> datas;

	public StockDAO() {
		this.datas = new ArrayList<>();

		Crawling.makeSample(this.datas);
		
		int num=1001;
		for(int i=0;i<this.datas.size();i++) {
			this.datas.get(i).setId(num++);
		}
	}

	public ArrayList<StockDTO> selectAll(StockDTO stock){
		return new ArrayList<>(this.datas);
	}

	public StockDTO selectOne(StockDTO stock){
		for(StockDTO dto : this.datas){
			if(dto.getName().equals(stock.getName())){
				return dto;
			}
		}
		return null;
	}

	public boolean insert(StockDTO stock){
		try{
			StockDTO dto = new StockDTO();
			dto.setName(stock.getName());
			dto.setPrice(stock.getPrice());
			//
			dto.setId(stock.getId());
			this.datas.add(dto);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	public boolean update(StockDTO stock){
		for(int i = 0; i < this.datas.size(); i++){
			if(this.datas.get(i).getName().equals(stock.getName())){
				this.datas.get(i).setPrice(stock.getPrice());
				return true;
			}
		}
		return false;
	}

	public boolean delete(StockDTO stock){
		for(int i = 0; i < this.datas.size(); i++){
			if(this.datas.get(i).getName().equals(stock.getName())){
				this.datas.remove(i);
				return true;
			}
		}
		return true;
	}
}
