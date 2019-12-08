import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

// this class is for Global Variable Sharing
public class Variable {
	static final int WIDTH = 800;
	static final int HEIGHT = 800;
	static int MY_SCORE = 0;
	
	static final int BAR_Y = HEIGHT/10*8;
	static final int BAR_WIDTH = 150;
	static final int BAR_HEIGHT = 30;
	static final int BAR_ARC = 5;
	static final int BAR_DIST = 10;
	static final int BAR_REPAINT_DELAY = 10;
	
	static final int WALL_THICKNESS = 10;
	static final int WALL_CORRECTION = 5;
	
	static int BALL_DIST_X = 4;
	static int BALL_DIST_Y = 4;
	static final int BALL_SIZE = 10;
	static final int BALL_REPAINT_DELAY = 8;
	static final int BALL_COLLISION_MARGIN = 10;
	
	static final int MAX_DEGREE = 360;	
	
	static int getRandDeg(int lowBound, int highBound) {
		int range = highBound - lowBound;
		return (int)(Math.random()*range+1 + lowBound);
	}
	static int getNormalizedDeg(int deg) {
		int finalAngle = deg % 360;
		if( finalAngle < 0 ) 
		{
		     finalAngle += 360;
		}
		return finalAngle;
	}
}
