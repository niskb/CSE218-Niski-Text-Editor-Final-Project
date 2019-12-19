package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;

import view.SceneBuilder;

public class SpellChecker {

	private static Hashtable<Integer, String> dictionary;

	public SpellChecker() {
		try {
			Scanner sc = new Scanner(new File("inputData\\dictionary.txt"));
			dictionary = new Hashtable<Integer, String>(198347); // closest prime number double to the number of lines
																	// of dictionary.txt
			int i = 0;
			while (sc.hasNextLine()) {
				dictionary.put(i++, sc.nextLine());
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary file not found!");
		}
	}

	public void checkSpelling() {
		String text = SceneBuilder.getTheTextArea().getText();
		String[] words = ParagraphGenerator.wordsSeperator(text);
		for (int i = 0; i < words.length; i++) {
			if (!dictionary.contains(words[i])) {
				AlertMessages.spelledWordWrong(words[i]);
			}
		}
	}

}
