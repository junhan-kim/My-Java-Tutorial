import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// SWING : GUI Framework. ��Ƽ ������

class HelloPanel extends JPanel{
	HelloPanel(){
		setLayout(new BorderLayout());  // BorderLayout ����
		setBackground(Color.GREEN);
		JButton but1 = new JButton("BUT1");
		JButton but2 = new JButton("BUT2");
		JButton but3 = new JButton("BUT3");
		//add(but1);   // default (FlowLayout)
		//add(but2);
		//add(but3);
		add(but1, BorderLayout.CENTER); // BorderLayout
		add(but2, BorderLayout.EAST);
		add(but3, BorderLayout.WEST);
	}
}

public class MyHelloSwing extends JFrame {
	public static void main(String[] args) {
		new MyHelloSwing();
		
		// JFrame f = new JFrame();
		// System.out.println(f.getSize());  -> ������ ����� ����
	}
	
	MyHelloSwing(){
		setTitle("Hello Swing!");
		setSize(300, 300);
		
		add(new HelloPanel());
		
		// ������ ���� ���� �����忡�� ���ư��Ƿ�, �������� �������鼭 ���ÿ� �Ʒ� �ݺ����� ���� ����
		// while(true) { System.out.println("done!"); }
		
		// ���ȭ�� �Ҹ��� �ڵ� (Frame ������ Panel �۾��� ��)
		//JPanel p = new JPanel();
		//p.setBackground(Color.GREEN);
		//JButton but = new JButton("BUT");
		//p.add(but);
		//add(p);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE); // this. ���� �˻�����
	}
}
