package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.dao.BoardDAO;
import model.dto.BoardDTO;


public class MainBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MainBoardServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[로그] GET 요청");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[로그] POST 요청");
		
		
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setCondition(request.getParameter("condition"));
		boardDTO.setKeyword(request.getParameter("keyword"));
		
		BoardDAO boardDAO = new BoardDAO();
		ArrayList<BoardDTO> datas = boardDAO.selectAll(boardDTO);
		
		response.setContentType("application/json; charset=UTF-8");
		
		Gson gson = new Gson();
		String json = gson.toJson(datas); // ← List<BoardDTO> → JSON 문자열

		try (PrintWriter out = response.getWriter()) {
			out.print(json);
		}
	}
}
