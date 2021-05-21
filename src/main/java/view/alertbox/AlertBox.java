package view.alertbox;

import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Entity;

import java.util.List;

public abstract class AlertBox {
    Stage window;

    public AlertBox() {
        this.window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(450);
        window.setMinHeight(300);
    }

    public abstract void createAlertBox(Entity entity);

    public abstract void createAlertBox(List<Entity> entity);


}
