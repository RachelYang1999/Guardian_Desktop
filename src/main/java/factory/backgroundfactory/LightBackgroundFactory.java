package factory.backgroundfactory;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import view.ImageUtil;

/**
 * The background factory for producing lighter color background
 * @author Rachel Yang
 */
public class LightBackgroundFactory implements BackgroundFactory {

  /**
   *
   * @return The ImageView with light color background image
   * @throws Exception
   */
  @Override
  public ImageView getBackground() throws Exception {
    ImageView imageView = new ImageView();
    Image image = new Image("/background.jpeg", 1280, 800, true, true);
    WritableImage wImage = new ImageUtil().imgOpacity(image, 0.4);
    imageView.setImage(wImage);
    return imageView;
  }
}
