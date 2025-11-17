package thread;

class MyRunnable implements Runnable {

	@Override
	public void run() {
		System.out.println("[MyRunnable 스레드] 로그01");
		System.out.println(Thread.currentThread().getName());
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("[MyRunnable 스레드] 로그02");
		System.out.println(Thread.currentThread().getName());
		
	}
	
}

public class ThreadTest02 {
	public static void main(String[] args) {
		MyRunnable mr01 = new MyRunnable();
		Thread t01 = new Thread(mr01);
		t01.start();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("[main 스레드] 로그01");
	}
}
