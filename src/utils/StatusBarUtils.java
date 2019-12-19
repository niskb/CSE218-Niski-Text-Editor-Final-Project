package utils;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class StatusBarUtils {

	public static void setStatusIncludingAll(Label theStatusBar, TextArea theTextArea) {
		theStatusBar.setText("Word Count: " + Counter.countWords(theTextArea.getText()) + " Sentence Count: "
				+ Counter.countSentences(theTextArea.getText()) + " Flesch Score: "
				+ Counter.calculateFleschScore(theTextArea.getText()));
	}

	public static void setStatusWordCount(Label theStatusBar, TextArea theTextArea) {
		theStatusBar.setText("Word Count: " + Counter.countWords(theTextArea.getText()));
	}

	public static void setStatusSentenceCount(Label theStatusBar, TextArea theTextArea) {
		theStatusBar.setText(" Sentence Count: " + Counter.countSentences(theTextArea.getText()));
	}

	public static void setStatusFleschScore(Label theStatusBar, TextArea theTextArea) {
		theStatusBar.setText(" Flesch Score: " + Counter.calculateFleschScore(theTextArea.getText()));
	}

}
