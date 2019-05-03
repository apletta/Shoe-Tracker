package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

class HomeScreen {

  protected static BorderPane screen() {

    Button addProduct = new Button("Add Product");
    addProduct.setPrefSize(300, 100);

    Button lookupProduct = new Button("Lookup Product");
    lookupProduct.setPrefSize(300, 100);

    // lookupProduct actions
    lookupProduct.setOnAction(new EventHandler<ActionEvent>() {

      public void handle(ActionEvent event) {

        // Load lookupProduct window
        Stage stage = (Stage) lookupProduct.getScene().getWindow();
        Scene scene = new Scene(LookupScreen.screen(), Main.SCREEN_LENGTH, Main.SCREEN_WIDTH);
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
        Scene scene = new Scene(AddProductScreen.screen(), Main.SCREEN_LENGTH, Main.SCREEN_WIDTH);
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
    root.setPadding(new Insets(Main.SCREEN_PADDING,Main.SCREEN_PADDING,Main.SCREEN_PADDING,Main.SCREEN_PADDING));
    root.setTop(TopArea.topArea("Home"));
    return root;
  }



}
