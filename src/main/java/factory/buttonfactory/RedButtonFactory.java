package factory.buttonfactory;

import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * The button factory for producing buttons with the red style
 * @author Rachel Yang
 */
public class RedButtonFactory implements ButtonFactory {
  /**
   *
   * @return Button in the red style will be returned
   */
  @Override
  public Button createButton() {
    Button button = new Button();
    button.setStyle(
        //                "-fx-alignment: center;" +
        "-fx-padding: 8 15 15 15;\n"
            + "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n"
            + "    -fx-background-radius: 8;\n"
            + "    -fx-background-color: \n"
            + "        linear-gradient(from 0% 93% to 0% 100%, #bd6262 0%, #8b3b24 100%),\n"
            + "        #ad4d3d,\n"
            + "        #ce6f5e,\n"
            + "        radial-gradient(center 50% 50%, radius 100%, #b3451a, #92160a);\n"
            + "    -fx-effect: dropshadow( gaussian , rgba(109,99,99,0.71) , 4,0,0,1 );\n");
    button.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    return button;
  }
}
