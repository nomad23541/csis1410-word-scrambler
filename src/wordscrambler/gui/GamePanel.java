package wordscrambler.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import keeptoo.KGradientPanel;
import wordscrambler.level.LevelManager;

/**
 * GamePanel class, displays the game area
 * 
 * @author Chris Reading
 * @author Cannon Rudd
 * @author Cesar Ramirez
 *
 */
public class GamePanel extends JPanel {
	
	/**
	 * LevelManager in order to switch levels, etc
	 */
	private LevelManager lm;
	
	/**
	 * List<Label> that contains all the labels that will be filled in when a user clicks a button
	 */
	private List<JLabel> labels;
	
	/**
	 * List<JButton> buttons that the user can click to fill in labels
	 */
	private List<JButton> buttons;
	
	/**
	 * JLabel used for displaying the countdown timer
	 */
	private JLabel timerLbl;
	
	/**
	 * All of the panels, buttons, and labels are initialized here.
	 */
	private JPanel boxPanel;
	private JPanel charPanel;
	private JPanel btnPanel;
	private JButton btnResetLevel;
	private JButton btnHint;
	private JLabel lblLevel;
	private JPanel levelPanel;
	private KGradientPanel gradientPanel;
	private JButton btnSave;
	private JButton btnPause;
	private JPanel headerPnl;
	private JLabel lblNewLabel;
	private JButton btnResetGame;
	
	/**
	 * Constructor - Creates and paints the properties of the game.
	 */
	public GamePanel() {
		// setup the level
		lm = new LevelManager(this);
				
		File save = new File("LevelSave.txt");
		if(save.exists()) {
			try(BufferedReader reader = new BufferedReader(new FileReader(save))) {
				String line;
				while((line = reader.readLine()) != null) {
					lm.setCurrentLevel(lm.getLevelByNumber(Integer.parseInt(line)));
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		setupBoxLabels();
		setupCharButtons();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		gradientPanel = new KGradientPanel();
		add(gradientPanel);
		gradientPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		headers();
		
		boxes();
		
		charPanel = new JPanel();
		gradientPanel.add(charPanel);
		charPanel.setBackground(new Color(0, 0, 0, 0));
		
		menu(save);
		
		this.setBackground(new Color(0, 0, 0, 0));
		
		for(JLabel label : labels) {
			boxPanel.add(label);
		}
		
		for(JButton button : buttons) {
			charPanel.add(button);
		}
		
		boxPanel.add(timerLbl);
		
		// start the timer
		lm.getCurrentLevel().getTimer().start();
		
		// DEBUGGING
		System.out.println("Level Word: " + lm.getCurrentLevel().getWordGenerator().getWord());
		
		// timer to continuously repaint the gradient panel
		Timer timer = new Timer(0, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gradientPanel.repaint();
			}
			
		});
		timer.start();
	}

	/**
	 * Creates the box panel with the blank labels and the timer.
	 */
	private void boxes() {
		boxPanel = new JPanel();
		gradientPanel.add(boxPanel);
		boxPanel.setBackground(new Color(0, 0, 0, 0));
		
		timerLbl = new JLabel();
		timerLbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		timerLbl.setForeground(Color.WHITE);
		timerLbl.setText("" + lm.getCurrentLevel().getTimer().getDuration());
	}

	/**
	 * Creates the logo and displays the level number at the top.
	 */
	private void headers() {
		headerPnl = new JPanel();
		FlowLayout flowLayout = (FlowLayout) headerPnl.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		gradientPanel.add(headerPnl);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(GamePanel.class.getResource("/wordscrambler/gui/images/WordMix.png")));
		headerPnl.add(lblNewLabel);
		headerPnl.setBackground(new Color(0, 0, 0, 0));
		headerPnl.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		levelPanel = new JPanel();
		gradientPanel.add(levelPanel);
		levelPanel.setBackground(new Color(0, 0, 0, 0));
		levelPanel.setMinimumSize(new Dimension(10, 0));
		levelPanel.setPreferredSize(new Dimension(10, 0));
		
		lblLevel = new JLabel("level");
		levelPanel.add(lblLevel);
		lblLevel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblLevel.setBorder(new EmptyBorder(10, 10, 10, 10));
		lblLevel.setForeground(Color.WHITE);
		
		lblLevel.setText("Level: " + lm.getCurrentLevel().getLevelNumber());
	}

