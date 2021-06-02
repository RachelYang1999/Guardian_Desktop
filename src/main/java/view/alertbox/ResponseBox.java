package view.alertbox;

import factory.backgroundfactory.BackgroundFactory;
import factory.backgroundfactory.LightBackgroundFactory;
import factory.buttonfactory.BrownButtonFactory;
import factory.buttonfactory.ButtonFactory;
import factory.buttonfactory.GrayButtonFactory;
import javafx.geometry.Insets;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.domain.Entity;
import util.ClipboardUtil;
import util.RequestMapping;

import java.util.List;

public class ResponseBox extends AlertBox {

  @Override
  public void createAlertBox(Entity entity) {
    //        String entityInformation = entity.getEntityInformation();
    //
    //        window.setTitle("Response");
    //        Button gotButton = new Button("Got It!");
    //        gotButton.setOnAction(e -> window.close());
    //        gotButton.setFont(Font.font("Arial", FontWeight.BOLD, 15));
    //
    //        Button copyButton = new Button("Copy Information");
    //
    //        String finalInfo = entityInformation;
    //        copyButton.setOnAction(e -> {
    //            ClipboardUtil clipboardUtil = new ClipboardUtil();
    //            clipboardUtil.setClipboardString(finalInfo);
    //            String copiedText = clipboardUtil.getClipboardString();
    //            System.out.println("Copied text to clipboard: " + copiedText);
    //        });
    //
    //        copyButton.setFont(Font.font("Arial", FontWeight.BOLD, 15));
    //
    //        Text info = new Text(finalInfo);
    //        ScrollPane scrollPane = new ScrollPane();
    //        scrollPane.setContent(info);
    //        scrollPane.setMaxHeight(800);
    //        scrollPane.setMaxWidth(800);
    //
    //        HBox buttonHBox = new HBox(10);
    //        buttonHBox.getChildren().addAll(copyButton, gotButton);
    //        buttonHBox.setAlignment(Pos.CENTER);
    //
    //        Text text = new Text(finalInfo);
    //        text.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
    //
    //        Scene scene = new Scene(new Group());
    //
    //        VBox root = new VBox();
    //        root.getChildren().addAll(scrollPane, buttonHBox);
    //        scene.setRoot(root);
    //
    //        window.setScene(scene);
    //
    //        // The user cannot do anything till he or she close the alert window
    //        window.showAndWait();
  }

  @Override
  public void createAlertBox(String title, String headerText, String contentText) {

    window.setTitle(title);
    Button gotButton = new Button("Got It!");
    gotButton.setOnAction(e -> window.close());
    gotButton.setFont(Font.font("Arial", FontWeight.BOLD, 15));

    String finalInfo = contentText;

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

  @Override
  public Stage getWindow(String title, String headerText, String contentText) {

    window.setTitle(title);
    Button gotButton = new Button("Got It!");
    gotButton.setOnAction(e -> window.close());
    gotButton.setFont(Font.font("Arial", FontWeight.BOLD, 15));

    String finalInfo = contentText;

    Text header = new Text(headerText);
    header.setFont(Font.font("Arial", FontWeight.BOLD, 20));

    Text info = new Text(finalInfo);

    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(info);
    scrollPane.setPadding(new Insets(10));

    HBox buttonHBox = new HBox(10);
    buttonHBox.getChildren().addAll(gotButton);
    buttonHBox.setAlignment(Pos.CENTER);

    VBox content = new VBox();
    content.getChildren().addAll(scrollPane, buttonHBox);

    BorderPane pane = new BorderPane();
    pane.setTop(header);
    pane.setAlignment(header, Pos.BOTTOM_CENTER);

    pane.setCenter(content);
    pane.setAlignment(content, Pos.CENTER);

    Scene scene = new Scene(pane, 1000, 1000);

    window.setScene(scene);
    return window;
  }

  @Override
  public Stage getWindow(Entity entity) {
    return null;
  }

  @Override
  public Alert getAlertBox(Entity entity) {
    return null;
  }

  @Override
  public void createAlertBox(List<Entity> entity) {}
}
