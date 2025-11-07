package model.dto;

public class BagDTO {
	private int bagPk;
	private int memberPk;
	private int productPk;
	private int productCount;
	private int productPrice;
	private String productBrand;
	private String productName;
	
	
	
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductBrand() {
		return productBrand;
	}
	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getBagPk() {
		return bagPk;
	}
	public void setBagPk(int bagPk) {
		this.bagPk = bagPk;
	}
	public int getMemberPk() {
		return memberPk;
	}
	public void setMemberPk(int memberPk) {
		this.memberPk = memberPk;
	}
	public int getProductPk() {
		return productPk;
	}
	public void setProductPk(int productPk) {
		this.productPk = productPk;
	}
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	@Override
	public String toString() {
		return "BagDTO [bagPk=" + bagPk + ", memberPk=" + memberPk + ", productPk=" + productPk + ", productCount="
				+ productCount + ", productPrice=" + productPrice + ", productBrand=" + productBrand + ", productName="
				+ productName + "]";
	}
	
	
}
