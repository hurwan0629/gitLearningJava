package model;

public class NewsDTO {
	private int id; // 뉴스 고유id
	private String url; // 뉴스 링크
	private String title; // 뉴스 제목
	private String press; // 언론사
	private String date; // 작성 날짜
	private String summary; // 뉴스 요약
	
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	 public String toString() {
	   return "NewsDTO [id]"+id+", [summary]"+summary+", [title]"+title+", [url]"+url;
	 }
	
}
