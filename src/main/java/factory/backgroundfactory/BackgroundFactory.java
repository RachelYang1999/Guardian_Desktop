package factory.backgroundfactory;

import javafx.scene.image.ImageView;

/**
 * The background factory interface for producing background
 * @author Rachel Yang
 */
public interface BackgroundFactory {
  /**
   *
   * @return The ImageView with deep or light color background image
   * @throws Exception
   */
  public ImageView getBackground() throws Exception;
}
