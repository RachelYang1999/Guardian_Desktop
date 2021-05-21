package view.alertbox;

import javafx.scene.control.Alert;
import model.Entity;

import java.util.List;

public class UnknownErrorBox extends AlertBox {

    @Override
    public void createAlertBox(Entity entity) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error");
        alert.setContentText("Unknown Error!");
        alert.showAndWait();
    }

    @Override
    public void createAlertBox(List<Entity> entity) {
    }
}
