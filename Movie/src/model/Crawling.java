package model;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawling {
	public static void makeSample(ArrayList<MovieDTO> datas) {
		
        String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=%EC%98%81%ED%99%94&ackey=18ft716e";

        try {

        	Document doc = Jsoup.connect(url).get(); // 연결
        	int MID = 101; // PK 값
            // 영화 블록 단위로 반복
            Elements movieBlocks = doc.select("div.data_box"); // 블럭
            for (Element block : movieBlocks) {
                MovieDTO data = new MovieDTO();

                // 제목 가져오기
                Element titleElem = block.selectFirst("a.this_text._text");
                if (titleElem != null) {
                    data.setTitle(titleElem.text().trim());
                }

                // 개요(장르) 가져오기
                Element dd = block.selectFirst("dl.info_group dt:contains(개요) + dd");
                if (dd != null) { // null 이 아니라면 
                    data.setGenre(dd.text().trim()); // dd에서 텍스트를 꺼내 공백 제거
                }
                data.setTitleId(MID++);

                datas.add(data);
                if (MID > 105) { // 5번만 반복
                	break;
                }
            }
		} catch (IOException e) { // 예외처리
			e.printStackTrace();
		}
	}

}
