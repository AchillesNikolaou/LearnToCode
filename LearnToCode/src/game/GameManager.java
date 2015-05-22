// GameManager.java
package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.concurrent.TimeUnit;

public class GameManager {
	
	private static final int ANIMATION_SPEED = 5;
	
	private ResourceManager resourceManager;
	
	// main game fields
	private boolean inStartingMenu;
	private boolean isStartGameSelected;
	private boolean inOptions;
	private boolean inMainGame;
	private static boolean helpIsOn;
	private int helpScreenX;
	private int helpScreenY;
	
	private static int round;
	private boolean showThumbsUp;
	
	// round 1 support
	private int golfBallX;
	private int golfBallY;
	private int holeX;
	private int holeY;
	private boolean golfBallVisible;
	private static boolean moveGolfBall;
	private static int moveGolfBallValue;
	private static boolean moveGolfBallLeft;
	private static boolean moveGolfBallRight;
	private static boolean moveGolfBallUp;
	private static boolean moveGolfBallDown;
	
	// round 2 support
	private int jX;
	private int jY;
	private int a1X;
	private int a1Y;
	private int vX;
	private int vY;
	private int a2X;
	private int a2Y;
	private int frameX;
	private int frameY;
	
	private static boolean moveJLetter;
	private static int moveJLetterValue;
	private static boolean moveJLetterLeft;
	private static boolean moveJLetterRight;
	private static boolean moveJLetterUp;
	private static boolean moveJLetterDown;
	
	private static boolean moveA1Letter;
	private static int moveA1LetterValue;
	private static boolean moveA1LetterLeft;
	private static boolean moveA1LetterRight;
	private static boolean moveA1LetterUp;
	private static boolean moveA1LetterDown;
	
	private static boolean moveVLetter;
	private static int moveVLetterValue;
	private static boolean moveVLetterLeft;
	private static boolean moveVLetterRight;
	private static boolean moveVLetterUp;
	private static boolean moveVLetterDown;
	
	private static boolean moveA2Letter;
	private static int moveA2LetterValue;
	private static boolean moveA2LetterLeft;
	private static boolean moveA2LetterRight;
	private static boolean moveA2LetterUp;
	private static boolean moveA2LetterDown;
	
