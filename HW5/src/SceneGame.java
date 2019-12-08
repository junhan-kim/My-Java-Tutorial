import java.util.Vector;

public class SceneGame extends Scene{
	int stage;
	int blockCnt;
	public Bar bar;
	public Block[][] blockArr;
	public Vector<Ball> ballArr;
	public Wall[] wallArr;
	Thread blockCollisionDetector;
	Thread wallCollisionDetector;
	Thread barCollisionDetector;
	Thread ballEnterIntoLowerBoundDetector;
	Thread nextStage;
	Thread GameoverDetector;
	Thread wallDelay;
	BlockBreaker blockBreaker;
	boolean nextStageFlag = false;
	
	SceneGame(BlockBreaker blockBreaker){
		System.out.println("SceneGame started");	
		this.setLayout(null);	
		Variable.MY_SCORE = 0;
		this.blockBreaker = blockBreaker;
		stage = 1;	
		
		initGameStage(stage);			
		initBar();		
		ballArr = new Vector<Ball>();
		initBall();	
		initWall();
		
		// polling for detecting collision with blocks
		blockCollisionDetector = new Thread(()->{
			try {
				while(true) {
					for(int i = 0; i < blockArr.length; i++) {
						for(int j = 0; j < blockArr[i].length; j++) {
							synchronized(ballArr) {
								for(int k = 0; k < ballArr.size(); k++) {
									Ball ball = ballArr.elementAt(k);
									if(blockArr[i][j].isValid() && ball.ballRect.intersects(blockArr[i][j].blockRect)) {
										System.out.println("block intersect");	
										blockBreaker.Play("resources/boom.wav");
										processCollisionBallAndBlock(ball, blockArr[i][j]);				
										processRemoveBlock(blockArr[i][j]);
										ball.barCollisionFlag = false;
													
										// ball is split to 3 balls
										if(blockArr[i][j].type == 0) {					
											System.out.println("special block");		
											blockBreaker.Play("/special.wav");
											splitBall(ball, k);
										}
										
										System.out.println(blockCnt);
										// next stage
										if(blockCnt <= 0) {
											blockBreaker.Play("/victory.wav");
											setNextStage();
										}
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
		
		
		// detecting collision with wall
		wallCollisionDetector = new Thread(()->{
			try {
				while(true) {		
					synchronized(ballArr) {
						for(int i = 0; i < ballArr.size(); i++) {
							Ball ball = ballArr.elementAt(i);
							if((ball.x < Variable.WALL_THICKNESS || ball.y < Variable.WALL_THICKNESS || ball.x > Variable.WIDTH - Variable.WALL_THICKNESS))
							{
								if(ball.x < Variable.WALL_THICKNESS) { 
									ball.distX = -ball.distX;
								}
								if(ball.y < Variable.WALL_THICKNESS) {
									ball.distY = -ball.distY;
								}
								if(ball.x > Variable.WIDTH - Variable.WALL_THICKNESS)  {
									ball.distX = -ball.distX;
								}
								ball.barCollisionFlag = false;
							}
						}
					}
					Thread.sleep(0);
				}			
			} catch (InterruptedException e) {
			}
		});
		wallCollisionDetector.start();
		
		
		// detecting collision with bar
		barCollisionDetector = new Thread(()->{
			try {
				while(true) {
					synchronized(ballArr) {
						for(Ball ball : ballArr) {
							if(!ball.barCollisionFlag && ball.ballRect.intersects(bar.barRect)) {
								ball.barCollisionFlag = true;
								System.out.println("bar intersect");
								ball.deg = getBallDegInCollisionWithBar(ball);
								System.out.println("ball.deg : " + ball.deg);
								ball.distY = -ball.distY;	
							}
						}
					}
					Thread.sleep(0);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		barCollisionDetector.start();
		
		ballEnterIntoLowerBoundDetector = new Thread(()->{
			while(true) {
				synchronized (ballArr) {
					for(int i = 0; i < ballArr.size(); i++) {
						Ball nowBall = ballArr.elementAt(i);
						if(nowBall.y > Variable.HEIGHT) {
							remove(nowBall);
							ballArr.remove(i);
						}
					}
				}
			}
		});
		ballEnterIntoLowerBoundDetector.start();
		
		
		GameoverDetector = new Thread(()->{			
			boolean stop = false;
			try {
				while(!stop) {
					if(!nextStageFlag && ballArr.size() == 0) {
						System.out.println("gameover");
						blockBreaker.changeScene();
						blockBreaker.Play("/over.wav");
						GameoverDetector.interrupt();
					}		
					Thread.sleep(1000);
				}	
			} catch (InterruptedException e) {
				stop = true;
			}
		});
		GameoverDetector.start();
		
	}
	
	private void initGameStage(int stage) {
		int rowColumns = stage * 3;
		blockCnt = rowColumns * rowColumns;
		blockArr = new Block[rowColumns][rowColumns];	
		for(int i = 0; i < blockArr.length; i++) {
			blockArr[i] = new Block[rowColumns];
			for(int j = 0; j < blockArr[i].length; j++) {
				blockArr[i][j] = new Block(Block.getNewBlockType(), stage, i, j);
				add(blockArr[i][j]);
			}			
		}
	}	
	private void initBar() {
		bar = new Bar();
		add(bar);
	}	
	private void initBall() {
		ballArr.add(new Ball());
		add(ballArr.firstElement());
	}	
	private void initWall() {
		wallArr = new Wall[3];
		wallArr[0] = new Wall(0, 0, Variable.WIDTH, Variable.WALL_THICKNESS);  // upper
		wallArr[1] = new Wall(0, 0, Variable.WALL_THICKNESS, Variable.HEIGHT);  // left
		wallArr[2] = new Wall(Variable.WIDTH-(Variable.WALL_THICKNESS+Variable.WALL_CORRECTION), 0, Variable.WALL_THICKNESS, Variable.HEIGHT);  // right
		for(int i = 0; i < wallArr.length; i++) {
			add(wallArr[i]);
		}
	}
	 
	private int getBallDegInCollisionWithBar(Ball b) {    
		// change degree 
		int ret = (int)((b.x - bar.barLocX) + 10 + Variable.MAX_DEGREE/2);
		if(ret < 200) ret = 200;
		if(ret > 340) ret = 340;
		return ret;
	}
	
	private void processCollisionBallAndBlock(Ball ball, Block block) {
		if(ball.y <= block.rBlockY +     // bottom
				block.rBlockHeight
				 + Variable.BALL_COLLISION_MARGIN) {
			ball.distY = -ball.distY;
		}
		else if(ball.x + Variable.BALL_SIZE >= block.rBlockX    // left
				- Variable.BALL_COLLISION_MARGIN) {
			ball.distX = -ball.distX;
		}
		else if(ball.y + Variable.BALL_SIZE >= block.rBlockY    // top
				- Variable.BALL_COLLISION_MARGIN) {
			ball.distY = -ball.distY;
		}
		else if(ball.x <= block.rBlockX +   // right
				block.rBlockWidth  
				+ Variable.BALL_COLLISION_MARGIN) {
			ball.distX = -ball.distX;
		}
	}
	private void processRemoveBlock(Block block) {
		Variable.MY_SCORE += 10;
		blockCnt--;
		remove(block);
	}
	
	private void splitBall(Ball ball, int idx) {
		int x = ball.x;
		int y = ball.y;
		int distX = ball.distX;
		int distY = ball.distY;
		int deg = ball.deg;
		
		Ball[] balls = new Ball[3];
		balls[0] = new Ball();
		balls[0].setMovingContext(x, y, distX, distY, deg - 25);
		balls[1] = new Ball();
		balls[1].setMovingContext(x, y, distX, distY, deg);
		balls[2] = new Ball();
		balls[2].setMovingContext(x, y, distX, distY, deg + 25);
		
		for(Ball b : balls) {
			synchronized(ballArr){
				ballArr.add(b);
			}
			add(b);
		}
		
		ballArr.remove(idx);
		remove(ball);
		System.out.println(ballArr.size());
	}
	
	private void setNextStage() {
		if(stage >= 3) blockBreaker.changeScene();
		
		remove(bar);
		initBar();	
		for(Ball ball : ballArr) {
			remove(ball);
		}
		
		nextStageFlag = true;
		ballArr.clear();
		initBall();			
		nextStageFlag = false;
		
		initGameStage(++stage);
		System.out.println("stage" + stage);
	}
}