	/**
	 * Creates and provides functionality for all of the buttons in the menu.
	 * @param save
	 */
	private void menu(File save) {
		btnPanel = new JPanel();
		gradientPanel.add(btnPanel);
		btnPanel.setPreferredSize(new Dimension(10, 5));
		btnPanel.setBackground(new Color(0, 0, 0, 0));
		
		
		btnHint = new JButton("Hint?");
		btnHint.setFont(new Font("Impact", Font.PLAIN, 20));
		btnHint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getHint();
			}
		});
		btnPanel.add(btnHint);
		
		btnResetGame = new JButton("Reset Game");
		btnResetGame.setFont(new Font("Impact", Font.PLAIN, 20));
		btnResetGame.setVisible(false);
		btnResetGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetGame();
			}
		});
		btnPanel.add(btnResetGame);
		
		btnResetLevel = new JButton("Reset Level");
		btnResetLevel.setFont(new Font("Impact", Font.PLAIN, 20));
		btnResetLevel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				resetLevel();
			}
		});
		btnResetLevel.setVisible(false);
		btnPanel.add(btnResetLevel);
		
		btnSave = new JButton("Save");
		btnSave.setFont(new Font("Impact", Font.PLAIN, 20));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PrintWriter writer = new PrintWriter(save);
					writer.println(lm.getCurrentLevel().getLevelNumber());
					writer.close();
					btnSave.setText("Saved");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSave.setVisible(false);
		btnPanel.add(btnSave);
		
		btnPause = new JButton(new ImageIcon(GamePanel.class.getResource("/wordscrambler/gui/images/pause.png")));
		btnPause.setBorderPainted(false);
		btnPause.setFont(new Font("Impact", Font.PLAIN, 20));
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				togglePause();
			}
		});
		btnPanel.add(btnPause);
	}
	
	/**
	 * Toggles between a visible and non-visible menu depending on its current status.
	 */
	public void togglePause() {
		if(!btnSave.isVisible()) {
			btnSave.setVisible(true);
			btnResetGame.setVisible(true);
			btnResetLevel.setVisible(true);
			btnHint.setVisible(false);
			btnPause.setIcon(new ImageIcon(GamePanel.class.getResource("/wordscrambler/gui/images/play.png")));
			lm.getCurrentLevel().getTimer().stop();
			charPanel.setVisible(false);
		}
		else {
			btnSave.setVisible(false);
			btnResetGame.setVisible(false);
			btnResetLevel.setVisible(false);
			btnHint.setVisible(true);
			btnPause.setIcon(new ImageIcon(GamePanel.class.getResource("/wordscrambler/gui/images/pause.png")));
			lm.getCurrentLevel().getTimer().start();
			charPanel.setVisible(true);
		}
	}
	
	/**
	 * Updates the current time on the label.
	 * @param currentTime
	 */
	public void updateTimerLabel(int currentTime) {
		this.timerLbl.setText("" + currentTime);
	}
	
	/**
	 * Times out the display.
	 */
	public void displayTimeOut() {
		new TimeoutDialog((JFrame) this.getParent().getParent().getParent(), this);
	}
	
	/**
	 * Resets the game.
	 */
	public void resetGame() {
		File file = new File("LevelSave.txt");
		if(file.exists()) {
			file.delete();
		}
		
		lm = new LevelManager(this);
		resetLevel();
	}
	
	/**
	 * Resets the level.
	 */
	public void resetLevel() {
		lm.getCurrentLevel().getTimer().stop();
		lm.resetLevel();
		lblLevel.setText("Level: " + lm.getCurrentLevel().getLevelNumber());
		
		labels.clear();
		buttons.clear();
		
		boxPanel.removeAll();
		boxPanel.revalidate();
		boxPanel.repaint();
		charPanel.removeAll();
		charPanel.revalidate();
		charPanel.repaint();
		
		setupBoxLabels();
		setupCharButtons();
		
		for(JLabel label : labels) {
			boxPanel.add(label);
		}
		
		for(JButton button : buttons) {
			charPanel.add(button);
		}
		
		timerLbl.setText("" + lm.getCurrentLevel().getTimer().getStartTime());
		boxPanel.add(timerLbl);
		
		lm.getCurrentLevel().getTimer().start();
		
		System.out.println("Level word: " + lm.getCurrentLevel().getWordGenerator().getWord());
		togglePause();
	}
	
	/**
	 * Updates to the next level once one level is beaten.
	 */
	public void nextLevel() {
		lm.getCurrentLevel().getTimer().stop();
		lm.nextLevel((JFrame) this.getParent().getParent().getParent(), this);
		lblLevel.setText("Level: " + lm.getCurrentLevel().getLevelNumber());
		
		labels.clear();
		buttons.clear();
		
		boxPanel.removeAll();
		boxPanel.revalidate();
		boxPanel.repaint();
		charPanel.removeAll();
		charPanel.revalidate();
		gradientPanel.repaint();
		charPanel.repaint();
		
		setupBoxLabels();
		setupCharButtons();
		
		for(JLabel label : labels) {
			boxPanel.add(label);
		}
		
		for(JButton button : buttons) {
			charPanel.add(button);
		}
		
		timerLbl.setText("" + lm.getCurrentLevel().getTimer().getStartTime());
		boxPanel.add(timerLbl);
		
		lm.getCurrentLevel().getTimer().start();
		
		System.out.println("Level word: " + lm.getCurrentLevel().getWordGenerator().getWord());
	}
	
	/**
	 * Adds a character, or a few depending on the size of the word, to the answer. It also docks time.
	 */
	public void getHint() {
		// disallow spamming to pass level when timer is 0
		if(lm.getCurrentLevel().getTimer().getDuration() <= 0) {
			return;
		}
		
		lm.getCurrentLevel().getTimer().useHint();
		String word = lm.getCurrentLevel().getWordGenerator().getWord();
		
		int timesToRun = 1;
		
		if(word.length() == 6) {
			timesToRun = 3;
		} else if(word.length() >= 7) {
			timesToRun = 4;
		}
		
		for(int j = 0; j < timesToRun; j++) {
			int index = 0; 
			for(int i = 0; i < labels.size(); i++) {
				JLabel lbl = labels.get(i);
				if(lbl.getText().isEmpty()) {
					index = i; 
					break;
				}
			}
			
			String charToRemove = "" + word.charAt(index);
			JButton btnToHide = null;
			for(JButton button : buttons) {
				if(button.getText().equalsIgnoreCase(charToRemove)) {
					btnToHide = button;
				}
			}
			JLabel label = getLabelToFill(labels, charToRemove);
			label.setText(charToRemove);
			btnToHide.setVisible(false);	
		}
	}
	
	/**
	 * Creates the labels that will be fill in using the size of the word in the Level
	 */
	private void setupBoxLabels() {
		labels = new ArrayList<JLabel>();

		for(int i = 0; i < lm.getCurrentLevel().getWordGenerator().getWord().length(); i++) {
			JLabel label = new JLabel("", SwingConstants.CENTER);
			label.setFont(new Font("Tahoma", Font.BOLD, 16));
			label.setPreferredSize(new Dimension(40, 40));
			label.setOpaque(true);
			label.setBackground(Color.WHITE);
			label.setForeground(Color.BLACK);
			
			// on label click, remove text and show button that contains that text
			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					for(JButton btn : buttons) {
						if(!btn.isVisible() && btn.getText().equalsIgnoreCase(label.getText().trim())) {
							btn.setVisible(true);
							label.setText("");
							break;
						}
					}
				}
			});
			
			labels.add(label);
		}
	}
	
	/**
	 * Creates the buttons based on the Level's word charArray
	 */
	public void setupCharButtons() {
		buttons = new ArrayList<JButton>();
		
		List<Character> charArray = lm.getCurrentLevel().getWordGenerator().getCharArray();
		
		for(Character letter : charArray) {
			JButton btn = new JButton(letter.toString());
			btn.setPreferredSize(new Dimension(60, 60));
			btn.setFocusPainted(false);
			
			// Hide button and set next fillable Jlabel with that button's text
			btn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JLabel label = getLabelToFill(labels, btn.getText());
					if(label != null) {
						label.setText(btn.getText());
						btn.setVisible(false);	
					}
				}
			});

			buttons.add(btn);
		}
	}
	
	/**
	 * Gets the next label to fill in, if there are no more labels to fill in then
	 * we will check to see if the user got the right word.
	 * 
	 * @param labels List<JLabel> to iterate through
	 * @return Returns the JLabel to manipulate
	 */
	public JLabel getLabelToFill(List<JLabel> labels, String lastCharStr) {
		for(int i = 0; i < labels.size(); i++) {
			JLabel label = labels.get(i);
			
			if(label.getText().isEmpty() && i == labels.size() - 1) {
				StringBuilder finalWord = new StringBuilder();
				for(JLabel lbl : labels) {
					finalWord.append(lbl.getText());
				}
				
				finalWord.append(lastCharStr);
								
				if(finalWord.toString().equalsIgnoreCase(lm.getCurrentLevel().getWordGenerator().getWord())) {
					nextLevel();
				}	
			}
			
			if(label.getText().isEmpty()) {
				return label;
			}
		}

		return null;
	}
	
}
