package view;

import engine.GameEngine;
import factory.backgroundfactory.BackgroundFactory;
import factory.backgroundfactory.LightBackgroundFactory;
import factory.buttonfactory.BrownButtonFactory;
import factory.buttonfactory.ButtonFactory;
import factory.buttonfactory.MenuButtonFactory;
import factory.buttonfactory.RedButtonFactory;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import view.alertbox.AlertBox;
import view.alertbox.LogoutSuccessfullyBox;

public class MainMenuScene {
    private Stage window;
    private Scene scene;
    private BackgroundFactory backgroundFactory;
    private ButtonFactory buttonFactory;
    private AlertBox alertBox;

    public MainMenuScene(Stage window, GameEngine gameEngine) throws Exception {
        this.window = window;
        this.backgroundFactory = new LightBackgroundFactory();
        this.buttonFactory = new BrownButtonFactory();
        this.window.setTitle("Main Menu");

        Text t = new Text();
        t.setCache(true);
        t.setText("Main Menu");
        t.setFill(Color.web("#FFFFFF"));
        t.setFont(Font.font("Arial", FontWeight.BOLD, 60));
        t.setLayoutX(250);
        t.setLayoutY(230);
        Reflection r = new Reflection();
        r.setFraction(0.7f);
        t.setEffect(r);

        this.buttonFactory = new MenuButtonFactory();
        Button button11 = buttonFactory.createButton();
        button11.setText("Loan");
        button11.setOnAction(event -> {
//            try {
//                window.setScene(new LoanMainScene(window, gameEngine).getScene());
//                window.setTitle("Loan Page");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        });
        button11.setAlignment(Pos.CENTER);

        Button button12 = buttonFactory.createButton();
        button12.setText("Ships");
        button12.setOnAction(event -> {
//            try {
//                window.setScene(new ShipMainScene(window, gameEngine).getScene());
//                window.setTitle("Ship Page");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        });
        button12.setAlignment(Pos.CENTER);

        Button button13 = buttonFactory.createButton();
        button13.setText("Flight\nPlan");
        button13.setTextAlignment(TextAlignment.CENTER);

        button13.setOnAction(event -> {
//            try {
//                window.setScene(new FlightPlanMainScene(window, gameEngine).getScene());
//                window.setTitle("Flight Plan Page");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        });
        button13.setAlignment(Pos.CENTER);


        VBox buttonCol1 = new VBox(20);
        buttonCol1.getChildren().addAll(button11);
        buttonCol1.setAlignment(Pos.CENTER);

        VBox buttonCol2 = new VBox(20);
        buttonCol2.getChildren().addAll(button12);
        buttonCol2.setAlignment(Pos.CENTER);

        VBox buttonCol3 = new VBox(20);
        buttonCol3.getChildren().addAll(button13);
        buttonCol3.setAlignment(Pos.CENTER);

        HBox mainHBox = new HBox(20);
        mainHBox.getChildren().addAll(buttonCol1, buttonCol2, buttonCol3);
        mainHBox.setAlignment(Pos.CENTER);

        this.buttonFactory = new RedButtonFactory();
        Button buttonLogOut = buttonFactory.createButton();
        buttonLogOut.setText("Log Out");
        buttonLogOut.setOnAction(event -> {
//            this.alertBox = new LogoutSuccessfullyBox();
//            alertBox.createAlertBox(gameEngine.getUser());
//            try {
////                gameEngine.userLogOut();
//                window.setScene(new WelcomeScene(window, gameEngine).getScene());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        });


        BorderPane pane = new BorderPane();
        pane.getChildren().add(this.backgroundFactory.getBackground());

        pane.setTop(t);
        pane.setAlignment(t, Pos.BOTTOM_CENTER);

        pane.setCenter(mainHBox);
        pane.setAlignment(mainHBox, Pos.CENTER);
        pane.setVisible(true);

        pane.setBottom(buttonLogOut);
        pane.setAlignment(buttonLogOut, Pos.BOTTOM_RIGHT);

        this.scene = new Scene(pane, 1280, 800);
    }

    public Scene getScene() {
        return this.scene;
    }
}
