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

class ProductInfoScreen {
	
      protected static BorderPane screen() {
	  GridPane pane = new GridPane();
	  pane.setHgap(10);
	  pane.setVgap(10);
	  pane.setPadding(new Insets(10,10,10,10));
	  
	  ImageView image = new ImageView(new Image("aj1.png"));
	  image.setFitWidth(200);
	  image.setFitHeight(150);
	  
	  Text prodNum = new Text("Product #:");
	  GridPane.setConstraints(prodNum, 0, 0);
	  prodNum.setId("text");
	  
	  Text displayNum = new Text("This is the product num");
	  displayNum.setId("text2");
	  GridPane.setConstraints(displayNum, 1, 0);
	  
	  Text name = new Text("Name:");
	  GridPane.setConstraints(name, 0, 1);
	  name.setId("text");
	  Text displayName = new Text("This is the product name");
	  displayName.setId("text2");
	  GridPane.setConstraints(displayName, 1, 1);
	  
	  Text amountInStock = new Text("Amount in Stock:");
	  GridPane.setConstraints(amountInStock, 0, 2);
	  amountInStock.setId("text");
	  Text displayStock = new Text("This is the amount in stock");
	  displayStock.setId("text2");
	  GridPane.setConstraints(displayStock, 1, 2);
	  
	  pane.setAlignment(Pos.CENTER);
	  pane.getChildren().addAll(prodNum,name,amountInStock,displayNum,
	      displayName,displayStock);
	  
	  HBox hbox = new HBox(30);
	  hbox.setAlignment(Pos.CENTER);
	  hbox.getChildren().addAll(image,pane);
	  
	  Button checkSize = new Button("Check Sizes");
	  checkSize.setAlignment(Pos.CENTER);
	  Button delete = new Button("Delete Product(s)");
	  delete.setAlignment(Pos.CENTER);
	  
	  VBox vbox = new VBox(15);
	  vbox.setAlignment(Pos.CENTER);
	  vbox.getChildren().addAll(delete, checkSize);
	  
	  BorderPane.setAlignment(delete, Pos.BOTTOM_LEFT);
	  delete.setAlignment(Pos.CENTER);
	  
	  VBox box = new VBox(80);
	  box.setAlignment(Pos.CENTER);
	  box.getChildren().addAll(hbox,vbox);
	  
	  
	  
	  BorderPane root = new BorderPane();
	  root.setCenter(box);
	  root.setTop(TopArea.topArea("Product Info"));
	  
	  
	  return root;
	}

}