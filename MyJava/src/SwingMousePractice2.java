import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


class MousePanel extends JPanel implements ActionListener, 
											MouseListener,
											KeyListener
{
	JButton button;
	JLabel label;
	MousePanel(){
		setBackground(Color.GREEN);
		setLayout(null);  // 좌표 설정 가능
		button = new JButton("Push");
		label = new JLabel("label");
		button.setBounds(0, 0, 100, 30); // X Y W H
		label.setBounds(0, 30, 100, 30);
		
		// Anonymous Class (멤버 변수 사용 O, 로컬 변수 사용 x)
		button.addActionListener(this);
		addMouseListener(this);
		
		setFocusable(true);  // 포커스 가능하게
		requestFocus();  // 포커스 할당
		addKeyListener(this);  // 키보드 이벤트는 포커스 기준

		add(button);
		add(label);
	}	
	public void actionPerformed(ActionEvent arg0) {
		label.setText("Button Click"); // 로컬변수 사용시 상수 처리
	}
	
	public void mouseClicked(MouseEvent arg0) {
	}
	public void mouseEntered(MouseEvent arg0) {
		setBackground(Color.BLUE);
	}
	public void mouseExited(MouseEvent arg0) {
		setBackground(Color.YELLOW);
	}
	public void mousePressed(MouseEvent arg0) {
		setBackground(Color.RED);
		if(arg0.getButton()==MouseEvent.BUTTON1) { // Mouse Left
			int x = arg0.getXOnScreen();//.getX();
			int y = arg0.getYOnScreen();
			label.setText("Mouse Pos : (" + x + "," + y + ")");
			button.setBounds(x, y, 100, 30);
		}
	}
	public void mouseReleased(MouseEvent arg0) {
		setBackground(Color.GREEN);
	}
	
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_SPACE) {
			System.out.println("hahaha");	
		}
	}
	public void keyReleased(KeyEvent arg0) {	
		
	}
	public void keyTyped(KeyEvent arg0) { 
	}
}

public class SwingMousePractice2 extends JFrame {
	public static void main(String[] args) {
		new SwingMousePractice2();
	}
	SwingMousePractice2(){
		setSize(400, 400);
		setTitle("Mouse Practice");
	
		add(new MousePanel());
		
		setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
