class Account{
	int balance = 0;
	// notify, wait can use in sync scope
	synchronized void deposit(int in) {
		balance += in;
		notify();  // kill
		System.out.println("Deposit : " + balance);
	}
	synchronized void withdraw(int out) {
		while(balance<out) {
			try {
				wait();   // pause
			} catch(InterruptedException e) {}
		}
		balance -= out;
		System.out.println("Withdraw : " + balance);
	}
}


public class WaitnotifyPractice {
	public static void main(String[] args) {
		Account ac = new Account();
		Thread t = new Thread(()->{   // lambda(functional) expression
				while(true) {
					ac.withdraw(1000);
					try {
						Thread.sleep(1000);
					}catch(InterruptedException e) {}
				}
			}
		);
		t.start();
		
		while(true) {
			ac.deposit(300);
			try {
				Thread.sleep(500);
			} catch(InterruptedException e) {
				return;
			}
		}
	}
}
