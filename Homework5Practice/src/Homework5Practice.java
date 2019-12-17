import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

abstract class MyObject {
	abstract void draw(Graphics g);
	void update(double dt) {};
	void resolveCollision(MyObject in) {};
	boolean isDead() {return false;}
}

class MyWall extends MyObject{
	Color color = Color.black;
	double x, y, w, h;
	MyWall(double _x, double _y, double _w, double _h, Color c){
		x = _x;
		y = _y;
		w = _w;
		h = _h;
		color = c;
	}
	@Override
	void draw(Graphics g) {
		g.setColor(color);
		g.fillRect((int)x,(int)y,(int)w,(int)h);
		
	}
}

class MySphere extends MyObject{
	double x, y, r;
	double prex, prey;
	double vx, vy;
	double age = 0;
	static final double MAXAGE = 5;
	Color color;
	MySphere(double _x, double _y, double _r, Color c){
		x = _x;
		y = _y;
		r = _r;
		color = c;
		vx = Math.random()*400-200;		// [-100,100]
		vy = Math.random()*400-200;		// [-100,100]
	}	
	@Override
	void draw(Graphics g) {
		g.setColor(color);
		g.fillOval((int)(x-r),(int)(y-r),(int)(2*r),(int)(2*r));
	}
	@Override
	void update(double dt) {
		prex = x;
		prey = y;
		x += vx*dt;
		y += vy*dt;
		age += dt;
		r = 10.0 * (MAXAGE-age)/MAXAGE;
	}
	@Override 
	void resolveCollision(MyObject in) {
		if(this == in) return;
		if(in instanceof MyWall) {
			MyWall w = (MyWall)in;
			double x1 = w.x - r;
			double y1 = w.y - r;
			double x2 = w.x + w.w + r;
			double y2 = w.y + w.h + r;
			if(x>x1 && x<x2 && y>y1 && y <y2) {
				if(prex < x1) {x = x1; vx = -vx;}
				if(prex > x2) {x = x2; vx = -vx;}
				if(prey < y1) {y = y1; vy = -vy;}
				if(prey > y2) {y = y2; vy = -vy;}
			}
		}
	}
	@Override
	boolean isDead() {
		if (age >= MAXAGE) return true;
		return false;
	}
}


class Homework5Panel extends JPanel implements Runnable, KeyListener{
	LinkedList<MyObject> objs;
	Homework5Panel(){
		objs = new LinkedList<MyObject>();
		objs.add(new MyWall(0,0,500,30, Color.blue));
		objs.add(new MyWall(0,470,500,30, Color.blue));
		objs.add(new MyWall(0,0,30,500, Color.blue));
		objs.add(new MyWall(470,0,30,500, Color.blue));
		
		objs.add(new MyWall(100,100,100,200, Color.blue));
		objs.add(new MyWall(300,300,100,200, Color.blue));
		
		
		//objs.add(new MySphere(250,250,10, Color.red));
		
		Thread t = new Thread(this);
		t.start();
		
		addKeyListener(this);
		setFocusable(true);
		requestFocus();
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(MyObject o : objs)
			o.draw(g);
		
		g.drawString("objs:"+objs.size(), 100,100);
	}
	@Override
	public void run() {
		while(true) {
			for(MyObject o : objs)
				o.update(0.033);
			for(MyObject o1 : objs) {
				if(o1 instanceof MySphere)
					for(MyObject o2 : objs) {
						o1.resolveCollision(o2);
					}
			}
			Iterator<MyObject>it = objs.iterator();
			while(it.hasNext()) {
				MyObject o = it.next();
				if(o.isDead())
					it.remove();
			}
			
			repaint();
			try {Thread.sleep(33);} 
			catch (InterruptedException e){return;}
		}
	}
	public void keyPressed(KeyEvent e) {	
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			objs.add(new MySphere(250,250,10, Color.red));
		}
	}
	
	public void keyTyped(KeyEvent e) {	}
	public void keyReleased(KeyEvent e) {	}
	
}	
public class Homework5Practice extends JFrame{
	public static void main(String[] args) {
		new Homework5Practice();
	}
	Homework5Practice(){
		setSize(550,550);
		setTitle("Homework Practice");
		
		add(new Homework5Panel());
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

}