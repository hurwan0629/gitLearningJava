package model;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawling {
	public static void makeSample(ArrayList<MelonDTO> datas) {
		// 샘플(DTO)을 배열에 채우기
		String url = "https://www.melon.com/";
		try {
			Document doc = Jsoup.connect(url).get();

			int MID = 1001;

			Elements elems = doc.select("a.ellipsis");

			for (Element elem : elems) {
				MelonDTO data = new MelonDTO();
				data.setmId(MID++);
				data.setTitle(elem.text());
				datas.add(data);
				if (MID > 1010) {
					break;
				}
			}
			
			elems = doc.select("div.ellipsis > a.fc_mgray:first-child");

			int i=0;
			for (Element elem : elems) {
				datas.get(i).setName(elem.text());
				i++;
				if(i>=10) {
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("샘플데이터 생성 완료");
		
	}
}
