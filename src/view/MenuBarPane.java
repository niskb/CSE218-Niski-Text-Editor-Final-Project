package view;

import java.util.Optional;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.MasterLinkList;
import utils.AlertMessages;
import utils.FileUtils;
import utils.MasterLinkListBuilder;
import utils.ParagraphGenerator;
import utils.StatusBarUtils;

public class MenuBarPane {

	private static MenuBar theMenuBar = new MenuBar();

	public static MenuBar buildMenuBar(Stage primaryStage) {
		Menu fileMenu = buildFileMenu(primaryStage);
		Menu viewMenu = buildViewMenu();
		Menu editMenu = buildEditMenu();
		Menu utilMenu = buildUtilMenu();
		theMenuBar.getMenus().addAll(fileMenu, viewMenu, editMenu, utilMenu);
		return theMenuBar;
	}

	private static Menu buildFileMenu(Stage primaryStage) {
		Menu fileMenu = new Menu("File");
		buildFileMenuItems(fileMenu, primaryStage);
		return fileMenu;
	}

	private static void buildFileMenuItems(Menu fileMenu, Stage primaryStage) {
		MenuItem newMenuItem = new MenuItem("New");
		newMenuItem.setOnAction(e -> {
			if (SceneBuilder.isFileSaved()) {
				SceneBuilder.getTheBorderPane().setLeft(null);
				SceneBuilder.getTheBorderPane().setRight(null);
				SceneBuilder.getTheTextArea().setText("");
				SceneBuilder.getTheBorderPane().setCenter(SceneBuilder.getTheTextArea());
				SceneBuilder.setFileLoaded(new Boolean(true));
			} else
				AlertMessages.fileNotSaved(SceneBuilder.getTheAlert());
		});
		MenuItem openMenuItem = new MenuItem("Open");
		openMenuItem.setOnAction(e -> {
			if (SceneBuilder.isFileSaved()) {
				SceneBuilder.setTheTextFile(FileUtils.loadTextFile(primaryStage));
				if (SceneBuilder.getTheTextFile() != null) {
					SceneBuilder.getTheBorderPane().setLeft(null);
					SceneBuilder.getTheBorderPane().setRight(null);
					SceneBuilder.getTheBorderPane().setCenter(SceneBuilder.getTheTextArea());
					SceneBuilder.setFileLoaded(new Boolean(true));
					SceneBuilder.getTheTextArea().setText(
							FileUtils.scanThroughTextFile(SceneBuilder.getTheTextFile(), SceneBuilder.getTheAlert()));
				} else
					AlertMessages.noFileLoaded(SceneBuilder.getTheAlert());
			} else
				AlertMessages.fileNotSaved(SceneBuilder.getTheAlert());
		});
		MenuItem closeMenuItem = new MenuItem("Close");
		closeMenuItem.setOnAction(e -> {
			if (SceneBuilder.isFileSaved()) {
				SceneBuilder.getTheBorderPane().setCenter(null);
				SceneBuilder.setFileLoaded(new Boolean(false));
			} else
				AlertMessages.fileNotSaved(SceneBuilder.getTheAlert());
		});
		MenuItem saveMenuItem = new MenuItem("Save");
		saveMenuItem.setOnAction(e -> {
			FileUtils.saveTextFile(primaryStage);
		});
		MenuItem exitMenuItem = new MenuItem("Exit");
		exitMenuItem.setOnAction(e -> {
			if (SceneBuilder.isFileLoaded()) {
				if (SceneBuilder.isFileSaved())
					Platform.exit();
				else
					AlertMessages.fileNotSaved(SceneBuilder.getTheAlert());
			} else if (SceneBuilder.isFileSaved())
				Platform.exit();
		});
		fileMenu.getItems().addAll(newMenuItem, openMenuItem, closeMenuItem, saveMenuItem, exitMenuItem);
	}

	private static Menu buildViewMenu() {
		Menu viewMenu = new Menu("View");
		buildViewMenuItems(viewMenu);
		return viewMenu;
	}

