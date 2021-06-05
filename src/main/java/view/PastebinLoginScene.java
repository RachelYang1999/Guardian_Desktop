package view;

import factory.backgroundfactory.BackgroundFactory;
import factory.backgroundfactory.LightBackgroundFactory;
import factory.buttonfactory.BrownButtonFactory;
import factory.buttonfactory.ButtonFactory;
import factory.buttonfactory.GrayButtonFactory;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.domain.Entity;
import model.domain.Pastebin;
import model.domain.User;
import util.RequestMapping;
import view.alertbox.*;

public class PastebinLoginScene {
  private Stage window;
  private Scene scene;
  private BackgroundFactory backgroundFactory;
  private ButtonFactory buttonFactory;

  public PastebinLoginScene(Stage window, RequestMapping requestMapping, String copiedText)
      throws Exception {
    this.window = new Stage();
    window.setTitle("Pastebin Login");
    this.backgroundFactory = new LightBackgroundFactory();
    this.buttonFactory = new BrownButtonFactory();

    Text t = new Text();
    t.setCache(true);
    t.setText("Please input your Pastebin token in here!");
    t.setFill(Color.web("#FFFFFF"));
    t.setFont(Font.font("Arial", FontWeight.BOLD, 50));
    t.setLayoutX(250);
    t.setLayoutY(230);
    Reflection r = new Reflection();
    r.setFraction(0.7f);
    t.setEffect(r);

    Label tokenLabel = new Label("Token");
    TextField tokenTextField = new TextField("agr_jX9Bg7kE3-XgRLIt-TWk2teKqTxN");
    tokenLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

    tokenTextField.setFont(Font.font("Arial", FontWeight.BOLD, 15));
    tokenTextField.setMaxWidth(250);
    HBox tokenHBox = new HBox();
    tokenHBox.getChildren().addAll(tokenLabel, tokenTextField);
    tokenHBox.setAlignment(Pos.CENTER);

    Button loginButton = buttonFactory.createButton();
    loginButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
    loginButton.setText("Login");
    loginButton.setOnAction(
        event -> {
          String inputToken = tokenTextField.getText();

          /*
          Create a new thread for processing backend logic
           */
          Task<Entity> task = new Task<Entity>() {
            @Override
            protected Entity call() throws Exception {
              return requestMapping.getPastebinLink(inputToken, copiedText);
            }

            @Override
            protected void succeeded() {
              if (getValue().getEntityType().equals("Pastebin")) {

                Pastebin pastebin = (Pastebin) getValue();
                AlertBox alertBox = new ResponseBoxWithCopyButton();
                alertBox.createAlertBox(
                        "Log In Successfully", "Here is your Pastebinlink", pastebin.getLink());
//                System.out.println("Login successfully in LoginScene!");
//                System.out.println("[LoginScene] Token: " + inputToken);
              } else if (getValue().getEntityType().equals("ErrorInfo")) {
                AlertBox alertBox  = new ErrorBox();
                alertBox.createAlertBox(getValue());
//                System.out.println("[LoginScene] The token is incorrect");
              } else {
                AlertBox alertBox  = new UnknownErrorBox();
                alertBox.createAlertBox(getValue());
              }
            }
          };
          new Thread(task).start();
        });

    this.buttonFactory = new GrayButtonFactory();
    Button backButton = buttonFactory.createButton();
    backButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
    backButton.setText("Back");
    backButton.setOnAction(
        event -> {
          window.close();
        });

    VBox labelBox = new VBox(20);
    labelBox.getChildren().addAll(tokenLabel);
    labelBox.setAlignment(Pos.CENTER_RIGHT);

    VBox textFieldBox = new VBox(15);
    textFieldBox.getChildren().addAll(tokenTextField);
    textFieldBox.setAlignment(Pos.CENTER_LEFT);

    HBox hBox = new HBox(3);
    hBox.getChildren().addAll(labelBox, textFieldBox);
    hBox.setAlignment(Pos.CENTER);

    HBox buttonBox = new HBox(15);
    buttonBox.getChildren().addAll(loginButton, backButton);
    buttonBox.setAlignment(Pos.CENTER);

    VBox totalForm = new VBox(20);
    totalForm.getChildren().addAll(hBox, buttonBox);
    totalForm.setAlignment(Pos.CENTER);

    BorderPane pane = new BorderPane();
    pane.getChildren().add(this.backgroundFactory.getBackground());
    pane.setTop(t);
    pane.setAlignment(t, Pos.BOTTOM_CENTER);

    pane.setCenter(totalForm);
    pane.setAlignment(totalForm, Pos.CENTER);

    this.scene = new Scene(pane, 1280, 800);
  }

  public Scene getScene() {
    return this.scene;
  }
}
