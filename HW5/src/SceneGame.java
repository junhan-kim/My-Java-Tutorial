import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;

public class SceneGame extends Scene{
	int stage;
	int blockCnt;
	public Bar bar;
	public Block[][] blockArr;
	public Vector<Ball> ballArr;
	public Wall[] wallArr;
	boolean barIntersectFlag = false;
	boolean wallIntersectFlag = false;
	Thread blockCollisionDetector;
	Thread wallCollisionDetector;
	Thread wallDelay;
	Thread barCollisionDetector;
	Thread nextStage;
	Thread GameoverDetector;
	Robot r;
	boolean gameoverFlag = false;
	boolean gameClearFlag = false;
	
	SceneGame(){
		System.out.println("SceneGame started");	
		this.setLayout(null);	
		Variable.MY_SCORE = 0;
		
		stage = 1;
		blockCnt = stage*3*stage*3;
		blockArr = new Block[stage*3][stage*3];	
		for(int i = 0; i < blockArr.length; i++) {
			blockArr[i] = new Block[stage*3];
			for(int j = 0; j < blockArr[i].length; j++) {
				int type = (int)(Math.random()*Block.SPECIAL_BLOCK_CHANCE);
				blockArr[i][j] = new Block(type, stage, i, j);
				add(blockArr[i][j]);
			}			
		}
			
		bar = new Bar();
		add(bar);
		
		ballArr = new Vector<Ball>();
		ballArr.add(new Ball());
		add(ballArr.firstElement());
		
		wallArr = new Wall[3];
		wallArr[0] = new Wall(0, 0, Variable.WIDTH, Variable.WALL_THICKNESS);  // upper
		wallArr[1] = new Wall(0, 0, Variable.WALL_THICKNESS, Variable.HEIGHT);  // left
		wallArr[2] = new Wall(Variable.WIDTH-(Variable.WALL_THICKNESS+Variable.WALL_CORRECTION), 0, Variable.WALL_THICKNESS, Variable.HEIGHT);  // right
		for(int i = 0; i < wallArr.length; i++) {
			add(wallArr[i]);
		}
		
		// polling for Collision
		blockCollisionDetector = new Thread(()->{
			try {
				while(true) {
					// detecting collision with block
					for(int i = 0; i < blockArr.length; i++) {
						for(int j = 0; j < blockArr[i].length; j++) {
							for(Ball b : ballArr) {
								if(blockArr[i][j].isValid() && b.ballRect.intersects(blockArr[i][j].blockRect)) {
									if(b.y <= blockArr[i][j].rBlockY +     // bottom
											blockArr[i][j].rBlockHeight
											 + Variable.BALL_COLLISION_MARGIN) {
										b.distY = -b.distY;
									}
									else if(b.x + Variable.BALL_SIZE >= blockArr[i][j].rBlockX    // left
											- Variable.BALL_COLLISION_MARGIN) {
										b.distX = -b.distX;
									}
									else if(b.y + Variable.BALL_SIZE >= blockArr[i][j].rBlockY    // top
											- Variable.BALL_COLLISION_MARGIN) {
										b.distY = -b.distY;
									}
									else if(b.x <= blockArr[i][j].rBlockX +   // right
											blockArr[i][j].rBlockWidth  
											+ Variable.BALL_COLLISION_MARGIN) {
										b.distX = -b.distX;
									}
									System.out.println("block intersect");
									barIntersectFlag = false;
									
									Variable.MY_SCORE += 10;
									blockCnt--;
									remove(blockArr[i][j]);
									System.out.println(blockCnt);
									
									
									// ball splits 3 balls
									if(blockArr[i][j].type == 0) {
										System.out.println("special block");
										//remove(ball);
	
										//ball = new Ball();
											//ball[0].deg
										//	ball[1].deg = Variable.getNormalizedDeg(ball[1].deg + 180);
											//ball[2].deg
										//	add(ball);	
										//}													
									}
									
									
									// next stage
									if(blockCnt <= 0) {
										if(stage >= 3) gameClearFlag = true;
										stage++;
										blockCnt = stage*3*stage*3;
										
										blockArr = new Block[stage*3][stage*3];	
										for(int l = 0; l < blockArr.length; l++) {
											blockArr[l] = new Block[stage*3];
											for(int m = 0; m < blockArr[l].length; m++) {
												int type = (int)(Math.random()*Block.SPECIAL_BLOCK_CHANCE);
												blockArr[l][m] = new Block(type, stage, l, m);
												add(blockArr[l][m]);
											}			
										}
										
										ballArr.add(new Ball());
										add(ballArr.firstElement());
										
										remove(bar);
										bar = new Bar();
										add(bar);
									}
								}
							}
						}
					}
					Thread.sleep(0);
				}	
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		blockCollisionDetector.start();
		
		wallCollisionDetector = new Thread(()->{
			try {
				while(true) {
					// detecting collision with wall
					for(int i = 0; i < wallArr.length; i++) {
						for(Ball b : ballArr) {
							if(!wallIntersectFlag && b.ballRect.intersects(wallArr[i].wallRect)) {
								barIntersectFlag = false;
								wallDelay = new Thread(()->{
									try {
										wallIntersectFlag = true;
										Thread.sleep(50);
										wallIntersectFlag = false;
									} catch (InterruptedException e) {
									}
								});
								wallDelay.start();
								if(wallArr[i] == wallArr[0]) {
									b.distY = -b.distY;
								}
								else {
									b.distX = -b.distX;
								}
								System.out.println("wall intersect");
							}
						}
					}
					Thread.sleep(1);
				}			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		wallCollisionDetector.start();
		
		barCollisionDetector = new Thread(()->{
			try {
				while(true) {
					// detecting collision with bar
					for(Ball b : ballArr) {
						if(!barIntersectFlag && b.ballRect.intersects(bar.barRect)) {
							System.out.println("ball and bar are intersected!!");
							barIntersectFlag = true;
							b.deg = (int)(((double)(b.x - bar.barLocX) / Variable.BAR_WIDTH) 
									* (Variable.MAX_DEGREE/2 - 20)) + Variable.MAX_DEGREE/2 + 40;
							b.distY = -b.distY;
							System.out.println(b.deg);
							
						}
					}
					Thread.sleep(5);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		barCollisionDetector.start();
		
		GameoverDetector = new Thread(()->{
			try {
				while(true) {
					for(Ball b : ballArr) {
						if(Ball.ballCnt == 0) {
							gameoverFlag = true;
							r.keyPress(KeyEvent.VK_SPACE);	
						}
						if((!gameoverFlag && b.y > Variable.HEIGHT)
							|| gameClearFlag) {
							Ball.ballCnt--;
							remove(b);
						}		
					}
					Thread.sleep(100);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		});
		GameoverDetector.start();
		
		
		try {
			r = new Robot();
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
	}
}
