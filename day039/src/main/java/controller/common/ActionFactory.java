package controller.common;

import java.util.HashMap;
import java.util.Map;

import controller.board.DeleteBoardAction;
import controller.board.UpdateContentAction;
import controller.board.UpdateTitleAction;
import controller.board.WriteAction;
import controller.member.DeleteMemberAction;
import controller.member.JoinAction;
import controller.member.LoginAction;
import controller.member.LoginFailAction;
import controller.member.LogoutAction;
import controller.member.UpdateNameAction;
import controller.page.BoardPageAction;
import controller.page.JoinPageAction;
import controller.page.MainPageAction;
import controller.page.MyPageAction;
import controller.page.UpdateContentPageAction;
import controller.page.WritePageAction;
import controller.reply.DeleteReplyAction;
import controller.reply.UpdateReplyAction;
import controller.reply.WriteReplyAction;

public class ActionFactory {
	private Map<String, Action> map;
	
	public ActionFactory() {
		map = new HashMap<>();
		
		map.put("/mainPage.do", new MainPageAction());
		map.put("/login.do", new LoginAction());
		map.put("/loginFail.do", new LoginFailAction());
		map.put("/logout.do", new LogoutAction());
		map.put("/joinPage.do", new JoinPageAction());
		map.put("/join.do", new JoinAction());
		map.put("/updateName.do", new UpdateNameAction());
		map.put("/myPage.do", new MyPageAction());
		map.put("/deleteMember.do", new DeleteMemberAction());
		map.put("/boardPage.do", new BoardPageAction());
		map.put("/write.do", new WriteAction());
		map.put("/writePage.do", new WritePageAction());
		map.put("/updateTitle.do", new UpdateTitleAction());
		map.put("/deleteBoard.do", new DeleteBoardAction());
		map.put("/writeReply.do", new WriteReplyAction());
		map.put("/updateReply.do", new UpdateReplyAction());
		map.put("/updateContentPage.do", new UpdateContentPageAction());
		map.put("/updateContent.do", new UpdateContentAction());
		map.put("/deleteReply.do", new DeleteReplyAction());
	}
	
	public Action getAction(String command) {
		return map.get(command);
	}
}
