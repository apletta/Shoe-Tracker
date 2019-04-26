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
      
      Stock.shoeTable.addShoe(123456789, "Nike", 8.5, 5);
      Stock.shoeTable.addShoe(123456789, "Nike", 5.5, 3);
      Stock.shoeTable.addShoe(123456789, "Nike", 10.5, 6);
      Stock.shoeTable.deleteShoe(123456789, 8.5, 4);
      
      System.out.println(Stock.shoeTable.checkSize(123456789));
		launch(args);
	}
}
