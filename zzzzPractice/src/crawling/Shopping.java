package crawling;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Shopping {
	public static void main(String[] args) {
//		에그드롭 url
//		String url = "https://eggdrop.com/";
		
		// 서브웨이 url
		String url = "http://subway.co.kr/";
		
		try {
			Document doc = Jsoup.connect(url).get();
			System.out.println(doc);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
