package view.alertbox;

import javafx.scene.control.Alert;
import model.Entity;

import java.util.List;

public class LogoutSuccessfullyBox extends AlertBox {

    @Override
    public void createAlertBox(Entity entity) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Log Out Successfully");
        alert.setHeaderText(null);
        alert.setContentText("You have logged out!");

        alert.showAndWait();
    }

    @Override
    public void createAlertBox(List<Entity> entity) {

    }
}
