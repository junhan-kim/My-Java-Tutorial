import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

class MyGraphics2DPanel1 extends JPanel 
				        implements MouseMotionListener
{
	ArrayList<Integer> ptx;
	ArrayList<Integer> pty;
	
	MyGraphics2DPanel1(){
		setBackground(Color.GREEN);
		ptx = new ArrayList<>();
		pty = new ArrayList<>();
		addMouseMotionListener(this);
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
//		g.drawRect(100, 100, 200, 200);
		for(int i=0; i<ptx.size(); i++)
		{
			int x = ptx.get(i);
			int y = pty.get(i);
			g.fillOval(x, y, 2, 2);
		}		
	}
	public void mouseDragged(MouseEvent e) {
		ptx.add(e.getX());
		pty.add(e.getY());
		repaint();
	}
	public void mouseMoved(MouseEvent e) {	}	
}
public class SwingGraphics2D1 extends JFrame{
	public static void main(String[] args) {
		new SwingGraphics2D1();
	}
	SwingGraphics2D1()	{
		setSize(400, 400);
		setTitle("Graphics2D");
		
		add(new MyGraphics2DPanel1());
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

}