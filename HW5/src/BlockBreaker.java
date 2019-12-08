import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

// �ʼ�
	// IMAGE, SOUND ADDITION
	// ������ ��� �������� �� �κ��� �ϴ� �ϰ� ������� ���ؼ� ����   + ����

// ���� �Ǹ�  .. �������� �ƽ��������� ����� (���������� �׸� ũ������)
	//variable�� ���� �ִ� ������ class�� �ֱ�  (���� : frame������ ����ϴ� ������ x)
	//x y w h �� rect�� �ֱ�
	//�߻����� int���� enum���� �ǹ� �ο� �ϱ�
    //���ȭ �ϱ�   ex) blockCollisionDetector

// �������
    // swing�� �θ� �ڽ� ���谡 �ʹ� ��ƴ�. 
    //   => add��� ���䶧���� �Ϲ����� Ŭ���� �󿡼��� �θ� �ڽ� ���谡 ȥ��������
    //   => �߸� ������.. �ν��Ͻ������� ������ ���� ���� Ŭ�������� �ؾ� �ϴµ�
    //      �ڲ� ������ ������ ó���� �Ϸ��� �ؼ� ��������
    //      �ѹ� ���̰��� ������ �Ը� Ŀ������ ������������ �޶�
    //   => �ڹ��� gc��ɿ� ���� ����������Ŭ�� ��ȣ�� + ���� ������ + ���������� ������ ��� �� ��ġ�� 
    //      ������ �������� ���������� �����ϱⰡ �������
	//   => �Ŵ��� ������Ʈ���� ��� ����Ǿ������� � ��å�� ���ϴ��� ���� ����� ��
	//   => ���� : �� ���ظ� ���ϴ� ���¿���  �ż��ϰ� ����� �߰��ؾ��ϴ� ���, ����� �������� �ʰ� �ָԱ����� �ϴٺ��� �����̴��
	//      �ذ�� : �Ｎ���� �߰����� �ʴ´�. ó������ ������ �Ϻ��� ��� ����. �������� ��� �����ؼ� �߰�
    //             ��ü ������ ���̻� ������ �� ������ ���� �ڵ带 ó������ �ٽ� ¥�°� ���� (�����丵)
	//      ���� ����or������ ���� ���꼺�� �������� �������� ���� ���̻��ߵȴ�..
	//   �� ������ �ٺ��� ���� : ȸ�簡�� �Ϲ������� ¥���� Ʋ�� �ְ� �� ��Ÿ���� ����� ���߸� ��.
    //                     ������ �Ҹ�Ȯ�� ����
	//   ������ ����� ���� �߰��ϱ⺸�ٴ� �ϴ� �������� ��Ȯ�ϰ� ��� �����ؾ���

    //  ball���� ���� ���ͽ�ߵ�... ���� �Ⱦ��ϱ� ������ ���� ���� ���� �����ؾߴ�
    //  ���� �и��Ǿ��־ ���߿� �����ϱ⵵ �����, �ڵ� ������ �������...
    //  �ٵ� �ϴ� �ð��� ��� ����ħ

	// �̹��� Ȯ���� ������...  ����� �߰��ϴ°� ��¥ ��~~~�� �� �߿��ϴ�.
	// �ѹ� ��ĥ�� Ȯ���� ���Ķ� . �ѹ�����Ҷ� ��� ����ض� (�ð� ���� ����)
	// �ᱹ �ʱ� ���迡 ���� �ð���  ���� �������� �ð��� �ٿ��ش� (Ư�� ū �Ը� ������Ʈ�ϼ���)
// ����
    // �� ������ �����   ->  intersect���� ���� x,y,w,h �������� ó���ϸ� �ذ� ����
    // ������������ �Ѿ�� �浹�� �ʰ� ���۵� 
	// 

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
			//e.printStackTrace();
		}
	}
}

public class BlockBreaker extends JFrame implements KeyListener {
	static int HIGH_SCORE = 0;
	int sceneID;
	Scene nowScene;
	boolean keyPressedFlag = false;	
	Thread keyboardPressedThread;
	
	public static void main(String[] args) {
		new BlockBreaker();
	}
	
	BlockBreaker(){
		setTitle("BlockBreaker");
		setSize(Variable.WIDTH, Variable.HEIGHT);
		setResizable(false);
		this.addKeyListener(this);
		
		sceneID = 0;
		nowScene = new SceneStart();
		add(nowScene);
		
		setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// Scene Change
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(nowScene instanceof SceneGame) {
				SceneGame sg = (SceneGame)nowScene;
				sg.gameoverFlag = true;
			}
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
				nowScene = new SceneGame();
				break;
			case 2:
				nowScene = new SceneOver();
				break;
			}
			add(nowScene);
			revalidate();
			repaint();
		}
		
		// keyboard control with Thread
		if(nowScene instanceof SceneGame && !keyPressedFlag) {
			SceneGame sg = (SceneGame)nowScene;
			int direction = 0;
			if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				keyPressedFlag = true;
				System.out.println("LEFT");
				direction = 0;
				keyboardPressedThread = new Thread(new MyRunnable(sg, direction));
				keyboardPressedThread.start();
			}
			else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				keyPressedFlag = true;
				System.out.println("RIGHT");
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
	}
	public void keyTyped(KeyEvent e) {		
	}
}
