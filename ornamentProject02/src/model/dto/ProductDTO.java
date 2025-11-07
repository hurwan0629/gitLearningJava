package model.dto;

public class ProductDTO {// 상품DTO
	private int productPK; // 상품번호
	private String productName; // 상품이름
	private int productPrice; // 상품가격
	private int productCount; // 상품재고
	private String productBrand; // 상품브랜드
	
	private String condition; // 분기점
	private String keyword; // 상품 검색어


	// getter / setter
	public int getProductPK() {
		return productPK;
	}
	public void setProductPK(int productPK) {
		this.productPK = productPK;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	public String getProductBrand() {
		return productBrand;
	}
	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	// toString
	@Override
	public String toString() {
		return "ProductDTO [productPK=" + productPK + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", productCount=" + productCount + ", procuctBrand=" + productBrand + "]";
	}
}
