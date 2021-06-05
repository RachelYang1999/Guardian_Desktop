package view.alertbox;

import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.domain.Entity;

import java.util.List;

public abstract class AlertBox {
  Stage window;

  public AlertBox() {
    this.window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);
    window.setMinWidth(650);
    window.setMinHeight(450);
  }

  public abstract void createAlertBox(Entity entity);

  public abstract void createAlertBox(String title, String headerText, String contentText);

  public abstract Stage getWindow(String title, String headerText, String contentText);

  public abstract Stage getWindow(Entity entity);

  public abstract Alert getAlertBox(Entity entity);

  public abstract void createAlertBox(List<Entity> entity);

  public abstract void close();

}
