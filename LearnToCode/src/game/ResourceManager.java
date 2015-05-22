// ResourceManager.java
package game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ResourceManager {

	// menu images
	private BufferedImage menuBackgroundImage;
	private BufferedImage startGameOnImage;
	private BufferedImage startGameOffImage;
	private BufferedImage optionsOnImage;
	private BufferedImage optionsOffImage;
	private BufferedImage optionsImage;
	
	private BufferedImage thumbsUpImage;
	
	// round 1 images
	private BufferedImage backgroundRound1Image;
	private BufferedImage golfBallImage;
	private BufferedImage holeImage;
	private BufferedImage round1HelpScreen;
	
	// round 2 images
	private BufferedImage jLetterImage;
	private BufferedImage a1LetterImage;
	private BufferedImage vLetterImage;
	private BufferedImage a2LetterImage;
	private BufferedImage frameImage;
	private BufferedImage round2HelpScreen;
	
	// round 3 images
	private BufferedImage pictureFrameImage;
	private BufferedImage pictureLionImage;
	private BufferedImage round3HelpScreen;
	
	// game over
	private BufferedImage gameOverImage;
	
	
	// SOUND
	private Mixer mixer;
	private Clip beepClip;
	private Clip glassPingClip;
	private Clip pageTurnClip;
	private Clip classical1Clip;
	private Clip classical2Clip;
	private Clip classical3Clip;
	
	
	/** The ResourceManager constructor. */
	public ResourceManager() {
		loadImages();
		loadSounds();
	} // end ResourceManager constructor
	
	
	// get methods
	public BufferedImage getMenuBackgroundImage()   { return menuBackgroundImage; }
	public BufferedImage getStartGameOnImage()      { return startGameOnImage; }
	public BufferedImage getStartGameOffImage()     { return startGameOffImage; }
	public BufferedImage getOptionsOnImage()        { return optionsOnImage; }
	public BufferedImage getOptionsOffImage()       { return optionsOffImage; }
	public BufferedImage getOptionsImage()          { return optionsImage; }
	public BufferedImage getThumbsUpImage()         { return thumbsUpImage; }
	public BufferedImage getBackgroundRound1Image() { return backgroundRound1Image; }
	public BufferedImage getGolfBallImage()         { return golfBallImage; }
	public BufferedImage getHoleImage()             { return holeImage; }
	public BufferedImage getRound1HelpImage()       { return round1HelpScreen; }
	public BufferedImage getJLetterImage()          { return jLetterImage; }
	public BufferedImage getA1LetterImage()         { return a1LetterImage; }
	public BufferedImage getVLetterImage()          { return vLetterImage; }
	public BufferedImage getA2LetterImage()         { return a2LetterImage; }
	public BufferedImage getFrameImage()            { return frameImage; }
	public BufferedImage getRound2HelpImage()       { return round2HelpScreen; }
	public BufferedImage getPictureFrameImage()     { return pictureFrameImage; }
	public BufferedImage getPictureLionImage()      { return pictureLionImage; }
	public BufferedImage getRound3HelpImage()       { return round3HelpScreen; }
	public BufferedImage getGameOverImage()         { return gameOverImage; }
	
	public Clip getBeepSound()                      { return beepClip; }
	public Clip getglassPingSound()                 { return glassPingClip; }
	public Clip getPageTurnSound()                  { return pageTurnClip; }
	public Clip getClassical1Sound()                { return classical1Clip; }
	public Clip getClassical2Sound()                { return classical2Clip; }
	public Clip getClassical3Sound()                { return classical3Clip; }
	
	
	private void loadImages() {
		menuBackgroundImage = loadImage("/images/menu/menu_background.png");
		startGameOnImage = loadImage("/images/menu/start_game_on.png");
		startGameOffImage = loadImage("/images/menu/start_game_off.png");
		optionsOnImage = loadImage("/images/menu/options_on.png");
		optionsOffImage = loadImage("/images/menu/options_off.png");
		optionsImage = loadImage("/images/menu/options.png");
		thumbsUpImage = loadImage("/images/thumbs_up.png");
		backgroundRound1Image = loadImage("/images/round1/background.png");
		golfBallImage = loadImage("/images/round1/golfball.png");
		holeImage = loadImage("/images/round1/hole.png");
		round1HelpScreen = loadImage("/images/round1/round1_help_screen.png");
		jLetterImage = loadImage("/images/round2/j.png");
		a1LetterImage = loadImage("/images/round2/a.png");
		vLetterImage = loadImage("/images/round2/v.png");
		a2LetterImage = loadImage("/images/round2/a.png");
		frameImage = loadImage("/images/round2/frame.png");
		round2HelpScreen = loadImage("/images/round2/round2_help_screen.png");
		pictureFrameImage = loadImage("/images/round3/picture_frame.png");
		pictureLionImage = loadImage("/images/round3/picture_lion_large.png");
		round3HelpScreen = loadImage("/images/round3/round3_help_screen.png");
		gameOverImage = loadImage("/images/round4/gameover.png");
	} // end loadImages method
	
	
	private void loadSounds() {
		Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
		
		mixer = AudioSystem.getMixer(mixInfos[0]);

		DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);

		try {
			beepClip = (Clip) mixer.getLine(dataInfo);
			glassPingClip = (Clip) mixer.getLine(dataInfo);
			pageTurnClip = (Clip) mixer.getLine(dataInfo);
			classical1Clip = (Clip) mixer.getLine(dataInfo);
			classical2Clip = (Clip) mixer.getLine(dataInfo);
			classical3Clip = (Clip) mixer.getLine(dataInfo);
		} catch (LineUnavailableException lue) {
			lue.printStackTrace();
		}

		try {
			URL soundURL = getClass().getResource("/sounds/woosh_sound.wav");
			AudioInputStream audioStream = AudioSystem
					.getAudioInputStream(soundURL);
			beepClip.open(audioStream);
			
			soundURL = getClass().getResource("/sounds/glass_ping.wav");
			audioStream = AudioSystem
					.getAudioInputStream(soundURL);
			glassPingClip.open(audioStream);
			
			soundURL = getClass().getResource("/sounds/page_turn.wav");
			audioStream = AudioSystem
					.getAudioInputStream(soundURL);
			pageTurnClip.open(audioStream);
			
			soundURL = getClass().getResource("/sounds/classical_1.wav");
			audioStream = AudioSystem
					.getAudioInputStream(soundURL);
			classical1Clip.open(audioStream);
			
			soundURL = getClass().getResource("/sounds/classical_2.wav");
			audioStream = AudioSystem
					.getAudioInputStream(soundURL);
			classical2Clip.open(audioStream);
			
			soundURL = getClass().getResource("/sounds/classical_3.wav");
			audioStream = AudioSystem
					.getAudioInputStream(soundURL);
			classical3Clip.open(audioStream);
		} catch (LineUnavailableException lue) {
			lue.printStackTrace();
		} catch (UnsupportedAudioFileException uafe) {
			uafe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

//		clip.start();
//
//		do {
//			try {
//				Thread.sleep(50);
//			} catch (InterruptedException ie) {
//				ie.printStackTrace();
//			}
//		} while (clip.isActive());
		
	} // end of loadSounds method
	
	

	/** Loads an image file and returns a BufferedImage object. */
	private BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	} // end method BufferedImage

} // end of class ResourceManager
