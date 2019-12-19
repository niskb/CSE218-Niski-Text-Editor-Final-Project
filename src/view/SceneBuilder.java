package view;

import java.io.File;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import utils.SpellChecker;
import utils.StatusBarUtils;

public class SceneBuilder {

	private static BorderPane theBorderPane = new BorderPane();
	private static Scene theScene = new Scene(theBorderPane, 1280, 720);
	private static Alert theAlert = new Alert(AlertType.NONE);
	private static TextInputDialog theTextInputDialog = new TextInputDialog();
	private static Label theStatusBar = new Label("This is where the status bar is located!");
	private static boolean isFileLoaded = false;
	private static boolean isFileSaved = true;
	private static File theTextFile = null;
	private static TextArea theTextArea = new TextArea();
	private static String thePreviousTextAreaString = "";
	private static Label theMarkovLabel = new Label("");
	private static SpellChecker theSpellChecker = new SpellChecker();

	public SceneBuilder(Stage primaryStage) {
		theTextInputDialog.setTitle("Text Input Dialog");
		theTextArea.setMaxSize(541, 700);
		theTextArea.setOnKeyTyped(e -> {
			StatusBarUtils.setStatusIncludingAll(theStatusBar, theTextArea);
			// updating status bar takes too much time for large files
			if (!(theTextArea.getText().equals(thePreviousTextAreaString)) && !(thePreviousTextAreaString.equals("")))
				isFileSaved = false;
			theSpellChecker.checkSpelling();
			thePreviousTextAreaString = theTextArea.getText();
		});
		theMarkovLabel.setPrefWidth(739);
		theMarkovLabel.setWrapText(true);
		theBorderPane.setTop(MenuBarPane.buildMenuBar(primaryStage));
		theBorderPane.setBottom(theStatusBar);
		BorderPane.setAlignment(theStatusBar, Pos.BOTTOM_CENTER);
	}

	public static BorderPane getTheBorderPane() {
		return theBorderPane;
	}

	public Scene getTheScene() {
		return theScene;
	}

	public static Alert getTheAlert() {
		return theAlert;
	}

	public static TextInputDialog getTheTextInputDialog() {
		return theTextInputDialog;
	}

	public static Label getTheStatusBar() {
		return theStatusBar;
	}

	public static boolean isFileLoaded() {
		return isFileLoaded;
	}

	public static void setFileLoaded(boolean isFileLoaded) {
		SceneBuilder.isFileLoaded = isFileLoaded;
	}

	public static boolean isFileSaved() {
		return isFileSaved;
	}

	public static void setFileSaved(boolean isFileSaved) {
		SceneBuilder.isFileSaved = isFileSaved;
	}

	public static File getTheTextFile() {
		return theTextFile;
	}

	public static void setTheTextFile(File theTextFile) {
		SceneBuilder.theTextFile = theTextFile;
	}

	public static TextArea getTheTextArea() {
		return theTextArea;
	}

	public static Label getTheMarkovLabel() {
		return theMarkovLabel;
	}

}
