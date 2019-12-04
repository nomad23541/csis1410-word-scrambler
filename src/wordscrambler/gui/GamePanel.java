package wordscrambler.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import wordscrambler.level.LevelManager;
import keeptoo.KGradientPanel;
import java.awt.GridLayout;
import javax.swing.ImageIcon;

/**
 * GamePanel class, displays the game area
 * 
 * @author Chris Reading
 * @author Cannon Rudd
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
	
	private JPanel boxPanel;
	private JPanel charPanel;
	private JPanel btnPanel;
	private JButton btnResetLevel;
	private JButton btnHint;
	private JPanel headerPanel;
	private JLabel label_1;
	private JLabel lblLevel;
	private JPanel levelPanel;
	private KGradientPanel gradientPanel;
	private JButton btnSave;
	private JButton btnPause;
	
	public GamePanel() {
		File save = new File("LevelSave.txt");
		if(save.exists()) {
			try (Scanner reader = new Scanner(save)){
				if(reader.hasNext()) {
					lm.setCurrentLevel(lm.getLevelByNumber(reader.nextInt()));
				}
			} catch (FileNotFoundException e2) {
				e2.printStackTrace();
			}
		}
		// setup the level
		lm = new LevelManager(this);
		setupBoxLabels();
		setupCharButtons();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		gradientPanel = new KGradientPanel();
		add(gradientPanel);
		gradientPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
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
		
		headerPanel = new JPanel();
		levelPanel.add(headerPanel);
		headerPanel.setBackground(new Color(0, 0, 0, 0));
		headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(GamePanel.class.getResource("/wordscrambler/gui/images/WordMix.png")));
		label_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		label_1.setBackground(new Color(0, 0, 0, 0));
		label_1.setOpaque(false);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Dialog", Font.PLAIN, 25));
		headerPanel.add(label_1);
		
		boxPanel = new JPanel();
		gradientPanel.add(boxPanel);
		boxPanel.setBackground(new Color(0, 0, 0, 0));
		
		timerLbl = new JLabel();
		timerLbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		timerLbl.setForeground(Color.WHITE);
		timerLbl.setText("" + lm.getCurrentLevel().getTimer().getDuration());
		
		charPanel = new JPanel();
		gradientPanel.add(charPanel);
		charPanel.setBackground(new Color(0, 0, 0, 0));
		
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
				if(!btnSave.isVisible()) {
					btnSave.setVisible(true);
					btnResetLevel.setVisible(true);
					btnPause.setIcon(new ImageIcon(GamePanel.class.getResource("/wordscrambler/gui/images/play.png")));
					lm.getCurrentLevel().getTimer().stop();
					charPanel.setVisible(false);
				}
				else {
					btnSave.setVisible(false);
					btnResetLevel.setVisible(false);
					btnPause.setIcon(new ImageIcon(GamePanel.class.getResource("/wordscrambler/gui/images/pause.png")));
					lm.getCurrentLevel().getTimer().start();
					charPanel.setVisible(true);
				}
				
			}
		});
		btnPanel.add(btnPause);
		
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

	public void updateTimerLabel(int currentTime) {
		this.timerLbl.setText("" + currentTime);
	}
	
	public void displayTimeOut() {
		new TimeoutDialog((JFrame) this.getParent().getParent().getParent(), this);
	}
	
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
	}
	
	public void nextLevel() {
		lm.getCurrentLevel().getTimer().stop();
		lm.nextLevel();
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
