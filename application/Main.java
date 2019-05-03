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
import application.LookupScreen.*;
import application.ProductInfoScreen.*;
import application.AddProductScreen.*;

import application.Stock;
import shoetable.ShoeInfo;

public class Main extends Application {
  
  protected static final int SCREEN_LENGTH = 1200;
  protected static final int SCREEN_WIDTH = 900;
  
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
