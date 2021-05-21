package factory.buttonfactory;

import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GrayButtonFactory implements ButtonFactory{
    @Override
    public Button createButton() {
        Button button = new Button();
        button.setStyle(
//                "-fx-alignment: center;" +
                "-fx-padding: 8 15 15 15;\n" +
                        "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                        "    -fx-background-radius: 8;\n" +
                        "    -fx-background-color: \n" +
                        "        linear-gradient(from 0% 93% to 0% 100%, #a5a4a3 0%, #929292 100%),\n" +
                        "        #838682,\n" +
                        "        #9ea09d,\n" +
                        "        radial-gradient(center 50% 50%, radius 100%, #8a8d88, #909090);\n" +
                        "    -fx-effect: dropshadow( gaussian , rgba(40,39,39,0.71) , 4,0,0,1 );\n");
        button.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        return button;
    }
}
