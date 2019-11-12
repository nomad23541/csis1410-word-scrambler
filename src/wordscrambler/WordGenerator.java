package wordscrambler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * WordGenerator class, in charge of selecting a word.
 * 
 * @author Chris Reading
 *
 */
public class WordGenerator {
	
	/**
	 * String array of words to randomly be selected
	 */
	private final String[] words = {
			"club", "wreck", "fat", "smash", "error", "aback", "flesh", "playground", "connection", "cooing", "bear", "relation", "beneficial", "calculating", "bolt", "quiet", "shirt", "unite", "vacation", "yarn"
	};
	
	/**
	 * The Random type that will be used
	 */
	private final Random rand;
	/**
	 * The main word that this class will produce
	 */
	private final String word;
	
	/**
	 * Constructs the WordGenerator class
	 * Initializes rand and assigns word to a random string from words[]
	 */
	public WordGenerator() {
		this.rand = new Random();
		
		// get random word from words[]
		this.word = words[rand.nextInt(words.length)];
	}
	
	/**
	 * Returns the randomly selected word
	 * @return String of the word
	 */
	public String getWord() {
		return this.word;
	}
	
	/**
	 * Returns a Character List of the word, if extraCharAmount is greater than zero, it will add random characters to the array
	 * Before being returned, the List is shuffled.
	 * 
	 * @param extraCharAmount int amount of extra characters to be added
	 * @return A List<Character> of the word, with extra if parameter is greater than zero.
	 */
	public List<Character> getCharArray(int extraCharAmount) {
		List<Character> chars = new ArrayList<Character>();
		// first add all characters of word
		for(char c: this.word.toCharArray()) {
			chars.add(c);
		}
		
		// now if extraCharAmount is greater than zero, add random characters to the array
		if(extraCharAmount > 0) {
			for(int i = 0; i < extraCharAmount; i++) {
				chars.add((char) (this.rand.nextInt(26) + 'a'));
			}
		}
		
		// now randomize the order
		Collections.shuffle(chars);
		return chars;
	}
	
}
