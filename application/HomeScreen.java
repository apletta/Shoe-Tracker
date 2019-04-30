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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.*;
import application.TopArea.*;

class HomeScreen {

  protected static BorderPane screen() {

    Button addProduct = new Button("Add Product");
    addProduct.setPrefSize(300, 100);

    Button lookupProduct = new Button("Lookup Product");
    lookupProduct.setPrefSize(300, 100);

    // ADD BUTTON SHADOW
    DropShadow shadow = new DropShadow();
    // add shadow
    lookupProduct.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        lookupProduct.setEffect(shadow);
      }
    });
    // remove shadow
    lookupProduct.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        lookupProduct.setEffect(null);
      }
    });
    addProduct.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        addProduct.setEffect(shadow);
      }
    });
    // remove shadow
    addProduct.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        addProduct.setEffect(null);
      }
    });

    // lookupProduct actions
    lookupProduct.setOnAction(new EventHandler<ActionEvent>() {

      public void handle(ActionEvent event) {

        // Load lookupProduct window
        Stage stage = (Stage) lookupProduct.getScene().getWindow();
        Scene scene = new Scene(LookupScreen.screen(), 1600, 900);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setTitle("Sole Table");
        stage.setScene(scene);
        stage.hide();
        stage.show();
      }

    });

    // addProduct actions
    addProduct.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        // Load ProductInfoScreen
        Stage stage = (Stage) addProduct.getScene().getWindow();
        Scene scene = new Scene(AddProductScreen.screen(), 1600, 900);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setTitle("Sole Table");
        stage.setScene(scene);
        stage.hide();
        stage.show();
      }
    });

    HBox box = new HBox();
    box.getChildren().addAll(lookupProduct, addProduct);
    box.setSpacing(200);
    box.setAlignment(Pos.CENTER);

    BorderPane root = new BorderPane();
    root.setCenter(box);
    root.setTop(TopArea.topArea("Home"));
    return root;
  }



}
