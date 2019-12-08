class MyATM extends Thread{
	MyBank bank;
	MyATM(MyBank in) {bank = in;}
	public void run() {
		bank.add();
	}
}
// java system pj
// exam practice to bb

class MyBank{
	int money = 0;
	// critical section scope  최대한 작게
	synchronized void add() {   // 동기화 => locking (병렬, 비동기 x)
		for(int i = 0; i < 100; i++) {
			int m = money;
			m += 10;
			try {
				Thread.sleep((int)Math.random()*10);
			} catch(InterruptedException e) {
				return;
			}
			money = m;
			System.out.println("money =" + money);
		}
	}
}



public class MoreThread2 {

	public static void main(String[] args) {
		MyBank bank = new MyBank();
		MyATM t1 = new MyATM(bank);
		MyATM t2 = new MyATM(bank);
		t1.start();
		t2.start();
		
		

	}

}
