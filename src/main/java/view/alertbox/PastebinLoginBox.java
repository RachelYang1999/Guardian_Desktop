package view.alertbox;

import factory.backgroundfactory.BackgroundFactory;
import factory.backgroundfactory.LightBackgroundFactory;
import factory.buttonfactory.BrownButtonFactory;
import factory.buttonfactory.ButtonFactory;
import factory.buttonfactory.GrayButtonFactory;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
import util.RequestMapping;
import view.alertbox.ErrorBox;
import view.alertbox.UnknownErrorBox;

public class PastebinLoginBox {

  private Stage window;
  private BackgroundFactory backgroundFactory;
  private ButtonFactory buttonFactory;
  private String copiedText;
  private RequestMapping requestMapping;
  private AlertBox alertBox;

  public PastebinLoginBox(RequestMapping requestMapping, String copiedText) {
    window = new Stage();
    window.setTitle("Pastebin Login");
    window.setMinWidth(1280);
    window.setMinHeight(800);

    this.requestMapping = requestMapping;
    this.copiedText = copiedText;

    this.backgroundFactory = new LightBackgroundFactory();
    this.buttonFactory = new BrownButtonFactory();
  }

  public void createAlertBox() throws Exception {
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
          //            String inputUserName = userNameTextField.getText();
          String inputToken = tokenTextField.getText();
          Entity returnedEntity = requestMapping.getPastebinLink(inputToken, copiedText);

          if (returnedEntity.getEntityType().equals("Pastebin")) {
            Pastebin pastebin = (Pastebin) returnedEntity;
            this.alertBox = new ResponseBoxWithCopyButton();
            alertBox.createAlertBox(
                "Log In Successfully", "Here is your Pastebinlink", pastebin.getLink());
            System.out.println("Login successfully in LoginScene!");
            System.out.println("[LoginScene] Token: " + inputToken);
            this.window.close();
          } else if (returnedEntity.getEntityType().equals("ErrorInfo")) {
            this.alertBox = new ErrorBox();
            //                alertBox.createAlertBox(returnedEntity);
            if (returnedEntity.getEntityInformation().contains("invalid api_dev_key")) {
              alertBox.createAlertBox(
                  "Login Failed", "Authorization Error", "This token is invalid, please try again");
            } else {
              alertBox.createAlertBox(returnedEntity);
            }
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

    Scene scene = new Scene(pane, 1280, 800);
    window.setScene(scene);

    // The user cannot do anything till he or she close the alert window
    window.showAndWait();
  }

  public void createAlertBox(String title, String headerText, String contentText) {

    window.setTitle(title);
    Button gotButton = new Button("Got It!");
    gotButton.setOnAction(e -> window.close());
    gotButton.setFont(Font.font("Arial", FontWeight.BOLD, 15));

    //        Button copyButton = new Button("Copy Information");

    String finalInfo = contentText;
    //        copyButton.setOnAction(e -> {
    //            ClipboardUtil clipboardUtil = new ClipboardUtil();
    //            clipboardUtil.setClipboardString(finalInfo);
    //            String copiedText = clipboardUtil.getClipboardString();
    //            System.out.println("Copied text to clipboard: " + copiedText);
    //        });
    //
    //        copyButton.setFont(Font.font("Arial", FontWeight.BOLD, 15));

    Text header = new Text(headerText);
    header.setFont(Font.font("Arial", FontWeight.BOLD, 20));

    Text info = new Text(finalInfo);
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(info);
    scrollPane.setMaxHeight(800);
    scrollPane.setMaxWidth(800);

    HBox buttonHBox = new HBox(10);
    buttonHBox.getChildren().addAll(gotButton);
    buttonHBox.setAlignment(Pos.CENTER);

    VBox content = new VBox();
    content.getChildren().addAll(scrollPane, buttonHBox);

    BorderPane pane = new BorderPane();
    pane.setTop(header);
    pane.setAlignment(header, Pos.BOTTOM_CENTER);

    pane.setCenter(scrollPane);
    pane.setAlignment(scrollPane, Pos.CENTER);

    pane.setBottom(buttonHBox);
    pane.setAlignment(buttonHBox, Pos.CENTER);

    Scene scene = new Scene(pane, 450, 300);

    window.setScene(scene);

    // The user cannot do anything till he or she close the alert window
    window.showAndWait();
  }
}
