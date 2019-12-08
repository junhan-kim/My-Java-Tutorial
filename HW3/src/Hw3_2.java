import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;


class CalButton extends JButton{
	CalButton(String str){
		super(str);
		setPreferredSize(new Dimension(Hw3_2.WIDTH/5, Hw3_2.HEIGHT/7));
	}
}

class OpButton extends JButton{
	OpButton(String str){
		super(str);
		setBackground(Color.CYAN);
		setPreferredSize(new Dimension(Hw3_2.WIDTH/5, Hw3_2.HEIGHT/7));
	}
}

class InputPanel extends JPanel{
	InputPanel(){	
		setBackground(Color.LIGHT_GRAY);
		add(new JLabel("수식입력"));
		add(new JTextField(20)); 
	}
}

class CalPanel extends JPanel{
	CalPanel(){
		setBackground(Color.WHITE);

		String[] calStr = {"0", "1", "2", "3", "4", "5", "6", "7",
				"8", "9", "CE", "계산"};
		CalButton[] calBut = new CalButton[12];
		for(int i = 0; i < calBut.length; i++) {
			calBut[i] = new CalButton(calStr[i]);
			add(calBut[i]);
		}
		
		String[] opStr = {"+", "-", "x", "/"};
		OpButton[] opBut = new OpButton[4];
		for(int i = 0; i < opBut.length; i++) {
			opBut[i] = new OpButton(opStr[i]);
			add(opBut[i]);
		}
	}
}

class OutputPanel extends JPanel{
	OutputPanel(){
		setBackground(Color.YELLOW);
		add(new JLabel("계산 결과"));
		
		JLabel label = new JLabel();
		label.setPreferredSize(new Dimension(Hw3_2.WIDTH/2, Hw3_2.HEIGHT/10));
		label.setBackground(Color.WHITE);
		label.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		label.setOpaque(true);
		add(label);
	}
}

class MainPanel extends JPanel{
	MainPanel(){
		setLayout(new BorderLayout());
		add(new InputPanel(), BorderLayout.NORTH);
		add(new CalPanel(), BorderLayout.CENTER);
		add(new OutputPanel(), BorderLayout.SOUTH);
	}
}

public class Hw3_2 extends JFrame {
	JPanel mainPanel;
	JPanel calPanel;
	JPanel outputPanel;
	JTextField tf;
	
	public static final int WIDTH = 340;
	public static final int HEIGHT = 300;
		
	public static void main(String[] args) {
		new Hw3_2();
	}
	Hw3_2(){
		setTitle("계산기 프레임");
		setSize(WIDTH, HEIGHT);
		
		add(new MainPanel());
		
		setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
