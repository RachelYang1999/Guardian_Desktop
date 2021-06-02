package view;

import util.RequestMapping;
import factory.backgroundfactory.BackgroundFactory;
import factory.backgroundfactory.DeepBackgroundFactory;
import factory.buttonfactory.BrownButtonFactory;
import factory.buttonfactory.ButtonFactory;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.alertbox.AlertBox;

public class WelcomeScene {
  private Stage window;
  private Scene scene;
  private ButtonFactory buttonFactory;
  private BackgroundFactory backgroundFactory;
  private AlertBox alertBox;

  public WelcomeScene(Stage window, RequestMapping requestMapping) throws Exception {
    this.window = window;
    this.backgroundFactory = new DeepBackgroundFactory();
    this.buttonFactory = new BrownButtonFactory();

    Button buttonVisitor = buttonFactory.createButton();
    buttonVisitor.setText("I Don't Have A Token");
    buttonVisitor.setPrefWidth(250);
    buttonVisitor.setLayoutX(328);
    buttonVisitor.setLayoutY(630);
    buttonVisitor.setOnAction(
        event -> {
          try {
            this.window.setScene(new SignupScene(this.window, requestMapping).getScene());
            this.window.setTitle("Request A Token");
          } catch (Exception e) {
            e.printStackTrace();
          }
        });

    Button buttonUser = buttonFactory.createButton();
    buttonUser.setText("Log In With A Token");
    buttonUser.setPrefWidth(250);
    buttonUser.setLayoutX(700);
    buttonUser.setLayoutY(630);
    buttonUser.setOnAction(
        event -> {
          try {
            this.window.setScene(new GuardianLoginScene(this.window, requestMapping).getScene());
            this.window.setTitle("Login");
          } catch (Exception e) {
            e.printStackTrace();
          }
        });

    Text t = new Text();
    t.setCache(true);
    t.setText("Welcome To Guardian Desktop!");
    t.setFill(Color.web("#FFFFFF"));
    t.setFont(Font.font("Arial", FontWeight.BOLD, 70));
    t.setLayoutX(200);
    t.setLayoutY(310);

    Reflection r = new Reflection();
    r.setFraction(0.7f);
    t.setEffect(r);

    HBox hBox = new HBox(30);
    hBox.getChildren().addAll(buttonVisitor, buttonUser);
    hBox.setAlignment(Pos.CENTER);
    //        hBox.setStyle("-fx-background-color:#00ffff");

    VBox vBox = new VBox(10);
    Region region1 = new Region();
    region1.setPrefHeight(100);
    VBox.setVgrow(region1, Priority.ALWAYS);
    vBox.getChildren().addAll(hBox, region1);
    //        vBox.setStyle("-fx-background-color:#8b2727");

    BorderPane borderPane = new BorderPane();
    borderPane.getChildren().add(this.backgroundFactory.getBackground());
    borderPane.setCenter(t);
    borderPane.setAlignment(t, Pos.CENTER);
    borderPane.setBottom(vBox);
    borderPane.setAlignment(vBox, Pos.TOP_CENTER);

    this.scene = new Scene(borderPane, 1280, 800);
  }

  public Scene getScene() {
    return this.scene;
  }
}