	// round 3 support
	private int pictureFrameX;
	private int pictureFrameY;
	private int pictureLionX;
	private int pictureLionY;
	private static float pictureLionScaleX;
	private static float pictureLionScaleY;
	private static boolean movePictureLion;
	private static int movePictureLionValue;
	private static boolean movePictureLionLeft;
	private static boolean movePictureLionRight;
	private static boolean movePictureLionUp;
	private static boolean movePictureLionDown;
	
	
	/** The GameManager constructor. */
	public GameManager() {
		resourceManager = new ResourceManager();
		
		// set the menu
		setInStartingMenu(true);      // default true
		setIsStartGameSelected(true); // default true
		setInOptions(false);
		
		// set the main game
		helpIsOn = true;
		helpScreenX = 140;
		helpScreenY = 60;
		showThumbsUp = false;
		
		// set round 1
		setInMainGame(false); // default false
		setRound(0);          // default 0
		golfBallX = 3;
		golfBallY = 3;
		golfBallVisible = true;
		holeX = 1000;
		holeY = 430;
		
		// set round 2
		frameX = GamePanel.PWIDTH / 2 - 564 / 2;
		frameY = GamePanel.PHEIGHT / 2 - 234 / 2;
		jX = 10;
		jY = 10;
		a1X = 70;
		a1Y = 10;
		vX = 10;
		vY = 70;
		a2X = 100;
		a2Y = 100;
		
		// set round 3
		pictureFrameX = 800;
		pictureFrameY = 30;
		pictureLionX = 40;
		pictureLionY = 300;
		pictureLionScaleX = (int) (1240 * 1);
		pictureLionScaleY = (int) (772 * 1);
		
	} // end of GameManager constructor
	
	
	public void update(long elapsedTime) {
		
		if (helpIsOn && helpScreenX > 140) {
			helpScreenX = (int) (helpScreenX - ANIMATION_SPEED * 6);
		}
		else if (!helpIsOn && helpScreenX < 1281) {
			helpScreenX = (int) (helpScreenX + ANIMATION_SPEED * 6);
		}
		
		if (round == 1) {
			playClassical1Sound();
			if (moveGolfBall) {
				getRound1Colission();
				if (moveGolfBallLeft && golfBallVisible) {
					golfBallX = (int) (golfBallX - ANIMATION_SPEED);
					moveGolfBallValue--;
					if (moveGolfBallValue == 0) {
						moveGolfBall = false;
					}
				}
				else if (moveGolfBallRight && golfBallVisible) {
					golfBallX = (int) (golfBallX + ANIMATION_SPEED);
					moveGolfBallValue--;
					if (moveGolfBallValue == 0) {
						moveGolfBall = false;
					}
				}
				else if (moveGolfBallUp && golfBallVisible) {
					golfBallY = (int) (golfBallY - ANIMATION_SPEED);
					moveGolfBallValue--;
					if (moveGolfBallValue == 0) {
						moveGolfBall = false;
					}
				}
				else if (moveGolfBallDown && golfBallVisible) {
					golfBallY = (int) (golfBallY + ANIMATION_SPEED);
					moveGolfBallValue--;
					if (moveGolfBallValue == 0) {
						moveGolfBall = false;
					}
				}
			}
		}
		else if (round == 2) {
			playClassical2Sound();
			getRound2Colission();
			if (moveJLetter) {
				if (moveJLetterLeft) {
					jX = (int) (jX - ANIMATION_SPEED);
					moveJLetterValue--;
					if (moveJLetterValue == 0) {
						moveJLetter = false;
					}
				}
				else if (moveJLetterRight) {
					jX = (int) (jX + ANIMATION_SPEED);
					moveJLetterValue--;
					if (moveJLetterValue == 0) {
						moveJLetter = false;
					}
				}
				else if (moveJLetterUp) {
					jY = (int) (jY - ANIMATION_SPEED);
					moveJLetterValue--;
					if (moveJLetterValue == 0) {
						moveJLetter = false;
					}
				}
				else if (moveJLetterDown) {
					jY = (int) (jY + ANIMATION_SPEED);
					moveJLetterValue--;
					if (moveJLetterValue == 0) {
						moveJLetter = false;
					}
				}
			}
			else if (moveA1Letter) {
				if (moveA1LetterLeft) {
					a1X = (int) (a1X - ANIMATION_SPEED);
					moveA1LetterValue--;
					if (moveA1LetterValue == 0) {
						moveA1Letter = false;
					}
				}
				else if (moveA1LetterRight) {
					a1X = (int) (a1X + ANIMATION_SPEED);
					moveA1LetterValue--;
					if (moveA1LetterValue == 0) {
						moveA1Letter = false;
					}
				}
				else if (moveA1LetterUp) {
					a1Y = (int) (a1Y - ANIMATION_SPEED);
					moveA1LetterValue--;
					if (moveA1LetterValue == 0) {
						moveA1Letter = false;
					}
				}
				else if (moveA1LetterDown) {
					a1Y = (int) (a1Y + ANIMATION_SPEED);
					moveA1LetterValue--;
					if (moveA1LetterValue == 0) {
						moveA1Letter = false;
					}
				}
			}
			else if (moveVLetter) {
				if (moveVLetterLeft) {
					vX = (int) (vX - ANIMATION_SPEED);
					moveVLetterValue--;
					if (moveVLetterValue == 0) {
						moveVLetter = false;
					}
				}
				else if (moveVLetterRight) {
					vX = (int) (vX + ANIMATION_SPEED);
					moveVLetterValue--;
					if (moveVLetterValue == 0) {
						moveVLetter = false;
					}
				}
				else if (moveVLetterUp) {
					vY = (int) (vY - ANIMATION_SPEED);
					moveVLetterValue--;
					if (moveVLetterValue == 0) {
						moveVLetter = false;
					}
				}
				else if (moveVLetterDown) {
					vY = (int) (vY + ANIMATION_SPEED);
					moveVLetterValue--;
					if (moveVLetterValue == 0) {
						moveVLetter = false;
					}
				}
			}
			else if (moveA2Letter) {
				if (moveA2LetterLeft) {
					a2X = (int) (a2X - ANIMATION_SPEED);
					moveA2LetterValue--;
					if (moveA2LetterValue == 0) {
						moveA2Letter = false;
					}
				}
				else if (moveA2LetterRight) {
					a2X = (int) (a2X + ANIMATION_SPEED);
					moveA2LetterValue--;
					if (moveA2LetterValue == 0) {
						moveA2Letter = false;
					}
				}
				else if (moveA2LetterUp) {
					a2Y = (int) (a2Y - ANIMATION_SPEED);
					moveA2LetterValue--;
					if (moveA2LetterValue == 0) {
						moveA2Letter = false;
					}
				}
				else if (moveA2LetterDown) {
					a2Y = (int) (a2Y + ANIMATION_SPEED);
					moveA2LetterValue--;
					if (moveA2LetterValue == 0) {
						moveA2Letter = false;
					}
				}
			}
		}
		if (round == 3) {
			playClassical3Sound();
			if (movePictureLion) {
				if (movePictureLionLeft) {
					pictureLionX = (int) (pictureLionX - ANIMATION_SPEED);
					movePictureLionValue--;
					if (movePictureLionValue == 0) {
						movePictureLion = false;
					}
				}
				else if (movePictureLionRight) {
					pictureLionX = (int) (pictureLionX + ANIMATION_SPEED);
					movePictureLionValue--;
					if (movePictureLionValue == 0) {
						movePictureLion = false;
					}
				}
				else if (movePictureLionUp) {
					pictureLionY = (int) (pictureLionY - ANIMATION_SPEED);
					movePictureLionValue--;
					if (movePictureLionValue == 0) {
						movePictureLion = false;
					}
				}
				else if (movePictureLionDown) {
					pictureLionY = (int) (pictureLionY + ANIMATION_SPEED);
					movePictureLionValue--;
					if (movePictureLionValue == 0) {
						movePictureLion = false;
					}
				}
				getRound3Colission();
			}
		}
	} // end of update method
	
	
	public void render(Graphics dbg) {
		Graphics2D dbg2d = (Graphics2D) dbg;
		
		// in menu
		if (getInStartingMenu()) {
			dbg2d.drawImage(resourceManager.getMenuBackgroundImage(), 0, 0, null);
			if (getIsStartGameSelected()) {
				dbg2d.drawImage(resourceManager.getStartGameOnImage(), 220, 200, null);
				dbg2d.drawImage(resourceManager.getOptionsOffImage(), 220, 400, null);
			}
			else {
				dbg2d.drawImage(resourceManager.getStartGameOffImage(), 220, 200, null);
				dbg2d.drawImage(resourceManager.getOptionsOnImage(), 220, 400, null);
			}
		}
		// in options
		if (getInOptions()) {
			dbg2d.drawImage(resourceManager.getMenuBackgroundImage(), 0, 0, null);
			dbg2d.drawImage(resourceManager.getOptionsImage(), 0, 0, null);
		}
		
		if (getInMainGame()) {
			
			if (round == 1) {
				dbg2d.drawImage(resourceManager.getBackgroundRound1Image(), 0, 0, null);
				dbg2d.drawImage(resourceManager.getHoleImage(), holeX, holeY, null);
				if (golfBallVisible)
					dbg2d.drawImage(resourceManager.getGolfBallImage(), golfBallX, golfBallY, null);
				dbg2d.drawImage(resourceManager.getRound1HelpImage(), helpScreenX, helpScreenY, null);
			}
			else if (round == 2) {
				dbg2d.drawImage(resourceManager.getJLetterImage(), jX, jY, null);
				dbg2d.drawImage(resourceManager.getA1LetterImage(), a1X, a1Y, null);
				dbg2d.drawImage(resourceManager.getVLetterImage(), vX, vY, null);
				dbg2d.drawImage(resourceManager.getA2LetterImage(), a2X, a2Y, null);
				dbg2d.drawImage(resourceManager.getFrameImage(), frameX, frameY, null);
				dbg2d.drawImage(resourceManager.getRound2HelpImage(), helpScreenX, helpScreenY, null);
			}
			else if (round == 3) {
				dbg2d.drawImage(resourceManager.getPictureLionImage(), pictureLionX, pictureLionY, (int)pictureLionScaleX, (int)pictureLionScaleY, null);
				dbg2d.drawImage(resourceManager.getPictureFrameImage(), pictureFrameX, pictureFrameY, null);
				dbg2d.drawImage(resourceManager.getRound3HelpImage(), helpScreenX, helpScreenY, null);
			}
			else if (round == 4) {
				dbg2d.drawImage(resourceManager.getGameOverImage(), 0, 0, null);
			}
		}
			
	} // end of render method
	
	
	// inMenu
	public void setInStartingMenu(boolean inStartingMenu) { this.inStartingMenu = inStartingMenu; }
	public boolean getInStartingMenu()                    { return inStartingMenu; }
	
