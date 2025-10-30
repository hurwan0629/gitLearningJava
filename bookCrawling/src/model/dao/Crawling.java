package model.dao;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.dto.BookDTO;

public class Crawling {

	public static void main(String[] args) {
		String url = "https://ys-dl.tistory.com/entry/%EC%84%9C%EC%9A%B8-%EC%9E%90%EA%B0%80%EC%97%90-%EB%8C%80%EA%B8%B0%EC%97%85-%EB%8B%A4%EB%8B%88%EB%8A%94-%EA%B9%80-%EB%B6%80%EC%9E%A5-%EC%9D%B4%EC%95%BC%EA%B8%B0-%EC%B4%AC%EC%98%81%EC%A7%80-10%EA%B3%B3%EC%9D%84-%EC%95%8C%EB%A0%A4%EB%93%9C%EB%A6%BD%EB%8B%88%EB%8B%A4%EC%97%85%EB%8D%B0%EC%9D%B4%ED%8A%B8%EC%A4%91";
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			System.out.println(doc);
			// 크롤링 해서
//			Elements datas = doc.select("ol.prod_list");

			// 하나씩 요소 넣기
//			for (Element data : datas) {
//				System.out.println(data);
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		

	}

	public static ArrayList<BookDTO> crawl(String url) {
		ArrayList<BookDTO> list = new ArrayList<>();
		return null;
	}
}
