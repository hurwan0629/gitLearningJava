package model;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Crawling {
	static public void makeSample(ArrayList<StockDTO> datas) {
		String url = "https://finance.naver.com/sise/lastsearch2.naver";

		try{
			Document doc = Jsoup.connect(url).get();

			Elements elements = doc.select("#contentarea .box_type_l table tbody tr");
			
			int i=0;
			for(Element element : elements){
				String name = element.select("td > a").text();
				String price = element.select("td:nth-of-type(4)").text();

				if(name.isEmpty() || price.isEmpty()){
					continue;
				}
				StockDTO data = new StockDTO();
				data.setName(name);
				data.setPrice(Integer.parseInt(price.replaceAll(",","")));
				datas.add(data);
				if(datas.size() >= 10) {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
