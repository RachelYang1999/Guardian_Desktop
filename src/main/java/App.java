import util.RequestMapping;
import javafx.application.Application;
import javafx.stage.Stage;
import view.WelcomeScene;

import java.util.List;

public class App extends Application {
    private Stage window;
    private RequestMapping requestMapping;

    @Override
    public void start(Stage primaryStage) throws Exception {

        List<String> args = getParameters().getRaw();

        requestMapping = new RequestMapping(args.get(0));

        window = primaryStage;
        window.setTitle("Welcome");
        window.setScene(new WelcomeScene(this.window, requestMapping).getScene());
        window.show();
    }

    public static void main(String[] args) throws Exception {

        if (args.length != 1) {
            throw new Exception("Incorrect number of command line arguments");
        }
        if (!args[0].equals("online") && !args[0].equals("offline")) {
            throw new Exception("Incorrect command line argument");
        }

        launch(args);
    }


}