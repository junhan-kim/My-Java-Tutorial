import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;

public class Bar extends JComponent{
	public int barLocX;  // bar location x
	public int barLocY;  // bar location y
	Rectangle barRect;
	
	Bar(){
		this.setBounds(0, 0, Variable.WIDTH, Variable.HEIGHT);
		
		barLocX = Variable.WIDTH/2 - Variable.BAR_WIDTH/2;
		barLocY = Variable.BAR_Y;
		barRect = new Rectangle();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;

		// draw bar background
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		g2.setColor(Color.WHITE);
		g2.fillRoundRect(barLocX-2, barLocY-2, 
				Variable.BAR_WIDTH+4, Variable.BAR_HEIGHT+4, 
				Variable.BAR_ARC, Variable.BAR_ARC);
		barRect.setRect(barLocX-2, barLocY-2, Variable.BAR_WIDTH+4, Variable.BAR_HEIGHT+4);
		
		// draw bar
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
		GradientPaint gp = new GradientPaint(barLocX+Variable.BAR_WIDTH/2, barLocY, new Color(200, 75, 0),
				barLocX+Variable.BAR_WIDTH/2, barLocY+Variable.BAR_HEIGHT, new Color(50, 30, 19));
		g2.setPaint(gp);
		g2.fillRoundRect(barLocX, barLocY, 
				Variable.BAR_WIDTH, Variable.BAR_HEIGHT, 
				Variable.BAR_ARC, Variable.BAR_ARC);		
	}
}
