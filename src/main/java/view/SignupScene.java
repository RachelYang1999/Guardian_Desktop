package view;

import util.RequestMapping;
import factory.backgroundfactory.BackgroundFactory;
import factory.backgroundfactory.DeepBackgroundFactory;
import factory.buttonfactory.BrownButtonFactory;
import factory.buttonfactory.ButtonFactory;
import factory.buttonfactory.GrayButtonFactory;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import util.ClipboardUtil;
import view.alertbox.AlertBox;
import view.alertbox.ResponseBox;

public class SignupScene {
    private Stage window;
    private Scene scene;
    private ButtonFactory buttonFactory;
    private BackgroundFactory backgroundFactory;
    private AlertBox alertBox;

    public SignupScene(Stage window, RequestMapping requestMapping) throws Exception {
        this.window = window;
        this.backgroundFactory = new DeepBackgroundFactory();
        this.buttonFactory = new BrownButtonFactory();

        Text t = new Text();
        t.setCache(true);
        t.setText("Sign Up");
        t.setFill(Color.web("#FFFFFF"));
        t.setFont(Font.font("Arial", FontWeight.BOLD, 70));
        t.setLayoutX(250);
        t.setLayoutY(230);
        Reflection r = new Reflection();
        r.setFraction(0.7f);
        t.setEffect(r);

        Button copyButton = buttonFactory.createButton();

        copyButton.setText("You need to signup for a token via the Guardian website\nPlease click on this button to copy the web link");
        copyButton.setTextAlignment(TextAlignment.CENTER);
        String finalInfo = "https://open-platform.theguardian.com/access/";
        copyButton.setOnAction(copy -> {
            this.alertBox = new ResponseBox();
            String info = "You have copied this link successfully!\n" +
                    "Please access this sign up website by pasting the link to a browser~";
            alertBox.createAlertBox("Copy Link", "Congratulations", info);
            try {
                window.setScene(new GuardianLoginScene(window, requestMapping).getScene());
            } catch (Exception e) {
                e.printStackTrace();
            }
            ClipboardUtil clipboardUtil = new ClipboardUtil();
            clipboardUtil.setClipboardString(finalInfo);
            String copiedText = clipboardUtil.getClipboardString();
            System.out.println("Copied text to clipboard: " + copiedText);
        });

        copyButton.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        this.buttonFactory = new GrayButtonFactory();
        Button backButton = buttonFactory.createButton();
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        backButton.setText("Back");
        backButton.setOnAction(event -> {
            try {
                window.setScene(new WelcomeScene(window, requestMapping).getScene());
                window.setTitle("Welcome");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        VBox totalForm = new VBox(20);
        totalForm.getChildren().addAll(copyButton, backButton);
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
