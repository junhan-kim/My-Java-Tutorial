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
		setLayout(null);  // ��ǥ ���� ����
		button = new JButton("Push");
		label = new JLabel("label");
		button.setBounds(0, 0, 100, 30); // X Y W H
		label.setBounds(0, 30, 100, 30);
		
		// Anonymous Class (��� ���� ��� O, ���� ���� ��� x)
		button.addActionListener(this);
		addMouseListener(this);
		
		setFocusable(true);  // ��Ŀ�� �����ϰ�
		requestFocus();  // ��Ŀ�� �Ҵ�
		addKeyListener(this);  // Ű���� �̺�Ʈ�� ��Ŀ�� ����

		add(button);
		add(label);
	}	
	public void actionPerformed(ActionEvent arg0) {
		label.setText("Button Click"); // ���ú��� ���� ��� ó��
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