	// isStartGameSelected
	public void setIsStartGameSelected(boolean isStartGameSelected) { this.isStartGameSelected = isStartGameSelected; }
	public boolean getIsStartGameSelected()                         { return isStartGameSelected; }
	
	// inOptions
	public void setInOptions(boolean inOptions) { this.inOptions = inOptions; }
	public boolean getInOptions()               { return inOptions; }
	
	// inMainGame
	public void setInMainGame(boolean inMainGame) { this.inMainGame = inMainGame; }
	public boolean getInMainGame()                { return inMainGame; }
	
	// round
	public void setRound(int round) { GameManager.round = round; }
	public static int getRound()    { return round; }
	
	// help is on
	public static void setHelpIsOn(boolean helpIsOn) { GameManager.helpIsOn = helpIsOn; }
	public static boolean getHelpIsOn()              { return GameManager.helpIsOn; }
	
	// play beep sound
	public void playBeepSound() {
		if (resourceManager.getBeepSound().isActive()) {
			resourceManager.getBeepSound().stop();
			resourceManager.getBeepSound().setFramePosition(0);
			resourceManager.getBeepSound().start();
		}
		if (!resourceManager.getBeepSound().isActive()) {
			resourceManager.getBeepSound().stop();
			resourceManager.getBeepSound().setFramePosition(0);
			resourceManager.getBeepSound().start();
		}
		
//		do {
//			try {
//				Thread.sleep(5);
//			} catch (InterruptedException ie) {
//				ie.printStackTrace();
//			}
//		} while (resourceManager.getBeepSound().isActive());
//		resourceManager.getBeepSound().stop();
//		resourceManager.getBeepSound().setFramePosition(0);
	}
	
