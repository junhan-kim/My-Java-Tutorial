import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;

public class Ball extends JComponent {
	int x;
	int y;
	int deg;  // deg : 0 ~ 360
	int distX;  // dist : ball's moving distance per frame
	int distY;
	Rectangle ballRect;	
	Thread ballMoveThread;
	static int ballCnt = 0;
	 	
	Ball(){
		this.setBounds(0, 0, Variable.WIDTH, Variable.HEIGHT);
		ballCnt++;
		
		x = Variable.WIDTH/2 - Variable.BALL_SIZE/2;
		y = Variable.BAR_Y - Variable.BALL_SIZE - 10; 
		// #warning : do not confuse that y-axis directs toward down in computer coordinate system  
		deg = Variable.getRandDeg(225, 315);
		distX = Variable.BALL_DIST_X;
		distY = Variable.BALL_DIST_Y;
		ballRect = new Rectangle(x, y, Variable.BALL_SIZE, Variable.BALL_SIZE); 
		
		ballMoveThread = new Thread(()->{
			try {
				while(true) {
					Thread.sleep(Variable.BALL_REPAINT_DELAY);
					x += (int)(distX * Math.cos(Math.toRadians(deg)));  // add x displacement
					y += (int)(distY * Math.sin(Math.toRadians(deg)));  // add y displacement
					ballRect.setRect(x, y, Variable.BALL_SIZE, Variable.BALL_SIZE);
					//System.out.println("ballRect : " + ballRect + " " + deg);
					repaint();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		ballMoveThread.start();
	}	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.WHITE);
		g2.fillOval(x, y, Variable.BALL_SIZE, Variable.BALL_SIZE);
	}
}
