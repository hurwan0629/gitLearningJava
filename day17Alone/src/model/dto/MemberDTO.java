package model.dto;

public class MemberDTO {
	private String memberName;
	private String memberPw;
	private String memberId;
	private String memberCondtion;
	private String memberRole;
	
	
	
	public String getMemberCondtion() {
		return memberCondtion;
	}
	public void setMemberCondtion(String memberCondtion) {
		this.memberCondtion = memberCondtion;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberRole() {
		return memberRole;
	}
	public void setMemberRole(String memberRole) {
		this.memberRole = memberRole;
	}
	@Override
	public String toString() {
		return "MemberDTO [memberName=" + memberName + ", memberPw=" + memberPw + ", memberId=" + memberId
				+ ", memberRole=" + memberRole + "]";
	}

	
}
