package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.MemberDAO;
import model.dto.MemberDTO;

// 회원가입시 아이디 중복되나 확인하는 부분
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CheckServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[로그] GET 요청");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[로그] POST 요청");
		
		
		// member모델한테 아이디 중복검사 요청
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMid(request.getParameter("mid"));
		memberDTO.setCondition(request.getParameter("condition"));
		System.out.println("[로그] memberDTO : " + memberDTO);
		memberDTO = memberDAO.selectOne(memberDTO);
		boolean flag = memberDTO == null ? true : false;
		
		// 응답하기
		PrintWriter out = response.getWriter();
		out.print(flag);
	}

}
