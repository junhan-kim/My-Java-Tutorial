import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;

public class SceneStart extends Scene{
	JLabel[] labelArr;
	Thread textBlinkThread;
	Thread titleAnimationThread;
	
	SceneStart(){
		System.out.println("SceneStart started");
		
		setLayout(null);  // setLayout + setBounds
		
		labelArr = new JLabel[4];
		labelArr[0] = new JLabel("Java Programming");
		labelArr[1] = new JLabel("Homework #5");
		labelArr[2] = new JLabel("BLOCK BREAKER");
		labelArr[3] = new JLabel("PRESS SPACEBAR TO PLAY!");
		
		labelArr[0].setBounds(0, Variable.HEIGHT/10*1, Variable.WIDTH, Variable.HEIGHT/9);
		labelArr[1].setBounds(0, Variable.HEIGHT/10*2, Variable.WIDTH, Variable.HEIGHT/9);
		labelArr[2].setBounds(0, Variable.HEIGHT/10*4, Variable.WIDTH, Variable.HEIGHT/8);
		labelArr[3].setBounds(0, Variable.HEIGHT/10*7, Variable.WIDTH, Variable.HEIGHT/8);
		
		labelArr[0].setFont(new Font("Serif", Font.BOLD, 40));
		labelArr[1].setFont(new Font("Serif", Font.BOLD, 40));
		labelArr[2].setFont(new Font("Comic Sans MS", Font.BOLD, 70));
		labelArr[3].setFont(new Font("Arial", Font.BOLD, 50));
		
		labelArr[0].setForeground(Color.WHITE);
		labelArr[1].setForeground(Color.WHITE);
		labelArr[2].setForeground(Color.WHITE);
		labelArr[3].setForeground(Color.RED);
			
		for(int i = 0; i < labelArr.length; i++) {
			labelArr[i].setHorizontalAlignment(JLabel.CENTER);
			add(labelArr[i]);
		}
		
		textBlinkThread = new Thread(()->{
			try {
				while(true) {
					labelArr[3].setVisible(true);
					Thread.sleep(300);
					labelArr[3].setVisible(false);
					Thread.sleep(150);	
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		textBlinkThread.start();
		
		titleAnimationThread = new Thread(()->{
			int change = 0;
			boolean up = true;  // flag
			while(true) {
				try {
					if(change > 10) up = false;
					if(change < -10) up = true;
					if(up) change++; 
					else change--;
					labelArr[2].setFont(new Font("Comic Sans MS", Font.BOLD, 70 + change));
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		titleAnimationThread.start();
	}
}
