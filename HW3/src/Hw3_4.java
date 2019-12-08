import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;


class CLabel extends JLabel{
	public static final int WIDTH = 15;
	public static final int HEIGHT = 20;
	
	CLabel(String str){
		super(str);
		setSize(WIDTH, HEIGHT);
		setLocation(100, 100);
		setBackground(Color.CYAN);
		setOpaque(true);
		setBorder(new EtchedBorder(EtchedBorder.RAISED));
		addMouseListener(new CClickListener());
	}
	
	private class CClickListener implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent arg0) {
			if((CLabel)arg0.getSource() == CLabel.this) {
				int x = (int)(Math.random() * 460);
				int y = (int)(Math.random() * 440);
				CLabel.this.setBounds(x, y, WIDTH, HEIGHT);
			}
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
		}
		@Override
		public void mouseExited(MouseEvent arg0) {	
		}
		@Override
		public void mousePressed(MouseEvent arg0) {	
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {	
		}	
	}
}

public class Hw3_4 extends JFrame {
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	
	public static void main(String[] args) {
		new Hw3_4();
	}
	
	Hw3_4(){
		setTitle("클릭 연습 용 응용프로그램");
		setSize(WIDTH, HEIGHT);
		
		Container cp = getContentPane();
		cp.setLayout(null);
		cp.add(new CLabel("C"));
		
		setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
