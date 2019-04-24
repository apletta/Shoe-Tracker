package application;
	
import javafx.application.Application;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.text.*;


public class Main extends Application {
  
	@Override
	public void start(Stage primaryStage) {
		try {
	        Scene scene = new Scene(productInfoScreen(),500,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Sole Table");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private BorderPane homeScreen() {
      
      Button addProduct = new Button("Add Product");
      addProduct.setPrefSize(300, 100);
      
      Button lookupProduct = new Button("Lookup Product");
      lookupProduct.setPrefSize(300, 100);
      
      HBox box = new HBox();
      box.getChildren().addAll(lookupProduct,addProduct);
      box.setSpacing(200);
      box.setAlignment(Pos.CENTER);
      
      BorderPane root = new BorderPane();
      root.setCenter(box);
      root.setTop(topArea("Home"));
      return root;
	}
	
	
	private BorderPane addProductScreen() {
	  
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
      
      Text enterSize = new Text("Enter Size:");
      enterSize.setId("text");
      GridPane.setConstraints(enterSize, 0, 2);
      ComboBox<Double> size = new ComboBox<>();
         for(double i = 9; i < 13; i = i+0.5) {
            size.getItems().add(i);
          }
      GridPane.setConstraints(size, 1, 2);
      
      
      Text quantity = new Text("Quantity:");
      quantity.setId("text");
      GridPane.setConstraints(quantity, 0, 3);
      ComboBox<Integer> prodQuan = new ComboBox<>();
        for(int i = 1; i < 11; i++) {
          prodQuan.getItems().add(i);
        }
      GridPane.setConstraints(prodQuan, 1, 3);
      
      Button addButton = new Button("Add");
      addButton.setPrefSize(100, 5);
      GridPane.setConstraints(addButton, 1, 9);
      GridPane.setHalignment(addButton, HPos.RIGHT);
      
      gridPane.setAlignment(Pos.CENTER);
      gridPane.getChildren().addAll(enterProdName,enterProdNum, enterSize, quantity,
          prodNum,prodName,size,prodQuan, addButton);      

      BorderPane.setAlignment(gridPane, Pos.CENTER);
      BorderPane screen = new BorderPane();
      screen.setCenter(gridPane);
      screen.setTop(topArea("Add Product"));
      
      return screen;
	}
	
	private BorderPane lookupScreen() {
	  GridPane pane = new GridPane();
	  pane.setHgap(10);
	  pane.setVgap(10);
	  pane.setPadding(new Insets(10,10,10,10));
	  pane.setAlignment(Pos.CENTER);
	  Text promptUser = new Text("Enter Product Number:");
	  promptUser.setId("text");
	  GridPane.setConstraints(promptUser, 0, 0);
	  
	  TextField typeBox = new TextField();
	  GridPane.setConstraints(typeBox, 1, 0);
	  
	  pane.getChildren().addAll(promptUser,typeBox);
	  
      Button lookupProduct = new Button("Lookup Product");
      lookupProduct.setPrefSize(300, 50);
      lookupProduct.setAlignment(Pos.CENTER);
      
      VBox box = new VBox(50);
      box.setAlignment(Pos.CENTER);
      box.getChildren().addAll(pane,lookupProduct);
      
      BorderPane borderPane = new BorderPane();
      borderPane.setCenter(box);
      borderPane.setTop(topArea("Lookup Product"));
	  
	  return borderPane;
	}
	
	private BorderPane productInfoScreen() {
	  GridPane pane = new GridPane();
	  pane.setHgap(10);
	  pane.setVgap(10);
	  pane.setPadding(new Insets(10,10,10,10));
	  
	  //Rectangle showView = new Rectangle();
	  
	  Text prodNum = new Text("Product #:");
	  GridPane.setConstraints(prodNum, 0, 0);
	  prodNum.setId("text");
	  
	  Text displayNum = new Text("This is the product num");
	  displayNum.setId("text2");
	  GridPane.setConstraints(displayNum, 1, 0);
	  
	  Text name = new Text("Name:");
	  GridPane.setConstraints(name, 0, 1);
	  name.setId("text");
	  Text displayName = new Text("This is the product name");
	  displayName.setId("text2");
	  GridPane.setConstraints(displayName, 1, 1);
	  
	  Text amountInStock = new Text("Amount in Stock:");
	  GridPane.setConstraints(amountInStock, 0, 2);
	  amountInStock.setId("text");
	  Text displayStock = new Text("This is the amount in stock");
	  displayStock.setId("text2");
	  GridPane.setConstraints(displayStock, 1, 2);
	  
	  pane.setAlignment(Pos.CENTER);
	  pane.getChildren().addAll(prodNum,name,amountInStock,displayNum,
	      displayName,displayStock);
	  
	  BorderPane root = new BorderPane();
	  root.setCenter(pane);
	  root.setTop(topArea("Product Info"));
	  
	  return root;
	}
	
	private HBox topButtons() {
      ImageView img = new ImageView(new Image("home.png"));
      img.setFitWidth(35);
      img.setFitHeight(35);
      final Button homeButton = new Button();
      homeButton.setGraphic(img);
      
      ImageView img2 = new ImageView(new Image("menu.png"));
      img2.setFitWidth(35);
      img2.setFitHeight(35);
      final Button chooseAll = new Button();
      chooseAll.setGraphic(img2);
      
      ImageView helpImg = new ImageView(new Image("questionMark.png"));
      helpImg.setFitWidth(35);
      helpImg.setFitHeight(35);
      final Button helpButton = new Button();
      helpButton.setGraphic(helpImg);
      
      
      ImageView exitImg = new ImageView(new Image("circleExit.png"));
      exitImg.setFitWidth(35);
      exitImg.setFitHeight(35);
      final Button exitButton = new Button();
      exitButton.setGraphic(exitImg);
      
      HBox box = new HBox(0.5);
      box.getChildren().addAll(homeButton,chooseAll,helpButton,exitButton);
      
      return box;
	}
	
	private HBox topArea(String portion) {
	  Label topLabel = new Label("Story Inventory | " + portion);
	  
	  Region rg1 = new Region();
	  HBox.setHgrow(rg1, Priority.ALWAYS);
	  Region rg2 = new Region();
	  HBox.setHgrow(rg2, Priority.ALWAYS);
	  HBox top = new HBox(topLabel,rg1,rg2,topButtons());
	  return top;
	  
	}
	public static void main(String[] args) {
		launch(args);
	}
}
