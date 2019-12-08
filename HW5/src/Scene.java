import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

abstract public class Scene extends JPanel {	
	Scene(){}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// paint background
		Graphics2D g2 = (Graphics2D)g;
		GradientPaint gp = new GradientPaint(
				Variable.WIDTH/2, 0, new Color(0, 0, 0),
				Variable.WIDTH/2, Variable.HEIGHT, new Color(200, 200, 255));
		g2.setPaint(gp);
		g2.fillRect(0, 0, Variable.WIDTH, Variable.HEIGHT);	
	}
}
