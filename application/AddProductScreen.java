package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.stage.*;
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

class AddProductScreen {

  protected static BorderPane screen() {

    //      int userProdNum;
    //      String userProdName;
    //      Double userProdSize;
    //      int userQuantity;

    GridPane gridPane = new GridPane();
    gridPane.setHgap(10);
    gridPane.setVgap(10);
    gridPane.setPadding(new Insets(10,10,10,10));

    Text enterProdNum = new Text("Enter Product Number:");
    enterProdNum.setId("text");
    GridPane.setConstraints(enterProdNum, 0, 0);
    TextField prodNum = new TextField();
    GridPane.setConstraints(prodNum, 1, 0);


    Text enterProdName = new Text("Enter Product Name:");
    enterProdName.setId("text");
    GridPane.setConstraints(enterProdName, 0, 1);
    TextField prodName = new TextField();
    GridPane.setConstraints(prodName, 1, 1);


    final double defaultSize = 8.5;
    Text enterSize = new Text("Enter Size:");
    enterSize.setId("text");
    GridPane.setConstraints(enterSize, 0, 2);
    ComboBox<Double> size = new ComboBox<>();
    for(double i = 5; i < 13; i = i+0.5) {
      size.getItems().add(i);
    }
    size.setValue(defaultSize);
    GridPane.setConstraints(size, 1, 2);


    final int defaultQuan = 1;
    Text quantity = new Text("Quantity:");
    quantity.setId("text");
    GridPane.setConstraints(quantity, 0, 3);
    ComboBox<Integer> prodQuan = new ComboBox<>();
    for(int i = 1; i < 11; i++) {
      prodQuan.getItems().add(i);
    }
    prodQuan.setValue(defaultQuan);
    GridPane.setConstraints(prodQuan, 1, 3);


    Button addButton = new Button("Add");
    addButton.setPrefSize(100, 5);
    GridPane.setConstraints(addButton, 1, 9);
    GridPane.setHalignment(addButton, HPos.RIGHT);


    Button loadImageButton = new Button("Load Image...");
    loadImageButton.setPrefSize(200, 5);
    GridPane.setConstraints(loadImageButton, 0, 4);
    GridPane.setHalignment(loadImageButton, HPos.RIGHT);

    Alert errorAlert = new Alert(AlertType.ERROR);
    errorAlert.setContentText("1. Fill out ALL Fields\n"
        + "2. Please enter positive integers only, max length\n "
        + "   10 digits");
    errorAlert.setHeaderText(null);

    ButtonType goHome = new ButtonType("Return Home",ButtonBar.ButtonData.FINISH);
    ButtonType addMore = new ButtonType("Add Another",ButtonBar.ButtonData.BACK_PREVIOUS);

    Alert addedAlert = new Alert(AlertType.CONFIRMATION);
    addedAlert.setContentText("Product Sucessfully Added!");
    addedAlert.setHeaderText(null);
    addedAlert.getButtonTypes().clear();
    addedAlert.getButtonTypes().addAll(goHome,addMore);
    
    Alert addedFileAlert = new Alert(AlertType.CONFIRMATION);
    addedFileAlert.setContentText("File Sucessfully Added!");
    addedFileAlert.setHeaderText(null);
    addedFileAlert.getButtonTypes().clear();
    addedFileAlert.getButtonTypes().addAll(goHome,addMore);

    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open Resource File");   

    ArrayList<Image> imageArray = new ArrayList<Image>();
    loadImageButton.setOnAction(e -> {
      Stage stage = new Stage();
      File file = fileChooser.showOpenDialog(stage);
      if (file != null) {
        loadImageButton.setText("Image added");
        Image shoeImage = new Image(file.toURI().toString());
        imageArray.add(shoeImage);
      }
    });


    //Button allowing user to strictly upload .json files
    Button addFromFile = new Button("Add From File");
    addFromFile.setPrefSize(200, 5);
    GridPane.setConstraints(addFromFile, 0, 5);
    GridPane.setHalignment(addFromFile, HPos.RIGHT);
    ArrayList<String> fileList = new ArrayList<>();

    addFromFile.setOnAction(e -> {
      Stage stage = new Stage();
      File file = fileChooser.showOpenDialog(stage);
      if (file != null) {
        addFromFile.setText(file.getName());
        prodName.setDisable(true);
        prodNum.setDisable(true);
        size.setDisable(true);
        prodQuan.setDisable(true);
        loadImageButton.setDisable(true);
        fileList.add(file.getAbsolutePath());
      }
    });

    //Actions for addButton
    addButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {

        //Checks to make sure add from file was not chosen, if so, simple add
        //content in the file the user wishes to upload
        if(prodNum.isDisabled() && prodName.isDisabled() && size.isDisabled()
            && prodQuan.isDisabled() && loadImageButton.isDisabled()) {
            try {
              addFromFile(fileList.get(0));
              fileList.clear();
              //Code Reference:
              //https://www.programcreek.com/java-api-examples/?api=javafx.scene.control.ButtonType
              Optional<ButtonType> result = addedFileAlert.showAndWait();
              if(result.get() == goHome) {
                //System.out.println("Button Clicked");
                Stage stage = new Stage();
                Scene scene = new Scene(HomeScreen.screen(), Main.SCREEN_LENGTH, Main.SCREEN_WIDTH);
                scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                stage.setTitle("Sole Table");
                stage.setScene(scene);
                stage.show();

                //Closes and hides current window
                //https://stackoverflow.com/questions/15041760/javafx-open-new-window
                ((Node)(event.getSource())).getScene().getWindow().hide();
              }
              else if(result.get() == addMore) {
                Stage stage = (Stage) addButton.getScene().getWindow();
                Scene scene = new Scene(AddProductScreen.screen(), Main.SCREEN_LENGTH, Main.SCREEN_WIDTH);
                scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                stage.setTitle("Sole Table");
                stage.setScene(scene);
                stage.hide();
                stage.show();
              }
            }
            //catch(FileNotFoundException e) {}
            catch(IOException e) {
              //e.printStackTrace();
            }
            catch(ParseException e) {
              Alert a = new Alert(AlertType.ERROR);
              a.setHeaderText(null);
              a.setContentText("File could not be uploaded due to either of the following:\n\n"
                  + "1. The file uploaded was not a .json file\n"
                  + "2. The .json file is formatted incorrectly");
              a.show();
            }
        }
        
        else {
          try {
            int userProdNum = Integer.parseInt(prodNum.getText()); //GRABS INPUT FROM USER
            String userProdName = prodName.getText().trim(); //GRABS INPUT FROM USER
            double userProdSize = size.getValue(); //GRABS INPUT FROM USER
            int userQuantity = prodQuan.getValue(); //GRABS INPUT FROM USER
            Image userImage = imageArray.get(0);
            imageArray.clear();
            // Stock.shoeTable.addShoe(userProdNum, userProdName, userProdSize, userQuantity);
            Stock.shoeTable.addShoe(userProdNum, userProdName, userProdSize, userQuantity, userImage);

            //Code Reference:
            //https://www.programcreek.com/java-api-examples/?api=javafx.scene.control.ButtonType
            Optional<ButtonType> result = addedAlert.showAndWait();
            if(result.get() == goHome) {
              //System.out.println("Button Clicked");
              Stage stage = new Stage();
              Scene scene = new Scene(HomeScreen.screen(), Main.SCREEN_LENGTH, Main.SCREEN_WIDTH);
              scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
              stage.setTitle("Sole Table");
              stage.setScene(scene);
              stage.show();

              //Closes and hides current window
              //https://stackoverflow.com/questions/15041760/javafx-open-new-window
              ((Node)(event.getSource())).getScene().getWindow().hide();
            }
            else if(result.get() == addMore) {
              prodNum.clear();
              prodName.clear();
              size.getSelectionModel().clearSelection();
              prodQuan.getSelectionModel().clearSelection();
            }

          }
          catch(Exception e) {
            e.printStackTrace();
            errorAlert.show();
          }
        }
      }
    });

    gridPane.setAlignment(Pos.CENTER);
    gridPane.getChildren().addAll(enterProdName,enterProdNum, enterSize, quantity,
        prodNum,prodName,size,prodQuan, addButton, loadImageButton,addFromFile);      

    BorderPane.setAlignment(gridPane, Pos.CENTER);
    BorderPane screen = new BorderPane();
    screen.setCenter(gridPane);
    screen.setTop(TopArea.topArea("Add Product"));

    return screen;
  }


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
      int prodNum = Integer.parseInt((String)jsonShoes.get("productNum"));
      // The current shoe's product name
      String prodName = (String) jsonShoes.get("productName");
      // The current shoe's product size
      double size = Double.parseDouble((String)jsonShoes.get("size"));
      // The current shoe's quantity to add
      int quantity = Integer.parseInt((String)jsonShoes.get("quantity"));
      // The current shoe's image to add
      Image img = new Image((String) jsonShoes.get("image"));
      // Adds shipment to the stock
      Stock.shoeTable.addShoe(prodNum, prodName, size, quantity, img);
    }

  }

  private static void openFile(File file) {
    try {
      Desktop desktop = Desktop.getDesktop();
      desktop.open(file);
    } catch (IOException ex) {
      Logger.getLogger(
          FileChooser.class.getName()).log(
              Level.SEVERE, null, ex
              );
    }

  }

}