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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
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
      
      //BUTTON SHADOW
      DropShadow shadow = new DropShadow();
      // add shadow
      addButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
          addButton.setEffect(shadow);
        }
      });
      // remove shadow
      addButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
          addButton.setEffect(null);
        }
      });
      
      Alert errorAlert = new Alert(AlertType.ERROR);
      errorAlert.setContentText("1. Fill out ALL Fields\n"
          + "2. Please enter positive integers only, max length\n "
          + "   10 digits");
      errorAlert.setHeaderText(null);
      
      ButtonType goHome = new ButtonType("Return Home",ButtonBar.ButtonData.FINISH);
      ButtonType addMore = new ButtonType("Add Another",ButtonBar.ButtonData.BACK_PREVIOUS);
      
      Alert addedAlert = new Alert(AlertType.CONFIRMATION);
      addedAlert.setContentText("Product Sucessfully Added!");
      addedAlert.setHeaderText(null);
      addedAlert.getButtonTypes().clear();
      addedAlert.getButtonTypes().addAll(goHome,addMore);
      
      addButton.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {
          try {
            int userProdNum = Integer.parseInt(prodNum.getText()); //GRABS INPUT FROM USER
            String userProdName = prodName.getText().trim(); //GRABS INPUT FROM USER
            double userProdSize = size.getValue(); //GRABS INPUT FROM USER
            int userQuantity = prodQuan.getValue(); //GRABS INPUT FROM USER
            Stock.shoeTable.addShoe(userProdNum, userProdName, userProdSize, userQuantity);

            //Code Reference:
            //https://www.programcreek.com/java-api-examples/?api=javafx.scene.control.ButtonType
            Optional<ButtonType> result = addedAlert.showAndWait();
            if(result.get() == goHome) {
              //System.out.println("Button Clicked");
              Stage stage = new Stage();
              Scene scene = new Scene(HomeScreen.screen(), 1600, 900);
              scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
              stage.setTitle("Sole Table");
              stage.setScene(scene);
              stage.show();
              
              //Closes and hides current window
              //https://stackoverflow.com/questions/15041760/javafx-open-new-window
              ((Node)(event.getSource())).getScene().getWindow().hide();
            }
            else if(result.get() == addMore) {
              prodNum.clear();
              prodName.clear();
              size.getSelectionModel().clearSelection();
              prodQuan.getSelectionModel().clearSelection();
            }
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