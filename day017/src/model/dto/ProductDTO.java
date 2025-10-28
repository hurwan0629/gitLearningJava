package model.dto;

public class ProductDTO {
	private String productId;
	private String productName;
	private int productPrice;
	private int productCount;
	private String productCondition;
	private String productKeyword;
	
	
	public String getProductKeyword() {
		return productKeyword;
	}
	public void setProductKeyword(String productKeyword) {
		this.productKeyword = productKeyword;
	}
	public String getProductCondition() {
		return productCondition;
	}
	public void setProductCondition(String productCondition) {
		this.productCondition = productCondition;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
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
	@Override
	public String toString() {
		return "ProductDTO [productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice + ", productCount="
				+ productCount + "]";
	}
}
