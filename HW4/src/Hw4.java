import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



class Hw4CalButton extends JButton{	
	Hw4OutputPanel outputPanel;
	String[] strArr = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "0"};
	static int acc = 0;
	static boolean bPlus;
	static boolean bMinus;
	static boolean bReg;
	
	Hw4CalButton(String str, Hw4OutputPanel outputPanel){
		super(str);
		this.addActionListener(new butListener());
		this.outputPanel = outputPanel;
		
		bPlus = false;
		bMinus = false;
		bReg = false;
	}	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));

		g.setColor(new Color(231, 220, 200));
		g.fillRoundRect(getSize().width/8, getSize().height/8, 
						getSize().width/8*6, getSize().height/8*6,
						10, 10);			
	}
	
	private class butListener implements ActionListener{
		boolean hasEqualString(ActionEvent e) {
			for(int i = 0; i < strArr.length; i++)
				if(((JButton)e.getSource()).getText().equals(strArr[i])) {
					return true;
				}
			return false;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(hasEqualString(e)) {
				if(outputPanel.numStr == "0" || bReg) 
					outputPanel.numStr = "";			
				bReg = false;
				outputPanel.numStr += Hw4CalButton.this.getText();
				outputPanel.repaint();
			}
			if(((JButton)e.getSource()).getText().equals("C")) {
				acc = 0;
				outputPanel.numStr = "0";
				outputPanel.repaint();
				bPlus = false;
				bMinus = false;
				bReg = false;
			}
			if(((JButton)e.getSource()).getText().equals("+")) {
				if(bPlus) acc += Integer.parseInt(outputPanel.numStr);
				else if(bMinus) acc -= Integer.parseInt(outputPanel.numStr);
				else acc = Integer.parseInt(outputPanel.numStr);		
				bPlus = true;
				bMinus = false;
				bReg = true;	
				outputPanel.numStr = Integer.toString(acc);
				outputPanel.repaint();
			}
			if(((JButton)e.getSource()).getText().equals("-")) {
				if(bPlus) acc += Integer.parseInt(outputPanel.numStr);
				else if(bMinus) acc -= Integer.parseInt(outputPanel.numStr);
				else acc = Integer.parseInt(outputPanel.numStr);		
				bPlus = false;
				bMinus = true;
				bReg = true;			
				outputPanel.numStr = Integer.toString(acc);
				outputPanel.repaint();
			}
			if(((JButton)e.getSource()).getText().equals("=")) {
				if(bPlus) acc += Integer.parseInt(outputPanel.numStr);
				else if(bMinus) acc -= Integer.parseInt(outputPanel.numStr);		
				else acc = Integer.parseInt(outputPanel.numStr);
				bPlus = false;
				bMinus = false;
				bReg = false;
				outputPanel.numStr = Integer.toString(acc);
				outputPanel.repaint();
			}			
		}
	}
}

class Hw4InputPanel extends JPanel{
	Hw4OutputPanel outputPanel;
	Hw4CalButton[] butArr;
	String[] strArr = {"7", "8", "9", "C", "4", "5", "6", "+", "1", "2", "3", "-", "0", "", "", "="};
	Font font; 
	
	Hw4InputPanel(Hw4OutputPanel outputPanel){
		this.outputPanel = outputPanel;
		setLayout(new GridLayout(4, 4));
		font = new Font("Berlin Sans FB", Font.BOLD, 40);
		
		butArr = new Hw4CalButton[16];
		for(int i = 0; i < butArr.length; i++) {
			butArr[i] = new Hw4CalButton(strArr[i], outputPanel);
			butArr[i].setFont(font);
			if(butArr[i].getText() == "C") butArr[i].setForeground(Color.RED);
			add(butArr[i]);
		}		
	}
}

class Hw4OutputPanel extends JPanel{
	String numStr;
	
	Hw4OutputPanel(){
		setPreferredSize(new Dimension(200, 100));
		numStr = "0";
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int x = getX()+10;
		int y = getY()+10;
		int width = getWidth()-20;
		int height = getHeight()-20;

		Graphics2D g2 = (Graphics2D)g;
		GradientPaint gp = new GradientPaint(getWidth()/2, getY(), new Color(150, 150, 150), 
				getWidth()/2, getHeight(), new Color(100, 100, 100));
		g2.setPaint(gp);
		g2.fillRoundRect(getX(), getY(), getWidth(), getHeight(), 5, 5);
		
		gp = new GradientPaint(x+width/2, y, new Color(100, 110, 100), 
				x+width/2, y+height, new Color(150, 160, 150));
		g2.setPaint(gp);
		g2.fillRoundRect(x, y, width, height, 5, 5);
			
		g.setFont(new Font("Arial", Font.BOLD, 40));
		FontMetrics fontMetrics = g2.getFontMetrics();	
		g2.setColor(Color.WHITE);
		g2.drawString(numStr, x+width-50 - fontMetrics.stringWidth(numStr), y+height/8*6+1);
		g.setColor(Color.BLACK);
		g2.drawString(numStr, x+width-50 - fontMetrics.stringWidth(numStr), y+height/8*6);
	}
}


class Hw4MainPanel extends JPanel{	
	Hw4OutputPanel outputPanel;
	Hw4MainPanel(){	
		outputPanel = new Hw4OutputPanel();
		
		setLayout(new BorderLayout());
		add(new Hw4InputPanel(outputPanel), BorderLayout.CENTER);
		add(outputPanel, BorderLayout.NORTH);
	}
}


public class Hw4 extends JFrame{
	public static void main(String[] args) {
		new Hw4();
	}
	Hw4(){
		setTitle("Calculator");
		setSize(400, 400);	
		this.setMinimumSize(new Dimension(300, 300));
		
		add(new Hw4MainPanel());	
		
		setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
