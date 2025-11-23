package controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

@WebFilter("*.jsp")
public class EncFilter extends HttpFilter implements Filter {
    public EncFilter() {
        super();
    }
	public void destroy() {
		
	}
	// WebFilter이 실행될 때 마다 동작을 하는 부분
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("[로그] 필터 메서드 호출됨");
		request.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// 최초로 한번 실행
		System.out.println("[로그] 서버가 시작될 떄 필터 초기화");
	}
}
