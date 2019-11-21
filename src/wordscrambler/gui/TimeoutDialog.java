package wordscrambler.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TimeoutDialog extends JDialog {
	
	/**
	public static void main(String[] args) {
		new TimeoutDialog();
	}
	*/
	
	public TimeoutDialog(JFrame parent, GamePanel gamePanel) {
		super(parent, "Timeout", true);
		this.setLocationRelativeTo(parent);
		this.setSize(new Dimension(300, 200));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnRetry = new JButton("Retry");
		panel.add(btnRetry);
		
		btnRetry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gamePanel.resetLevel();
				dispose();
			}
			
		});
		
		JButton btnMainMenu = new JButton("Main Menu");
		panel.add(btnMainMenu);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTimesOutTry = new JLabel("Time's out! Try again?");
		lblTimesOutTry.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblTimesOutTry);
		
		this.setVisible(true);
	}

}
