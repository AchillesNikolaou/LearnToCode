// GamePanel.java
package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

	// size of panel
	public static final int PWIDTH = 1280;
	public static final int PHEIGHT = 720;

	// Number of frames with a delay of 0 ms before the animation thread yields
	// to other running threads.
	private static final int NO_DELAYS_PER_YIELD = 16;

	// no. of frames that can be skipped in any one animation loop
	// i.e the games state is updated but not rendered
	private static final int MAX_FRAME_SKIPS = 5;

	private Thread animator; // the thread that performs the animation
	private volatile boolean running = false; // used to stop the animation
												// thread
	private volatile boolean isPaused = false;

	private long period; // period between drawing in _nanosecs_

	private Test testTop;

	private long gameStartTime; // when the game started
	private int timeSpentInGame;

	// used at game termination
	private volatile boolean gameOver = false;
	private int score = 0;

	// for displaying messages
	private Font msgsFont;
	private FontMetrics metrics;

	// off-screen rendering
	private Graphics dbg;
	private BufferedImage dbImage = null;
	
	private GameManager gameManager;
	
	
	/** The GamePanel constructor. */
	public GamePanel(Test testTop, long period) {
		this.testTop = testTop;
		this.period = period;
		
		gameManager = new GameManager();

		setDoubleBuffered(true);
		setBackground(Color.white);
		setPreferredSize(new Dimension(PWIDTH, PHEIGHT));

		setFocusable(true);
		requestFocus(); // the JPanel now has focus, so receives key events
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				processKey(e);
			}
		});
		
		isPaused = false;

		// set up message font
		msgsFont = new Font("SansSerif", Font.BOLD, 24);
		metrics = this.getFontMetrics(msgsFont);
		
	} // end of GamePanel constructor
	
	
	/** Handles termination, help, and game-play keys. */
	private void processKey(KeyEvent e) {
		int keyCode = e.getKeyCode();

		// termination keys
		// listen for esc on the canvas to
		// allow a convenient exit from the full screen configuration
		if (keyCode == KeyEvent.VK_ESCAPE) {
			running = false;
		}

		// help controls
		if (keyCode == KeyEvent.VK_H) {
			
		}
		
		// pause key
		// listen for p to block update
		if (keyCode == KeyEvent.VK_P) {
			if (isPaused) { // the game is already paused
				isPaused = false;
			}
			else { // the game is not paused
				isPaused = true;
			}
		}

		// game-play keys
		if (!isPaused && !gameOver) {
			// left key
			if (keyCode == KeyEvent.VK_LEFT) {
				if (gameManager.getInMainGame()) {
					
				}
			}
			// right key
			if (keyCode == KeyEvent.VK_RIGHT) {

			}
			// up key
			if (keyCode == KeyEvent.VK_UP) {
				if (gameManager.getInStartingMenu()) {
					if (/*gameManager.getIsStartGameSelected() ||*/
						!gameManager.getIsStartGameSelected()) {
						gameManager.setIsStartGameSelected(true);
						gameManager.playBeepSound();
					}
				}
			}
			// down key
			if (keyCode == KeyEvent.VK_DOWN) {
				if (gameManager.getInStartingMenu()) {
					if (gameManager.getIsStartGameSelected()) {
						gameManager.setIsStartGameSelected(false);
						gameManager.playBeepSound();
					}
				}
			}
			// space key
			if (keyCode == KeyEvent.VK_SPACE) {
				// Do something in response
			}
			// enter key
			if (keyCode == KeyEvent.VK_ENTER) {
				// in main menu
				if (gameManager.getInStartingMenu() && gameManager.getIsStartGameSelected()) {
					gameManager.setInStartingMenu(false);
					gameManager.setIsStartGameSelected(false);
					gameManager.setInMainGame(true);
					gameManager.setRound(1);
				}
				if (gameManager.getInStartingMenu() && !gameManager.getIsStartGameSelected()) {
					gameManager.setInStartingMenu(false);
					gameManager.setIsStartGameSelected(false);
					gameManager.setInOptions(true);
				}
				if (gameManager.getRound() == 1) {
					String messageText;
					messageText = testTop.getUserInputField().getText();
					testTop.getMessageField().setText(messageText);
				}
			}
			// backspace key
			if (keyCode == KeyEvent.VK_BACK_SPACE) {
				if (gameManager.getInOptions()) {
					gameManager.setInOptions(false);
					gameManager.setInStartingMenu(true);
					gameManager.setIsStartGameSelected(false);
				}
				if (gameManager.getInMainGame()) {
					gameManager.setInMainGame(false);
					gameManager.setInStartingMenu(true);
					gameManager.setIsStartGameSelected(true);
				}
			}
		}
	} // end of processKey method
	
	
	/** Wait for the JPanel to be added to the JFrame before starting. */
	public void addNotify() {
		super.addNotify(); // creates the peer
		startGame(); // start the thread
	} // end of addNotify method
	
	
	/** Initialize and start the thread. */
	private void startGame() {
		if (animator == null || !running) {
			animator = new Thread(this);
			animator.start();
		} // end if
	} // end of startGame method

	// ------------- game life cycle methods ------------
	// called by the JFrame's window listener methods
	
	/** Called when the JFrame is activated / deiconified. */
	public void resumeGame() {
		isPaused = false;
	} // end of resumeGame method
	
	
	/** Called when the JFrame is deactivated / iconified. */
	public void pauseGame() {
		isPaused = true;
	} // end of pauseGame method
	
	
	/** Called when the JFrame is closing. */
	public void stopGame() {
		running = false;
	} // end of stopGame method
	
	// ----------------------------------------------
	
	
	/** The frames of the animation are drawn inside the while loop. */
	public void run() {
		long beforeTime, afterTime, timeDiff, sleepTime;
		long overSleepTime = 0L;
		int noDelays = 0;
		long excess = 0L;

		gameStartTime = System.nanoTime();
		beforeTime = gameStartTime;
		
		// support for the update method
		long startTime = System.currentTimeMillis();
        long currTime = startTime;

		running = true;

		while (running) {
			
			long elapsedTime =
	                System.currentTimeMillis() - currTime;
	            currTime += elapsedTime;
			
			gameUpdate(elapsedTime);
			gameRender();
			paintScreen();

			afterTime = System.nanoTime();
			timeDiff = afterTime - beforeTime;
			sleepTime = (period - timeDiff) - overSleepTime;

			if (sleepTime > 0) { // some time left in this cycle
				try {
					Thread.sleep(sleepTime / 1000000L); // nano -> ms
				} catch (InterruptedException ex) {
				}
				overSleepTime = (System.nanoTime() - afterTime) - sleepTime;
			} else { // sleepTime <= 0; the frame took longer than the period
				excess -= sleepTime; // store excess time value
				overSleepTime = 0L;

				if (++noDelays >= NO_DELAYS_PER_YIELD) {
					Thread.yield(); // give another thread a chance to run
					noDelays = 0;
				}
			}

			beforeTime = System.nanoTime();

			/*
			 * If frame animation is taking too long, update the game state
			 * without rendering it, to get the updates/sec nearer to the
			 * required FPS.
			 */
			int skips = 0;
			while ((excess > period) && (skips < MAX_FRAME_SKIPS)) {
				excess -= period;
				gameUpdate(elapsedTime); // update state but don't render
				skips++;
			}
		}
		System.exit(0); // so window disappears
	} // end of run method
	
	
	private void gameUpdate(long elapsedTime) {
		
		if (!isPaused && !gameOver) {
			gameManager.update(elapsedTime);
		}
	} // end of gameUpdate method
	
	
	private void gameRender() {
		if (dbImage == null) {
			dbImage = (BufferedImage) createImage(PWIDTH, PHEIGHT);
			if (dbImage == null) {
				System.out.println("dbImage is null");
				return;
			} else
				dbg = dbImage.getGraphics();
		}

		// draw a white background
		dbg.setColor(Color.white);
		dbg.fillRect(0, 0, PWIDTH, PHEIGHT);

		// draw the game elements: order is important
		gameManager.render(dbg);

		if (gameOver)
			gameOverMessage(dbg);

	} // end of gameRender method
	
	
	/** Center the game-over message in the panel. */
	private void gameOverMessage(Graphics g) {
		String msg = "Game Over. Your score: " + score;

		int x = (PWIDTH - metrics.stringWidth(msg)) / 2;
		int y = (PHEIGHT - metrics.getHeight()) / 2;
		g.setColor(Color.black);
		g.setFont(msgsFont);
		g.drawString(msg, x, y);
	} // end of gameOverMessage message
	
	
	/** Use active rendering to put the buffered image on-screen. */
	private void paintScreen() {
		Graphics g;
		try {
			g = this.getGraphics();
			if ((g != null) && (dbImage != null))
				g.drawImage(dbImage, 0, 0, null);
			// Sync the display on some systems.
			// (on Linux, this fixes event queue problems)
			Toolkit.getDefaultToolkit().sync();
			g.dispose();
		} catch (Exception e) {
			System.out.println("Graphics context error: " + e);
		}
	} // end of paintScreen method
	
} // end of class GamePanel
