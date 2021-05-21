package view.alertbox;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Entity;
import model.User;
import utility.ClipboardUtil;

import java.util.List;

public class SignupSuccessfullyBox extends AlertBox {

    @Override
    public void createAlertBox(Entity entity) {
        User user = (User) entity;
        window.setTitle("Sign Up Successfully");

        Button gotButton = new Button("Got It!");
        gotButton.setOnAction(e -> window.close());
        gotButton.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        Button copyButton = new Button("Copy Token");
        copyButton.setOnAction(e -> {
            ClipboardUtil clipboardUtil = new ClipboardUtil();
            clipboardUtil.setClipboardString(user.getToken());
            String copiedText = clipboardUtil.getClipboardString();
            System.out.println("Copied text to clipboard: " + copiedText);
        });

        copyButton.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        HBox buttonHBox = new HBox(10);
        buttonHBox.getChildren().addAll(copyButton, gotButton);
        buttonHBox.setAlignment(Pos.CENTER);

        String userInfo =
                "Username: " + user.getUsername() + "\n" +
                        "Token: " + user.getToken() + "\n";
        Label label = new Label(userInfo);
        label.setFont(Font.font("Arial", FontWeight.NORMAL, 20));

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, buttonHBox);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        // The user cannot do anything till he or she close the alert window
        window.showAndWait();
    }

    @Override
    public void createAlertBox(List<Entity> entity) {

    }
}
