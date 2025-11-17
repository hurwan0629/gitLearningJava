package thread;

class MyThread extends Thread {
	@Override
	public void run() {
		System.out.println("[스레드1] 실행");
		System.out.println(Thread.currentThread().getName());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("[스레드1] 2초후");
		System.out.println(Thread.currentThread().getName());
	}
}

public class ThreadTest01 {
	public static void main(String[] args) {
		MyThread mt01 = new MyThread();
		MyThread mt02 = new MyThread();
		mt01.start();
		mt02.start();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("[메인 스레드] 0.5초 후");
		System.out.println(Thread.currentThread().getName());
	}
}
