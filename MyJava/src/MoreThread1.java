class MySumThread extends Thread{
	int sum;
	int start;
	int end;
	MySumThread(int s, int e){
		start = s;
		end = e;
	}
	public void run() {
		sum = 0;
		for(int i = start; i<=end; i++) {
			sum += i;
			try {
				Thread.sleep(1);   // 0.001�ʸ�ŭ sleep
			} catch(InterruptedException e) {
				return;
			}
		}
	}
}


public class MoreThread1 {
	public static void main(String[] args) {  // master
		System.out.println("start");
		MySumThread t1 = new MySumThread(1, 500);  // slaves
		MySumThread t2 = new MySumThread(501, 1000);
		MySumThread t3 = new MySumThread(1001, 1500);
		MySumThread t4 = new MySumThread(1501, 2000);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		try{
			t1.join(); // t1�� ���������� wait   => ����ȭ     // ���� ������ �� �ð���ŭ ��ٸ�
			t2.join();
			t3.join();
			t4.join();
			System.out.println("sum = " + (t1.sum + t2.sum + t3.sum + t4.sum));
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

}
