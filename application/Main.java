//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Main
// Files: Main.java
// Course: CS400, Spring 2019 (LEC 001: Deb Deppeler)
// Due: 5/3/2019
//
// Authors: Alex Pletta, Joziah Mays, Grace Joyce, Lu Duan, Liang Shang
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
package application;

import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;

/**
 * Shoe-Tracker Main: Runs the application.
 *
 */
public class Main extends Application {

  // declares screen length, width and padding around application.
  protected static final int SCREEN_LENGTH = 1200;
  protected static final int SCREEN_WIDTH = 900;
  protected static final int SCREEN_PADDING = 10;

  /**
   * Sets up the primary stage.
   */
  @Override
  public void start(Stage primaryStage) {
    try {
      // sets in initial scene to specified parameters
      Scene scene = new Scene(HomeScreen.screen(), SCREEN_LENGTH, SCREEN_WIDTH);
      // sets style to the application.css
      scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      primaryStage.setTitle("Sole Table");
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception e) { // catches exceptions thrown
      e.printStackTrace();
    }
  }

  /**
   * Runs the Shoe-Tracker application.
   * 
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }
}
