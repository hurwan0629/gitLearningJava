package model.dao;

import java.util.ArrayList;

import model.dto.ProductDTO;

public class ProductDAO {
	private ArrayList<ProductDTO> datas;
	public ProductDAO() {
		this.datas = new ArrayList<>();

		ProductDTO data01 = new ProductDTO();
		data01.setProductId(1001);
		data01.setProductName("모자");
		data01.setPrice(3600);
		data01.setCount(5);
		this.datas.add(data01);
		ProductDTO data02 = new ProductDTO();
		data02.setProductId(1002);
		data02.setProductName("모래시계");
		data02.setPrice(3250);
		data02.setCount(0);
		this.datas.add(data02);
	}

	public ArrayList<ProductDTO> selectAll(ProductDTO productDTO){ 
		ArrayList<ProductDTO> datas = new ArrayList<>();

		if(productDTO.getCondition().equals("ALL")) {
			for(int i=0;i<this.datas.size();i++) {
				ProductDTO data = new ProductDTO();
				data.setProductId(this.datas.get(i).getProductId());
				data.setProductName(this.datas.get(i).getProductName());
				data.setPrice(this.datas.get(i).getPrice());
				data.setCount(this.datas.get(i).getCount());
				datas.add(data);
			}
		}
		else if(productDTO.getCondition().equals("SEARCH")) {
			for(int i=0;i<this.datas.size();i++) {
				if(this.datas.get(i).getProductName().contains(productDTO.getProductName())) {
					ProductDTO data = new ProductDTO();
					data.setProductId(this.datas.get(i).getProductId());
					data.setProductName(this.datas.get(i).getProductName());
					data.setPrice(this.datas.get(i).getPrice());
					data.setCount(this.datas.get(i).getCount());
					datas.add(data);
				}
			}
		}

		return datas;
	}
	public ProductDTO selectOne(ProductDTO productDTO) {
		for(int i=0;i<this.datas.size();i++) {
			if(this.datas.get(i).getProductId() == productDTO.getProductId()) {
				ProductDTO data = new ProductDTO();
				data.setProductId(this.datas.get(i).getProductId());
				data.setProductName(this.datas.get(i).getProductName());
				data.setPrice(this.datas.get(i).getPrice());
				data.setCount(this.datas.get(i).getCount());
				return data;
			}
		}
		return null;
	}

	public boolean insert(ProductDTO productDTO) {
		try {
			ProductDTO data = new ProductDTO();
			data.setProductId(productDTO.getProductId());
			data.setProductName(productDTO.getProductName());
			data.setPrice(productDTO.getPrice());
			data.setCount(productDTO.getCount());
			this.datas.add(data);
		}
		catch(Exception e) {
			return false;
		}
		return true;
	}
	public boolean update(ProductDTO productDTO) {
		if(productDTO.getCondition().equals("ADD")) {
			for(int i=0;i<this.datas.size();i++) {
				if(this.datas.get(i).getProductId() == productDTO.getProductId()) {
					this.datas.get(i).setCount(this.datas.get(i).getCount() + productDTO.getCount());
					return true;
				}
			}
		}
		else if(productDTO.getCondition().equals("PAY")) {
			for(int i=0;i<this.datas.size();i++) {
				if(this.datas.get(i).getProductId() == productDTO.getProductId()) {
					if(this.datas.get(i).getCount() >= productDTO.getCount()) {
						this.datas.get(i).setCount(this.datas.get(i).getCount() - productDTO.getCount());
					}
					else {
						System.out.println(" [로그] ProductDAO.update() - PAY : 재고없음");
						return false;
					}
					return true;
				}
			}
		}
		return false;
	}
	public boolean delete(ProductDTO productDTO) {
		for(int i=0;i<this.datas.size();i++) {
			if(this.datas.get(i).getProductId() == productDTO.getProductId()) {
				this.datas.remove(i);
				return true;
			}
		}
		return false;
	}
}
