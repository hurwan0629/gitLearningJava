package controller;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


// ▼ 서블릿 컨테이너에 인식시키는 역할
@WebListener
public class InitListener implements ServletContextListener {
	
    public InitListener() {
    	
    }
    // 연결 끊기 용 메서드
    public void contextDestroyed(ServletContextEvent sce)  { 
    	
    }
    
    // 생성용 메서드
    public void contextInitialized(ServletContextEvent sce)  { 
    		System.out.println("[로그] 서버가 시작될 때를 감지(모니터링)하여 리스너가 자동 수행됨");
    		// 샘플데이터 넣기 좋은 파트
    		// application scope에 datas생성해서 저장
    		ServletContext application = sce.getServletContext();
    		
    		ArrayList<String> datas = new ArrayList<>();
    		datas.add("apple");
    		datas.add("banana");
    		datas.add("kiwi");
    		datas.add("Python");
    		datas.add("Java");
    		
    		
    		application.setAttribute("datas", datas);
    }
}
