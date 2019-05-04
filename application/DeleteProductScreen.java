//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: DeleteProductScreen
// Files: DeleteProductScreen.java
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * DeleteProduct screen class for the Shoe-Tracker application.
 */
class DeleteProductScreen {

  /**
   * Sets up DeleteProductScreen
   * 
   * @return borderPane
   */
  protected static BorderPane screen() {
    GridPane pane = new GridPane(); // new GridPane with specified preferences
    pane.setHgap(5);
    pane.setVgap(5);
    // padding for window
    pane.setPadding(new Insets(Main.SCREEN_PADDING, Main.SCREEN_PADDING, Main.SCREEN_PADDING,
        Main.SCREEN_PADDING));

    Text txt = new Text("Available Sizes"); // Text for available sizes with preferences
    txt.setStyle("-fx-font-weight: bold");
    GridPane.setConstraints(txt, 0, 0);
    pane.setAlignment(Pos.CENTER); // center position

    HBox box = new HBox(9); // new Hbox
    box.setAlignment(Pos.CENTER); // in center
    GridPane.setConstraints(box, 0, 2);
    pane.getChildren().addAll(txt, box); // add the text to the Hbox

    HBox hbox = new HBox(1);
    ComboBox<Integer> combo = new ComboBox<>();

    // Made up array list to store the size that the user wants to delete since this information
    // cannot be accessed directly from the anonymous method
    ArrayList<Double> sizeLs = new ArrayList<>();

    // List of the available sizes in stock for particular shoe
    List<String> availableSizes = Stock.shoeTable.getSizeList();
    // String of the shoe's sizes and how much of each size exist
    String checkSizeList = Stock.shoeTable.checkSize();

    // Iterates and creates new buttons for each size that's available
    for (int i = 0; i < availableSizes.size(); i++) {
      // Creates new button
      Button newButt = new Button(availableSizes.get(i));
      // Positions the button to the center
      newButt.setAlignment(Pos.CENTER);
      // Adds new button to the hbox it belongs to
      box.getChildren().add(newButt);

      /**
       * Action for the available sizes buttons.
       */
      newButt.setOnAction(new EventHandler<ActionEvent>() {
        /**
         * Handles action when user presses button.
         */
        @Override
        public void handle(ActionEvent event) {
          // Splits the checkSizeList so that accessing the data will be easier
          String[] listSplit = checkSizeList.toString().split(" ");
          // For made up list mentioned earlier...clears the list so that every time
          // the user chooses a button, the text of the button will be stored at index
          // 0 in the made up list. That text will later be used to delete
          sizeLs.clear();
          // The text of the button is stored when this line executes
          sizeLs.add(Double.parseDouble(newButt.getText()));
          // This list is for east access to data
          List<String> list = new ArrayList<>();
          // The amount of the particular sizes for that shoe:
          // i.e. 6.5(8). The process below will get rid of "6.5", "("
          // and ")" so that "8" is the only thing left
          String quan = "";
          // Adds the elements in the split array to list
          for (int i = 0; i < listSplit.length; i++) {
            list.add(listSplit[i]);
          }
          // For-loop responsible for getting rid of extra data
          for (int j = 0; j < list.size(); j++) {
            // Checks if the element contains the size that the user wants
            // to delete from the stock
            if (list.get(j).contains(newButt.getText())) {
              // As explained about, this gets rid of "6.5"
              quan = list.get(j).replaceFirst(newButt.getText(), "");
              // Gets rid of "("
              quan = quan.replace("(", "");
              // Gets rid of ")" and only leaves the number
              quan = quan.replace(")", "");
            }
          }
          // Clears the combo box every time so that each size is show its
          // respect amount in stock
          combo.getItems().clear();

          // The number left from the process above
          int availableQuan = Integer.parseInt(quan);
          for (int k = 1; k <= availableQuan; k++) {
            // Combo box of the available amount user can
            // delete
            combo.getItems().add(k);
          }
        }
      });
    }

    Button button = new Button("DELETE"); // new delete button
    button.setAlignment(Pos.CENTER);

    Button backButton = new Button("Back"); // new back button
    backButton.setAlignment(Pos.CENTER);

    // back button in preferred location
    HBox hboxBack = new HBox(1);
    hboxBack.setAlignment(Pos.CENTER);
    hboxBack.getChildren().addAll(backButton);
    GridPane.setConstraints(hboxBack, 0, 28);

    hbox.setAlignment(Pos.CENTER); // sets to center
    hbox.getChildren().addAll(combo, button); // adds combo and button
    GridPane.setConstraints(hbox, 0, 6);

    pane.getChildren().addAll(hbox, hboxBack);

    /**
     * Action for the back button.
     */
    backButton.setOnAction(new EventHandler<ActionEvent>() {
      /**
       * When pressed takes user back to the previous screen.
       */
      @Override
      public void handle(ActionEvent event) {
        // Load previous screen
        Stage stage = (Stage) backButton.getScene().getWindow();
        Scene scene = new Scene(ProductInfoScreen.screen(), Main.SCREEN_LENGTH, Main.SCREEN_WIDTH);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setTitle("Sole Table");
        stage.setScene(scene);
        stage.show();
      }

    });

    /**
     * Action for the delete button.
     */
    button.setOnAction(new EventHandler<ActionEvent>() {
      /**
       * When pressed deletes specified shoe.
       */
      @Override
      public void handle(ActionEvent event) {
        // yes and no button when confirming deletion
        ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
        // alert to check if this is the user's wanted action
        Alert checkDelete = new Alert(AlertType.CONFIRMATION);
        checkDelete.setContentText("Are you sure you want to delete the " + "following:\n\n"
            + "Shoe: " + CurrentShoeInfo.currentShoe.name + "\n" + "Size: " + sizeLs.get(0) + "\n"
            + "Quantity: " + combo.getValue());
        checkDelete.setHeaderText(null);
        checkDelete.getButtonTypes().clear(); // clear buttons
        checkDelete.getButtonTypes().addAll(yesButton, noButton); // adds buttons to the alert

        Optional<ButtonType> result = checkDelete.showAndWait(); // waits for response
        if (result.get() == yesButton) {// if yes
          Stock.shoeTable.deleteShoe(sizeLs.get(0), combo.getValue()); // delete shoe from hash
                                                                       // table
          CurrentShoeInfo.currentShoe.totalQuantity =
              CurrentShoeInfo.currentShoe.totalQuantity - combo.getValue(); // delete shoe from
                                                                            // current shoe counter
          // creates new home button
          ButtonType goHome = new ButtonType("Return Home", ButtonBar.ButtonData.FINISH);
          // creates new delete button
          ButtonType deleteMore = new ButtonType("Delete Another ", ButtonBar.ButtonData.OTHER);
          Alert addedAlert = new Alert(AlertType.CONFIRMATION); // looks for confirmation
          addedAlert.setContentText("Product Sucessfully Deleted!");// text if deleted
          addedAlert.setHeaderText(null); // resets
          addedAlert.setTitle(null);
          addedAlert.getButtonTypes().clear();
          addedAlert.getButtonTypes().addAll(goHome, deleteMore);

          Optional<ButtonType> newResult = addedAlert.showAndWait(); // show alert and wait for
                                                                     // response
          if (newResult.get() == goHome) { // if user wants to go home
            Stage stage = new Stage();
            // load home screen
            Scene scene = new Scene(HomeScreen.screen(), Main.SCREEN_LENGTH, Main.SCREEN_WIDTH);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            stage.setTitle("Sole Table");
            stage.setScene(scene);
            stage.hide();
            stage.show();

            // Closes and hides current window
            // https://stackoverflow.com/questions/15041760/javafx-open-new-window
            ((Node) (event.getSource())).getScene().getWindow().hide();
          } else if (newResult.get() == deleteMore) { // if user wants to delete more
            Stage stage = new Stage();
            // load delete product screen
            Scene scene =
                new Scene(DeleteProductScreen.screen(), Main.SCREEN_LENGTH, Main.SCREEN_WIDTH);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            stage.setTitle("Sole Table");
            stage.setScene(scene);
            //stage.hide();
            stage.show();
            
            // Closes and hides current window
            // https://stackoverflow.com/questions/15041760/javafx-open-new-window
            ((Node) (event.getSource())).getScene().getWindow().hide();
            
            
          }
        }
      }
    });

    VBox vbox = new VBox(15); // new vertical box
    vbox.setAlignment(Pos.CENTER); // center position

    vbox.getChildren().addAll(pane); // adds all from pane

    BorderPane borderPane = new BorderPane();
    // padding for the DeleteProductScreen window
    borderPane.setPadding(new Insets(Main.SCREEN_PADDING, Main.SCREEN_PADDING, Main.SCREEN_PADDING,
        Main.SCREEN_PADDING));
    borderPane.setCenter(vbox);
    borderPane.setTop(TopArea.topArea("Delete Product")); // sets top area header
    return borderPane;
  }

}
