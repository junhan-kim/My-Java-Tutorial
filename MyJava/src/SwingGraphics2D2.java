import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

class MyCurve{
	Color color;
	ArrayList<Integer> ptx;
	ArrayList<Integer> pty;
	MyCurve(){
		ptx = new ArrayList<>();
		pty = new ArrayList<>();
		
		int r = (int)(Math.random()*256);
		int g = (int)(Math.random()*256);
		int b = (int)(Math.random()*256);
		color = new Color(r,g,b);
	}
	void addPoint(int x, int y)	{
		ptx.add(x);
		pty.add(y);
	}
	void draw(Graphics g)	{
		g.setColor(color);
		for(int i=0; i<ptx.size()-1; i++) {
			int x1 = ptx.get(i);
			int y1 = pty.get(i);
			int x2 = ptx.get(i+1);
			int y2 = pty.get(i+1);
			g.drawLine(x1,y1, x2, y2);
		}
	}
	
}

class MyGraphics2DPanel2 extends JPanel 
				        implements MouseMotionListener,
				                   MouseListener
{
	ArrayList <MyCurve> curves;
	
	MyGraphics2DPanel2(){
		setBackground(Color.GREEN);
		addMouseMotionListener(this);
		addMouseListener(this);
		curves = new ArrayList<>();
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		for(MyCurve c : curves){
			c.draw(g);
		}		
	}
	public void mousePressed(MouseEvent e) {
		MyCurve c = new MyCurve();
		c.addPoint(e.getX(), e.getY());
		curves.add(c);
	}
	public void mouseDragged(MouseEvent e) {
		if(curves.size()<1) return;
		curves.get(curves.size()-1).addPoint(e.getX(), e.getY());
		repaint();
	}
	public void mouseReleased(MouseEvent e) {
		if(curves.size()<1) return;
		curves.get(curves.size()-1).addPoint(e.getX(), e.getY());
		repaint();
	}
	
	public void mouseMoved(MouseEvent e) {	}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}	
}
public class SwingGraphics2D2 extends JFrame{
	public static void main(String[] args) {
		new SwingGraphics2D2();
	}
	SwingGraphics2D2()	{
		setSize(400,400);
		setTitle("Graphics2D");
		
		add(new MyGraphics2DPanel2());
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}