package model;

public class StockDTO {
	private String name;
	private int price;
	
	// 이거 id 추가로 만들었습니다.
	private int id;
	
	@Override
	public String toString() {
		return "StockDTO{" +
				"name='" + name + '\'' +
				", price=" + price +
				'}';
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
