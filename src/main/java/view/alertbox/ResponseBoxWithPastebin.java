package view.alertbox;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.domain.Entity;
import model.domain.Pastebin;
import util.RequestMapping;
import view.GuardianLoginScene;
import view.PastebinLoginScene;

import java.util.List;

public class ResponseBoxWithPastebin {

  Stage window;
  //    Stage initWindow;
  RequestMapping requestMapping;

  public ResponseBoxWithPastebin(RequestMapping requestMapping) {
    this.window = new Stage();
    //        this.initWindow = window;
    this.requestMapping = requestMapping;
    window.setMinWidth(650);
    window.setMinHeight(450);
  }

  public void createAlertBox(String title, String headerText, String copiedText) {
    String finalInfo = copiedText;
    window.setTitle(title);
    Button gotButton = new Button("Got It!");
    gotButton.setOnAction(e -> window.close());
    gotButton.setFont(Font.font("Arial", FontWeight.BOLD, 15));
    Button pastebinButton = new Button("Copy To Pastebin");
    pastebinButton.setFont(Font.font("Arial", FontWeight.BOLD, 15));

    pastebinButton.setOnAction(
        event -> {
          PastebinLoginBox pastebinLoginBox = new PastebinLoginBox(requestMapping, copiedText);
          try {
            pastebinLoginBox.createAlertBox();
          } catch (Exception e) {
            e.printStackTrace();
          }

          this.window.close();
        });

    Text header = new Text(headerText);
    header.setFont(Font.font("Arial", FontWeight.BOLD, 20));

    Text info = new Text(finalInfo);
    info.setFont(Font.font("Arial", FontWeight.BOLD, 15));

    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(info);
    scrollPane.setMaxHeight(800);
    scrollPane.setMaxWidth(800);

    HBox buttonHBox = new HBox(10);
    buttonHBox.getChildren().addAll(pastebinButton, gotButton);
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

  public void createAlertBox(Entity entity) {
    window.setTitle("Article Information");
    Button gotButton = new Button("Got It!");
    gotButton.setOnAction(e -> window.close());
    gotButton.setFont(Font.font("Arial", FontWeight.BOLD, 15));

    Button pastebinButton = new Button("Copy To Pastebin");

    String finalInfo = entity.getEntityInformation();

    pastebinButton.setOnAction(
        event -> {
          window.close();
        });
    //        copyButton.setOnAction(e -> {
    //            ClipboardUtil clipboardUtil = new ClipboardUtil();
    //            clipboardUtil.setClipboardString(finalInfo);
    //            String copiedText = clipboardUtil.getClipboardString();
    //            System.out.println("Copied text to clipboard: " + copiedText);
    //        });
    //
    //        copyButton.setFont(Font.font("Arial", FontWeight.BOLD, 15));

    Text header = new Text("headerText");
    header.setFont(Font.font("Arial", FontWeight.BOLD, 20));

    Text info = new Text(finalInfo);
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(info);
    scrollPane.setMaxHeight(800);
    scrollPane.setMaxWidth(800);

    HBox buttonHBox = new HBox(10);
    buttonHBox.getChildren().addAll(pastebinButton, gotButton);
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
