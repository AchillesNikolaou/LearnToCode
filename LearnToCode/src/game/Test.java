// Test.java
package game;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Test extends JFrame implements WindowListener {
	
	private static int DEFAULT_FPS = 60;
	
	private JTextField userInputField; // displays the user input text field
	private JButton helpButton;       // displays the Help button
	private JButton compileButton;     // displays the Compile button
	private JTextField messageField;   // displays time spent in game
	
	private GamePanel gp; // where the game is drawn
	
	private GameManager gameManager = new GameManager();
	
	
	/** The Test constructor. */
	public Test(long period) {
		super("Learn To Code");
		
		Container c = getContentPane(); // default BorderLayout used
		gp = new GamePanel(this, period);
		c.add(gp, "Center");
		
		JPanel ctrls = new JPanel(); // a row of textfields
		ctrls.setLayout( new BoxLayout(ctrls, BoxLayout.X_AXIS));
		
		userInputField = new JTextField(/*"Type a command here..."*/);
		userInputField.setMinimumSize(new Dimension(300, 100));
		ctrls.add(userInputField);
		
		helpButton = new JButton("Hide Help");
		helpButton.setActionCommand("clear text");
		helpButton.setToolTipText("Click this button to display help.");
		ctrls.add(helpButton);
		
		compileButton = new JButton("Compile");
		compileButton.setMnemonic(KeyEvent.VK_ENTER);
		compileButton.setToolTipText("Click this button to complile your text.");
		ctrls.add(compileButton);
		
		messageField = new JTextField("");
		messageField.setEditable(false);
		ctrls.add(messageField);
		
		c.add(ctrls, "South");
		
		addWindowListener(this);
		pack();
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		
		
		// Setting an action for the compile button
		compileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String inputText = userInputField.getText();

				if (GameManager.getRound() == 1) {
					int value = GameManager.ckeckRound1Answer(inputText);

					if (value != 1000 && inputText.contains("moveLeft")) {
						messageField.setText(String.valueOf("Move left by "
								+ value));
					} else if (value != 1000 && inputText.contains("moveRight")) {
						messageField.setText(String.valueOf("Move right by "
								+ value));
					} else if (value != 1000 && inputText.contains("moveUp")) {
						messageField.setText(String.valueOf("Move up by "
								+ value));
					} else if (value != 1000 && inputText.contains("moveDown")) {
						messageField.setText(String.valueOf("Move down by "
								+ value));
					} else
						messageField.setText("Wrong input...");
				}
				else if (GameManager.getRound() == 2) {
					
					if (inputText.contains("j.")) {
						int value = GameManager.checkRound2JAnswer(inputText);

						if (value != 1000 && inputText.contains("moveLeft")) {
							messageField.setText(String.valueOf("Move left by "
									+ value));
						} else if (value != 1000
								&& inputText.contains("moveRight")) {
							messageField.setText(String
									.valueOf("Move right by " + value));
						} else if (value != 1000
								&& inputText.contains("moveUp")) {
							messageField.setText(String.valueOf("Move up by "
									+ value));
						} else if (value != 1000
								&& inputText.contains("moveDown")) {
							messageField.setText(String.valueOf("Move down by "
									+ value));
						} else
							messageField.setText("Wrong input...");
					}
					else if (inputText.contains("a1.")) {
						int value = GameManager.checkRound2A1Answer(inputText);

						if (value != 1000 && inputText.contains("moveLeft")) {
							messageField.setText(String.valueOf("Move left by "
									+ value));
						} else if (value != 1000
								&& inputText.contains("moveRight")) {
							messageField.setText(String
									.valueOf("Move right by " + value));
						} else if (value != 1000
								&& inputText.contains("moveUp")) {
							messageField.setText(String.valueOf("Move up by "
									+ value));
						} else if (value != 1000
								&& inputText.contains("moveDown")) {
							messageField.setText(String.valueOf("Move down by "
									+ value));
						} else
							messageField.setText("Wrong input...");
					}
					else if (inputText.contains("v.")) {
						int value = GameManager.checkRound2VAnswer(inputText);

						if (value != 1000 && inputText.contains("moveLeft")) {
							messageField.setText(String.valueOf("Move left by "
									+ value));
						} else if (value != 1000
								&& inputText.contains("moveRight")) {
							messageField.setText(String
									.valueOf("Move right by " + value));
						} else if (value != 1000
								&& inputText.contains("moveUp")) {
							messageField.setText(String.valueOf("Move up by "
									+ value));
						} else if (value != 1000
								&& inputText.contains("moveDown")) {
							messageField.setText(String.valueOf("Move down by "
									+ value));
						} else
							messageField.setText("Wrong input...");
					}
					if (inputText.contains("a2.")) {
						int value = GameManager.checkRound2A2Answer(inputText);

						if (value != 1000 && inputText.contains("moveLeft")) {
							messageField.setText(String.valueOf("Move left by "
									+ value));
						} else if (value != 1000
								&& inputText.contains("moveRight")) {
							messageField.setText(String
									.valueOf("Move right by " + value));
						} else if (value != 1000
								&& inputText.contains("moveUp")) {
							messageField.setText(String.valueOf("Move up by "
									+ value));
						} else if (value != 1000
								&& inputText.contains("moveDown")) {
							messageField.setText(String.valueOf("Move down by "
									+ value));
						} else
							messageField.setText("Wrong input...");
					}
				}
				else if (GameManager.getRound() == 3) {
					int value = GameManager.ckeckRound3Answer(inputText);

					if (value != 1000 && inputText.contains("moveLeft")) {
						messageField.setText(String.valueOf("Move left by "
								+ value));
					} else if (value != 1000 && inputText.contains("moveRight")) {
						messageField.setText(String.valueOf("Move right by "
								+ value));
					} else if (value != 1000 && inputText.contains("moveUp")) {
						messageField.setText(String.valueOf("Move up by "
								+ value));
					} else if (value != 1000 && inputText.contains("moveDown")) {
						messageField.setText(String.valueOf("Move down by "
								+ value));
					}
					else if (inputText.contains("scaleUp")) {
						messageField.setText(String.valueOf("The picture has been upscaled"));
					}
					else if (inputText.contains("scaleDown")) {
						messageField.setText(String.valueOf("The picture has been downscaled"));
					}
					else
						messageField.setText("Wrong input...");
				}
			}
		});
		
		// Setting an action for the clear button
		helpButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (GameManager.getHelpIsOn()) {
					if (GameManager.getRound() > 0) {
						GameManager.setHelpIsOn(false);
						helpButton.setText("Show Help");
						gameManager.playPageTurnSound();
					}
				}
				else if (!GameManager.getHelpIsOn()) {
					if (GameManager.getRound() > 0) {
						GameManager.setHelpIsOn(true);
						helpButton.setText("Hide Help");
						gameManager.playPageTurnSound();
					}
				}
			}
		});
		
	}  // end of Test constructor
	
	
	
	
	// get methods
	public JTextField getUserInputField() { return userInputField; }
	public JButton getHelpButton()        { return helpButton; }
	public JButton getCompileButton()     { return compileButton; }
	public JTextField getMessageField()   { return messageField; }
	
	
	// ----------------- window listener methods -------------
	public void windowActivated(WindowEvent e)   { gp.resumeGame(); }
	public void windowDeactivated(WindowEvent e) { gp.pauseGame(); }
	public void windowDeiconified(WindowEvent e) { gp.resumeGame(); }
	public void windowIconified(WindowEvent e)   { gp.pauseGame(); }
	public void windowClosing(WindowEvent e)     { gp.stopGame(); }
	public void windowClosed(WindowEvent e)      { }
	public void windowOpened(WindowEvent e)      { }
	// -------------------------------------------------------
	
	
	public static void main(String args[]) {
		long period = (long) 1000.0 / DEFAULT_FPS;
		new Test(period * 1000000L); // ms --> nanosecs
	} // end of main method
	
} // end of class Test
