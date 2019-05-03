//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: ProductInfoScreen
// Files: ProductInfoScreen.java
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
import javafx.scene.image.*;
import javafx.scene.text.*;

/**
 * Sets up the product information screen for the shoe-tracker application.
 */
class ProductInfoScreen {

  /**
   * Implements buttons and other aspects product information screen.
   * 
   * @return root
   */
  protected static BorderPane screen() {
    GridPane pane = new GridPane(); // creates a new GridPane
    pane.setHgap(10); // sets preferences
    pane.setVgap(10);
    // creates a blue padding around the application
    pane.setPadding(new Insets(Main.SCREEN_PADDING, Main.SCREEN_PADDING, Main.SCREEN_PADDING,
        Main.SCREEN_PADDING));

    // loads the shoe image
    ImageView image = new ImageView(CurrentShoeInfo.currentShoe.image);
    image.setFitWidth(200); // sets preferences
    image.setFitHeight(150);

    Text prodNum = new Text("Product #:"); // creates new text field to enter prod #
    GridPane.setConstraints(prodNum, 0, 0);
    prodNum.setId("text");
    // number displayed
    Text displayNum = new Text(CurrentShoeInfo.currentShoe.productNumber + "");
    displayNum.setId("text2");
    GridPane.setConstraints(displayNum, 1, 0);

    Text name = new Text("Name:"); // takes in name of the shoe
    GridPane.setConstraints(name, 0, 1);
    name.setId("text");
    Text displayName = new Text(CurrentShoeInfo.currentShoe.name);// displays name
    displayName.setId("text2");
    GridPane.setConstraints(displayName, 1, 1);

    Text amountInStock = new Text("Amount in Stock:"); // shows how many in stock
    GridPane.setConstraints(amountInStock, 0, 2);
    amountInStock.setId("text");
    Text displayStock = new Text(CurrentShoeInfo.currentShoe.totalQuantity + "");
    displayStock.setId("text2");
    GridPane.setConstraints(displayStock, 1, 2);

    pane.setAlignment(Pos.CENTER); // sets preferred alignment
    pane.getChildren().addAll(prodNum, name, amountInStock, displayNum, displayName, displayStock);

    // creates the Hbox and sets to center of field
    HBox hbox = new HBox(30);
    hbox.setAlignment(Pos.CENTER);
    hbox.getChildren().addAll(image, pane);
    // creates the needed buttons
    Button checkSize = new Button("Check Sizes");
    checkSize.setAlignment(Pos.CENTER);
    Button delete = new Button("Delete Product(s)");
    delete.setAlignment(Pos.CENTER);
    Button backButton = new Button("Back");
    backButton.setAlignment(Pos.CENTER);


    /**
     * Delete button actions.
     */
    delete.setOnAction(new EventHandler<ActionEvent>() {
      /**
       * Handles the event.
       */
      public void handle(ActionEvent event) {
        Stage stage = (Stage) delete.getScene().getWindow();// get button
        Scene scene = new Scene(DeleteProductScreen.screen(), 1200, 900);// load delete screen
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setTitle("Sole Table");
        stage.setScene(scene);
        stage.show();
      }
    });

    // new Vbox to position in the center at specified location
    VBox vbox = new VBox(15);
    vbox.setAlignment(Pos.CENTER);
    vbox.getChildren().addAll(delete, checkSize);

    /**
     * Check size button action when clicked.
     */
    checkSize.setOnAction(new EventHandler<ActionEvent>() {
      /**
       * Handles the event.
       */
      @Override
      public void handle(ActionEvent event) {
        // List of sizes and availability
        String sizes = Stock.shoeTable.checkSize();
        checkSize.setVisible(false);
        delete.setVisible(false);
        Label sizeLabel = new Label("Sizes In Stock");
        sizeLabel.setUnderline(true);
        Text displaySizes = new Text(sizes);
        displaySizes.setId("text");
        // Clears the box so that the list of available sizes are just shown
        vbox.getChildren().clear();
        // Adds the label and displaySizes to scene
        vbox.getChildren().addAll(sizeLabel, displaySizes, backButton);
      }
    });

    /**
     * Actions for the back button.
     */
    backButton.setOnAction(new EventHandler<ActionEvent>() {
      /**
       * Handles the event.
       */
      @Override
      public void handle(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow(); // gets the button
        // goes back to the previous screen
        Scene scene = new Scene(ProductInfoScreen.screen(), Main.SCREEN_LENGTH, Main.SCREEN_WIDTH);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setTitle("Sole Table");
        stage.setScene(scene);
        stage.show();
      }
    });
    // sets position of delete
    BorderPane.setAlignment(delete, Pos.BOTTOM_LEFT);
    delete.setAlignment(Pos.CENTER);

    // creates the Vbox in the center position
    VBox box = new VBox(80);
    box.setAlignment(Pos.CENTER);
    box.getChildren().addAll(hbox, vbox);


    // sets the pre-setup BorderPane to the root
    BorderPane root = new BorderPane();
    // adds padding around screen
    root.setPadding(new Insets(Main.SCREEN_PADDING, Main.SCREEN_PADDING, Main.SCREEN_PADDING,
        Main.SCREEN_PADDING));
    root.setCenter(box);
    root.setTop(TopArea.topArea("Product Info"));


    return root;
  }

}
