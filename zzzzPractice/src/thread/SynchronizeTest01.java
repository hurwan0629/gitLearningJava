package thread;

class AddHundred implements Runnable{
	private static int cnt=0;
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+" 시작");
		synchronized() {
			for(int i=0;i<10000;i++) {
				cnt+=1;
			}
		}
	}

	public static int getCnt() {
		return cnt;
	}

	public static void setCnt(int cnt) {
		AddHundred.cnt = cnt;
	}
	
}

public class SynchronizeTest01 {
	public static void main(String[] args) {
		Thread t1 = new Thread(new AddHundred());
		Thread t2 = new Thread(new AddHundred());
		
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(AddHundred.getCnt());
	}
}
