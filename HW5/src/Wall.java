import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;

public class Wall extends JComponent {
	Rectangle wallRect;
	
	Wall(int x, int y, int w, int h){
		this.setBounds(0, 0, Variable.WIDTH, Variable.HEIGHT);
		
		wallRect = new Rectangle(x, y, w, h);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		// draw walls
		g2.setColor(Color.GRAY);
		g2.fillRect(wallRect.x, wallRect.y, wallRect.width, wallRect.height);		
	}	
}
