package view;

public class AdminView extends View {
	
	public int getProductPrice() {
		System.out.print("상품가격입력 >> ");
		int price = View.scanner.nextInt();
		return price;
	}
}