	private static void buildViewMenuItems(Menu viewMenu) {
		MenuItem wordCountMenuItem = new MenuItem("Word Count");
		wordCountMenuItem.setOnAction(e -> {
			if (SceneBuilder.isFileLoaded())
				StatusBarUtils.setStatusWordCount(SceneBuilder.getTheStatusBar(), SceneBuilder.getTheTextArea());
			else
				AlertMessages.noFileLoaded(SceneBuilder.getTheAlert());
		});
		MenuItem sentenceCountMenuItem = new MenuItem("Sentence Count");
		sentenceCountMenuItem.setOnAction(e -> {
			if (SceneBuilder.isFileLoaded())
				StatusBarUtils.setStatusSentenceCount(SceneBuilder.getTheStatusBar(), SceneBuilder.getTheTextArea());
			else
				AlertMessages.noFileLoaded(SceneBuilder.getTheAlert());
		});
		MenuItem fleschScoreMenuItem = new MenuItem("Flesch Score");
		fleschScoreMenuItem.setOnAction(e -> {
			if (SceneBuilder.isFileLoaded())
				StatusBarUtils.setStatusFleschScore(SceneBuilder.getTheStatusBar(), SceneBuilder.getTheTextArea());
			else
				AlertMessages.noFileLoaded(SceneBuilder.getTheAlert());
		});
		viewMenu.getItems().addAll(wordCountMenuItem, sentenceCountMenuItem, fleschScoreMenuItem);
	}

	private static Menu buildEditMenu() {
		Menu editMenu = new Menu("Edit");
		buildEditMenuItems(editMenu);
		return editMenu;
	}

	private static void buildEditMenuItems(Menu editMenu) {
		MenuItem copyMenuItem = new MenuItem("Copy");
		copyMenuItem.setOnAction(e -> {
			if (SceneBuilder.isFileLoaded())
				SceneBuilder.getTheTextArea().copy();
			else
				AlertMessages.noFileLoaded(SceneBuilder.getTheAlert());
		});
		MenuItem cutMenuItem = new MenuItem("Cut");
		cutMenuItem.setOnAction(e -> {
			if (SceneBuilder.isFileLoaded())
				SceneBuilder.getTheTextArea().cut();
			else
				AlertMessages.noFileLoaded(SceneBuilder.getTheAlert());
		});
		MenuItem deleteMenuItem = new MenuItem("Delete");
		deleteMenuItem.setOnAction(e -> {
			if (SceneBuilder.isFileLoaded())
				SceneBuilder.getTheTextArea().replaceSelection("");
			else
				AlertMessages.noFileLoaded(SceneBuilder.getTheAlert());
		});
		MenuItem pasteMenuItem = new MenuItem("Paste");
		pasteMenuItem.setOnAction(e -> {
			if (SceneBuilder.isFileLoaded())
				SceneBuilder.getTheTextArea().paste();
			else
				AlertMessages.noFileLoaded(SceneBuilder.getTheAlert());
		});
		MenuItem markovMenuItem = new MenuItem("Markov");
		markovMenuItem.setOnAction(e -> {
			if (SceneBuilder.isFileLoaded()) {
				SceneBuilder.getTheTextInputDialog().setHeaderText("Markov starting with what word?");
				SceneBuilder.getTheTextInputDialog().setContentText("Enter word:");
				String STARTING_WORD = "";
				Optional<String> result = SceneBuilder.getTheTextInputDialog().showAndWait();
				if (result.isPresent())
					STARTING_WORD = result.get();
				result = null;
				SceneBuilder.getTheTextInputDialog().setHeaderText("Markov for how many words?");
				SceneBuilder.getTheTextInputDialog().setContentText("Enter integer greater than 0:");
				String NUMBER_OF_WORDS = "";
				result = SceneBuilder.getTheTextInputDialog().showAndWait();
				if (result.isPresent())
					NUMBER_OF_WORDS = result.get();
				MasterLinkList THE_MASTER_LIST = MasterLinkListBuilder.buildMasterLinkList(
						ParagraphGenerator.wordsSeperator(SceneBuilder.getTheTextArea().getText()));
				buildMarkovContent(THE_MASTER_LIST, STARTING_WORD, NUMBER_OF_WORDS);
				BorderPane.setAlignment(SceneBuilder.getTheMarkovLabel(), Pos.CENTER_LEFT);
				SceneBuilder.getTheBorderPane().setCenter(null);
				SceneBuilder.getTheBorderPane().setLeft(SceneBuilder.getTheTextArea());
				SceneBuilder.getTheBorderPane().setRight(SceneBuilder.getTheMarkovLabel());
			} else
				AlertMessages.noFileLoaded(SceneBuilder.getTheAlert());
		});
		editMenu.getItems().addAll(copyMenuItem, cutMenuItem, deleteMenuItem, pasteMenuItem, markovMenuItem);
	}

