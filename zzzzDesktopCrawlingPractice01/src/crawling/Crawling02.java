package crawling;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawling02 {
	public static void main(String[] args) {
		// 트리나라 메인 페이지
		String mainUrl =  "https://treenara.co.kr/index.html";
		String[] shopUrl = 
			{
		        "https://treenara.co.kr/product/list.html?cate_no=4",            
		        "https://treenara.co.kr/product/list.html?cate_no=30",
		        "https://treenara.co.kr/product/list.html?cate_no=24",
		        "https://treenara.co.kr/product/list.html?cate_no=126",
		        "https://treenara.co.kr/product/list.html?cate_no=12",
		        "https://treenara.co.kr/product/list.html?cate_no=7"
			};
		try {
			for(int i=0;i<shopUrl.length;i++) {
				Document doc = Jsoup.connect(shopUrl[i]).get();

				Elements elems = doc.select("div.row > div[id^=anchorBoxId_]");
				for(Element elem:elems) {
					String productName = elem.select("p.name a span:not(.title)").text();
					if(!productName.isEmpty()) {
						System.out.println("상품명: " + productName);
					}
					// 일단 판매가 위주로 뽑았는데 판매가 없고 소비자가만 있으면 소비자가를 뽑음
					Elements productPrice = elem.select("ul.xans-element-.xans-product.xans-product-listitem > li:last-of-type");
					if(!productPrice.isEmpty()) {
						System.out.print("가격: ");
						//System.out.println(productPrice);
						System.out.println(Integer.parseInt(productPrice.text().replace(" ", "").replace("판매가:","").replace("소비자가:","").replace(",","").replace("원", "")));
						System.out.println();
					}
				}
				System.out.println("----------------------------------");
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
