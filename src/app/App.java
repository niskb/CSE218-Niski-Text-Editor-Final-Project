package app;

import javafx.application.Application;
import javafx.stage.Stage;
//import test.LoopTimer;
import view.SceneBuilder;

public class App extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("CSE218 Niski Text Editor Final Project");
		primaryStage.setScene(new SceneBuilder(primaryStage).getTheScene());
		primaryStage.show();
	}

}
