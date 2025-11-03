package model.dto;

public class MemberDTO {
	private String mid; // PK
	private String passwd;
	private String name;
	private String mrole;
	
	private String condition;
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMrole() {
		return mrole;
	}
	public void setMrole(String mrole) {
		this.mrole = mrole;
	}
	@Override
	public String toString() {
		return "MemberDTO [mid=" + mid + ", passwd=" + passwd + ", name=" + name + ", mrole=" + mrole + "]";
	}
}
