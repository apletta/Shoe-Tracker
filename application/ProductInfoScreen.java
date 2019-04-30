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
import java.util.List;
import application.TopArea.*;

class ProductInfoScreen {

  protected static BorderPane screen() {
    GridPane pane = new GridPane();
    pane.setHgap(10);
    pane.setVgap(10);
    pane.setPadding(new Insets(10, 10, 10, 10));

    // Need to add lookup functions to find actual object info
    ImageView image = new ImageView(new Image("aj1.png"));
    image.setFitWidth(200);
    image.setFitHeight(150);

    Text prodNum = new Text("Product #:");
    GridPane.setConstraints(prodNum, 0, 0);
    prodNum.setId("text");

    Text displayNum = new Text(CurrentShoeInfo.currentShoe.productNumber + "");
    displayNum.setId("text2");
    GridPane.setConstraints(displayNum, 1, 0);

    Text name = new Text("Name:");
    GridPane.setConstraints(name, 0, 1);
    name.setId("text");
    Text displayName = new Text(CurrentShoeInfo.currentShoe.name);
    displayName.setId("text2");
    GridPane.setConstraints(displayName, 1, 1);

    Text amountInStock = new Text("Amount in Stock:");
    GridPane.setConstraints(amountInStock, 0, 2);
    amountInStock.setId("text");
    Text displayStock = new Text(CurrentShoeInfo.currentShoe.totalQuantity + "");
    displayStock.setId("text2");
    GridPane.setConstraints(displayStock, 1, 2);

    pane.setAlignment(Pos.CENTER);
    pane.getChildren().addAll(prodNum, name, amountInStock, displayNum, displayName, displayStock);

    HBox hbox = new HBox(30);
    hbox.setAlignment(Pos.CENTER);
    hbox.getChildren().addAll(image, pane);

    Button checkSize = new Button("Check Sizes");
    checkSize.setAlignment(Pos.CENTER);
    Button delete = new Button("Delete Product(s)");
    delete.setAlignment(Pos.CENTER);

    // ADD SHADOWS
    DropShadow shadow = new DropShadow();
    // add shadow
    checkSize.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        checkSize.setEffect(shadow);
      }
    });
    // remove shadow
    checkSize.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        checkSize.setEffect(null);
      }
    });

    delete.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        delete.setEffect(shadow);
      }
    });
    // remove shadow
    delete.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        delete.setEffect(null);
      }
    });


    // delete actions
    delete.setOnAction(new EventHandler<ActionEvent>() {

      public void handle(ActionEvent event) {
        // Load ProductInfoScreen
        Stage stage = (Stage) delete.getScene().getWindow();
        Scene scene = new Scene(DeleteProductScreen.screen(), 1600, 900);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setTitle("Sole Table");
        stage.setScene(scene);
        // stage.hide();
        stage.show();
      }
    });


    VBox vbox = new VBox(15);
    vbox.setAlignment(Pos.CENTER);
    vbox.getChildren().addAll(delete, checkSize);

    checkSize.setOnAction(new EventHandler<ActionEvent>() {

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
        // Clears the box so that the list of available sizes are
        // Just shown
        vbox.getChildren().clear();
        // Adds the label and displaySizes to scene
        vbox.getChildren().addAll(sizeLabel, displaySizes);
      }
    });

    BorderPane.setAlignment(delete, Pos.BOTTOM_LEFT);
    delete.setAlignment(Pos.CENTER);

    VBox box = new VBox(80);
    box.setAlignment(Pos.CENTER);
    box.getChildren().addAll(hbox, vbox);



    BorderPane root = new BorderPane();
    root.setCenter(box);
    root.setTop(TopArea.topArea("Product Info"));


    return root;
  }

}
