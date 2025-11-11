package model.crawling;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.dao.ProductDAO;
import model.dto.ProductDTO;

public class CrawlingProducts {
	public static void crawlProducts() {
		ProductDAO pDAO = new ProductDAO();
		
		ProductDTO pDTO = new ProductDTO();
		pDTO.setCondition("ALL_DESC");
		if(pDAO.selectAll(pDTO).size()>0) {
			System.out.println("CrawlingProducts [로그] 데이터가 1개 이상 존재합니다.");
			return;
		}
		
		
		System.out.println("CrawlingProducts.CrawlProducts [로그] start Crawling");
		
		// 이마트 몰 url
		final String EmallUrl = "https://emart.ssg.com/search.ssg?query=%EC%98%A4%EB%84%88%EB%A8%BC%ED%8A%B8%EC%84%B8%ED%8A%B8";

		try {
			Document doc = Jsoup.connect(EmallUrl).get();
			
			// 각 물품 리스트 속 상품 데이터 박스들
// 1번 선택자
			final String tableClassSelector = "css-1byjpa6";
			Elements tableElements = doc.select("div." + tableClassSelector + " > div");
			int i=0;
			for(Element tableElement : tableElements) {
				// 상품 이름 추출
				final String productNameSelector = "div.css-1mrk1dy"; 
// 2번 선택자			
				String productName =  tableElement.select(productNameSelector).text();
				// 상품 가격 추출후 정제
// 3번 선택자
				final String productPriceSelector = "em.css-1oiygnj"; 
				String productPriceString = tableElement.select(productPriceSelector).text().substring(4);
				productPriceString= productPriceString.replaceAll(",", "");
				productPriceString = productPriceString.replaceAll("원", "");
				int productPrice = Integer.parseInt(productPriceString);
				
				// 상품 초기화 30 개
				int productCount = 30;

// 4번 선택자
				// 상품 브랜드 추출
				final String productBrandSelector = "em.chakra-text.css-408eai"; 
				String productBrand =  tableElement.select(productBrandSelector).text();
				
				// 상품 DTO제작
				ProductDTO data = new ProductDTO();
				data.setProductName(productName);
				data.setProductPrice(productPrice);
				data.setProductCount(productCount);
				data.setProductBrand(productBrand);
				
				// DB에 상품 넣기
				System.out.println("CrawlingProducts.CrawlProducts [로그] " + (i+1) + "번 샘플 생성 전");
				pDAO.insert(data);
				
				// 40개 까지만 insert
				if(i++>=40) {
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("CrawlingProducts.CrawlProducts [로그] finish Crawling");
	}
}
