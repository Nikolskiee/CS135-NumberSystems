
package application;
	
import application.controller.AppController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * 
 * This class contains the main method.
 * It will serve as the starting point of the application.
 * @author Nichol John F. Famadico
 */
public class Main extends Application {
	/**
	 * An object that will store the instance of the application's GUI.
	 */
	Stage primaryStage;
	
	/**
	 * This method starts the graphical interface of application by bringing an initial stage forward in a window.
	 * It sets the local stage object to the one instantiated by JavaFX package.
	 * @param pimaryStage The Active Stage
	 */
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		startCalculator();
	}
	
	/**
	 * This method loads the contents into the primary stage.
	 * It looks for an FXML file and its respective controller then bring them up into the window.
	 * This method initializes the FXML file and the controller so that GUI contents and algorithms work accordingly.
	 */
	public void startCalculator() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/AppView.fxml"));
			AnchorPane application = (AnchorPane) loader.load();
			
			Scene scene = new Scene(application);
			scene.getStylesheets().add("application/application.css");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			AppController controller = loader.getController();
			controller.setMain(this);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The public, static, and void main method required for Java Applications to run.
	 * It calls the launch method of the JavaFX to launch the application.
	 * @param args It receives the argument passed by JavaFX for the initialization of the app.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
