package view.alertbox;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Entity;
import model.User;

import java.util.List;

public class SignupUnsuccessfullyBox extends AlertBox {

    @Override
    public void createAlertBox(Entity entity) {
        User user = (User) entity;
        window.setTitle("Warning");

        Button button = new Button("Got It!");
        button.setOnAction(e -> window.close());
        button.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        String userInfo = "The username has already been used, please change another one";
        Label label = new Label(userInfo);
        label.setFont(Font.font("Arial", FontWeight.NORMAL, 20));

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label , button);
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
