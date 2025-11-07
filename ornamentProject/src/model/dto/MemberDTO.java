package model.dto;

public class MemberDTO {
	private int memberPk;
	private String memberId;
	private String memberName;
	private String memberPassword;
	private String memberAddress;
	private String memberPhoneNumber;
	private String memberRole;
	
	private String condition;

	public int getMemberPk() {
		return memberPk;
	}

	public void setMemberPk(int memberPk) {
		this.memberPk = memberPk;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public String getMemberPhoneNumber() {
		return memberPhoneNumber;
	}

	public void setMemberPhoneNumber(String memberPhoneNumber) {
		this.memberPhoneNumber = memberPhoneNumber;
	}

	public String getMemberRole() {
		return memberRole;
	}

	public void setMemberRole(String memberRole) {
		this.memberRole = memberRole;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	@Override
	public String toString() {
		return "ModelDTO [memberPk=" + memberPk + ", memberId=" + memberId + ", memberName=" + memberName
				+ ",memberAddress=" + memberAddress + ", memberPhoneNumber="
				+ memberPhoneNumber + ", memberRole=" + memberRole + ", condition=" + condition + "]";
	}
	
}
