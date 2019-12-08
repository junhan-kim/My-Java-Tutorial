import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

// 필수
	// IMAGE, SOUND ADDITION
	// 보고서는 기능 명세식으로 각 부분이 하는 일과 구현방식 대해서 설명   + 과정

// 여유 되면  .. 못했으면 아쉬운점에다 쓰면됨 (점수배점은 그리 크지않음)
	//variable로 쓰고 있는 변수들 class로 넣기  (주의 : frame에서도 써야하는 변수는 x)
	//x y w h 를 rect로 넣기
	//추상적인 int값을 enum으로 의미 부여 하기
    //모듈화 하기   ex) blockCollisionDetector

// 어려운점
    // swing의 부모 자식 관계가 너무 어렵다. 
    //   => add라는 개념때문에 일반적인 클래스 상에서의 부모 자식 관계가 혼란스러움
    //   => 잘못 설계함.. 인스턴스끼리의 조작을 상위 레벨 클래스에서 해야 하는데
    //      자꾸 생성자 내에서 처리를 하려고 해서 꼬여버림
    //      한번 꼬이고나면 플젝의 규모가 커질수록 유지보수성이 급락
    //   => 자바의 gc기능에 의한 라이프사이클의 모호함 + 여러 쓰레드 + 비정상적인 접근의 허용 이 겹치니 
    //      구조가 마구마구 꼬여버려서 관리하기가 힘들어짐
	//   => 거대한 프로젝트들이 어떻게 설계되어있으며 어떤 정책을 취하는지 보고 배워야 됨
	//   => 이유 : 잘 이해를 못하는 상태에서  신속하게 기능을 추가해야하는 경우, 제대로 이해하지 않고 주먹구구로 하다보니 개판이댄다
	//      해결법 : 즉석에서 추가하지 않는다. 처음부터 구조를 완벽히 잡고 시작. 느리더라도 깊게 생각해서 추가
    //             전체 구조가 더이상 겉잡을 수 없을떈 차라리 코드를 처음부터 다시 짜는게 낫다 (리팩토링)
	//      역시 고전or저수준 언어는 생산성이 떨어지고 쓸데없이 힘을 많이빼야된다..
	//   이 문제의 근본적 원인 : 회사가면 일반적으로 짜여진 틀이 있고 그 스타일을 배워서 맞추면 됨.
    //                     스윙의 불명확한 구조
	//   세부적 기능을 마구 추가하기보다는 일단 구조부터 명확하게 잡고 시작해야함

    //  ball에는 원래 벡터써야됨... 벡터 안쓰니깐 사이즈 변수 따로 만들어서 관리해야댐
    //  따로 분리되어있어서 나중에 관리하기도 힘들고, 코드 리딩도 힘들어짐...
    //  근데 일단 시간이 없어서 못고침

	// 이번에 확실히 느낀점...  기능을 추가하는건 진짜 전~~~혀 안 중요하다.
	// 한번 고칠때 확실히 고쳐라 . 한번고민할때 깊게 고민해라 (시간 많이 들어도됨)
	// 결국 초기 설계에 들인 시간이  나중 유지보수 시간을 줄여준다 (특히 큰 규모 프로젝트일수록)
// 에러
    // 벽 가끔씩 통과됨   ->  intersect말고 직접 x,y,w,h 조건으로 처리하면 해결 가능
    // 다음스테이지 넘어가면 충돌이 늦게 시작됨 
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
