package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import view.SceneBuilder;
import javafx.stage.Stage;

public class FileUtils {

	private static String LOADED_FILE_LOCATION = "";
	private static int NUMBER_OF_LINES = 0;

	public static File loadTextFile(Stage primaryStage) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Text File");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("TXT", "*.txt"));
		return fileChooser.showOpenDialog(primaryStage);
	}

	public static String scanThroughTextFile(File loadedTextFile, Alert theAlert) {
		NUMBER_OF_LINES = 0;
		String textAreaString = "";
		try {
			Scanner sc = new Scanner(loadedTextFile);
			while (sc.hasNextLine()) {
				textAreaString = textAreaString + sc.nextLine() + "\n";
				NUMBER_OF_LINES++;
			}
			LOADED_FILE_LOCATION = loadedTextFile.getAbsolutePath();
			sc.close();
		} catch (FileNotFoundException e) {
			AlertMessages.cantReadFile(theAlert);
		}
		return textAreaString;
	}

	public static void percentageOfTextFile(int percentage, Alert theAlert) {
		String shortenedTextAreaString = "";
		if (percentage > 100 || percentage < 0) {
			AlertMessages.percentageIsEnteredWrong(theAlert);
		} else {
			try {
				Scanner sc = new Scanner(new File(LOADED_FILE_LOCATION));
				for (int i = 0; i < NUMBER_OF_LINES * (percentage / 100.0); i++) {
					shortenedTextAreaString = shortenedTextAreaString + sc.nextLine() + "\n";
				}
				sc.close();
				saveShortenedTextFile(shortenedTextAreaString, percentage, theAlert);
			} catch (FileNotFoundException e) {
				AlertMessages.noFileLoaded(SceneBuilder.getTheAlert());
			}
		}
	}

	public static void saveTextFile(Stage primaryStage) {
		File savedFile = FileUtils.savingTextFile(primaryStage);
		try {
			FileWriter fw = new FileWriter(savedFile);
			fw.write(SceneBuilder.getTheTextArea().getText());
			fw.close();
			SceneBuilder.setFileSaved(new Boolean(true));
		} catch (Exception e) {
			AlertMessages.cantSaveFile(SceneBuilder.getTheAlert());
		}
	}

	private static File savingTextFile(Stage primaryStage) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Text File");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("TXT", "*.txt"));
		return fileChooser.showSaveDialog(primaryStage);
	}

	private static void saveShortenedTextFile(String shortenedTextAreaString, int percentage, Alert theAlert) {
		try {
			FileWriter fw = new FileWriter("outputData\\percentage" + percentage + ".txt", false);
			fw.write(shortenedTextAreaString);
			fw.close();
		} catch (IOException e) {
			AlertMessages.cantSaveFile(SceneBuilder.getTheAlert());
		}
	}

}
