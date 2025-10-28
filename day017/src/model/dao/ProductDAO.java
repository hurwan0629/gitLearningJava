package model.dao;

import java.util.ArrayList;

import model.dto.ProductDTO;

public class ProductDAO {
	private ArrayList<ProductDTO> datas;
	public ProductDAO() {
		this.datas = new ArrayList<>();
		
		ProductDTO sample01 = new ProductDTO();
		sample01.setProductCount(4);
		sample01.setProductId("1001");
		sample01.setProductName("존야의 모래시계");
		sample01.setProductPrice(3250);
		
		ProductDTO sample02 = new ProductDTO();
		sample02.setProductCount(0);
		sample02.setProductId("1002");
		sample02.setProductName("라바돈의 마법모자");
		sample02.setProductPrice(3600);
		
		this.datas.add(sample01);
		this.datas.add(sample02);
	}

	public ArrayList<ProductDTO> selectAll(ProductDTO productDTO){ 
		ArrayList<ProductDTO> datas = new ArrayList<>();
		if(productDTO.getProductCondition().equals("ALL")) {
			for(int i=0;i<this.datas.size();i++) {
//				System.out.println(this.datas.get(i));
				ProductDTO data = new ProductDTO();
				data.setProductCount(this.datas.get(i).getProductCount());
				data.setProductPrice(this.datas.get(i).getProductPrice());
				data.setProductId(this.datas.get(i).getProductId());
				data.setProductName(this.datas.get(i).getProductName());
				datas.add(data);
			}
		}
		else if(productDTO.getProductCondition().equals("KEYWORD")) {
			for(int i=0;i<this.datas.size();i++) {
				if(this.datas.get(i).getProductName().contains(productDTO.getProductKeyword())) {
					ProductDTO data = new ProductDTO();
					data.setProductCount(this.datas.get(i).getProductCount());
					data.setProductPrice(this.datas.get(i).getProductPrice());
					data.setProductId(this.datas.get(i).getProductId());
					data.setProductName(this.datas.get(i).getProductName());
					datas.add(data);
				}
			}
		}
		return datas;
	}
	public ProductDTO selectOne(ProductDTO productDTO) {
//		System.out.println(" [로그1] ");
//		System.out.println(this.datas.get(0));
		System.out.println(productDTO);
		for(int i=0;i<this.datas.size();i++) {
			if(this.datas.get(i).getProductId().equals(productDTO.getProductId())) {
				ProductDTO data = new ProductDTO();
				data.setProductCount(this.datas.get(i).getProductCount());
				data.setProductPrice(this.datas.get(i).getProductPrice());
				data.setProductId(this.datas.get(i).getProductId());
				data.setProductName(this.datas.get(i).getProductName());
//				System.out.println(data);
				return data;
			}
		}
		return null;
	}

	public boolean insert(ProductDTO productDTO) {
		try {
			ProductDTO data = new ProductDTO();
			data.setProductCount(productDTO.getProductCount());
			data.setProductPrice(productDTO.getProductPrice());
			data.setProductId(productDTO.getProductId());
			data.setProductName(productDTO.getProductName());
			datas.add(data);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	// 재고 추가
	public boolean update(ProductDTO productDTO) {
		if(productDTO.getProductCondition().equals("ADD")) {
			for(int i=0;i<this.datas.size();i++) {
				if(this.datas.get(i).getProductId().equals(productDTO.getProductId())) {
					this.datas.get(i).setProductCount(this.datas.get(i).getProductCount() + productDTO.getProductCount());
					
					return true;
				}
			}
		}
		else if(productDTO.getProductCondition().equals("PAY")) {
			for(int i=0;i<this.datas.size();i++) {
				if(this.datas.get(i).getProductId().equals(productDTO.getProductId())) {
					if(this.datas.get(i).getProductCount()>=productDTO.getProductCount()) {
						this.datas.get(i).setProductCount(this.datas.get(i).getProductCount() - productDTO.getProductCount());
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
			if(this.datas.get(i).getProductId().equals(productDTO.getProductId())) {
				this.datas.remove(i);
				
				return true;
			}
		}
		return false;
	}
}
