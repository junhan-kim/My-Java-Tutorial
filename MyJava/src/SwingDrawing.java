import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

class DrawingPanel extends JPanel implements MouseListener, 
											MouseMotionListener,
											KeyListener{
	int px = 30;
	int py = 30;
	int dw = 50;
	int dh = 50;
	
	DrawingPanel(){
		this.setBackground(Color.GREEN);
		addMouseListener(this);
		addMouseMotionListener(this);
		
		setFocusable(true);
		requestFocus();
		addKeyListener(this);
	} 
	
	@Override  // 추가 작업
	public void paintComponent(Graphics g) {  // 무효화되면 자동호출, 계속해서 re-Rendering
		super.paintComponent(g);  // 기존 내용 유지 (생성자 부분 등)
		Dimension size = this.getSize();
		int w = size.width;
		int h = size.height;
		System.out.println(w + "," + h);
		
		g.setColor(new Color(255, 200, 200));
		g.drawRect(100, 100, w-200, h-200);
		g.fillRect(100, 100, w-200, h-200);
		g.fillRoundRect(0, 0, w-200, h-200, 40, 40);
		g.setColor(new Color(255, 255, 255));
		g.drawLine(100, 100, w-100, h-200);
		g.drawOval(100, 100, w-200, h-200);
		g.fillOval(100, 100, w-200, h-200);

		g.setColor(new Color(255, 0, 0));
		g.setFont(new Font("궁서체", Font.BOLD, 40));
		g.drawString("안녕", w-300, h-300);
		
		int[] x = {w/2, 100, w-100};
		int[] y = {100, h-100, h-100};
		g.setColor(Color.BLUE);
		g.drawPolygon(x, y, 3);
		
		for(int i = 0; i < 25; i++) {
			g.setColor(new Color(i*10, i*10, i*10));
			int cx = w/2;
			int cy = h/2;
			int ow = w-200-i*(w-200)/25;
			int oh = h-200-i*(h-200)/25;
			g.fillOval(cx-ow/2, cy-oh/2, ow, oh);
		}
		
		g.drawRect(px, py, dw, dh);
		g.fillRect(px, py, dw, dh);
	}
	
	public void mouseClicked(MouseEvent arg0) {
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
		System.out.println("Click");
		// 반복문으로 똑같은 작업 여러번 보내도 마지막꺼만 보내짐
		px = arg0.getX();
		py = arg0.getY();
		requestFocus();
	}
	public void mouseReleased(MouseEvent arg0) {
		dw = arg0.getX() - px;
		dh = arg0.getY() - py;
		repaint();
	}

	// 움직일때마다 발생
	public void mouseDragged(MouseEvent arg0) {
		this.setBackground(Color.YELLOW);
		dw = arg0.getX() - px;
		dh = arg0.getY() - py;
		repaint();
	}
	public void mouseMoved(MouseEvent arg0) { // 움직일때마다 발생
		this.setBackground(Color.BLUE);
		System.out.println(arg0.getX() + "," + arg0.getY());
	}

	
	public void keyPressed(KeyEvent arg0) {
		System.out.println("Key");
		if(arg0.getKeyCode() == KeyEvent.VK_UP) {
			py -= 10;
		}
		if(arg0.getKeyCode() == KeyEvent.VK_DOWN) {
			py += 10;
		}
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			px -= 10;
		}
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			px += 10;
		}
		repaint();
	}
	public void keyReleased(KeyEvent arg0) {	
	}
	public void keyTyped(KeyEvent arg0) {	
	}
}

public class SwingDrawing extends JFrame {

	public static void main(String[] args) {
		new SwingDrawing();
	}
	SwingDrawing(){
		
		setTitle("Swing Drawing");
		setSize(400, 400);
		add(new DrawingPanel());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
