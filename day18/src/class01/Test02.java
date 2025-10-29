package class01;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test02 {
	public static void main(String[] args) {
		ArrayList<MelonDTO> datas = new ArrayList<>();
		
		String url = "https://www.melon.com/";
		try {
			Document doc = Jsoup.connect(url).get();
			
			int i=1;
			Elements elems = doc.select("a.ellipsis");
			for(Element elem:elems) {
				if(i>10) {
					break;
				}
//				System.out.println(i + ". " +elem.text());
				
				MelonDTO data = new MelonDTO();
				data.setTitle(elem.text());
				datas.add(data);
				
				i++;
			}
			i=1;
			
			elems = doc.select("div.ellipsis > a.fc_mgray:first-child");
			for(Element elem : elems) {
				if(i>10) {
					break;
				}
//				System.out.println(i + ". " +elem.text());
				datas.get(i-1).setName(elem.text());
				i++;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(MelonDTO data:datas) {
			System.out.println(data);
		}
		
		
	}
}
