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
	public static void main(String[] args) {
		crawlProducts();
	}
	public static void crawlProducts() {
		
		System.out.println("CrawlingProducts.CrawlProducts [로그] start Crawling");
		
		ProductDAO pDAO = new ProductDAO();
		ArrayList<ProductDTO> datas = new ArrayList<>();
		
		// 이마트 몰 url
		String url = "https://emart.ssg.com/search.ssg?query=%EC%98%A4%EB%84%88%EB%A8%BC%ED%8A%B8%EC%84%B8%ED%8A%B8";

		try {
			Document doc = Jsoup.connect(url).get();
			
			// 각 물품 리스트 속 상품 데이터 박스들
			final String tableClassSelector = "css-1byjpa6";
			Elements tableElements = doc.select("div." + tableClassSelector + " > div");
			int i=0;
			for(Element tableElement : tableElements) {
				// 상품 이름 추출
				String productName =  tableElement.select("div.css-1mrk1dy").text();
				// 상품 가격 추출후 정제
				String productPriceString = tableElement.select("em.css-1oiygnj").text().substring(4);
				productPriceString= productPriceString.replaceAll(",", "");
				productPriceString = productPriceString.replaceAll("원", "");
				int productPrice = Integer.parseInt(productPriceString);
				
				// 상품 초기화 30 개
				int productCount = 30;
				
				// 상품 브랜드 추출
				String productBrand =  tableElement.select("em.chakra-text.css-408eai").text();
				
				// 상품 DTO제작
				ProductDTO data = new ProductDTO();
				data.setProductName(productName);
				data.setProductPrice(productPrice);
				data.setProductCount(productCount);
				data.setProductBrand(productBrand);
				
				// DB에 상품 넣기
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