	public void playPageTurnSound() {
		resourceManager.getPageTurnSound().stop();
		resourceManager.getPageTurnSound().setFramePosition(0);
		resourceManager.getPageTurnSound().start();
	}
	
	// play ping sound
	public void playGlassPingSound() {
		resourceManager.getglassPingSound().stop();
		resourceManager.getglassPingSound().setFramePosition(0);
		showThumbsUp = true;
		resourceManager.getglassPingSound().start();

//		do {
//			try {
//				Thread.sleep(50);
//			} catch (InterruptedException ie) {
//				ie.printStackTrace();
//			}
//		} while (resourceManager.getglassPingSound().isActive());
		resourceManager.getBeepSound().stop();
		resourceManager.getBeepSound().setFramePosition(0);
		showThumbsUp = false;
	}
	
	// play classical music 1 sound
	public void playClassical1Sound() {
		if (!resourceManager.getClassical1Sound().isActive()) {
			resourceManager.getClassical1Sound().stop();
			resourceManager.getClassical1Sound().setFramePosition(0);
			resourceManager.getClassical1Sound().start();
		}

//		do {
//			try {
//				Thread.sleep(50);
//			} catch (InterruptedException ie) {
//				ie.printStackTrace();
//			}
//		} while (resourceManager.getClassical1Sound().isActive());
//		resourceManager.getClassical1Sound().stop();
//		resourceManager.getClassical1Sound().setFramePosition(0);
	}
	public void stopClassical1Sound() {
		resourceManager.getClassical1Sound().stop();
	}
	
