package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

class HelpScreen {
  protected static BorderPane screen() {
    GridPane pane = new GridPane();
    pane.setHgap(10);
    pane.setVgap(10);
    pane.setPadding(new Insets(10, 10, 10, 10));
    pane.setAlignment(Pos.CENTER);
    Text promptUser = new Text("Ask a Question:");
    promptUser.setId("text");
    GridPane.setConstraints(promptUser, 0, 0);

    TextField typeBox = new TextField();
    GridPane.setConstraints(typeBox, 1, 0);

    pane.getChildren().addAll(promptUser, typeBox);

    Button lookupProduct = new Button("Submit");
    lookupProduct.setPrefSize(300, 50);
    lookupProduct.setAlignment(Pos.CENTER);
    
    //Button shadow
    DropShadow shadow = new DropShadow();
    // add button
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

    // submit button action
    lookupProduct.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        // TODO
      }
    });


    VBox box = new VBox(50);
    box.setAlignment(Pos.CENTER);
    box.getChildren().addAll(pane, lookupProduct);

    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(box);
    borderPane.setTop(TopArea.topArea("Help"));

    return borderPane;
  }


}
