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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import javafx.scene.text.*;
import java.util.Optional;
import application.TopArea.*;

class AddProductScreen {
	
      protected static BorderPane screen() {
        
//      int userProdNum;
//      String userProdName;
//      Double userProdSize;
//      int userQuantity;
        
      GridPane gridPane = new GridPane();
      gridPane.setHgap(10);
      gridPane.setVgap(10);
      gridPane.setPadding(new Insets(10,10,10,10));
      
      Text enterProdNum = new Text("Enter Product Number:");
      enterProdNum.setId("text");
      GridPane.setConstraints(enterProdNum, 0, 0);
      TextField prodNum = new TextField();
      GridPane.setConstraints(prodNum, 1, 0);
      
      
      Text enterProdName = new Text("Enter Product Name:");
      enterProdName.setId("text");
      GridPane.setConstraints(enterProdName, 0, 1);
      TextField prodName = new TextField();
      GridPane.setConstraints(prodName, 1, 1);
      

      
      Text enterSize = new Text("Enter Size:");
      enterSize.setId("text");
      GridPane.setConstraints(enterSize, 0, 2);
      ComboBox<Double> size = new ComboBox<>();
         for(double i = 5; i < 13; i = i+0.5) {
            size.getItems().add(i);
          }
      GridPane.setConstraints(size, 1, 2);
      
      
      
      Text quantity = new Text("Quantity:");
      quantity.setId("text");
      GridPane.setConstraints(quantity, 0, 3);
      ComboBox<Integer> prodQuan = new ComboBox<>();
        for(int i = 1; i < 11; i++) {
          prodQuan.getItems().add(i);
        }
      GridPane.setConstraints(prodQuan, 1, 3);

      
      Button addButton = new Button("Add");
      addButton.setPrefSize(100, 5);
      GridPane.setConstraints(addButton, 1, 9);
      GridPane.setHalignment(addButton, HPos.RIGHT);
      
      Alert errorAlert = new Alert(AlertType.ERROR);
      errorAlert.setContentText("1. Fill out ALL Fields\n"
          + "2. Please enter positive integers only, max length\n "
          + "   10 digits");
      errorAlert.setHeaderText("Error!");
      
      ButtonType goHome = new ButtonType("Return Home",ButtonBar.ButtonData.OK_DONE);
      ButtonType addMore = new ButtonType("Add Another",ButtonBar.ButtonData.OTHER);
      
      Alert addedAlert = new Alert(AlertType.CONFIRMATION);
      addedAlert.setContentText("Producted Sucessfully Added!");
      addedAlert.setHeaderText("");
//      addedAlert.getButtonTypes().clear();
//      addedAlert.getButtonTypes().addAll(goHome,addMore);
      
      
      addButton.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {
          try {
            int userProdNum = Integer.parseInt(prodNum.getText()); //GRABS INPUT FROM USER
            String userProdName = prodName.getText().trim(); //GRABS INPUT FROM USER
            double userProdSize = size.getValue(); //GRABS INPUT FROM USER
            int userQuantity = prodQuan.getValue(); //GRABS INPUT FROM USER
            Stock.shoeTable.addShoe(userProdNum, userProdName, userProdSize, userQuantity);
            addedAlert.show();
          }
          catch(Exception e) {
            errorAlert.show();
          }
        }
      });
      
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