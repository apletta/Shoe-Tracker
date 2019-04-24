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

import application.TopArea.*;

class HomeScreen {
	
	protected static BorderPane homeScreen() {
      
      Button addProduct = new Button("Add Product");
      addProduct.setPrefSize(300, 100);
      
      Button lookupProduct = new Button("Lookup Product");
      lookupProduct.setPrefSize(300, 100);
      
      HBox box = new HBox();
      box.getChildren().addAll(lookupProduct,addProduct);
      box.setSpacing(200);
      box.setAlignment(Pos.CENTER);
      
      BorderPane root = new BorderPane();
      root.setCenter(box);
      root.setTop(TopArea.topArea("Home"));
      return root;
	}
	


}