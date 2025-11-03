package model.dto;

import java.util.Date;

public class ReplyDTO {
	private int rid; // PK
	private String content;
	private String mid; // FK
	private Date regdate;
	private int bid; // FK
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
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
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	@Override
	public String toString() {
		return "ReplyDTO [rid=" + rid + ", content=" + content + ", mid=" + mid + ", regdate=" + regdate + ", bid="
				+ bid + "]";
	}
}
