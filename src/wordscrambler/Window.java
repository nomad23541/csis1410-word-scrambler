package wordscrambler;

import java.awt.Dimension;

import javax.swing.JFrame;

import wordscrambler.gui.GamePanel;

/**
 * Window class, handles creating and adding the various panels to be used
 * @author Chris Reading
 *
 */
public class Window extends JFrame {
	
	public static void main(String[] args) {
		new Window();
	}
	
	public Window() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(800, 600));
		
		// add GamePanel
		this.setContentPane(new GamePanel());
		
		this.setVisible(true);
	}
	
}
