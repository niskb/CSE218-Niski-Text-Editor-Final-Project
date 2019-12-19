package utils;

public class Counter {

	public static int countWords(String text) {
		final int out = 0;
		final int in = 1;
		int state = out;
		int count = 0;
		int i = 0;
		while (i < text.length()) {
			if (text.charAt(i) == ' ' || text.charAt(i) == '\n' || text.charAt(i) == '\t')
				state = out;
			else if (state == out) {
				state = in;
				count++;
			}
			i++;
		}
		return count;
	}

	public static int countSentences(String text) {
		int count = 0;
		for (int i = 0; i < text.length() - 1; i++) {
			if (Character.isLetterOrDigit(text.charAt(i))
					&& (text.charAt(i) != '.' || text.charAt(i) != '!' || text.charAt(i) != '?')) {
				if (text.charAt(i + 1) != '-'
						&& ((text.charAt(i + 1) == '.' || text.charAt(i + 1) == '!' || text.charAt(i + 1) == '?')))
					count++;
			} else
				continue;
		}
		return count;
	}

	public static double calculateFleschScore(String text) {
		double readabilityEase = 0.0;
		int wordCount = countWords(text);
		int sentenceCount = countSentences(text);
		int syllableCount = countSyllables(text);
		double averageSentenceLength = (wordCount + 0.0) / (sentenceCount + 0.0);
		double averageSyllablesPerWord = (syllableCount + 0.0) / (wordCount + 0.0);
		readabilityEase = 206.835 - (1.015 * averageSentenceLength) - (84.6 * averageSyllablesPerWord);
		return readabilityEase;
	}

	public static int countSyllables(String text) {
		int count = 0;
		text = text.toLowerCase();
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == '\"' || text.charAt(i) == '\'' || text.charAt(i) == '-' || text.charAt(i) == ','
					|| text.charAt(i) == ')' || text.charAt(i) == '(') {
				text = text.substring(0, i) + text.substring(i + 1, text.length());
			}
		}
		boolean isPreviousVowel = false;
		for (int j = 0; j < text.length(); j++) {
			if (text.contains("a") || text.contains("e") || text.contains("i") || text.contains("o")
					|| text.contains("u")) {
				if (isVowel(text.charAt(j)) && !((text.charAt(j) == 'e') && (j == text.length() - 1))) {
					if (isPreviousVowel == false) {
						count++;
						isPreviousVowel = true;
					}
				} else {
					isPreviousVowel = false;
				}
			} else {
				count++;
				break;
			}
		}
		return count;
	}

	private static boolean isVowel(char ch) {
		if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
			return true;
		} else {
			return false;
		}
	}

}
