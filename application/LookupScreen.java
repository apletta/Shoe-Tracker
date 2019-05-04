//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: LookupScreen
// Files: LookupScreen.java
// Course: CS400, Spring 2019 (LEC 001: Deb Deppeler)
// Due: 5/3/2019
//
// Authors: Alex Pletta, Joziah Mays, Grace Joyce, Lu Duan, Liang Shang
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

package application;

import shoetable.ShoeInfo;
import shoetable.KeyNotFoundException;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.*;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

/**
 * Product lookup screen class for the Shoe-Tracker application.
 *
 */
class LookupScreen {

  /**
   * Sets up the Lookup Screen.
   * 
   * @return borderPane
   */
  protected static BorderPane screen() {

    GridPane pane = new GridPane(); // new gridPane with preferences
    pane.setHgap(10);
    pane.setVgap(10);
    // padding for lookUp window
    pane.setPadding(new Insets(Main.SCREEN_PADDING, Main.SCREEN_PADDING, Main.SCREEN_PADDING,
        Main.SCREEN_PADDING));
    pane.setAlignment(Pos.CENTER); // sets to center
    Text promptUser = new Text("Enter Product Number:"); // prompts user to enter prod #
    promptUser.setId("text");
    GridPane.setConstraints(promptUser, 0, 0);

    TextField typeBox = new TextField();// creates a box for user to enter answer
    GridPane.setConstraints(typeBox, 1, 0);

    // if not in constraints, error text is displayed
    Text errorText = new Text("Please enter positive integers only, max length 10 digits");
    errorText.setId("errorTextHidden");
    GridPane.setConstraints(errorText, 1, 1);

    pane.getChildren().addAll(promptUser, typeBox, errorText); // adds to pane

    Button lookupProduct = new Button("Lookup Product"); // new button to lookup the product
    lookupProduct.setPrefSize(300, 50);
    lookupProduct.setAlignment(Pos.CENTER);  

    /**
     * LookupProduct button actions.
     */
    lookupProduct.setOnAction(new EventHandler<ActionEvent>() {
    	
    	
      /**
       * Handles event when button clicked.
       */
      public void handle(ActionEvent event) {
        try {
          int productID = Integer.valueOf(typeBox.getText()); // user-entered productID

          if (productID <= 0) { // when ID is less than or equal to zero
            throw new NumberFormatException(); // exception
          }
          // Gets info from the current shoe but calling shoeTable.lookupShoe from Stock.java in
          // "application" Package
          ShoeInfo shoe = Stock.shoeTable.lookupShoe(productID);
          // Stores the current shoe's info in the currentShoe variable held in CurrenShoeInfo.java
          CurrentShoeInfo.currentShoe = shoe;
          // Load LookupProductScreen
          Stage stage = (Stage) lookupProduct.getScene().getWindow();
          Scene scene =
              new Scene(ProductInfoScreen.screen(), Main.SCREEN_LENGTH, Main.SCREEN_WIDTH);
          scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
          stage.setTitle("Sole Table");
          stage.setScene(scene);
          stage.show();

        } catch (NumberFormatException e) { // catches any exceptions
          errorText.setId("errorText"); // displays error message
          GridPane.setConstraints(errorText, 1, 1);
        }

        // Shows an error display when the shoe does not exist in table
        catch (KeyNotFoundException e) {
          Alert warning = new Alert(AlertType.ERROR);
          warning.setHeaderText(null);
          warning
              .setContentText("The product you wish to search for" + " does not exist. Try again.");
          warning.show();
        }
      }
    });

    VBox box = new VBox(50); // new vertical box to display initial set up
    box.setAlignment(Pos.CENTER); // positioned in the center
    box.getChildren().addAll(pane, lookupProduct);
    BorderPane borderPane = new BorderPane();
    // sets padding around lookup screen window
    borderPane.setPadding(new Insets(Main.SCREEN_PADDING, Main.SCREEN_PADDING, Main.SCREEN_PADDING,
        Main.SCREEN_PADDING));
    borderPane.setCenter(box);
    borderPane.setTop(TopArea.topArea("Lookup Product"));

    return borderPane;
  }

}
