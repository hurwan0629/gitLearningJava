package controller;

import model.crawling.CrawlingProducts;
import model.dao.BagDAO;
import model.dao.MemberDAO;
import model.dao.ProductDAO;
import model.dto.MemberDTO;
import view.View;

public class Controller {
	private ProductDAO productDAO;
	private MemberDAO memberDAO;
	private BagDAO bagDAO;
	private View view;
	
	private MemberDTO userInfo; 
	
	public Controller() {
		
	}
	public void startApp() {
		// CrawlingProducts.CrawlProducts();
	}
}
