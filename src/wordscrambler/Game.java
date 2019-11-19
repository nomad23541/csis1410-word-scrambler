package wordscrambler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import wordscrambler.level.LevelManager;

/*
*	Assignment: 	word-scrambler2
*	Program: 		Game
*	Programmer: 	Cannon Rudd
*	Created: 		Nov 18, 2019
*/

public class Game extends JFrame {

	private JPanel contentPane;
	
	LevelManager newManager = new LevelManager();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game frame = new Game();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Game() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 204, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblWordmix = new JLabel("Word-Mix");
		lblWordmix.setForeground(Color.WHITE);
		lblWordmix.setBackground(Color.WHITE);
		lblWordmix.setFont(new Font("Apple Chancery", Font.PLAIN, 25));
		lblWordmix.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblWordmix, BorderLayout.NORTH);
		
		JPanel gamePanel = new JPanel();
		gamePanel.setBackground(new Color(0, 204, 153));
		contentPane.add(gamePanel, BorderLayout.CENTER);
		gamePanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel boxPanel = new JPanel();
		gamePanel.add(boxPanel);
		
		JPanel charPanel = new JPanel();
		gamePanel.add(charPanel);
		
		for(JLabel label : getBoxes()) {
			boxPanel.add(label);
		}
		
		List<Character> charArray = newManager.getCurrentLevel().getWordGenerator().getCharArray(newManager.getCurrentLevel().getWordGenerator().getWord().length());
		
		System.out.println(newManager.getCurrentLevel().getWordGenerator().getWord());
		for(int i = 0; i < charArray.size(); i++) {
			Character letter = charArray.get(i);
			JButton button = new JButton(letter.toString());
			button.setPreferredSize(new Dimension(80, 80));
			button.setFocusPainted(false);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for(JLabel label : getBoxes()) {
						System.out.println(getBoxes().size());
						if(label.getText().isEmpty()) {
							new Thread(new Runnable() {

								@Override
								public void run() {
									label.setText(button.getText());
									System.out.println("Found JLabel: " + label.getText());
								}
								
							}).start();
							break;
						}
					}
					
					
					
					button.setVisible(false);
					
				}	
			});
			charPanel.add(button);
		}
		
		
		
	}
	
	public List<JLabel> getBoxes() {
		List<JLabel> labels = new ArrayList<JLabel>();
		for(int i = 0; i < newManager.getCurrentLevel().getWordGenerator().getWord().length(); i++) {
			JLabel label = new JLabel();
			label.setPreferredSize(new Dimension(40, 40));
			label.setOpaque(true);
			label.setBackground(Color.WHITE);
			label.setForeground(Color.BLACK);
			labels.add(label);
		}
		
		return labels;
	}

}
