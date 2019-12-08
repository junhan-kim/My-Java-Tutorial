// 1. Extending Thread Class    => 변수 공유가 안됨,  start 상속받음
/*
class MyThread extends Thread{
	String name;
	MyThread(String n) { name = n; }
	@Override
	public void run() {
		int sum = 0;
		for(int i = 0; i < 100; i++) {
			sum += i;
			System.out.println(name + " : " + i + " = " + sum);
		}
	}
}
*/

// 2. Implementing Runnable Interface
class MyThread implements Runnable{
	String name;
	MyThread(String n) { name = n; }
	@Override
	public void run() {
		int sum = 0;
		for(int i = 0; i < 100; i++) {
			sum += i;
			System.out.println(name + " : " + i + " = " + sum);
		}
	}
}



public class JavaThreadPractice {

	public static void main(String[] args) {
		//MyThread t1 = new MyThread("AAA");
		//MyThread t2 = new MyThread("BBB");
		//t1.run();  // run : 스레드 시작시 호출
		//t2.run(); 
		//t1.start();  // start : 스레드 시작
		//t2.start();
		//System.out.println("done");
		
		Thread t3 = new Thread(new MyThread("AAA"));  // new MyThread : Runnable
		Thread t4 = new Thread(new MyThread("BBB"));
		t3.start();
		t4.start();
		System.out.println("done");
	}

}
