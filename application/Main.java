package application;
	
import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;

public class Main extends Application {
  
	@Override
	public void start(Stage primaryStage) {
		try {
	        Scene scene = new Scene(HomeScreen.screen(),1600,900);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Sole Table");
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
