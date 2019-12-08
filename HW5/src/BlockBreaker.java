import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

class MyRunnable implements Runnable {
	SceneGame sg;
	int direction;  // 0 : left,  1 : right
	
	public MyRunnable(SceneGame sg, int direction) {
       this.sg = sg;
       this.direction = direction;
	}
	public void run() {
		try {
			while(true) {
				if(direction == 0) {
					if(sg.bar.barLocX > Variable.WALL_THICKNESS + Variable.WALL_CORRECTION)
						sg.bar.barLocX -= Variable.BAR_DIST;
				}
				else if(direction == 1) {
					if(sg.bar.barLocX + Variable.BAR_WIDTH < Variable.WIDTH - Variable.WALL_THICKNESS - Variable.WALL_CORRECTION)
						sg.bar.barLocX += Variable.BAR_DIST;
				}
				Thread.sleep(Variable.BAR_REPAINT_DELAY);
			}			
		} catch (InterruptedException e) {
		}
	}
}

public class BlockBreaker extends JFrame implements KeyListener {
	static int HIGH_SCORE = 0;
	int sceneID;
	public Scene nowScene;
	boolean keyPressedFlag = false;	
	Thread keyboardPressedThread;
	boolean sceneFlag = false;
	
	public static void main(String[] args) {
		new BlockBreaker();
	}
	
	BlockBreaker(){
		setTitle("BlockBreaker");
		setSize(Variable.WIDTH, Variable.HEIGHT);
		setResizable(false);
		this.addKeyListener(this);
		
		Play("/start.wav");
		
		sceneID = 0;
		nowScene = new SceneStart();
		add(nowScene);
		
		setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// Scene Change
		if(isntSceneGame() && !sceneFlag) {
			sceneFlag = true;
			changeScene();
			System.out.println("change scene");
		}
		
		// keyboard control with Thread
		if(nowScene instanceof SceneGame && !keyPressedFlag) {
			SceneGame sg = (SceneGame)nowScene;
			int direction = 0;
			if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				//System.out.println("LEFT");
				keyPressedFlag = true;
				direction = 0;
				keyboardPressedThread = new Thread(new MyRunnable(sg, direction));
				keyboardPressedThread.start();
			}
			else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				//System.out.println("RIGHT");
				keyPressedFlag = true;
				direction = 1;
				keyboardPressedThread = new Thread(new MyRunnable(sg, direction));
				keyboardPressedThread.start();
			}
			sg.revalidate();
			sg.repaint();
			sg.bar.revalidate();
			sg.bar.repaint();
		}
	}
	public void keyReleased(KeyEvent e) {
			keyboardPressedThread.interrupt();
			keyPressedFlag = false;
			sceneFlag = false;
	}
	public void keyTyped(KeyEvent e) {		
	}
	
	public void changeScene() {
		nowScene.removeAll();
		revalidate();
		repaint();
		remove(nowScene);
		sceneID = (sceneID + 1) % 3;
		switch(sceneID) {
		case 0:
			nowScene = new SceneStart();
			break;
		case 1:
			nowScene = new SceneGame(this);
			break;
		case 2:
			nowScene = new SceneOver();
			break;
		}
		add(nowScene);
		revalidate();
		repaint();
	}
	
	private boolean isntSceneGame() {
		return !(nowScene instanceof SceneGame);
	}
	
	public void Play(String fileName)
    {
        try
        {
        	AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResource(fileName));     	
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start(); 
        }
        catch (Exception ex)
        {
        	ex.getMessage();
        }
    }
}
