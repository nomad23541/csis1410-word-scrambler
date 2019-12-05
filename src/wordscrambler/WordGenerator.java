package wordscrambler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * WordGenerator class, in charge of selecting a word.
 * 
 * @author Chris Reading, Cesar Ramirez
 *
 */
public class WordGenerator {
	
	/**
	 * Creates an array of type String that will hold all the words for the game.
	 */
	private final String[] words = {
			"dog", "cat", "dad", "mad", "rad", "mix", "rat",
			"club", "four", "five", "rock", "bear", "beer",
			"limit", "seven", "aback", "flesh", "smash", "crash",
			"health", "height", "adware", "seller", "animal", "mother",
			"central", "develop", "opinion", "element", "science", "request",
			"building", "cultural", "contempt", "software", "complete", "criminal",
			"candidate", "housewife", "economist", "landowner", "inflation", "favorite",
			"leadership", "houseplant", "attraction", "allocation", "depression", "conception"
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
	public WordGenerator(int wordLength) {
		this.rand = new Random();
		
		// get random word from words[]
		this.word = getRandomString(wordLength);
	}
	
	/**
	 * Returns a random String based on the generated word.
	 * @param length
	 * @return
	 */
	public String getRandomString(int length) {
		String word = words[rand.nextInt(words.length)];
		while(word.length() != length) {
			word = words[rand.nextInt(words.length)];
		}

		return word;
	}
	
	/**
	 * Returns the randomly selected word
	 * @return String of the word
	 */
	public String getWord() {
		return this.word;
	}
	
	/**
	 * Returns a Character List of the word, adds random characters twice the size of the word
	 * Before being returned, the List is shuffled.
	 * 
	 * @return A List<Character> containing twice the amount of characters in the original word (random characters)
	 */
	public List<Character> getCharArray() {
		List<Character> chars = new ArrayList<Character>();
		// first add all characters of word
		for(char c: this.word.toCharArray()) {
			chars.add(c);
		}
		
		// now randomize the order
		Collections.shuffle(chars);
		return chars;
	}
	
}
