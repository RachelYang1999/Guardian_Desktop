package factory.buttonfactory;

import javafx.scene.control.Button;

/**
 * The button factory interface for producing buttons with different style
 * @author Rachel Yang
 */
public interface ButtonFactory {
  /**
   *
   * @return Button objects with different style
   */
  public Button createButton();
}
