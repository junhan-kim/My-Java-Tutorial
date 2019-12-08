import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class myPanel extends JPanel{
	myPanel(){
		Color[] colArr = new Color[10];
		setColArr(colArr);
		setLayout(new GridLayout());
		JButton[] butArr = new JButton[10];
		for(int i = 0; i < 10; i++) {
			butArr[i] = new JButton(Integer.toString(i));
			butArr[i].setBackground(colArr[i]);
			add(butArr[i]);
		}
	}
	
	void setColArr(Color[] colArr) {
		colArr[0] = Color.RED;
		colArr[1] = Color.ORANGE;
		colArr[2] = Color.YELLOW;
		colArr[3] = Color.GREEN; 
		colArr[4] = Color.CYAN;
		colArr[5] = Color.BLUE;
		colArr[6] = Color.MAGENTA;
		colArr[7] = Color.DARK_GRAY;
		colArr[8] = Color.PINK;
		colArr[9] = Color.LIGHT_GRAY;
	}
}


public class Hw3_1 extends JFrame {

	public static void main(String[] args) {
		new Hw3_1();
	}
	
	Hw3_1(){
		setTitle("Ten Color Buttons Frame");
		setSize(600, 300);
		add(new myPanel());
		
		setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
