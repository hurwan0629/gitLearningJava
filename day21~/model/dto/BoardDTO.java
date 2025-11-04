package model.dto;

public class BoardDTO {
	private int bid; // PK
	private String title;
	private String content;
	private String mid; // FK : 상대 테이블의 PK
	private int bcount;
	
	private String writer;
	
	private String condition;	
	
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public int getBcount() {
		return bcount;
	}
	public void setBcount(int bcount) {
		this.bcount = bcount;
	}
	@Override
	public String toString() {
		return "BoardDTO [bid=" + bid + ", title=" + title + ", content=" + content + ", mid=" + mid + ", bcount="
				+ bcount + "]";
	}
}
