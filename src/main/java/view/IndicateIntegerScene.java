package view;

import factory.backgroundfactory.BackgroundFactory;
import factory.backgroundfactory.LightBackgroundFactory;
import factory.buttonfactory.BrownButtonFactory;
import factory.buttonfactory.ButtonFactory;
import factory.buttonfactory.MenuButtonFactory;
import factory.buttonfactory.RedButtonFactory;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.domain.Entity;
import model.domain.User;
import util.RequestMapping;
import view.alertbox.AlertBox;
import view.alertbox.ErrorResponseBox;
import view.alertbox.ResponseBox;

public class IndicateIntegerScene {
  private Stage window;
  private Scene scene;
  private BackgroundFactory backgroundFactory;
  private ButtonFactory buttonFactory;
  private AlertBox alertBox;

  public IndicateIntegerScene(Stage window, RequestMapping requestMapping) throws Exception {
    this.window = window;
    this.backgroundFactory = new LightBackgroundFactory();
    this.buttonFactory = new BrownButtonFactory();
    this.window.setTitle("Indicate An Integer");

    Text t = new Text();
    t.setCache(true);
    t.setText("Indicate An Integer");
    //        t.setStyle("-fx-text-fill:#704728");
    t.setFill(Color.web("#704728"));
    t.setFont(Font.font("Arial", FontWeight.BOLD, 60));
    t.setLayoutX(250);
    t.setLayoutY(230);
    Reflection r = new Reflection();
    r.setFraction(0.7f);
    t.setEffect(r);

    ChoiceBox<String> choiceBox = new ChoiceBox<>();
    choiceBox.setItems(FXCollections.observableArrayList(
            "0", "1", "2", "3", "4")
    );
    choiceBox.setMinWidth(180);
    choiceBox.setStyle("-fx-background-color: #dbb58e;\n" +
            "-fx-font-family: Arial Unicode MS;\n" +
            "-fx-mark-color: #000000;");

    this.buttonFactory = new BrownButtonFactory();
    Button button11 = buttonFactory.createButton();
    button11.setText("Indicate");
    button11.setTextAlignment(TextAlignment.CENTER);
    button11.setOnAction(
        event -> {
          Entity returnedEntity = requestMapping.updateUserWithInputInteger(choiceBox.getValue());
          if (returnedEntity.getEntityType().equals("User")) {
            requestMapping.updateCurrentUser((User) returnedEntity);
            try {
              window.setScene(new MainMenuScene(window, requestMapping).getScene());
              window.setTitle("Main Menu");
            } catch (Exception e) {
              e.printStackTrace();
            }
          } else {
            this.alertBox = new ErrorResponseBox();
            alertBox.createAlertBox(
                    "Warning", "Invalid Choice", "Please select again");
          }

        });
    button11.setAlignment(Pos.CENTER);

    VBox buttonCol1 = new VBox(20);
    buttonCol1.getChildren().addAll(choiceBox, button11);
    buttonCol1.setAlignment(Pos.CENTER);


    HBox mainHBox = new HBox(20);

    mainHBox.getChildren().addAll(buttonCol1);
    mainHBox.setAlignment(Pos.CENTER);

    this.buttonFactory = new RedButtonFactory();
    Button buttonLogOut = buttonFactory.createButton();
    buttonLogOut.setText("Log Out");
    buttonLogOut.setOnAction(
        event -> {
          this.alertBox = new ResponseBox();
          alertBox.createAlertBox(
              "Log Out", "You have been logged out successfully!", "See you next time~");
          try {
            requestMapping.userLogOut();
            window.setScene(new WelcomeScene(window, requestMapping).getScene());
          } catch (Exception e) {
            e.printStackTrace();
          }
        });

    BorderPane pane = new BorderPane();
    pane.getChildren().add(this.backgroundFactory.getBackground());

    pane.setTop(t);
    pane.setAlignment(t, Pos.BOTTOM_CENTER);

    pane.setCenter(mainHBox);
    pane.setAlignment(mainHBox, Pos.CENTER);
    pane.setVisible(true);

    pane.setBottom(buttonLogOut);
    pane.setAlignment(buttonLogOut, Pos.BOTTOM_RIGHT);

    this.scene = new Scene(pane, 1280, 800);
  }

  public Scene getScene() {
    return this.scene;
  }
}
