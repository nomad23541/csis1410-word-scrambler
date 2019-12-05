package wordscrambler.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class YouWinDialog extends JDialog {
	
	public YouWinDialog(JFrame parent, GamePanel gamePanel) {
		super(parent, "You Win", true);
		this.setUndecorated(true);
		this.setLocationRelativeTo(parent);
		this.setSize(new Dimension(300, 200));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnRetry = new JButton("Restart Game");
		panel.add(btnRetry);
		
		JButton btnClose = new JButton("Close Game");
		panel.add(btnClose);
		
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		btnRetry.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gamePanel.resetGame();
				gamePanel.togglePause();
				dispose();
			}	
		});
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				gamePanel.resetGame();
				gamePanel.togglePause();
				dispose();
			}
		});
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTimesOutTry = new JLabel("You Win");
		lblTimesOutTry.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblTimesOutTry);
		
		this.setVisible(true);
	}

}
