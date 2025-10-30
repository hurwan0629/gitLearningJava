package model;

public class MovieDTO {
	private int titleId; // 영화 번호
	private String title; // 영화 이름
	private String genre; // 영화장르
	private String condition; // 분기점

	public int getTitleId() {
		return titleId;
	}
	public void setTitleId(int titleId) {
		this.titleId = titleId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	@Override
	public String toString() {
		return "MovieDTO [titleId=" + titleId + ", title=" + title + ", genre=" + genre + "]";
	}
	
}
