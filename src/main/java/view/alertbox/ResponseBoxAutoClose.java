package view.alertbox;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.domain.Entity;

import java.util.List;

public class ResponseBoxAutoClose{

  public void createAlertBox(Entity entity) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Response");
    alert.setHeaderText("Error");
    alert.setContentText(entity.getEntityInformation());
    alert.showAndWait();
    final Timeline timeline = new Timeline(new KeyFrame(Duration.millis(3000), x -> {
      alert.close();
    }));
    timeline.play();
  }

  public void createAlertBox(String title, String headerText, String contentText) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.setContentText(contentText);
    alert.showAndWait();

    final Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10000), x -> {
      alert.close();
    }));
    timeline.play();

  }



}
