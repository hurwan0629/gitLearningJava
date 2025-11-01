package model;

public class StudentDTO {
	private int studentId;
	private String studentName;
	private int studentAge;
	private int studentScore;
	
	private String studentCondition;
	
	public String getStudentCondition() {
		return studentCondition;
	}
	public void setStudentCondition(String studentCondition) {
		this.studentCondition = studentCondition;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getStudentAge() {
		return studentAge;
	}
	public void setStudentAge(int studentAge) {
		this.studentAge = studentAge;
	}
	public int getStudentScore() {
		return studentScore;
	}
	public void setStudentScore(int studentScore) {
		this.studentScore = studentScore;
	}
	@Override
	public String toString() {
		return "StudentDTO [studentId=" + studentId + ", studentName=" + studentName + ", studentAge=" + studentAge
				+ ", studentScore=" + studentScore + "]";
	}
	
}
