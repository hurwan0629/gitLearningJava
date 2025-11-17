package thread;

public class ThreadTest03 {
	public static void main(String[] args) {
		Thread t01 = new Thread() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
				System.out.println("hello");
			}
		};
		t01.start();
	}
}
