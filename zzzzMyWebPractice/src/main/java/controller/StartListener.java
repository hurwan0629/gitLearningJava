package controller;

import java.util.ArrayList;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class StartListener
 *
 */
@WebListener
public class StartListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public StartListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    		ArrayList<String> datas = new ArrayList<>();
    		datas.add("사과");
    		datas.add("바나나");
    		datas.add("키위");
    		sce.getServletContext().setAttribute("datas", datas);
    		System.out.println("applitionScope.datas 할당 완료 : ["+datas+"]");
    }
	
}
