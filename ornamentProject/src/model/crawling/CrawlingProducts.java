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
	public static void CrawlProducts() {
		
		System.out.println("CrawlingProducts.CrawlProducts [로그] start Crawling");
		
		ProductDAO pDAO = new ProductDAO();
		ArrayList<ProductDTO> datas = new ArrayList<>();
		
		
		String url = "https://emart.ssg.com/search.ssg?query=%EC%98%A4%EB%84%88%EB%A8%BC%ED%8A%B8%EC%84%B8%ED%8A%B8";

		try {
			Document doc = Jsoup.connect(url).get();
			
			final String tableClassSelector = "css-1byjpa6";
			Elements tableElements = doc.select("div." + tableClassSelector + " > div");
			int i=0;
			for(Element tableElement : tableElements) {
				String productName =  tableElement.select("div.css-1mrk1dy").text();
				
				String productPriceString = tableElement.select("em.css-1oiygnj").text().substring(4);
				productPriceString= productPriceString.replaceAll(",", "");
				productPriceString = productPriceString.replaceAll("원", "");
//				System.out.println(productPriceString);
				int productPrice = Integer.parseInt(productPriceString);
				
				int productCount = 30;
				
				String productBrand =  tableElement.select("em.chakra-text.css-408eai").text();
				ProductDTO data = new ProductDTO();
				data.setProductName(productName);
				data.setProductPrice(productPrice);
				data.setProductCount(productCount);
				data.setProductBrand(productBrand);
				pDAO.insert(data);
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
