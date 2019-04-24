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

class LookupScreen {
	
      protected static BorderPane screen() {
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(10,10,10,10));
        pane.setAlignment(Pos.CENTER);
        Text promptUser = new Text("Enter Product Number:");
        promptUser.setId("text");
        GridPane.setConstraints(promptUser, 0, 0);
        
        TextField typeBox = new TextField();
        GridPane.setConstraints(typeBox, 1, 0);
        
        pane.getChildren().addAll(promptUser,typeBox);
        
      Button lookupProduct = new Button("Lookup Product");
      lookupProduct.setPrefSize(300, 50);
      lookupProduct.setAlignment(Pos.CENTER);
      
      VBox box = new VBox(50);
      box.setAlignment(Pos.CENTER);
      box.getChildren().addAll(pane,lookupProduct);
      
      BorderPane borderPane = new BorderPane();
      borderPane.setCenter(box);
      borderPane.setTop(TopArea.topArea("Lookup Product"));
        
        return borderPane;
      }
      

}