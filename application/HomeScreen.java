//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: HomeScreen
// Files: HomeScreen.java
// Course: CS400, Spring 2019 (LEC 001: Deb Deppeler)
// Due: 5/3/2019
//
// Authors: Alex Pletta, Joziah Mays, Grace Joyce, Lu Duan, Liang Shang
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

/**
 * Sets up the primary home screen for the Shoe-Tracker application.
 *
 */
class HomeScreen {

  /**
   * Creates home screen display.
   * 
   * @return root
   */
  protected static BorderPane screen() {
    // creates addProduct button and sets to preferred size
    Button addProduct = new Button("Add Product");
    addProduct.setPrefSize(300, 100);
    // creates LookupProduct button and sets to preferred size
    Button lookupProduct = new Button("Lookup Product");
    lookupProduct.setPrefSize(300, 100);

    /**
     * LookupProduct button action.
     */
    lookupProduct.setOnAction(new EventHandler<ActionEvent>() {
      /**
       * When button clicked, navigates to the lookup product screen.
       */
      public void handle(ActionEvent event) {
        // Load lookupProduct window
        Stage stage = (Stage) lookupProduct.getScene().getWindow();
        Scene scene = new Scene(LookupScreen.screen(), Main.SCREEN_LENGTH, Main.SCREEN_WIDTH);
        // sets to specific style
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setTitle("Sole Table");
        stage.setScene(scene);
        stage.show();
      }

    });

    /**
     * AddProduct button action.
     */
    addProduct.setOnAction(new EventHandler<ActionEvent>() {
      /**
       * When button clicked, navigates to the add product screen.
       */
      public void handle(ActionEvent event) {
        // Load AddProductScreen window
        Stage stage = (Stage) addProduct.getScene().getWindow();
        Scene scene = new Scene(AddProductScreen.screen(), Main.SCREEN_LENGTH, Main.SCREEN_WIDTH);
        // sets to specific style
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setTitle("Sole Table");
        stage.setScene(scene);
        stage.show();
      }
    });
    // configures display layout
    HBox box = new HBox();
    box.getChildren().addAll(lookupProduct, addProduct);
    box.setSpacing(200);
    box.setAlignment(Pos.CENTER);

    BorderPane root = new BorderPane();
    root.setCenter(box); // sets Hbox to the center of the field
    // pads outside of window
    root.setPadding(new Insets(Main.SCREEN_PADDING, Main.SCREEN_PADDING, Main.SCREEN_PADDING,
        Main.SCREEN_PADDING));
    root.setTop(TopArea.topArea("Home"));
    return root;
  }
}
