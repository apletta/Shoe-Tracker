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

public class HelpScreen {
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
    final String words = "Hello! Thank you for using Sole Table! \n" + "\n"
        + "On the home screen, you can either add a new product or look up a product already in the table. \n"
        + "If you are unable to look up a product, it was most likely not added. If you are looking to delete a product, you must go to the product page. \n"
        + "\n" + "If you have any questions or concerns feel free to email CS400 Group 24: \n"
        + "Contact Info: Grace Joyce \n" + "Email: gjoyce@wisc.edu \n" + "\n" + "Feel free to send us feedback on how to improve this application. \n\nThanks again! \nCheers!\n";
   
    Label label = new Label(words);
    label.setWrapText(true);


    VBox box = new VBox(50);
    box.setAlignment(Pos.CENTER);
    box.getChildren().addAll(pane, lookupProduct);
    

    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(label);
    borderPane.setTop(TopArea.topArea("Help"));

    return borderPane;
  }
}
