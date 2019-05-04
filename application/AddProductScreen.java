//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////

//
// Title: AddProductScreen
// Files: AddProductScreen.java
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
import application.KeyNotFoundException;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import javafx.scene.text.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.awt.Desktop;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * AddProduct screen class for the Shoe-Tracker application.
 *
 */
class AddProductScreen {

  /**
   * Sets up AddProductScreen
   * 
   * @return screen
   */
  protected static BorderPane screen() {
    GridPane gridPane = new GridPane();// new GridPane and preferences
    gridPane.setHgap(10);
    gridPane.setVgap(10);
    // padding for the addProduct window
    gridPane.setPadding(new Insets(Main.SCREEN_PADDING, Main.SCREEN_PADDING, Main.SCREEN_PADDING,
        Main.SCREEN_PADDING));

    Text enterProdNum = new Text("Enter Product Number:"); // prompts user to enter product #
    enterProdNum.setId("text");
    GridPane.setConstraints(enterProdNum, 0, 0); // sets constraints
    TextField prodNum = new TextField(); // text field for user answer
    GridPane.setConstraints(prodNum, 1, 0);

    Text enterProdName = new Text("Enter Product Name:"); // prompts user to enter product name
    enterProdName.setId("text");
    GridPane.setConstraints(enterProdName, 0, 1);
    TextField prodName = new TextField(); // text field for user answer
    GridPane.setConstraints(prodName, 1, 1);

    final double defaultSize = 8.5;
    Text enterSize = new Text("Enter Size:"); // prompts user to enter product size
    enterSize.setId("text");
    GridPane.setConstraints(enterSize, 0, 2); // set constraints
    ComboBox<Double> size = new ComboBox<>(); // new combo box for size
    for (double i = 5; i < 13; i = i + 0.5) { // adds selected to get items
      size.getItems().add(i);
    }
    size.setValue(defaultSize);
    GridPane.setConstraints(size, 1, 2);

    final int defaultQuan = 1; // initializes default quantity of shoes
    Text quantity = new Text("Quantity:"); // prompts user to enter how many
    quantity.setId("text");
    GridPane.setConstraints(quantity, 0, 3);
    ComboBox<Integer> prodQuan = new ComboBox<>(); // combo box with choice of how many
    for (int i = 1; i < 11; i++) {
      prodQuan.getItems().add(i); // adds selected to get items
    }
    prodQuan.setValue(defaultQuan); // sets constraints
    GridPane.setConstraints(prodQuan, 1, 3);

    Button addButton = new Button("Add"); // new button to add
    addButton.setPrefSize(100, 5);
    GridPane.setConstraints(addButton, 1, 9);
    GridPane.setHalignment(addButton, HPos.RIGHT);

    Button loadImageButton = new Button("Load Image..."); // new button to load image
    loadImageButton.setPrefSize(200, 5);
    GridPane.setConstraints(loadImageButton, 0, 4);
    GridPane.setHalignment(loadImageButton, HPos.RIGHT);

    // alert if input is not as expected
    Alert errorAlert = new Alert(AlertType.ERROR);
    errorAlert.setContentText("1. Fill out ALL Fields\n"
        + "2. Please enter positive integers only, max length\n " + "   10 digits");
    errorAlert.setHeaderText(null);

    // alert if no image detected
    Alert noImageAlert = new Alert(AlertType.WARNING);
    // asks user if want to load image
    noImageAlert.setContentText("No image detected. Do you want to load an image?");
    ButtonType loadImage = new ButtonType("Yes", ButtonBar.ButtonData.YES); // button confirming
    ButtonType noImage = new ButtonType("No", ButtonBar.ButtonData.NO); // button to say no
    noImageAlert.setHeaderText(null);
    noImageAlert.getButtonTypes().clear();
    noImageAlert.getButtonTypes().addAll(noImage, loadImage); // add both to alert screen

    // new buttons to go to the home screen or back to the add screen
    ButtonType goHome = new ButtonType("Return Home", ButtonBar.ButtonData.FINISH);
    ButtonType addMore = new ButtonType("Add Another", ButtonBar.ButtonData.BACK_PREVIOUS);

    Alert addedAlert = new Alert(AlertType.CONFIRMATION);// alert if successfully added
    addedAlert.setContentText("Product Sucessfully Added!");
    addedAlert.setHeaderText(null);
    addedAlert.getButtonTypes().clear();
    addedAlert.getButtonTypes().addAll(goHome, addMore); // add buttons to alert

    Alert addedFileAlert = new Alert(AlertType.CONFIRMATION);// alert if image successfully added
    addedFileAlert.setContentText("File Sucessfully Added!");
    addedFileAlert.setHeaderText(null);
    addedFileAlert.getButtonTypes().clear();
    addedFileAlert.getButtonTypes().addAll(goHome, addMore);// add buttons to alert

    FileChooser fileChooser = new FileChooser(); // choose file
    fileChooser.setTitle("Open Resource File");

    ArrayList<Image> imageArray = new ArrayList<Image>(); // array of images
    // gets image from fileChooser
    loadImageButton.setOnAction(e -> {
      Stage stage = new Stage();
      File file = fileChooser.showOpenDialog(stage);
      if (file != null) { // if there is a file
        loadImageButton.setText("Image added"); // indicate image added
        Image shoeImage = new Image(file.toURI().toString());
        imageArray.add(shoeImage); // adds image
      }
    });

    // Button allowing user to strictly upload .json files
    Button addFromFile = new Button("Add From File");
    addFromFile.setPrefSize(200, 5);
    GridPane.setConstraints(addFromFile, 0, 5);
    GridPane.setHalignment(addFromFile, HPos.RIGHT);
    ArrayList<String> fileList = new ArrayList<>(); // new array list for files

    // add from file
    addFromFile.setOnAction(e -> {
      Stage stage = new Stage();
      File file = fileChooser.showOpenDialog(stage); // choose file
      if (file != null) { // if there is a file
        addFromFile.setText(file.getName()); // set name
        prodName.setDisable(true);
        prodNum.setDisable(true);
        size.setDisable(true); // set sizes
        prodQuan.setDisable(true); // set quantity
        loadImageButton.setDisable(true); // load image
        fileList.add(file.getAbsolutePath()); // add the chosen file
        
      }
    });

    /**
     * Action for AddButton.
     */
    addButton.setOnAction(new EventHandler<ActionEvent>() {
      /**
       * When button pressed go to AddProduct screen
       */
      @Override
      public void handle(ActionEvent event) {
        // Checks to make sure add from file was not chosen, if so, simple add
        // content in the file the user wishes to upload
        if (prodNum.isDisabled() && prodName.isDisabled() && size.isDisabled()
            && prodQuan.isDisabled() && loadImageButton.isDisabled()) {
          try {
            addFromFile(fileList.get(0));
            fileList.clear();
            // Code Reference:
            // https://www.programcreek.com/java-api-examples/?api=javafx.scene.control.ButtonType
            Optional<ButtonType> result = addedFileAlert.showAndWait();
            if (result.get() == goHome) {
              // System.out.println("Button Clicked");
              Stage stage = (Stage) (((Node) (event.getSource())).getScene().getWindow());
              Scene scene = new Scene(HomeScreen.screen(), Main.SCREEN_LENGTH, Main.SCREEN_WIDTH);
              scene.getStylesheets()
                  .add(getClass().getResource("application.css").toExternalForm());
              stage.setTitle("Sole Table");
              stage.setScene(scene);
              stage.show();

            } else if (result.get() == addMore) { // if the user wants to add more
              // load AddProductScreen
              Stage stage = (Stage) addButton.getScene().getWindow();
              Scene scene =
                  new Scene(AddProductScreen.screen(), Main.SCREEN_LENGTH, Main.SCREEN_WIDTH);
              scene.getStylesheets()
                  .add(getClass().getResource("application.css").toExternalForm());
              stage.setTitle("Sole Table");
              stage.setScene(scene);
              stage.show();
            }
          } catch (IOException e) { // catch IOException
          } catch (ParseException e) { // catch ParseException
            Alert a = new Alert(AlertType.ERROR);
            a.setHeaderText(null);
            // print error message
            a.setContentText("File could not be uploaded due to either of the following:\n\n"
                + "1. The file uploaded was not a .json file\n"
                + "2. The .json file is formatted incorrectly");
            a.show(); // display error message
          }
        }

        else {
          try {
            int userProdNum = Integer.parseInt(prodNum.getText()); // Gets product number
            String userProdName = prodName.getText().trim(); // Gets product name
            double userProdSize = size.getValue(); // Gets product size
            int userQuantity = prodQuan.getValue(); // Gets product quantity
            Image userImage = new Image("no-pic.png"); // default pic is that image not available

            if (userProdName.length() == 0) { // if no product name
              throw new Exception();
            }

            try {
              // if existing name doesn't match user name with same product number, say it's a
              // problem
              String existingName = Stock.shoeTable.lookupShoe(userProdNum).name;
              if (!existingName.equals(userProdName)) {
                // show error message
                Alert existingNameAlert = new Alert(AlertType.ERROR);
                existingNameAlert.setContentText(
                    "Product exists with same product number and different name. Please adjust name to add to product."
                        + "\n\nExisting number: " + userProdNum + "\nExisting name: "
                        + existingName);
                existingNameAlert.setHeaderText(null);
                Optional<ButtonType> result = existingNameAlert.showAndWait(); // wait for user

                throw new ExistingNameException(); // throw exception to break setOnAction block
              }
            } catch (KeyNotFoundException e) { // catch exception if key not found
            }


            if (imageArray.size() == 0) { // enter if user has not selected an image
              Optional<ButtonType> addImage = noImageAlert.showAndWait(); // if no image
              if (addImage.get() == loadImage) {
                // User wants to add image so help them go back
                throw new ExistingNameException();
              }

            } else { // else imageArray has an image in it, so load a Shoe with that image
              userImage = imageArray.get(0);
              imageArray.clear(); // clear array
            }

            // add to stock with given information
            Stock.shoeTable.addShoe(userProdNum, userProdName, userProdSize, userQuantity,
                userImage);

            // Code Reference:
            // https://www.programcreek.com/java-api-examples/?api=javafx.scene.control.ButtonType
            Optional<ButtonType> result = addedAlert.showAndWait();
            if (result.get() == goHome) { // if user wants to go home
              Stage stage = new Stage();
              // load home screen
              Scene scene = new Scene(HomeScreen.screen(), Main.SCREEN_LENGTH, Main.SCREEN_WIDTH);
              scene.getStylesheets()
                  .add(getClass().getResource("application.css").toExternalForm());
              stage.setTitle("Sole Table");
              stage.setScene(scene);
              stage.show();

              // Closes and hides current window
              // https://stackoverflow.com/questions/15041760/javafx-open-new-window
              ((Node) (event.getSource())).getScene().getWindow().hide();
            } else if (result.get() == addMore) { // if user wants to add more
              prodNum.clear(); // clear product info
              prodName.clear();
              size.getSelectionModel().clearSelection();
              prodQuan.getSelectionModel().clearSelection();
            }

          } catch (ExistingNameException e) { // catch if existing name

          } catch (Exception e) { // catch other exceptions
            e.printStackTrace();
            errorAlert.show(); // show alert
          }
        }
        
      }
    });

    gridPane.setAlignment(Pos.CENTER); // set to center
    // add all information
    gridPane.getChildren().addAll(enterProdName, enterProdNum, enterSize, quantity, prodNum,
        prodName, size, prodQuan, addButton, loadImageButton, addFromFile);

    BorderPane.setAlignment(gridPane, Pos.CENTER);
    BorderPane screen = new BorderPane();
    // padding for AddProoductWindow
    screen.setPadding(new Insets(Main.SCREEN_PADDING, Main.SCREEN_PADDING, Main.SCREEN_PADDING,
        Main.SCREEN_PADDING));
    screen.setCenter(gridPane);
    screen.setTop(TopArea.topArea("Add Product"));

    return screen;
  }

