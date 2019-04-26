package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.text.*;

import application.TopArea.*;

class AddProductScreen {
	
      protected static BorderPane screen() {
        
      int userProdNum;
      String userProdName;
      Double userProdSize;
      int userQuantity;
        
      GridPane gridPane = new GridPane();
      gridPane.setHgap(10);
      gridPane.setVgap(10);
      gridPane.setPadding(new Insets(10,10,10,10));
      
      Text enterProdNum = new Text("Enter Product Number:");
      enterProdNum.setId("text");
      GridPane.setConstraints(enterProdNum, 0, 0);
      TextField prodNum = new TextField();
      GridPane.setConstraints(prodNum, 1, 0);
      
      //userProdNum = Integer.parseInt(prodNum.getText()); //GRABS INPUT FROM USER
      
      
      Text enterProdName = new Text("Enter Product Name:");
      enterProdName.setId("text");
      GridPane.setConstraints(enterProdName, 0, 1);
      TextField prodName = new TextField();
      GridPane.setConstraints(prodName, 1, 1);
      
      //userProdName = prodName.getText(); //GRABS INPUT FROM USER
      
      Text enterSize = new Text("Enter Size:");
      enterSize.setId("text");
      GridPane.setConstraints(enterSize, 0, 2);
      ComboBox<Double> size = new ComboBox<>();
         for(double i = 5; i < 13; i = i+0.5) {
            size.getItems().add(i);
          }
      GridPane.setConstraints(size, 1, 2);
      
      //userProdSize = size.getValue(); //GRABS INPUT FROM USER
      
      
      Text quantity = new Text("Quantity:");
      quantity.setId("text");
      GridPane.setConstraints(quantity, 0, 3);
      ComboBox<Integer> prodQuan = new ComboBox<>();
        for(int i = 1; i < 11; i++) {
          prodQuan.getItems().add(i);
        }
      GridPane.setConstraints(prodQuan, 1, 3);
      
      //userQuantity = prodQuan.getValue(); //GRABS INPUT FROM USER
      
      Button addButton = new Button("Add");
      addButton.setPrefSize(100, 5);
      GridPane.setConstraints(addButton, 1, 9);
      GridPane.setHalignment(addButton, HPos.RIGHT);
      
      gridPane.setAlignment(Pos.CENTER);
      gridPane.getChildren().addAll(enterProdName,enterProdNum, enterSize, quantity,
          prodNum,prodName,size,prodQuan, addButton);      

      BorderPane.setAlignment(gridPane, Pos.CENTER);
      BorderPane screen = new BorderPane();
      screen.setCenter(gridPane);
      screen.setTop(TopArea.topArea("Add Product"));
      
      return screen;
      }

}