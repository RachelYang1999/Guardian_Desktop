package view.alertbox;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.domain.Entity;

import java.util.List;

public class ErrorResponseBox extends AlertBox {

  @Override
  public void createAlertBox(Entity entity) {
    window.setTitle("Error");
    Button gotButton = new Button("Got It!");
    gotButton.setOnAction(e -> window.close());
    gotButton.setFont(Font.font("Arial", FontWeight.BOLD, 15));

    String finalInfo = entity.getEntityInformation();

    Text header = new Text("Request Failed");
    header.setFill(Color.web("#DE112D"));
    header.setFont(Font.font("Arial", FontWeight.BOLD, 22));

    Text info = new Text(finalInfo);
    info.setFont(Font.font("Arial", FontWeight.BOLD, 15));

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
  public void createAlertBox(String title, String headerText, String contentText) {

    window.setTitle(title);
    Button gotButton = new Button("Got It!");
    gotButton.setOnAction(e -> window.close());
    gotButton.setFont(Font.font("Arial", FontWeight.BOLD, 15));

    String finalInfo = contentText;

    Text header = new Text(headerText);
    header.setFill(Color.web("#DE112D"));
    header.setFont(Font.font("Arial", FontWeight.BOLD, 22));

    Text info = new Text(finalInfo);
    info.setFont(Font.font("Arial", FontWeight.BOLD, 15));

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

    Label header = new Label(headerText);
    header.setFont(Font.font("Arial", FontWeight.BOLD, 20));
    header.setPadding(new Insets(5));

    Text info = new Text(finalInfo);
    info.setFont(Font.font("Arial", FontWeight.BOLD, 15));

    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(info);
    scrollPane.setPadding(new Insets(10));
    VBox scrollPaneBox = new VBox(5);
    scrollPaneBox.getChildren().addAll(scrollPane);


    HBox buttonHBox = new HBox(10);
    buttonHBox.getChildren().addAll(gotButton);
    buttonHBox.setAlignment(Pos.CENTER);

    VBox content = new VBox();
    content.getChildren().addAll(scrollPaneBox, buttonHBox);

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

  @Override
  public void close() {
    window.close();
  }
}
