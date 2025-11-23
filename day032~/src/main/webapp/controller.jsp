<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.dto.*, java.util.ArrayList" %>
<%
	request.setCharacterEncoding("UTF-8");
%>

<jsp:useBean id="boardDAO" class="model.dao.BoardDAO" />
<jsp:useBean id="boardDTO" class="model.dto.BoardDTO" />
<jsp:useBean id="memberDAO" class="model.dao.MemberDAO" />
<jsp:useBean id="memberDTO" class="model.dto.MemberDTO" />
<jsp:useBean id="replyDAO" class="model.dao.ReplyDAO" />
<jsp:useBean id="replyDTO" class="model.dto.ReplyDTO" />
<jsp:setProperty property="*" name="memberDTO" />
<jsp:setProperty property="*" name="replyDTO" />
<jsp:setProperty property="*" name="boardDTO" />


<%
String command = request.getParameter("command");

	System.out.println("command : " + command);	
	System.out.println("memberDTO : " + memberDTO);
	System.out.println("boardDTO : " + boardDTO);
	System.out.println("replyDTO : " + replyDTO);
	System.out.println();
	System.out.println();
	
	
	if(command.equals("LOGIN")){
		// V에게서 받아온 mid, mpw 정보를 가지고 
	
		// 모델에게 로그인 요청하기
		// = M한테 selectOne()요청하기
		memberDTO.setCondition("LOGIN");
		MemberDTO data = memberDAO.selectOne(memberDTO);
		
		if(data!=null){
	session.setAttribute("userInfo", data.getMid());
	session.setAttribute("userNameInfo", data.getName());
	session.setAttribute("userRoleInfo", data.getMrole());
	//response.sendRedirect("main.jsp");
	response.sendRedirect("controller.jsp?command=MAINPAGE");
		}
		else{
	out.println("<script>alert('로그인 실패...');location.href='controller.jsp?command=MAINPAGE';</script>");
		}
	
		// 로그인 성공하면 main.jsp로 이동
		// session에 로그인 정보 추가
	
		// 로그인 실패하면 main.jsp로 이동
		// 안내창 출력 후 이동	
	}
	// 로그아웃 페이지
	else if(command.equals("LOGOUT")){
		// 어짜피 센션에 userInfo 있으니까 들어온것임으로 세션확인은 생략
		// 센션 비우고
		// session.setAttribute("userInfo", null);
		session.removeAttribute("userInfo");
		session.removeAttribute("userNameInfo");
		session.removeAttribute("userRoleInfo");
		// controller을 통해 main.jsp로 돌아가기
		out.println("<script>alert('로그아웃');location.href='controller.jsp?command=MAINPAGE';</script>");
	}
	else if(command.equals("JOIN")){
		//사용자로부터 입력받은 정보를
		// String mid = request.getParameter("mid"); 
		// String mpw = request.getParameter("mpw");
		// String name = request.getParameter("name");
		
		//M한테 넘겨서 insert수행
		
		// ▼ 아래 작업은 액션태그에서 모두 끝남.
		// MemberDTO memberDTO = new MemberDTO();
		// memberDTO.setMid(mid);
		// memberDTO.setMpw(mpw);
		// memberDTO.setName(name);
		
		// 결과 받아서 화면에 출력
		System.out.println("1");
		if(memberDAO.insert(memberDTO)){
			System.out.println("2");
			out.println("<script>alert('회원가입 성공! 로그인해서 이용해주세요.');location.href='controller.jsp?command=MAINPAGE'</script>");
			// flag가 true이면 회원가입 성공시켜준 뒤
			// 메인으로 가기
		}
		else {
			System.out.println("3");
			// 회원가입 실패를 알려준뒤
			//request.setAttribute("msg","회원가입 실패... 관리자에게 문의해주세요.");
			//request.setAttribute("location","controller.jsp?command=JOINPAGE");
			// 회원가입 페이지로 이동시키기
			//pageContext.forward("message.jsp");
			out.println("<script>alert('회원가입 실패... 관리자에게 문의해주세요.');location.href='controller.jsp?command=JOINPAGE'</script>");
			
		}
	}
	else if(command.equals("UPDATENAME")){
		// 사용자가 보낸 파라미터 값 추출해서
		
		// dto에 넣어서
		
		if(memberDAO.update(memberDTO)){
			// 성공 알림 -> 로그아웃 ㄱㄱ
			out.println("<script>alert('이름변경 성공! 로그인해서 이용해주세요.');location.href='controller.jsp?command=LOGOUT'</script>");
		}
		else{
			// 실패 알림 -> 마이페이지 ㄱㄱ
			out.println("<script>alert('이름변경 실패... 관리자에게 문의해주세요.');location.href='controller.jsp?command=MYPAGE'</script>");
		}
		// 결과화면 보여주기
	}
	else if(command.equals("MAINPAGE")){
		// 메인페이지를 보여주는 로직
		// 이때 condition이 null이 아니라 null이 변형된 빈 값이 올 수도 있으므로 꼼꼼히 처리해주면 좋다.
		if(boardDTO.getCondition()==null || boardDTO.getCondition().isBlank() || boardDTO.getCondition().isEmpty()){
			// datas 준비해서
			boardDTO.setCondition("ALL");
			// 로그 남기기
			System.out.println("[로그] controller.jsp?command=MAINPAGE condition=ALL");
		}
		// 제목으로 검색
		else if(boardDTO.getCondition().equals("TITLE")){
			System.out.println("[로그] controller.jsp?command=MAINPAGE condition=TITLE");
		}
		// 작성자로 검색
		else if(boardDTO.getCondition().equals("WRITER")){
			System.out.println("[로그] controller.jsp?command=MAINPAGE condition=WRITER");
		}
		
		ArrayList<BoardDTO> datas =  boardDAO.selectAll(boardDTO);
		/**************
		for(int i=0;i<datas.size();i++){
			// datas에서 mid 뽑아서
			// MemberDTO에 넣고 이름 받아오기
			MemberDTO mDTO = new MemberDTO();
			mDTO.setCondition("GETUSERNAME");
		
			mDTO.setMid(datas.get(i).getWriter());
			datas.get(i).setWriterName(memberDAO.selectOne(mDTO).getName());
			
		}
		*******************/
		request.setAttribute("datas", datas);
		// 메인페이지 보여줘
		
		// 페이지 이동 방식
		// 1. 리다이랙트 - 새로운 요청을 하는것과 동일 / 기존의 요청정보인 request를 새로 만들어서 가져감
		// response.sendRedirect("main.jsp");
		// 2. 포워드 - 다음 페이지로 데이터를 넘겨줘야 하는 상황이 있을때
		pageContext.forward("main.jsp");
	} 
	else if(command.equals("JOINPAGE")){
		response.sendRedirect("join.jsp");
	}
	else if (command.equals("MYPAGE")) {
		// 방법 1. selectOne에 조건 분기 나누기
		// 방법 2. request session application을 통해 생존주기를 확인하여 필요한 것인지 판단하기
		
		// 세션에서 mid받기
		request.setAttribute("mid",session.getAttribute("userInfo"));
		
		// NAME를 받기위해 mid를 통해 데이터베이스에서 mid->name 받는 조건분기 만든뒤
		// 받아오기
		memberDTO.setMid((String)session.getAttribute("userInfo"));
		memberDTO.setCondition("GETUSERNAME");
		request.setAttribute("name",memberDAO.selectOne(memberDTO).getName());
		// request.setAttribute("name",session.getAttribute("userNameInfo"));
		pageContext.forward("mypage.jsp");
	} 
	else if (command.equals("MYPAGE")) {
		response.sendRedirect("join.jsp");
	} 
	else if(command.equals("DELETEMEMBER")){
		// 사용자에게 값 받아서
		// memberDTO에 세팅하고
		memberDTO.setMid((String)session.getAttribute("userInfo"));
		
		replyDTO.setCondition("UPDATE_DELMEM");
		replyDTO.setWriter((String)session.getAttribute("userInfo"));
		replyDAO.update(replyDTO);
		
		boardDTO.setCondition("UPDATE_DELMEM");
		boardDTO.setWriter((String)session.getAttribute("userInfo"));
		boardDAO.update(boardDTO);
		if(memberDAO.delete(memberDTO)){
			
		
			out.println("<script>alert('회원탈퇴 성공! 다음에 다시 이용해주세요.');location.href='controller.jsp?command=LOGOUT'</script>");
		}
		else{
			out.println("<script>alert('회원탈퇴 실패... 관리자에게 문의해주세요.');location.href='controller.jsp?command=MYPAGE'</script>");
		}
	}
	else if (command.equals("BOARDPAGE")) {
		
		// 상세히 볼 boardDTO를 뽑아내고 -> board
		BoardDTO board=boardDAO.selectOne(boardDTO);
		///////////////
		// MemberDTO mDTO = new MemberDTO();
		// mDTO.setCondition("GETUSERNAME");
	
		// mDTO.setMid(board.getWriter());
		// board.setWriterName(memberDAO.selectOne(mDTO).getName());
		/////////////
		System.out.println("[로그] controller.jsp?command=BOARDPAGE baord: " + board);
		request.setAttribute("board", board);
		// 상세히 볼 게시글의 bid를 가진 ArrayList<ReplyDTO> 를 뽑아내어 -> replyDatas
		request.setAttribute("replyDatas", replyDAO.selectAll(replyDTO));
		
		
		
		// System.out.println("===========");
		// System.out.println(request.getAttribute("board"));
		// System.out.println("===========");
		// System.out.println(request.getAttribute("replayDatas"));
		// 다음 페이지로 보내기
		
		pageContext.forward("board.jsp");
	} 
	else if (command.equals("WRITE")) {
		
		// 글 생성하려면 제목/쓴이/내용이 필요한데 제목/내용은 넘겨주는 값이고 
		// 글쓴이는 userInfo에서 퍼오기 
		boardDTO.setWriter((String)session.getAttribute("userInfo"));
		if(boardDAO.insert(boardDTO)){
			out.println("<script>alert('글 작성 성공!');location.href='controller.jsp?command=MAINPAGE'</script>");
		}
		else{
			out.println("<script>alert('글 작성 실패... 관리자에게 문의해주세요.');location.href='controller.jsp?command=MAINPAGE'</script>");
		}
	}
	else if (command.equals("WRITEPAGE")){
		// 만약 사용자가 회원가입이 안되어있다면
		// 애초에 로그인 해야지만 글 작성할 수 있지만
		if(session.getAttribute("userInfo")!=null){
			response.sendRedirect("write.jsp");
		}
	}
	
	else if (command.equals("UPDATETITLE")){
		boardDTO.setCondition("UPDATE_TITLE");
		if(boardDAO.update(boardDTO)){
			out.println("<script>alert('제목변경 성공!');location.href='controller.jsp?command=MAINPAGE'</script>");
		}
		else{
			out.println("<script>alert('제목변경 실패...');location.href='controller.jsp?command=MAINPAGE'</script>");
		}
	}
	else if (command.equals("DELETEBOARD")){
		if(boardDAO.delete(boardDTO)){
			response.sendRedirect("controller.jsp?command=MAINPAGE");
		}
		else{
			out.println("<script>alert('글 삭제 실패...');location.href='controller.jsp?command=MAINPAGE'</script>");
		}
	}
	else if (command.equals("WRITEREPLY")){
		// replyDTO에 content자동으로 매핑됨
		// userNameInfo에서 writer가져올 수 있음
		// BID는 form에서 hidden으로 받아오기
		// rid는 자동 설정됨
		if(session.getAttribute("userInfo") == null){
			out.println("<script>alert('댓글을 달려면 로그인을 해야합니다.');location.href='controller.jsp?command=BOARDPAGE&bid="+request.getParameter("bid")+"'</script>");
		}
		else {
			replyDTO.setWriter((String)session.getAttribute("userInfo"));
			if(replyDAO.insert(replyDTO)){
				out.println("<script>alert('댓글 추가됨');location.href='controller.jsp?command=BOARDPAGE&bid="+request.getParameter("bid")+"'</script>");
			}
		}
		
	}
	else if (command.equals("UPDATEREPLY")){
		replyDTO.setCondition("UPDATE_REPLY");
		if(replyDAO.update(replyDTO)){
			out.println("<script>alert('댓글 수정 완료');location.href='controller.jsp?command=BOARDPAGE&bid="+request.getParameter("bid")+"'</script>");
		}
		else{
			out.println("<script>alert('댓글 수정 실패');location.href='controller.jsp?command=BOARDPAGE&bid="+request.getParameter("bid")+"'</script>");
		}
	}
	else if (command.equals("DELETEREPLY")){
		System.out.println("[로그] controller.jsp command=DELETEREPLY");
		if(replyDAO.delete(replyDTO)){
			out.println("<script>alert('댓글 삭제됨');location.href='controller.jsp?command=BOARDPAGE&bid="+request.getParameter("bid")+"'</script>");
		}
		else{
			out.println("<script>alert('댓글 삭제 실패');location.href='controller.jsp?command=BOARDPAGE&bid="+request.getParameter("bid")+"'</script>");
		}
	}
	else if (command.equals("UPDATECONTENTPAGE")){
		
		request.setAttribute("boardDTO", boardDAO.selectOne(boardDTO));
		pageContext.forward("updateContent.jsp");
	}
	else if (command.equals("UPDATECONTENT")){
		System.out.println("asdfasdf");
		boardDTO.setCondition("UPDATE_CONTENT");
		if(boardDAO.update(boardDTO)){
			out.println("<script>alert('글 수정됨');location.href='controller.jsp?command=BOARDPAGE&bid="+request.getParameter("bid")+"'</script>");
		}
		else{
			out.println("<script>alert('글 수정 실패');location.href='controller.jsp?command=BOARDPAGE&bid="+request.getParameter("bid")+"'</script>");
		}
	}
%>