	private static void buildMarkovContent(MasterLinkList THE_MASTER_LIST, String STARTING_WORD,
			String NUMBER_OF_WORDS) {
		try {
			if (!SceneBuilder.isFileLoaded()) {
				AlertMessages.noFileLoaded(SceneBuilder.getTheAlert());
			} else if (Integer.parseInt(NUMBER_OF_WORDS) >= 0 && SceneBuilder.isFileLoaded()) {
				long start = System.currentTimeMillis();
				SceneBuilder.getTheMarkovLabel().setText(ParagraphGenerator.generateParagraphFromStartingWord(
						THE_MASTER_LIST, STARTING_WORD, Integer.parseInt(NUMBER_OF_WORDS)));
				SceneBuilder.getTheMarkovLabel().setText(SceneBuilder.getTheMarkovLabel().getText()
						+ "\n\nSUCCESS: Generated paragraph has been saved to new paragraphOutput.txt file in outputData folder! Took "
						+ (System.currentTimeMillis() - start) + " milliseconds for actual generation");
			}
		} catch (NumberFormatException e1) {
			AlertMessages.positiveIntegerEnteredWrong(SceneBuilder.getTheAlert());
		}
	}

	private static Menu buildUtilMenu() {
		Menu utilMenu = new Menu("Utils");
		buildUtilMenuItems(utilMenu);
		return utilMenu;
	}

	private static void buildUtilMenuItems(Menu utilMenu) {
		MenuItem lineChartOneLoopMenuItem = new MenuItem("One Loop Test");
		lineChartOneLoopMenuItem.setOnAction(e -> {
			if (SceneBuilder.isFileLoaded()) {
				try {
					SceneBuilder.getTheBorderPane().setCenter(BigOLineChart.generateOneLoopChart());
					SceneBuilder.getTheBorderPane().setLeft(null);
					SceneBuilder.getTheBorderPane().setRight(null);
				} catch (Exception e1) {
					AlertMessages.noFileLoaded(SceneBuilder.getTheAlert());
				}
			} else
				AlertMessages.noFileLoaded(SceneBuilder.getTheAlert());
		});
		MenuItem lineChartThreeLoopsMenuItem = new MenuItem("Three Loop Test");
		lineChartThreeLoopsMenuItem.setOnAction(e -> {
			if (SceneBuilder.isFileLoaded()) {
				try {
					SceneBuilder.getTheBorderPane().setCenter(BigOLineChart.generateThreeLoopsChart());
					SceneBuilder.getTheBorderPane().setLeft(null);
					SceneBuilder.getTheBorderPane().setRight(null);
				} catch (Exception e1) {
					AlertMessages.noFileLoaded(SceneBuilder.getTheAlert());
				}
			} else
				AlertMessages.noFileLoaded(SceneBuilder.getTheAlert());
		});
		MenuItem chopTextFileItem = new MenuItem("Chop Into Seperate Files");
		chopTextFileItem.setOnAction(e -> {
			if (SceneBuilder.isFileLoaded())
				chopTextFileIntoParts();
			else
				AlertMessages.noFileLoaded(SceneBuilder.getTheAlert());
		});
		utilMenu.getItems().addAll(lineChartOneLoopMenuItem, lineChartThreeLoopsMenuItem, chopTextFileItem);
	}

	private static void chopTextFileIntoParts() {
		for (int i = 10; i < 100; i += 10) {
			FileUtils.percentageOfTextFile(i, SceneBuilder.getTheAlert());
		}
	}

}
