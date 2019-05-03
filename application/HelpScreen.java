//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: HelpScreen
// Files: HelpScreen.java
// Course: CS400, Spring 2019 (LEC 001: Deb Deppeler)
// Due: 5/3/2019
//
// Authors: Alex Pletta, Joziah Mays, Grace Joyce, Lu Duan, Liang Shang
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Creates and sets up help screen for the Shoe-Tracker application.
 *
 */
public class HelpScreen {
  /**
   * Sets up help screen.
   * 
   * @return BorderPane
   */
  protected static BorderPane screen() {
    GridPane pane = new GridPane(); // creates a new GridPane with specified preferences
    pane.setHgap(10);
    pane.setVgap(10);
    // adds padding to window
    pane.setPadding(new Insets(Main.SCREEN_PADDING, Main.SCREEN_PADDING, Main.SCREEN_PADDING,
        Main.SCREEN_PADDING));
    pane.setAlignment(Pos.CENTER); // sets pane to center

    // tells user looking for help what to do
    final String words = "Hello! Thank you for using Sole Table! \n" + "\n"
        + "On the home screen, you can either add a new product or look up a product already in the table. \n"
        + "If you are unable to look up a product, it was most likely not added. If you are looking to delete a product, you must go to the product page. \n"
        + "\n" + "If you have any questions or concerns feel free to email CS400 Group 24: \n"
        + "Contact Info: Grace Joyce \n" + "Email: gjoyce@wisc.edu \n" + "\n"
        + "Feel free to send us feedback on how to improve this application. \n\nThanks again! \nCheers!\n";

    Label label = new Label(words); // sets as label
    label.setWrapText(true);
    // configures set up and padding of the help screen
    BorderPane borderPane = new BorderPane();
    borderPane.setPadding(new Insets(Main.SCREEN_PADDING, Main.SCREEN_PADDING, Main.SCREEN_PADDING,
        Main.SCREEN_PADDING));
    borderPane.setCenter(label);
    borderPane.setTop(TopArea.topArea("Help")); // for help button

    return borderPane;
  }
}
