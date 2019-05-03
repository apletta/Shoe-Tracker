package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.stage.*;

import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;


class TopArea {

  protected static HBox topButtons() {
    ImageView img = new ImageView(new Image("home.png"));
    img.setFitWidth(35);
    img.setFitHeight(35);
    final Button homeButton = new Button();
    homeButton.setGraphic(img);

    // homeButton actions
    homeButton.setOnAction(new EventHandler<ActionEvent>() {

      public void handle(ActionEvent event) {
        // Load ProductInfoScreen
        Stage stage = (Stage) homeButton.getScene().getWindow();
        Scene scene = new Scene(HomeScreen.screen(), 1600, 900);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setTitle("Sole Table");
        stage.setScene(scene);
        stage.hide();
        stage.show();
      }
    });

    ImageView helpImg = new ImageView(new Image("questionMark.png"));
    helpImg.setFitWidth(35);
    helpImg.setFitHeight(35);
    final Button helpButton = new Button();
    helpButton.setGraphic(helpImg);

    // helpButton actions
    helpButton.setOnAction(new EventHandler<ActionEvent>() {

      public void handle(ActionEvent event) {
        Stage stage = (Stage) helpButton.getScene().getWindow();
        Scene scene = new Scene(HelpScreen.screen(), 1600, 900);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setTitle("Sole Table");
        stage.setScene(scene);
        stage.hide();
        stage.show();
      }
    });



    ImageView exitImg = new ImageView(new Image("circleExit.png"));
    exitImg.setFitWidth(35);
    exitImg.setFitHeight(35);
    final Button exitButton = new Button();
    exitButton.setGraphic(exitImg);

    // exitButton actions
    exitButton.setOnAction(new EventHandler<ActionEvent>() {

      public void handle(ActionEvent event) {
        // Load ProductInfoScreen
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
      }
    });


    HBox box = new HBox(0.5);
    box.getChildren().addAll(homeButton, helpButton, exitButton);

    return box;
  }

  protected static HBox topArea(String portion) {
    Label topLabel = new Label("Store Inventory | " + portion);

    Region rg1 = new Region();
    HBox.setHgrow(rg1, Priority.ALWAYS);
    Region rg2 = new Region();
    HBox.setHgrow(rg2, Priority.ALWAYS);
    HBox top = new HBox(topLabel, rg1, rg2, topButtons());
    return top;

  }

}
