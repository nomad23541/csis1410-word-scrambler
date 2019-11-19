package wordscrambler.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import wordscrambler.level.LevelManager;

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
	
	public GamePanel() {
		// setup the level
		lm = new LevelManager(this);
		setupBoxLabels();
		setupCharButtons();
		
		JLabel lblWordmix = new JLabel("Word-Mix");
		lblWordmix.setForeground(Color.WHITE);
		lblWordmix.setBackground(Color.WHITE);
		lblWordmix.setFont(new Font("Apple Chancery", Font.PLAIN, 25));
		lblWordmix.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblWordmix, BorderLayout.NORTH);
		
		this.setBackground(new Color(0, 204, 153));
		this.setLayout(new GridLayout(4, 1, 0, 0));
		
		boxPanel = new JPanel();
		boxPanel.setBackground(new Color(0, 204, 153));
		this.add(boxPanel);
		
		timerLbl = new JLabel();
		timerLbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		timerLbl.setForeground(Color.WHITE);
		timerLbl.setText("" + lm.getCurrentLevel().getTimer().getDuration());
		
		charPanel = new JPanel();
		charPanel.setBackground(new Color(0, 204, 153));
		this.add(charPanel);
		
		btnPanel = new JPanel();
		btnPanel.setBackground(new Color(0, 204, 153));
		add(btnPanel);
		
		btnResetLevel = new JButton("Reset Level");
		btnPanel.add(btnResetLevel);
		
		btnHint = new JButton("Hint?");
		btnPanel.add(btnHint);
		
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
	}
	
	public void updateTimerLabel(int currentTime) {
		this.timerLbl.setText("" + currentTime);
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
			btn.setPreferredSize(new Dimension(80, 80));
			btn.setFocusPainted(false);
			
			// Hide button and set next fillable Jlabel with that button's text
			btn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JLabel label = getLabelToFill(labels);
					label.setText(btn.getText());
					btn.setVisible(false);
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
	public JLabel getLabelToFill(List<JLabel> labels) {
		for(JLabel label : labels) {
			if(label.getText().isEmpty()) {
				return label;
			}
		}
		
		// TODO: if there are no more labels to fill in, check to see if the player wins this level
		return null;
	}
	
}
