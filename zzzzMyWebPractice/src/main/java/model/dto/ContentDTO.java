package model.dto;

public class ContentDTO {
	private String content;
	private int cpk;
	
	
	public int getCpk() {
		return cpk;
	}

	public void setCpk(int cpk) {
		this.cpk = cpk;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ContentDTO [content=" + content + ", cpk=" + cpk + "]";
	}
	
}
