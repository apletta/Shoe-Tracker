//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: TopArea
// Files: TopArea.java
// Course: CS400, Spring 2019 (LEC 001: Deb Deppeler)
// Due: 5/3/2019
//
// Authors: Alex Pletta, Joziah Mays, Grace Joyce, Lu Duan, Liang Shang
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;

/**
 * This class sets up the top area of the application.
 */
class TopArea {

  /**
   * Sets up the buttons on top bar of application.
   * 
   * @return Hbox box
   */
  protected static HBox topButtons() {
    ImageView img = new ImageView(new Image("home.png")); // loads image
    // set image preferences
    img.setFitWidth(35);
    img.setFitHeight(35);
    final Button homeButton = new Button(); // adds a home button
    homeButton.setGraphic(img);

    // homeButton actions
    homeButton.setOnAction(new EventHandler<ActionEvent>() {

      /**
       * Sets home button action to go to home screen.
       */
      public void handle(ActionEvent event) {
        Stage stage = (Stage) homeButton.getScene().getWindow(); // gets home button
        // sets up home screen
        Scene scene = new Scene(HomeScreen.screen(), Main.SCREEN_LENGTH, Main.SCREEN_WIDTH);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setTitle("Sole Table");
        stage.setScene(scene);
        stage.show();
      }
    });

    // loads image for help button
    ImageView helpImg = new ImageView(new Image("questionMark.png"));
    helpImg.setFitWidth(35); // sets image preferences
    helpImg.setFitHeight(35);
    final Button helpButton = new Button(); // creates a new button
    helpButton.setGraphic(helpImg);

    // helpButton actions
    helpButton.setOnAction(new EventHandler<ActionEvent>() {

      /**
       * Sets help button action to go to help screen.
       */
      public void handle(ActionEvent event) {
        Stage stage = (Stage) helpButton.getScene().getWindow(); // gets help button
        // sets to go to help screen when button pressed
        Scene scene = new Scene(HelpScreen.screen(), Main.SCREEN_LENGTH, Main.SCREEN_WIDTH);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setTitle("Sole Table");
        stage.setScene(scene);
        stage.show();
      }
    });


    // gets image for exit button
    ImageView exitImg = new ImageView(new Image("circleExit.png"));
    exitImg.setFitWidth(35);
    exitImg.setFitHeight(35);
    final Button exitButton = new Button(); // creates exit button
    exitButton.setGraphic(exitImg);

    // exitButton actions
    exitButton.setOnAction(new EventHandler<ActionEvent>() {

      /**
       * Exit button action to quit out of application.
       */
      public void handle(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();// closes application
      }
    });

    // creates the Hbox with preferred buttons
    HBox box = new HBox(0.5);
    box.getChildren().addAll(homeButton, helpButton, exitButton);

    return box;
  }

  protected static HBox topArea(String portion) {
    // sets top label
    Label topLabel = new Label("Store Inventory | " + portion);
    // positions top bar
    Region rg1 = new Region();
    HBox.setHgrow(rg1, Priority.ALWAYS);
    Region rg2 = new Region();
    HBox.setHgrow(rg2, Priority.ALWAYS);
    HBox top = new HBox(topLabel, rg1, rg2, topButtons());
    return top;

  }

}
