package model.dto;

public class MemberDTO {
	private String memberId;
	private String memberPasswd;
	private String memberName;
	private String memberCondition;
	
	
	public String getMemberCondition() {
		return memberCondition;
	}
	public void setMemberCondition(String memberCondition) {
		this.memberCondition = memberCondition;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPasswd() {
		return memberPasswd;
	}
	public void setMemberPasswd(String memberPasswd) {
		this.memberPasswd = memberPasswd;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	@Override
	public String toString() {
		return "MemberDTO [memberId=" + memberId + ", memberPasswd=" + memberPasswd + ", memberName=" + memberName
				+ "]";
	}
}
