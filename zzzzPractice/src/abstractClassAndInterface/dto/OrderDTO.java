package abstractClassAndInterface.dto;

public class OrderDTO {
	private int orderPk;
	private int orderProductPk;
	private int orderProductCount;
	public int getOrderPk() {
		return orderPk;
	}
	public void setOrderPk(int orderPk) {
		this.orderPk = orderPk;
	}
	public int getOrderProductPk() {
		return orderProductPk;
	}
	public void setOrderProductPk(int orderProductPk) {
		this.orderProductPk = orderProductPk;
	}
	public int getOrderProductCount() {
		return orderProductCount;
	}
	public void setOrderProductCount(int orderProductCount) {
		this.orderProductCount = orderProductCount;
	}
	@Override
	public String toString() {
		return "OrderDTO [orderPk=" + orderPk + ", orderProductPk=" + orderProductPk + ", orderProductCount="
				+ orderProductCount + "]";
	}
}
