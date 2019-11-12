package wordscrambler.test;

import wordscrambler.WordGenerator;

public class WordGeneratorTest {
	
	public static void main(String[] args) {
		WordGenerator gen = new WordGenerator();
		System.out.println("gen word: " + gen.getWord());
		System.out.println("gen chars: ");
		gen.getCharArray(0).forEach((c) -> {
			System.out.println(c);
		});
		
		WordGenerator gen1 = new WordGenerator();
		System.out.println("gen1 word: " + gen1.getWord());
		System.out.println("gen1 chars: ");
		gen1.getCharArray(5).forEach((c) -> {
			System.out.println(c);
		});
	}

}
