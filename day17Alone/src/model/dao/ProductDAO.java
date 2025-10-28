package model.dao;

import java.util.ArrayList;

import model.dto.ProductDTO;

public class ProductDAO {
	private ArrayList<ProductDTO> datas;
	
	public ProductDAO() {
		this.datas = new ArrayList<>();
	}
	
	
	
	public ArrayList<ProductDTO> selectAll(ProductDTO productDTO) {
		ArrayList<ProductDTO> datas = new ArrayList<>();
		
		if(productDTO.getProductCondition().equals("ALL")) {
			for(ProductDTO data:this.datas) {
				ProductDTO pDTO = new ProductDTO();
				pDTO.setProductId(data.getProductId());
				pDTO.setProductName(data.getProductName());
				pDTO.setProductCount(data.getProductCount());
				pDTO.setProductPrice(data.getProductPrice());
				datas.add(pDTO);
			}
		}
		else if(productDTO.getProductCondition().equals("KEYWORD")) {
			// 상품 검색
			for(ProductDTO data:this.datas) {
				if(data.getProductName().contains(productDTO.getProductKeyword())){
					ProductDTO pDTO = new ProductDTO();
					pDTO.setProductId(data.getProductId());
					pDTO.setProductName(data.getProductName());
					pDTO.setProductCount(data.getProductCount());
					pDTO.setProductPrice(data.getProductPrice());
					datas.add(pDTO);
				}
			}
		}
		
		return datas;
	}

	public ProductDTO selectOne(ProductDTO productDTO) {
		return null;
	}

	
	public boolean insert(ProductDTO productDTO) {
		return false;
	}

	public boolean update(ProductDTO productDTO) {
		return false;
	}

	public boolean delete(ProductDTO productDTO) {
		return false;
	}

}
