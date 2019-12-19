package utils;

import model.MasterLink;
import model.MasterLinkList;

public class MasterLinkListBuilder {

	public static MasterLinkList buildMasterLinkList(String[] words) {
		int i = 0;
		int numberOfMasterLinks = 0;
		MasterLinkList theMasterList = new MasterLinkList();
		while (i < words.length) {
			if (!isSameWord(theMasterList, words[i])) {
				theMasterList.insertLast(words[i]);
				numberOfMasterLinks++;
				i++;
			} else {
				i++;
			}
		}
		for (i = 0; i < numberOfMasterLinks; i++) {
			MasterLink atSpecificMasterLink = theMasterList.getMasterLinkAtIndex(i);
			for (int j = 0; j < words.length - 1; j++) {
				if (atSpecificMasterLink.sData.equals(words[j])) {
					atSpecificMasterLink.nextWordList.insertLast(words[j + 1]);
				}
			}
		}
		return theMasterList;

	}

	private static boolean isSameWord(MasterLinkList theMasterList, String word) {
		MasterLink current = theMasterList.getFirst();
		while (current != null) {
			if (current.sData.equals(word)) {
				return true;
			} else {
				current = current.next;
			}
		}
		return false;
	}

}
