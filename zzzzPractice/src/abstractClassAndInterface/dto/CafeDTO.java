package abstractClassAndInterface.dto;

public class CafeDTO {
	private int cafePk;
	private String cafeName;
	public int getCafePk() {
		return cafePk;
	}
	public void setCafePk(int cafePk) {
		this.cafePk = cafePk;
	}
	public String getCafeName() {
		return cafeName;
	}
	public void setCafeName(String cafeName) {
		this.cafeName = cafeName;
	}
	@Override
	public String toString() {
		return "CafeDTO [cafePk=" + cafePk + ", cafeName=" + cafeName + "]";
	}
	
}
