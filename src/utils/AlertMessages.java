package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import view.SceneBuilder;

public class AlertMessages {

	public static void noFileLoaded(Alert theAlert) {
		theAlert.setAlertType(AlertType.ERROR);
		theAlert.setContentText("No text file has been loaded!");
		theAlert.showAndWait();
	}

	public static void cantSaveFile(Alert theAlert) {
		theAlert.setAlertType(AlertType.ERROR);
		theAlert.setContentText("The text file could not be saved!");
		theAlert.showAndWait();
	}

	public static void cantReadFile(Alert theAlert) {
		theAlert.setAlertType(AlertType.ERROR);
		theAlert.setContentText("The text file could not be loaded!");
		theAlert.showAndWait();
	}

	public static void percentageIsEnteredWrong(Alert theAlert) {
		theAlert.setAlertType(AlertType.ERROR);
		theAlert.setContentText("The percentage must be entered not as a word and must be between 0 to 100 inclusive!");
		theAlert.showAndWait();
	}

	public static void fileNotSaved(Alert theAlert) {
		theAlert.setAlertType(AlertType.WARNING);
		theAlert.setContentText("The text was modified and is not saved!");
		theAlert.showAndWait();
	}

	public static void positiveIntegerEnteredWrong(Alert theAlert) {
		theAlert.setAlertType(AlertType.WARNING);
		theAlert.setContentText("The integer must be entered not as a word and not negative!");
		theAlert.showAndWait();
	}

	public static void startingWordForMarkovDoesNotExist(Alert theAlert) {
		theAlert.setAlertType(AlertType.ERROR);
		theAlert.setContentText("The starting word that was specified is not in the list of used words!");
		theAlert.showAndWait();
	}

	public static void spelledWordWrong(String word) {
		if (!(word.isEmpty()))
			SceneBuilder.getTheStatusBar()
					.setText(SceneBuilder.getTheStatusBar().getText() + " ... " + word + " is spelled wrong!");
	}

}