  /**
   * Adds from a jsonFilepath.
   * 
   * @param jsonFilepath
   * @throws FileNotFoundException
   * @throws IOException
   * @throws ParseException
   */
  public static void addFromFile(String jsonFilepath)
      throws FileNotFoundException, IOException, ParseException {
    // Creates and instantiates a new JSONParser
    Object obj = new JSONParser().parse(new FileReader(jsonFilepath));
    // Creates a new object from parsing
    JSONObject parseMe = (JSONObject) obj;
    
    // Creates a new array from the "shoes" field of the json file
    JSONArray shoes = (JSONArray) parseMe.get("shoes");
    // For-loop that iterates through the shoes array
    for (int i = 0; i < shoes.size(); ++i) {
      // Gets the current shoes from json array
      JSONObject jsonShoes = (JSONObject) shoes.get(i);
      // The current shoe's product number
      int prodNum = Integer.parseInt((String) jsonShoes.get("productNum"));
      // The current shoe's product name
      String prodName = (String) jsonShoes.get("productName");
      // The current shoe's product size
      double size = Double.parseDouble((String) jsonShoes.get("size"));
      // The current shoe's quantity to add
      int quantity = Integer.parseInt((String) jsonShoes.get("quantity"));
      // The current shoe's image to add
      try {
      Image img = new Image((String) jsonShoes.get("image"));
      
      // Adds shipment to the stock
      Stock.shoeTable.addShoe(prodNum, prodName, size, quantity, img);
      
      } catch (IllegalArgumentException e) {
    	  Image img = (Image) jsonShoes.get("image");
    	  
    	  
    	     // Adds shipment to the stock
          Stock.shoeTable.addShoe(prodNum, prodName, size, quantity, img);
      }
    }
  }
}
