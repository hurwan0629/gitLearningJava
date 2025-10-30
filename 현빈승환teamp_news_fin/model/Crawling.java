package model;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Crawling {
    public static void makeSample(ArrayList<NewsDTO> datas) {
    	
    	/*
    	NewsDTO sample = new NewsDTO();
    	sample.setId(1000);
    	sample.setTitle("뉴스 제목");
    	sample.setUrl("https://news.naver.com");
    	sample.setPress("언론사");
        sample.setDate("4444-44-44");
        sample.setSummary("요약");
        datas.add(sample);
        */
        
        try {
            Document doc = Jsoup.connect("https://news.naver.com/").get();
            Elements newsElems = doc.select("a.cnf_news"); // 뉴스 제목 + 링크
            Elements pressElems = doc.select("div.cnf_press"); // 언론사
            Elements dateElems = doc.select("span.cnf_date"); // 날짜
            /// doc.select() > 원하는 태그만 추출하기
            int maxNews = 5;

            for (int i = 0; i < maxNews && i < newsElems.size(); i++) {
                NewsDTO news = new NewsDTO(); // 새로운 DTO 객체 생성
                news.setId(1001 + i);  // 임의 ID를 1001 부터 설정
                news.setTitle(newsElems.get(i).text());  // 뉴스 제목 설정
                news.setUrl(newsElems.get(i).attr("href"));  // 뉴스 링크 설정
                if (i < pressElems.size()) news.setPress(pressElems.get(i).text());  // 언론사정보가 있으면 DTO에 설정
                if (i < dateElems.size()) news.setDate(dateElems.get(i).text());  // 날짜 정보가 있으면 DTO 에 설정
                datas.add(news);  // DTO를 리스트에 추가
            }
        } catch (IOException e) { // 예외 발생 시 
            e.printStackTrace();
        }

        System.out.println("[로그] 뉴스 샘플 데이터 추가 완료");
    }
}
