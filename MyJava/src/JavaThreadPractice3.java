import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class MyThreadPanel3 extends JPanel implements Runnable, KeyListener{
	int num = 0;
	Thread t;
	boolean working = true;
	
	MyThreadPanel3(){
		addKeyListener(this);
		setFocusable(true);
		requestFocus();
		t = new Thread(this);
		t.start();
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Font font = new Font("Arial", Font.BOLD, 60);
		g.setFont(font);
		g.drawString(""+num, 100, 100);
	}
	public void run() {
		while(true) {  
			num++;
			repaint();  // �ٽ� paintComponent ȣ��
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {   // ���ͷ�Ʈ��(�ٸ���)
				return;
				//e.printStackTrace();  // ȣ�� ���� ����
			}
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			//working = !working;   // run �ѹ� �����ϰ� while�� ���������� ����
			if(!t.isAlive()) { 
				t = new Thread(this);  // �ѹ��������� �ٽ� start x  -> �ٽ� ������ ��������
				t.start();
			}
			else {
				t.interrupt();  // ������ �ߴ��Ͽ� �ٸ� �۾��� �� �ֵ���
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

public class JavaThreadPractice3 extends JFrame{

	public static void main(String[] args) {
		new JavaThreadPractice3();
	}
	JavaThreadPractice3(){
		setTitle("thread practice");
		setSize(300,300);	
		add(new MyThreadPanel3());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

}

