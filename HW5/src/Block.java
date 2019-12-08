import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;

public class Block extends JComponent {
	static final int SPECIAL_BLOCK_CHANCE = 5;  // more higher, more often
	
	int type;  // 0 : special,  other : special
	int stage;   // 1 ~ 3
	int iParam;   // i & j are blocks ID
	int jParam;
	int rBlockX;
	int rBlockY;
	int rBlockWidth;
	int rBlockHeight;
	Rectangle blockRect;
	static int blockCnt = 0;	
	Thread blockBlinkThread;
	boolean blinkFlag = false;
	
	Block(int type, int stage, int i, int j){
		this.setBounds(0, 0, Variable.WIDTH, Variable.HEIGHT);
				
		this.type = type;
		this.stage = stage;
		this.iParam = i;
		this.jParam = j;
		blockRect = new Rectangle();
		blockCnt++;
		
		blockBlinkThread = new Thread(()->{
			try {
				int startDelay = (int)(Math.random()*3000);
				Thread.sleep(startDelay);
				while(true) {
					blinkFlag = false;
					repaint();
					Thread.sleep(3000);			
					blinkFlag = true;
					repaint();
					Thread.sleep(100);	
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		if(type == 0) blockBlinkThread.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		// draw blocks
		// .. prefix 'r' means variable for rendering
		// .. no 'r' means variable for calculating
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

		int blockWidth = (Variable.WIDTH-(Variable.WALL_THICKNESS+Variable.WALL_THICKNESS+5))/(stage*3);
		int blockHeight = (Variable.HEIGHT/2-Variable.WALL_THICKNESS)/(stage*3);
		
		// draw block's background
		rBlockX = (Variable.WALL_THICKNESS + blockWidth*jParam);
		rBlockY = (Variable.WALL_THICKNESS + blockHeight*iParam);
		rBlockWidth = blockWidth;
		rBlockHeight = blockHeight;	
		g2.setColor(Color.WHITE);
		g2.fillRect(rBlockX, rBlockY, rBlockWidth, rBlockHeight);	
		blockRect.setRect(rBlockX, rBlockY, rBlockWidth, rBlockHeight);
			
		// draw block
		rBlockX = (Variable.WALL_THICKNESS + blockWidth*jParam) + 5;
		rBlockY = (Variable.WALL_THICKNESS + blockHeight*iParam) + 5;
		rBlockWidth = blockWidth - 10;
		rBlockHeight = blockHeight - 10;
		if(type == 0 && !blinkFlag) g2.setPaint(new Color(252, 212, 64));
		else if(type == 0 && blinkFlag)	g2.setPaint(new Color(237, 244, 4));
		else g2.setPaint(Color.MAGENTA);
		g2.fillRect(rBlockX, rBlockY, rBlockWidth, rBlockHeight);	
	}
	
	public static int getNewBlockType() {
		return (int)(Math.random()*SPECIAL_BLOCK_CHANCE);
	}
}