	// play beep sound
	public void playClassical2Sound() {
		if (!resourceManager.getClassical2Sound().isActive()) {
			resourceManager.getClassical2Sound().stop();
			resourceManager.getClassical2Sound().setFramePosition(0);
			resourceManager.getClassical2Sound().start();
		}
	}
	
	public void stopClassical2Sound() {
		resourceManager.getClassical2Sound().stop();
	}
	
	public void playClassical3Sound() {
		if (!resourceManager.getClassical3Sound().isActive()) {
			resourceManager.getClassical3Sound().stop();
			resourceManager.getClassical3Sound().setFramePosition(0);
			resourceManager.getClassical3Sound().start();
		}
	}
	
	public void stopClassical3Sound() {
		resourceManager.getClassical3Sound().stop();
	}
	
	
	
	// ===========================================================
	// ------------------------- round 1 -------------------------
	// ===========================================================
	
	public static int ckeckRound1Answer(String input) {
		
		if (input.contains("moveLeft")) {
			if (getRound1InputValue(input) != -1000) {
				moveGolfBall = true;
				moveGolfBallLeft = true;
				moveGolfBallRight = false;
				moveGolfBallUp = false;
				moveGolfBallDown = false;
				moveGolfBallValue = getRound1InputValue(input);
				return getRound1InputValue(input);
			}
		}
		else if (input.contains("moveRight")) {
			if (getRound1InputValue(input) != -1000) {
				moveGolfBall = true;
				moveGolfBallLeft = false;
				moveGolfBallRight = true;
				moveGolfBallUp = false;
				moveGolfBallDown = false;
				moveGolfBallValue = getRound1InputValue(input);
				return getRound1InputValue(input);
			}
		}
		else if (input.contains("moveUp")) {
			if (getRound1InputValue(input) != -1000) {
				moveGolfBall = true;
				moveGolfBallLeft = false;
				moveGolfBallRight = false;
				moveGolfBallUp = true;
				moveGolfBallDown = false;
				moveGolfBallValue = getRound1InputValue(input);
				return getRound1InputValue(input);
			}
		}
		else if (input.contains("moveDown")) {
			if (getRound1InputValue(input) != -1000) {
				moveGolfBall = true;
				moveGolfBallLeft = false;
				moveGolfBallRight = false;
				moveGolfBallUp = false;
				moveGolfBallDown = true;
				moveGolfBallValue = getRound1InputValue(input);
				return getRound1InputValue(input);
			}
		}
		return -1000;
	} // end of method checkRound1Answer
	
	
	public static int getRound1InputValue(String command) {		
		for (int i = 0; i <= 100; i++) {
			if (command.equals("golfball.moveLeft(" + i + ")") ||
				command.equals("golfball.moveRight(" + i + ")") ||
				command.equals("golfball.moveUp(" + i + ")") ||
				command.equals("golfball.moveDown(" + i + ")")) {
				
				return i;
			} // end if
		} // end for
		return -1000;
	} // end of method getRound1InputValue
	
	
	public void getRound1Colission() {
		if (golfBallX > holeX && golfBallX + 109 < holeX + 256 &&
				golfBallY > holeY && golfBallY + 109 < holeY + 256) {
			stopClassical1Sound();
			golfBallVisible = false;
			playGlassPingSound();
			
			setRound(2);
			helpIsOn = true;
			showThumbsUp = true;
		}
	} // end of method getRound1Colission
	
	
	// ===========================================================
	// ----------------------- end round 1 -----------------------
	// ===========================================================
	
	// ===========================================================
	// ------------------------- round 2 -------------------------
	// ===========================================================
	
