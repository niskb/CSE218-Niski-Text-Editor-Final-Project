package utils;

import java.io.FileWriter;
import java.io.IOException;

import model.Link;
import model.MasterLink;
import model.MasterLinkList;
import view.SceneBuilder;

public class ParagraphGenerator {

	public static String generateParagraph(MasterLinkList theMasterList, int numberOfWords) {
		int lengthOfMasterLinkList = theMasterList.getMasterLinkListLength();
		MasterLink startingWord = theMasterList.getMasterLinkAtIndex((int) (Math.random() * lengthOfMasterLinkList));
		int lengthOfStartingWordLinkList = startingWord.nextWordList.getLinkListLength();
		Link nextWord = startingWord.nextWordList.getLinkAtIndex((int) (Math.random() * lengthOfStartingWordLinkList));
		String generatedParagraph = "";
		for (int i = 0; i < numberOfWords; i++) {
			generatedParagraph = generatedParagraph + (nextWord.sData) + " ";
			nextWord = theMasterList.getMasterLinkFromWordData(nextWord.sData).nextWordList.getLinkAtIndex(
					(int) (Math.random() * theMasterList.getMasterLinkFromWordData(nextWord.sData).nextWordList
							.getLinkListLength()));
		}
		outputGeneratedParagraph(generatedParagraph);
		return generatedParagraph;
	}

	public static String generateParagraphNoOutput(MasterLinkList theMasterList, int numberOfWords) {
		int lengthOfMasterLinkList = theMasterList.getMasterLinkListLength();
		MasterLink startingWord = theMasterList.getMasterLinkAtIndex((int) (Math.random() * lengthOfMasterLinkList));
		int lengthOfStartingWordLinkList = startingWord.nextWordList.getLinkListLength();
		Link nextWord = startingWord.nextWordList.getLinkAtIndex((int) (Math.random() * lengthOfStartingWordLinkList));
		String generatedParagraph = "";
		for (int i = 0; i < numberOfWords; i++) {
			generatedParagraph = generatedParagraph + (nextWord.sData) + " ";
			nextWord = theMasterList.getMasterLinkFromWordData(nextWord.sData).nextWordList.getLinkAtIndex(
					(int) (Math.random() * theMasterList.getMasterLinkFromWordData(nextWord.sData).nextWordList
							.getLinkListLength()));
		}
		return generatedParagraph;
	}

	public static String generateParagraphFromStartingWord(MasterLinkList theMasterList, String inputWord,
			int numberOfWords) {
		if (wordIsInMasterLinkList(theMasterList, inputWord)) {
			MasterLink startingWord = theMasterList.getMasterLinkFromWordData(inputWord);
			int lengthOfStartingWordLinkList = startingWord.nextWordList.getLinkListLength();
			Link nextWord = startingWord.nextWordList
					.getLinkAtIndex((int) (Math.random() * lengthOfStartingWordLinkList));
			String generatedParagraph = startingWord.sData + " ";
			for (int i = 0; i < numberOfWords - 1; i++) {
				generatedParagraph = generatedParagraph + (nextWord.sData) + " ";
				nextWord = theMasterList.getMasterLinkFromWordData(nextWord.sData).nextWordList.getLinkAtIndex(
						(int) (Math.random() * theMasterList.getMasterLinkFromWordData(nextWord.sData).nextWordList
								.getLinkListLength()));
			}
			outputGeneratedParagraph(generatedParagraph);
			return generatedParagraph;
		} else {
			AlertMessages.startingWordForMarkovDoesNotExist(SceneBuilder.getTheAlert());
			return null;
		}
	}

	public static String generateParagraphFromStartingWordNoOutput(MasterLinkList theMasterList, String inputWord,
			int numberOfWords) {
		if (wordIsInMasterLinkList(theMasterList, inputWord)) {
			MasterLink startingWord = theMasterList.getMasterLinkFromWordData(inputWord);
			int lengthOfStartingWordLinkList = startingWord.nextWordList.getLinkListLength();
			Link nextWord = startingWord.nextWordList
					.getLinkAtIndex((int) (Math.random() * lengthOfStartingWordLinkList));
			String generatedParagraph = startingWord.sData + " ";
			for (int i = 0; i < numberOfWords - 1; i++) {
				generatedParagraph = generatedParagraph + (nextWord.sData) + " ";
				nextWord = theMasterList.getMasterLinkFromWordData(nextWord.sData).nextWordList.getLinkAtIndex(
						(int) (Math.random() * theMasterList.getMasterLinkFromWordData(nextWord.sData).nextWordList
								.getLinkListLength()));
			}
			return generatedParagraph;
		} else {
			AlertMessages.startingWordForMarkovDoesNotExist(SceneBuilder.getTheAlert());
			return null;
		}
	}

	private static boolean wordIsInMasterLinkList(MasterLinkList theMasterList, String inputWord) {
		for (int i = 0; i < theMasterList.getMasterLinkListLength(); i++) {
			if (theMasterList.getMasterLinkAtIndex(i).sData.equalsIgnoreCase(inputWord))
				return true;
			else
				continue;
		}
		return false;
	}

	private static void outputGeneratedParagraph(String paragraph) {
		try {
			FileWriter fw = new FileWriter("outputData\\paragraphOutput.txt", true);
			fw.write(paragraph);
			fw.write("\n");
			fw.close();
		} catch (IOException e) {
			e.getMessage();
		}
	}

	public static String[] wordsSeperator(String text) {
		if (!(text.isEmpty())) {
			String[] words = text.split("[^A-Za-z0-9\\-']+");
			// regex to include all letters, numbers, and dashes, sometimes '
			return words;
		} else {
			return new String[] { "error" };
		}
	}

}
