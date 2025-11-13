package abstractClassAndInterface.dto;

public class ProductDTO {
	// 상품 클래스
	private int productPk;
	private String productName;
	private int productCount;
	private int productPrice;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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
	@Override
	public String toString() {
		return "ProductDTO [productPk=" + productPk + ", productName=" + productName + ", productCount=" + productCount
				+ ", productPrice=" + productPrice + "]";
	}
	
	
	
}