	public static int checkRound2JAnswer(String input) {

		if (input.contains("moveLeft")) {
			if (getRound2JInputValue(input) != -1000) {
				moveJLetter = true;
				moveJLetterLeft = true;
				moveJLetterRight = false;
				moveJLetterUp = false;
				moveJLetterDown = false;
				moveJLetterValue = getRound2JInputValue(input);
				return getRound2JInputValue(input);
			}
		} else if (input.contains("moveRight")) {
			if (getRound2JInputValue(input) != -1000) {
				moveJLetter = true;
				moveJLetterLeft = false;
				moveJLetterRight = true;
				moveJLetterUp = false;
				moveJLetterDown = false;
				moveJLetterValue = getRound2JInputValue(input);
				return getRound2JInputValue(input);
			}
		} else if (input.contains("moveUp")) {
			if (getRound2JInputValue(input) != -1000) {
				moveJLetter = true;
				moveJLetterLeft = false;
				moveJLetterRight = false;
				moveJLetterUp = true;
				moveJLetterDown = false;
				moveJLetterValue = getRound2JInputValue(input);
				return getRound2JInputValue(input);
			}
		} else if (input.contains("moveDown")) {
			if (getRound2JInputValue(input) != -1000) {
				moveJLetter = true;
				moveJLetterLeft = false;
				moveJLetterRight = false;
				moveJLetterUp = false;
				moveJLetterDown = true;
				moveJLetterValue = getRound2JInputValue(input);
				return getRound2JInputValue(input);
			}
		}
		return -1000;
	}
	
	
	public static int getRound2JInputValue(String command) {		
		for (int i = 0; i <= 100; i++) {
			if (command.equals("j.moveLeft(" + i + ")") ||
				command.equals("j.moveRight(" + i + ")") ||
				command.equals("j.moveUp(" + i + ")") ||
				command.equals("j.moveDown(" + i + ")")) {
				
				return i;
			} // end if
		} // end for
		return -1000;
	} // end of method getJInputValue
	
	
	
	public static int checkRound2A1Answer(String input) {

		if (input.contains("moveLeft")) {
			if (getRound2A1InputValue(input) != -1000) {
				moveA1Letter = true;
				moveA1LetterLeft = true;
				moveA1LetterRight = false;
				moveA1LetterUp = false;
				moveA1LetterDown = false;
				moveA1LetterValue = getRound2A1InputValue(input);
				return getRound2A1InputValue(input);
			}
		} else if (input.contains("moveRight")) {
			if (getRound2A1InputValue(input) != -1000) {
				moveA1Letter = true;
				moveA1LetterLeft = false;
				moveA1LetterRight = true;
				moveA1LetterUp = false;
				moveA1LetterDown = false;
				moveA1LetterValue = getRound2A1InputValue(input);
				return getRound2A1InputValue(input);
			}
		} else if (input.contains("moveUp")) {
			if (getRound2A1InputValue(input) != -1000) {
				moveA1Letter = true;
				moveA1LetterLeft = false;
				moveA1LetterRight = false;
				moveA1LetterUp = true;
				moveA1LetterDown = false;
				moveA1LetterValue = getRound2A1InputValue(input);
				return getRound2A1InputValue(input);
			}
		} else if (input.contains("moveDown")) {
			if (getRound2A1InputValue(input) != -1000) {
				moveA1Letter = true;
				moveA1LetterLeft = false;
				moveA1LetterRight = false;
				moveA1LetterUp = false;
				moveA1LetterDown = true;
				moveA1LetterValue = getRound2A1InputValue(input);
				return getRound2A1InputValue(input);
			}
		}
		return -1000;
	}
	
	
	public static int getRound2A1InputValue(String command) {

		for (int i = 0; i <= 800; i++) {
			if (command.equals("a1.moveLeft(" + i + ")")
					|| command.equals("a1.moveRight(" + i + ")")
					|| command.equals("a1.moveUp(" + i + ")")
					|| command.equals("a1.moveDown(" + i + ")")) {

				return i;
			} // end if
		} // end for
		return -1000;
	}
	
	
	
