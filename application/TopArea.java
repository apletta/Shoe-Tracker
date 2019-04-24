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


class TopArea {



	protected static HBox topButtons() {
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
	
	protected static HBox topArea(String portion) {
	  Label topLabel = new Label("Story Inventory | " + portion);
	  
	  Region rg1 = new Region();
	  HBox.setHgrow(rg1, Priority.ALWAYS);
	  Region rg2 = new Region();
	  HBox.setHgrow(rg2, Priority.ALWAYS);
	  HBox top = new HBox(topLabel,rg1,rg2,topButtons());
	  return top;
	  
	}

}