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


class DeleteProductScreen {

	protected static BorderPane screen() {
	  ///Everything put into VBox
	  //1. Need a GridPane with "size" text and 
	  //   size choices(use HBOX)
	  //2. Need HBox for buttons
	  GridPane pane = new GridPane();
	  pane.setHgap(5);
	  pane.setVgap(5);
	  pane.setPadding(new Insets(10,10,10,10));
	  
	  Text txt = new Text("Size");
	  txt.setStyle("-fx-font-weight: bold");
	  GridPane.setConstraints(txt, 0, 0);
	  pane.setAlignment(Pos.CENTER);
	  
	  HBox box = new HBox(9);
	  for(double i = 9; i < 13; i = i + 0.5) {
	    Button newButt = new Button(i+"");
	    newButt.setAlignment(Pos.CENTER);
	    newButt.setId("");
	    box.getChildren().add(newButt);
	  }
	  box.setAlignment(Pos.CENTER);
	  GridPane.setConstraints(box, 0, 1);
	  pane.getChildren().addAll(txt,box);
	  
	  HBox hbox = new HBox(1);
	  ComboBox<Integer> combo = new ComboBox<>();
	  combo.setId("Quan");
      for(int i = 1; i < 11; i++) {
        combo.getItems().add(i);
      }
      
      Button button = new Button("DELETE");
      button.setAlignment(Pos.CENTER);
      hbox.setAlignment(Pos.CENTER);
      hbox.getChildren().addAll(combo,button);
      
      VBox vbox = new VBox(15);
      vbox.setAlignment(Pos.CENTER);
      vbox.getChildren().addAll(pane,hbox);
      
	  
	  BorderPane borderPane = new BorderPane();
	  borderPane.setCenter(vbox);
	  borderPane.setTop(TopArea.topArea("Delete Product"));
	  return borderPane;
	}

}