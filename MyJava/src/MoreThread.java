import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

class MoreThreadPanel extends JPanel implements Runnable{
	int x = 100;
	int y = 100;
	int dx = 4;
	int dy = 4;
	Thread t;
	MoreThreadPanel(){
		t = new Thread(this);
		t.start();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillRect(x, y, 50, 50);
	}
	void update() {
		x += dx;
		y += dy;
	}
	void resolveCollision() {
		Dimension d = getSize();
		int w = d.width;
		int h = d.height;
		if(x>w-50) {x = w-50; dx = -dx;}
		if(x<0)    {x = 0;    dx = -dx;}
		if(y>h-50) {y = h-50; dy = -dy; }
		if(y<0)    {y = 0;    dy = -dy; }
	}
	public void run() {
		while(true) {
			//getInput();
			update();
			resolveCollision();
			repaint();
			try {
				Thread.sleep(17);
			} catch(InterruptedException e) {   // sleep시에만 exception (연산때는 x)
				return;
			}
		}
	}
}

public class MoreThread extends JFrame {

	public static void main(String[] args) {
		new MoreThread();
	

	}
	MoreThread(){
		setTitle("Thread");
		setSize(400, 400);
		
		add(new MoreThreadPanel());
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
