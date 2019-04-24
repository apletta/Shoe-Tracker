package application;
	
import javafx.application.Application;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.text.*;

import application.HomeScreen.*;
import application.AddProductScreen;


public class Main extends Application {
  
	@Override
	public void start(Stage primaryStage) {
		try {
	        Scene scene = new Scene(HomeScreen.homeScreen(),1600,900);
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
