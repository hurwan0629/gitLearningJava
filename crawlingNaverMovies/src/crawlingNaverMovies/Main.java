package crawlingNaverMovies;

import java.io.IOException;
import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
	public static void main(String[] args) {
		String url = "https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&qvt=0&query=%ED%98%84%EC%9E%AC%EC%83%81%EC%98%81%EC%98%81%ED%99%94";
		try {
			Document doc = Jsoup.connect(url).get();
			
			Elements elems = doc.select("div.card_area._panel > div.card_item");
			System.out.println(elems.size());
			 for(Element elem : elems) {
				Elements data = elem.select("div.data_area");
//				System.out.println(data);
				// 제목
				String title = data.select("div.area_text_box > a.this_text._text").text();
				System.out.println(title);
				
				// 개요
				String info = data.select("dl.info_group:first-child > dd:nth-child(2)").text();
				System.out.println(info);
				
				// 개봉
				// String openDate = data.select("dl.info_group:nth-child(2) > dd:first-child").text();
				String openDate = data.select("dl.info_group:nth-child(2) > dd:nth-of-type(1)").text();
				
				System.out.println(openDate);
				
				// 별점
				String stars = data.select("dl.info_group:nth-child(2) > dd:nth-of-type(2)").text();
				System.out.println(stars);
				
				// 출현
				String appearance = data.select("dl.info_group:nth-child(3) > dd.type_ellip_2._ellipsis").text();
				System.out.println(Arrays.toString(appearance.split(", ")));
				// break;
				System.out.println();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
