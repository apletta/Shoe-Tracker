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
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import application.TopArea.*;


class DeleteProductScreen {

  protected static BorderPane screen() {
    /// Everything put into VBox
    // 1. Need a GridPane with "size" text and
    // size choices(use HBOX)
    // 2. Need HBox for buttons
    GridPane pane = new GridPane();
    pane.setHgap(5);
    pane.setVgap(5);
    pane.setPadding(new Insets(10, 10, 10, 10));

    Text txt = new Text("Available Sizes");
    txt.setStyle("-fx-font-weight: bold");
    GridPane.setConstraints(txt, 0, 0);
    pane.setAlignment(Pos.CENTER);

    HBox box = new HBox(9);


    box.setAlignment(Pos.CENTER);
    GridPane.setConstraints(box, 0, 1);
    pane.getChildren().addAll(txt, box);

    // BUTTON SHADOW
    DropShadow shadow = new DropShadow();
    // add shadow
    box.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        box.setEffect(shadow);
      }
    });
    // remove shadow
    box.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        box.setEffect(null);
      }
    });

    HBox hbox = new HBox(1);
    ComboBox<Integer> combo = new ComboBox<>();

    // Made up array list to store the size that the user wants
    // to delete since this information cannot be accessed directly
    // from the anonymous method
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
      // Actions if user clicks on button
      newButt.setOnAction(new EventHandler<ActionEvent>() {

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

    Button button = new Button("DELETE");
    button.setAlignment(Pos.CENTER);
    hbox.setAlignment(Pos.CENTER);
    hbox.getChildren().addAll(combo, button);

    // add shadow
    button.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        button.setEffect(shadow);
      }
    });
    // remove shadow
    button.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        button.setEffect(null);
      }
    });

    button.setOnAction(new EventHandler<ActionEvent>() {



      @Override
      public void handle(ActionEvent event) {
        ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
        Alert checkDelete = new Alert(AlertType.CONFIRMATION);
        checkDelete.setContentText("Are you sure you want to delete the " + "following:\n\n"
            + "Shoe: " + CurrentShoeInfo.currentShoe.name + "\n" + "Size: " + sizeLs.get(0) + "\n"
            + "Quantity: " + combo.getValue());
        checkDelete.setHeaderText(null);
        checkDelete.getButtonTypes().clear();
        checkDelete.getButtonTypes().addAll(yesButton, noButton);

        Optional<ButtonType> result = checkDelete.showAndWait();
        if (result.get() == yesButton) {
          Stock.shoeTable.deleteShoe(sizeLs.get(0), combo.getValue());

          ButtonType goHome = new ButtonType("Return Home", ButtonBar.ButtonData.FINISH);
          Alert addedAlert = new Alert(AlertType.CONFIRMATION);
          addedAlert.setContentText("Product Sucessfully Deleted!");
          addedAlert.setHeaderText(null);
          addedAlert.setTitle(null);
          addedAlert.getButtonTypes().clear();
          addedAlert.getButtonTypes().addAll(goHome);

          Optional<ButtonType> newResult = addedAlert.showAndWait();
          if (newResult.get() == goHome) {
            // System.out.println("Button Clicked");
            Stage stage = new Stage();
            Scene scene = new Scene(HomeScreen.screen(), 1600, 900);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            stage.setTitle("Sole Table");
            stage.setScene(scene);
            stage.hide();
            stage.show();

            // Closes and hides current window
            // https://stackoverflow.com/questions/15041760/javafx-open-new-window
            ((Node) (event.getSource())).getScene().getWindow().hide();
          }
        }
      }
    });


    VBox vbox = new VBox(15);
    vbox.setAlignment(Pos.CENTER);
    vbox.getChildren().addAll(pane, hbox);


    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(vbox);
    borderPane.setTop(TopArea.topArea("Delete Product"));
    return borderPane;
  }

}
