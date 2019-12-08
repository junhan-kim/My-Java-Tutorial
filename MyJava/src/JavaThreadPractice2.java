import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class MyThreadPanel extends JPanel implements Runnable{
	int num = 0;
	JButton but;
	MyThreadPanel(){
		/*
		but = new JButton("push");
		but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				increaseNum();
			}
		});
		add(but);
		*/
		Thread t = new Thread(this);
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
			repaint();  // 다시 paintComponent 호출
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {   // 인터럽트시(다른일)
				return;
				//e.printStackTrace();  // 호출 스택 추적
			}
		}
	}
}

public class JavaThreadPractice2 extends JFrame{

	public static void main(String[] args) {
		new JavaThreadPractice2();
	}
	JavaThreadPractice2(){
		setTitle("thread practice");
		setSize(300,300);	
		add(new MyThreadPanel());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

}
