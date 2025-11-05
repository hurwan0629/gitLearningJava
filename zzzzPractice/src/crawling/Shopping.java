package crawling;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Shopping {
	public static void main(String[] args) {
		String url = "https://emart.ssg.com/search.ssg?query=%EC%98%A4%EB%84%88%EB%A8%BC%ED%8A%B8%EC%84%B8%ED%8A%B8";
		
		try {
			Document doc = Jsoup.connect(url).get();
//			System.out.println(doc);
			String css1 = "css-3ns3mb";
			Elements datas1 = doc.select("div." + css1 + "> em");
			
			String css2 = "css-1mrk1dy";
			Elements datas2 = doc.select("div." + css2);
			for(int i=0;i<datas1.size();i++) {
				System.out.println("제조사? " + datas1.get(i).text());
				System.out.println((i+1) + "상품명: " + datas2.get(i).text());
				System.out.println();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
