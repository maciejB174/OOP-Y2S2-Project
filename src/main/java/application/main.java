package application;

import javafx.application.Application;


import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

/**
 * The main class of the application
 * @author Maciej Becmer
 *
 */
public class main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("League Manager application");		
			Controller controller = new Controller();
			TabPane tabPane = controller.returnTabPane();
			Scene scene = new Scene(tabPane, 630, 310);
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