	public static int checkRound2VAnswer(String input) {

		if (input.contains("moveLeft")) {
			if (getRound2VInputValue(input) != -1000) {
				moveVLetter = true;
				moveVLetterLeft = true;
				moveVLetterRight = false;
				moveVLetterUp = false;
				moveVLetterDown = false;
				moveVLetterValue = getRound2VInputValue(input);
				return getRound2VInputValue(input);
			}
		} else if (input.contains("moveRight")) {
			if (getRound2VInputValue(input) != -1000) {
				moveVLetter = true;
				moveVLetterLeft = false;
				moveVLetterRight = true;
				moveVLetterUp = false;
				moveVLetterDown = false;
				moveVLetterValue = getRound2VInputValue(input);
				return getRound2VInputValue(input);
			}
		} else if (input.contains("moveUp")) {
			if (getRound2VInputValue(input) != -1000) {
				moveVLetter = true;
				moveVLetterLeft = false;
				moveVLetterRight = false;
				moveVLetterUp = true;
				moveVLetterDown = false;
				moveVLetterValue = getRound2VInputValue(input);
				return getRound2VInputValue(input);
			}
		} else if (input.contains("moveDown")) {
			if (getRound2VInputValue(input) != -1000) {
				moveVLetter = true;
				moveVLetterLeft = false;
				moveVLetterRight = false;
				moveVLetterUp = false;
				moveVLetterDown = true;
				moveVLetterValue = getRound2VInputValue(input);
				return getRound2VInputValue(input);
			}
		}
		return -1000;
	}
	
	
	public static int getRound2VInputValue(String command) {		
		for (int i = 0; i <= 100; i++) {
			if (command.equals("v.moveLeft(" + i + ")") ||
				command.equals("v.moveRight(" + i + ")") ||
				command.equals("v.moveUp(" + i + ")") ||
				command.equals("v.moveDown(" + i + ")")) {
				
				return i;
			} // end if
		} // end for
		return -1000;
	} // end of method getJInputValue
	
	
	
	public static int checkRound2A2Answer(String input) {

		if (input.contains("moveLeft")) {
			if (getRound2A2InputValue(input) != -1000) {
				moveA2Letter = true;
				moveA2LetterLeft = true;
				moveA2LetterRight = false;
				moveA2LetterUp = false;
				moveA2LetterDown = false;
				moveA2LetterValue = getRound2A2InputValue(input);
				return getRound2A2InputValue(input);
			}
		} else if (input.contains("moveRight")) {
			if (getRound2A2InputValue(input) != -1000) {
				moveA2Letter = true;
				moveA2LetterLeft = false;
				moveA2LetterRight = true;
				moveA2LetterUp = false;
				moveA2LetterDown = false;
				moveA2LetterValue = getRound2A2InputValue(input);
				return getRound2A2InputValue(input);
			}
		} else if (input.contains("moveUp")) {
			if (getRound2A2InputValue(input) != -1000) {
				moveA2Letter = true;
				moveA2LetterLeft = false;
				moveA2LetterRight = false;
				moveA2LetterUp = true;
				moveA2LetterDown = false;
				moveA2LetterValue = getRound2A2InputValue(input);
				return getRound2A2InputValue(input);
			}
		} else if (input.contains("moveDown")) {
			if (getRound2A2InputValue(input) != -1000) {
				moveA2Letter = true;
				moveA2LetterLeft = false;
				moveA2LetterRight = false;
				moveA2LetterUp = false;
				moveA2LetterDown = true;
				moveA2LetterValue = getRound2A2InputValue(input);
				return getRound2A2InputValue(input);
			}
		}
		return -1000;
	}
	
	
	public static int getRound2A2InputValue(String command) {

		for (int i = 0; i <= 800; i++) {
			if (command.equals("a2.moveLeft(" + i + ")")
					|| command.equals("a2.moveRight(" + i + ")")
					|| command.equals("a2.moveUp(" + i + ")")
					|| command.equals("a2.moveDown(" + i + ")")) {

				return i;
			} // end if
		} // end for
		return -1000;
	}
	
	
	public void getRound2Colission() {
		if (jX > frameX && jX + 69 < frameX + 130 &&
			jY > frameY	&& jY + 141 < frameY + 234 &&
			a1X > frameX + 130 && a1X + 94 < frameX + 260 &&
			a1Y > frameY && a1Y + 113 < frameY + 234 &&
			vX > frameX + 260 && vX + 102 < frameX + 420 &&
			vY > frameY && vY + 106 < frameY + 234 &&
			a2X > frameX + 430 && a2X + 94 < frameX + 560 &&
			a2Y > frameY && a2Y + 113 < frameY + 234) {
			
			stopClassical2Sound();
			playGlassPingSound();
			setRound(3);
			helpIsOn = true;
		}
	} // end of method getRound1Colission
	
	
	// ===========================================================
	// ----------------------- end round 2 -----------------------
	// ===========================================================
	
