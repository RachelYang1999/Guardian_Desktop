package factory.backgroundfactory;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import view.ImageUtil;

public class DeepBackgroundFactory implements BackgroundFactory {
  @Override
  public ImageView getBackground() throws Exception {
    ImageView imageView = new ImageView();
    Image image = new Image("/background.jpeg", 1280, 800, true, true);
    WritableImage wImage = new ImageUtil().imgOpacity(image, 0.7);
    imageView.setImage(wImage);
    return imageView;
  }
}
