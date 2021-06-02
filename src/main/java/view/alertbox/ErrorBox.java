package view.alertbox;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.domain.Entity;

import java.util.List;

public class ErrorBox extends AlertBox {

  @Override
  public void createAlertBox(Entity entity) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("Error");
    alert.setContentText(entity.getEntityInformation());

    alert.showAndWait();
  }

  @Override
  public void createAlertBox(String title, String headerText, String contentText) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.setContentText(contentText);

    alert.showAndWait();
  }

  @Override
  public Stage getWindow(String title, String headerText, String contentText) {
    return null;
  }

  @Override
  public Stage getWindow(Entity entity) {
    return null;
  }

  @Override
  public Alert getAlertBox(Entity entity) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("Error");
    alert.setContentText(entity.getEntityInformation());
    return alert;
  }

  @Override
  public void createAlertBox(List<Entity> entity) {}
}