	// ===========================================================
	// ------------------------- round 3 -------------------------
	// ===========================================================
	
	public static int ckeckRound3Answer(String input) {
		
		if (input.contains("moveLeft")) {
			if (getRound3InputValue(input) != -1000) {
				movePictureLion = true;
				movePictureLionLeft = true;
				movePictureLionRight = false;
				movePictureLionUp = false;
				movePictureLionDown = false;
				movePictureLionValue = getRound3InputValue(input);
				return getRound3InputValue(input);
			}
		}
		else if (input.contains("moveRight")) {
			if (getRound3InputValue(input) != -1000) {
				movePictureLion = true;
				movePictureLionLeft = false;
				movePictureLionRight = true;
				movePictureLionUp = false;
				movePictureLionDown = false;
				movePictureLionValue = getRound3InputValue(input);
				return getRound3InputValue(input);
			}
		}
		else if (input.contains("moveUp")) {
			if (getRound3InputValue(input) != -1000) {
				movePictureLion = true;
				movePictureLionLeft = false;
				movePictureLionRight = false;
				movePictureLionUp = true;
				movePictureLionDown = false;
				movePictureLionValue = getRound3InputValue(input);
				return getRound3InputValue(input);
			}
		}
		else if (input.contains("moveDown")) {
			if (getRound3InputValue(input) != -1000) {
				movePictureLion = true;
				movePictureLionLeft = false;
				movePictureLionRight = false;
				movePictureLionUp = false;
				movePictureLionDown = true;
				movePictureLionValue = getRound3InputValue(input);
				return getRound3InputValue(input);
			}
		}
		else if (input.contains("scaleUp")) {
			pictureLionScaleX += 62;
			pictureLionScaleY += 38.6;
			return 50;
		}
		else if (input.contains("scaleDown")) {
			pictureLionScaleX -= 62;
			pictureLionScaleY -= 38.6;
			return 50;
		}
		return -1000;
	} // end of method checkRound1Answer
	
	
	public static int getRound3InputValue(String command) {		
		for (int i = 0; i <= 100; i++) {
			if (command.equals("picture.moveLeft(" + i + ")") ||
				command.equals("picture.moveRight(" + i + ")") ||
				command.equals("picture.moveUp(" + i + ")") ||
				command.equals("picture.moveDown(" + i + ")")) {
				
				return i;
			} // end if
		} // end for
		return -1000;
	} // end of method getRound1InputValue
	
	
	public void getRound3Colission() {
		if (pictureLionX > pictureFrameX + 62 && pictureLionX < pictureFrameX + 69 &&
			pictureLionY > pictureFrameY + 52 && pictureLionY < pictureFrameY + 60 &&
			pictureLionScaleX == 310) {
			
			stopClassical3Sound();
			playGlassPingSound();
			setRound(4);
//			helpIsOn = true;
		}
	} // end of method getRound1Colission
	
	
	// ===========================================================
	// ----------------------- end round 3 -----------------------
	// ===========================================================
	// ===========================================================
	// ------------------------- round 4 -------------------------
	// ===========================================================
	// ===========================================================
	// ----------------------- end round 4 -----------------------
	// ===========================================================
	// ===========================================================
	// ------------------------- round 5 -------------------------
	// ===========================================================
	// ===========================================================
	// ----------------------- end round 5 -----------------------
	// ===========================================================
	// ===========================================================
	// ------------------------- round 6 -------------------------
	// ===========================================================
	// ===========================================================
	// ----------------------- end round 6 -----------------------
	// ===========================================================

}
