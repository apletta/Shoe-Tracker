package application;
	
import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;

public class Main extends Application {
  
  protected static final int SCREEN_LENGTH = 1200;
  protected static final int SCREEN_WIDTH = 900;
  protected static final int SCREEN_PADDING = 10;
  
	@Override
	public void start(Stage primaryStage) {
		try {
	        Scene scene = new Scene(HomeScreen.screen(),SCREEN_LENGTH,SCREEN_WIDTH);
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
