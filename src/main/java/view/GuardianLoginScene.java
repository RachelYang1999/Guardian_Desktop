package view;

import util.RequestMapping;
import factory.backgroundfactory.BackgroundFactory;
import factory.backgroundfactory.LightBackgroundFactory;
import factory.buttonfactory.BrownButtonFactory;
import factory.buttonfactory.ButtonFactory;
import factory.buttonfactory.GrayButtonFactory;
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
import model.domain.User;
import view.alertbox.AlertBox;
import view.alertbox.ErrorBox;
import view.alertbox.ResponseBox;
import view.alertbox.UnknownErrorBox;

public class GuardianLoginScene {
  private Stage window;
  private Scene scene;
  private BackgroundFactory backgroundFactory;
  private ButtonFactory buttonFactory;
  private AlertBox alertBox;

  public GuardianLoginScene(Stage window, RequestMapping requestMapping) throws Exception {
    this.window = window;
    this.backgroundFactory = new LightBackgroundFactory();
    this.buttonFactory = new BrownButtonFactory();

    Button buttonVisitor = buttonFactory.createButton();
    buttonVisitor.setText("I AM A VISITOR");
    buttonVisitor.setPrefWidth(250);
    buttonVisitor.setLayoutX(328);
    buttonVisitor.setLayoutY(630);

    Text t = new Text();
    t.setCache(true);
    t.setText("Please log in here!");
    t.setFill(Color.web("#FFFFFF"));
    t.setFont(Font.font("Arial", FontWeight.BOLD, 70));
    t.setLayoutX(250);
    t.setLayoutY(230);
    Reflection r = new Reflection();
    r.setFraction(0.7f);
    t.setEffect(r);

    Label tokenLabel = new Label("Token");
    TextField tokenTextField = new TextField("1b0f84fb-9674-4fe2-b596-5836b2772fcb");
    tokenLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
    //        tokenLabel.setStyle("-fx-text-fill:#ffffff");

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
          //            String inputUserName = userNameTextField.getText();
          String inputToken = tokenTextField.getText();
          Entity returnedEntity = requestMapping.login(inputToken);

          if (returnedEntity.getEntityType().equals("User")) {
            User returnedUser = (User) returnedEntity;
            this.alertBox = new ResponseBox();
            alertBox.createAlertBox(
                "Log In Successfully",
                "Here is your account information",
                returnedEntity.getEntityInformation());
            try {
              window.setScene(new MainMenuScene(window, requestMapping).getScene());
            } catch (Exception e) {
              e.printStackTrace();
            }
            System.out.println("Login successfully in LoginScene!");
            System.out.println("[LoginScene] Token: " + inputToken);
          } else if (returnedEntity.getEntityType().equals("ErrorInfo")) {
            this.alertBox = new ErrorBox();
            alertBox.createAlertBox(returnedEntity);
            System.out.println("[LoginScene] The token is incorrect");
          } else {
            this.alertBox = new UnknownErrorBox();
            alertBox.createAlertBox(returnedEntity);
          }
        });

    this.buttonFactory = new GrayButtonFactory();
    Button backButton = buttonFactory.createButton();
    backButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
    backButton.setText("Back");
    backButton.setOnAction(
        event -> {
          try {
            window.setScene(new WelcomeScene(window, requestMapping).getScene());
            window.setTitle("Welcome");
          } catch (Exception e) {
            e.printStackTrace();
          }
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
