package wordscrambler;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import wordscrambler.gui.GamePanel;
import wordscrambler.io.SaveManager;

/**
 * Window class, handles creating and adding the various panels to be used
 * @author Chris Reading
 *
 */
public class Window extends JFrame {
	
	public static void main(String[] args) {
		try {
			// set swing to use the operating system's default theme
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		new Window();
	}
	
	public Window() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(800, 600));
		this.setLocationRelativeTo(null);
		
		// add GamePanel
		if(SaveManager.doesSerialFileExist()) {
			this.setContentPane(SaveManager.deserialize());
		} else {
			this.setContentPane(new GamePanel());
		}
		
		this.setVisible(true);
	}
	
}
