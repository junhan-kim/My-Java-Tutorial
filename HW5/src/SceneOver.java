import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class SceneOver extends Scene{
	JLabel[] labelArr;
	Thread textBlinkThread;
	Image img;
	
	SceneOver(){
		System.out.println("SceneOver started");
		 setLayout(null);  // setLayout + setBounds
		
		if(Variable.MY_SCORE > BlockBreaker.HIGH_SCORE)
			BlockBreaker.HIGH_SCORE = Variable.MY_SCORE;
		
		labelArr = new JLabel[4];
		labelArr[0] = new JLabel("GAME OVER");
		labelArr[1] = new JLabel("HIGH SCORE:" + BlockBreaker.HIGH_SCORE);
		labelArr[2] = new JLabel("YOUR SCORE:" + Variable.MY_SCORE);
		labelArr[3] = new JLabel("PRESS SPACEBAR!");
		
		labelArr[0].setBounds(0, Variable.HEIGHT/10*3, Variable.WIDTH, Variable.HEIGHT/9);
		labelArr[1].setBounds(0, Variable.HEIGHT/10*5, Variable.WIDTH, Variable.HEIGHT/9);
		labelArr[2].setBounds(0, Variable.HEIGHT/10*6, Variable.WIDTH, Variable.HEIGHT/8);
		labelArr[3].setBounds(0, Variable.HEIGHT/10*8, Variable.WIDTH, Variable.HEIGHT/8);
		
		labelArr[0].setFont(new Font("Serif", Font.BOLD, 70));
		labelArr[1].setFont(new Font("Andalus", Font.BOLD, 30));
		labelArr[2].setFont(new Font("Andalus", Font.BOLD, 30));
		labelArr[3].setFont(new Font("Arial", Font.BOLD, 40));
		
		labelArr[0].setForeground(Color.WHITE);
		labelArr[1].setForeground(Color.DARK_GRAY);
		labelArr[2].setForeground(Color.DARK_GRAY);
		labelArr[3].setForeground(Color.RED);
		
		
		
		String imageResource = "/thunder.jpg";
		Image myImage = null;
		try {
			myImage = ImageIO.read(getClass().getResourceAsStream(imageResource));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ImageIcon icon = new ImageIcon(myImage);
		img = icon.getImage();	
		
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
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(),getHeight(), null);
	}
}
