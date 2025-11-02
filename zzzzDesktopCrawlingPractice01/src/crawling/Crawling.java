package crawling;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Crawling {
	// 무엇을 크롤링해볼까
	// 유튜브는 좀 안떙기는거같고
	// 크롤링은 웹 페이지를 한번에 땡겨오는 거니까 뉴스가 좋으려나
	// 경제뉴스같은거 떙겨올 수 있나
	public static void main(String[] args) {
		String chosunEconomyMoneyUrl = "https://www.chosun.com/economy/money/";
		
		Document doc = null;
		try {
			doc = 
					Jsoup.connect(chosunEconomyMoneyUrl).get();
		} catch (IOException e) {
			System.out.println("url 문제");
			e.printStackTrace();
		}	
		System.out.println(doc);
		// 가져와 지지 않는다.
		// 차라리 react에서 html, css를 공부하는게 나을수도
	}	
}
