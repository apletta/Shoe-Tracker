package application;
import shoetable.ShoeInfo;
import shoetable.KeyNotFoundException;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import javafx.scene.text.*;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;

import java.io.IOException;

import application.TopArea.*;

class LookupScreen {

	protected static BorderPane screen() {

		GridPane pane = new GridPane();
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(10, 10, 10, 10));
		pane.setAlignment(Pos.CENTER);
		Text promptUser = new Text("Enter Product Number:");
		promptUser.setId("text");
		GridPane.setConstraints(promptUser, 0, 0);

		TextField typeBox = new TextField();
		GridPane.setConstraints(typeBox, 1, 0);
		
		Text errorText = new Text("Please enter positive integers only, max length 10 digits");
		errorText.setId("errorTextHidden");
		GridPane.setConstraints(errorText, 1, 1);

		pane.getChildren().addAll(promptUser, typeBox, errorText);

		Button lookupProduct = new Button("Lookup Product");
		lookupProduct.setPrefSize(300, 50);
		lookupProduct.setAlignment(Pos.CENTER);

		// lookupProduct actions
		lookupProduct.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {

				try {
					int productID = Integer.valueOf(typeBox.getText()); // user-entered productID

					if(productID <= 0 ) {
						throw new NumberFormatException();
					}
					 //Grabs the info from the current shoe but calling 
					 //shoeTable.lookupShoe from Stock.java in "application"
					 //Package
	                 ShoeInfo shoe = Stock.shoeTable.lookupShoe(productID);
	                 //Stores the current shoe's info in the currentShoe
	                 //variable held in CurrenShoeInfo.java
	                 CurrentShoeInfo.currentShoe = shoe;
					
					// find in hashtable
					// hashtable data to fields
					// display based on fields
					
					// Load ProductInfoScreen
					Stage stage = (Stage) lookupProduct.getScene().getWindow();
					Scene scene = new Scene(ProductInfoScreen.screen(), 1600, 900);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					stage.setTitle("Sole Table");
					stage.setScene(scene);
					//stage.hide();
					stage.show();
					
				} catch (NumberFormatException e) {
					errorText.setId("errorText");
					GridPane.setConstraints(errorText, 1, 1);
				}
				
				//Joziah Changes: Shows an error display when the 
				//shoe does not exist in table
				catch (KeyNotFoundException e) {
				  Alert warning = new Alert(AlertType.ERROR);
				  warning.setContentText("The product you wish to search for"
				      + " does not exist. Try again.");
				  warning.show();
				}

			}
		});

		VBox box = new VBox(50);
		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(pane, lookupProduct);

		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(box);
		borderPane.setTop(TopArea.topArea("Lookup Product"));

		return borderPane;
	}

}