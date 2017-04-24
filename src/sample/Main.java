package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public void start(Stage primaryStage) throws Exception {

		login log = new login();

		try {
			log.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		launch(args);

		if (Controller.name != "") {
			mainGUI mGUI = new mainGUI();

			mGUI.run();
		}
	}
}
