package view;

import engine.GameEngine;
import factory.backgroundfactory.BackgroundFactory;
import factory.backgroundfactory.DeepBackgroundFactory;
import factory.buttonfactory.BrownButtonFactory;
import factory.buttonfactory.ButtonFactory;
import factory.buttonfactory.GreenButtonFactory;
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
import view.alertbox.AlertBox;
import view.alertbox.SignupSuccessfullyBox;
import view.alertbox.SignupUnsuccessfullyBox;

import java.io.IOException;

public class SignupScene {
    private Stage window;
    private Scene scene;
    private BackgroundFactory backgroundFactory;
    private ButtonFactory buttonFactory;
    private AlertBox alertBox;

    public SignupScene(Stage window, GameEngine gameEngine) throws Exception {
        this.window = window;
        this.backgroundFactory = new DeepBackgroundFactory();
        this.buttonFactory = new BrownButtonFactory();

        Button buttonVisitor = buttonFactory.createButton();
        buttonVisitor.setText("I AM A VISITOR");
        buttonVisitor.setPrefWidth(250);
        buttonVisitor.setLayoutX(328);
        buttonVisitor.setLayoutY(630);

        Text t = new Text();
        t.setCache(true);
        t.setText("Please register here!");
        t.setFill(Color.web("#FFFFFF"));
        t.setFont(Font.font("Arial", FontWeight.BOLD, 70));
        t.setLayoutX(250);
        t.setLayoutY(230);
        Reflection r = new Reflection();
        r.setFraction(0.7f);
        t.setEffect(r);

        Label userNameLabel = new Label("Username");
        TextField userNameTextField = new TextField("test05102");
//        TextField userNameTextField = new TextField();
        userNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        userNameTextField.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        userNameTextField.setMaxWidth(250);
        HBox userNameHBox = new HBox();
        userNameHBox.getChildren().addAll(userNameLabel, userNameTextField);
        userNameHBox.setAlignment(Pos.CENTER);

        Button signupButton = buttonFactory.createButton();
        signupButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        signupButton.setText("Sign Up");
        signupButton.setOnAction(event -> {
            
        });

        Button backButton = buttonFactory.createButton();
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        backButton.setText("Back");
        backButton.setOnAction(event -> {
            try {
                window.setScene(new WelcomeScene(window, gameEngine).getScene());
                window.setTitle("Welcome");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        VBox labelBox = new VBox(20);
        labelBox.getChildren().addAll(userNameLabel);
        labelBox.setAlignment(Pos.CENTER_RIGHT);

        VBox textFieldBox = new VBox(15);
        textFieldBox.getChildren().addAll(userNameTextField);
        textFieldBox.setAlignment(Pos.CENTER_LEFT);

        HBox hBox = new HBox(3);
        hBox.getChildren().addAll(labelBox, textFieldBox);
        hBox.setAlignment(Pos.CENTER);

        HBox buttonBox = new HBox(15);
        buttonBox.getChildren().addAll(signupButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);

        buttonFactory = new GreenButtonFactory();
        Button loginButton = buttonFactory.createButton();
        loginButton.setFont(Font.font("Arial", FontWeight.BOLD, 17));
        loginButton.setText("Already have an account? Login!");
        loginButton.setOnAction(event -> {
            try {
                window.setScene(new LoginScene(window, gameEngine).getScene());
                window.setTitle("Login");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        VBox totalForm = new VBox(20);
        totalForm.getChildren().addAll(hBox, buttonBox, loginButton);
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
