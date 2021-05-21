package factory.buttonfactory;

import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MenuButtonFactory implements ButtonFactory {
    @Override
    public Button createButton() {
        Button button = new Button();
        button.setStyle(
//                "-fx-alignment: center;" +
                "-fx-padding: 8 15 15 15;\n" +
                        "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                        "    -fx-background-radius: 8;\n" +
                        "    -fx-background-color: \n" +
                        "        linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),\n" +
                        "        #9d4024,\n" +
                        "        #d86e3a,\n" +
                        "        radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);\n" +
                        "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n");
        button.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        button.setPrefSize(160, 120);
        return button;
    }
}
