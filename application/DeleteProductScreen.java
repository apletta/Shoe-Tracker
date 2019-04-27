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
import java.util.List;

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
	  
	  Text txt = new Text("Available Sizes");
	  txt.setStyle("-fx-font-weight: bold");
	  GridPane.setConstraints(txt, 0, 0);
	  pane.setAlignment(Pos.CENTER);
	  
	  HBox box = new HBox(9);
	  List<String> availableSizes = Stock.shoeTable.getSizeList();
	  double userSize = 0.0;
	  for(int i = 0; i < availableSizes.size(); i++) {
	       Button newButt = new Button(availableSizes.get(i));
	       newButt.setAlignment(Pos.CENTER);
	       box.getChildren().add(newButt);
	       newButt.setOnAction(new EventHandler<ActionEvent>() {
            double userSize =0;
            @Override
            public void handle(ActionEvent event) {
              //userSize = Double.parseDouble(newButt.getText().toString());
            }
          });
	  }
	  
	  box.setAlignment(Pos.CENTER);
	  GridPane.setConstraints(box, 0, 1);
	  pane.getChildren().addAll(txt,box);
	  
	  HBox hbox = new HBox(1);
	  ComboBox<Integer> combo = new ComboBox<>();
//      for(availableSizes) {
//        combo.getItems().add(i);
//      }
      
      Button button = new Button("DELETE");
      button.setAlignment(Pos.CENTER);
      hbox.setAlignment(Pos.CENTER);
      hbox.getChildren().addAll(combo,button);
      
      button.setOnAction(new EventHandler<ActionEvent>() {
        
        @Override
        public void handle(ActionEvent e) {
          //Node userSize = box.getChildren();
          //int userQuantity = combo.getValue();
          //Stock.shoeTable.deleteShoe(shoeSize, userQuantity);
          
        }
      });
      
      VBox vbox = new VBox(15);
      vbox.setAlignment(Pos.CENTER);
      vbox.getChildren().addAll(pane,hbox);
      
	  
	  BorderPane borderPane = new BorderPane();
	  borderPane.setCenter(vbox);
	  borderPane.setTop(TopArea.topArea("Delete Product"));
	  return borderPane;
	}

}