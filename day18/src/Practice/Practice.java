package Practice;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Practice {
	public static void main(String[] args) {
		
		String url = "https://finance.naver.com/sise/";
		try {
			Document doc = Jsoup.connect(url).get();
			
			int i=1;
			Elements elems = doc.select("ul#popularItemList > li ");
//			System.out.println(elems);
			
			for(Element elem : elems) {
				System.out.println(elem.text().substring(0, elem.text().length()-2));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
