import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;


class MyLabel extends JLabel{
	MyLabel(String str){
		super(str);
		setHorizontalAlignment(JLabel.CENTER);
		setFocusable(true);
		addKeyListener(new LeftKeyListener());
	}
	private class LeftKeyListener implements KeyListener{
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_LEFT){
				String str = MyLabel.this.getText();
				String newStr = str.substring(1);
				newStr += str.substring(0, 1);
				MyLabel.this.setText(newStr);
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
		}
		@Override
		public void keyTyped(KeyEvent e) {
		}
	}
}


public class Hw3_3 extends JFrame{

	public static void main(String[] args) {
		new Hw3_3();
	}

	Hw3_3(){
		setTitle("Left키로 문자열 이동");
		setSize(500, 300);
		
		add(new MyLabel("Love Java"));

		setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
