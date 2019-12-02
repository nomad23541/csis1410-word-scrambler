package wordscrambler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import wordscrambler.gui.GamePanel;
import wordscrambler.level.LevelManager;

/**
 * Manages serializing the LevelManager and deserializing for basic saving/loading
 * 
 * @author Chris Reading
 */
public class SaveManager {
	
	private static final String serLocation = "GamePanel.bin";

	public static void serialize(GamePanel lm) {
		try(FileOutputStream file = new FileOutputStream(serLocation); ObjectOutputStream out = new ObjectOutputStream(file)) {
			out.writeObject(lm);
			System.out.println("GamePanel has been serialized");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static GamePanel deserialize() {
		GamePanel lm = null;
		
		try(FileInputStream file = new FileInputStream(serLocation); ObjectInputStream input = new ObjectInputStream(file)) {
			lm = (GamePanel) input.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return lm;
	}
	
	public static boolean doesSerialFileExist() {
		File tempSer = new File(serLocation);
		return tempSer.exists();
	}
	
}
