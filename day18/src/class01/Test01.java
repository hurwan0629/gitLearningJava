package class01;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test01 {
	public static void main(String[] args) {
		// 끌어다 쓸 url 주소
		String url = "https://www.melon.com/";
		
		Connection conn = Jsoup.connect(url);
		Document doc = null;
		try {
			doc = conn.get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(doc);
		
//		String title = doc.title();
//		System.out.println(title);
		
		// doc에 스판태그 전부 선택
//		Elements elems = doc.select("span");
//		for(Element elem:elems) {
//			// 태그 포함해서 출력하기
//			System.out.println(elem);
//			// 태그 안의 데이터만 봅아내기
//			System.out.println(elem.text());
//		}
//		
		// a태그에서 href 속성을 가져온다.
//		Elements elems = doc.select("a[href]");
//		
//		for(Element elem:elems) {
//			System.out.println(elem);
//			System.out.println(elem.text());
//			// attribute -> attr() / href부분만 추출해줘 라는 뜻
//			System.out.println(elem.attr("href"));
//		}
		
		Elements elems = doc.select(".ellipsis");
		for(Element elem : elems) {
			System.out.println(elem.text());
		}
	}
}
