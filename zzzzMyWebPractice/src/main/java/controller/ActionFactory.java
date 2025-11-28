package controller;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
	private Map<String, Action> map;
	
	ActionFactory(){
		map = new HashMap<>();
		this.map.put("/writeContent.do", new WriteContentAction());
		this.map.put("/writeContentPage.do", new WriteContentPageAction());
		this.map.put("/viewContentPage.do", new ViewContentPageAction());
		this.map.put("/mainPage.do", new MainPageAction());
	}
	
	public Action getAction(String command) {
		return this.map.get(command);
	}
}
