import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// SWING : GUI Framework. 멀티 쓰레딩

class HelloPanel extends JPanel{
	HelloPanel(){
		setLayout(new BorderLayout());  // BorderLayout 적용
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
		// System.out.println(f.getSize());  -> 프레임 사이즈에 접근
	}
	
	MyHelloSwing(){
		setTitle("Hello Swing!");
		setSize(300, 300);
		
		add(new HelloPanel());
		
		// 프레임 등은 별도 쓰레드에서 돌아가므로, 프레임이 떠있으면서 동시에 아래 반복문이 수행 가능
		// while(true) { System.out.println("done!"); }
		
		// 모듈화에 불리한 코드 (Frame 내에서 Panel 작업을 함)
		//JPanel p = new JPanel();
		//p.setBackground(Color.GREEN);
		//JButton but = new JButton("BUT");
		//p.add(but);
		//add(p);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE); // this. 으로 검색가능
	}
}
