package client;

import controller.NewsController;

public class Client {
	public static void main(String[] args) {
		
		NewsController app = new NewsController();
		app.startApp();
	}
}