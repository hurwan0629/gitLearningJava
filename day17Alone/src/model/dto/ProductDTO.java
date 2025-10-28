package model.dto;

public class ProductDTO {
	private String productName;
	private	int productId;
	private int productCount;
	private	int productPrice;
	private String productCondition;
	private String productKeyword;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductCondition() {
		return productCondition;
	}
	public void setProductCondition(String productCondition) {
		this.productCondition = productCondition;
	}
	public String getProductKeyword() {
		return productKeyword;
	}
	public void setProductKeyword(String productKeyword) {
		this.productKeyword = productKeyword;
	}
	@Override
	public String toString() {
		return "ProductDTO [productName=" + productName + ", productId=" + productId + ", productCount=" + productCount
				+ ", productPrice=" + productPrice + "]";
	}
	
	
	
}
