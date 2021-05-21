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
import javafx.stage.Stage;
import model.Entity;
import model.ErrorInfo;
import model.User;
import view.alertbox.AlertBox;
import view.alertbox.AvailableShipBox;
import view.alertbox.ErrorBox;
import view.alertbox.MyShipsBox;

import java.util.List;

import static javafx.scene.text.TextAlignment.CENTER;

public class ShipMainScene {
    private Stage window;
    private Scene scene;
    private BackgroundFactory backgroundFactory;
    private ButtonFactory buttonFactory;
    private AlertBox alertBox;

    public ShipMainScene(Stage window, GameEngine gameEngine) throws Exception {
        this.window = window;
        this.backgroundFactory = new LightBackgroundFactory();
        this.buttonFactory = new BrownButtonFactory();

        Text t = new Text();
        t.setCache(true);
        t.setText("Ship Page");
        t.setFill(Color.web("#FFFFFF"));
        t.setFont(Font.font("Arial", FontWeight.BOLD, 60));
        t.setLayoutX(250);
        t.setLayoutY(230);
        Reflection r = new Reflection();
        r.setFraction(0.7f);
        t.setEffect(r);

        this.buttonFactory = new MenuButtonFactory();
        Button button11 = buttonFactory.createButton();
        button11.setText("My Ships");
        button11.setOnAction(event -> {
            User user = gameEngine.getUser();
            List<Entity> shipList = gameEngine.getShips(user.getUsername(), user.getToken());
            if (shipList == null) {
                // Add error box
                ErrorInfo errorInfo = new ErrorInfo();
                errorInfo.setErrorCode(402);
                errorInfo.setMessage("You don’t own a spaceship right now");
                errorInfo.setRelatedModule("My Ships");
                this.alertBox = new ErrorBox();
                alertBox.createAlertBox(errorInfo);
                try {
                    window.setScene(new ShipMainScene(window, gameEngine).getScene());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("[ShipMainScene] My Ship list is null");
            } else {
                this.alertBox = new MyShipsBox();
                alertBox.createAlertBox(shipList);
                try {
                    window.setScene(new ShipMainScene(window, gameEngine).getScene());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        button11.setAlignment(Pos.CENTER);

        Button button12 = buttonFactory.createButton();
        button12.setText("Available\nShips");
        button12.setTextAlignment(CENTER);

        button12.setOnAction(event -> {
            User user = gameEngine.getUser();
            List<Entity> loanList = null;
            loanList = gameEngine.getAvailableShip(user.getToken(), "");
            if (loanList == null) {
                System.out.println("[LoanMainScene] The Available Loan list is null");
            } else {
                this.alertBox = new AvailableShipBox();
                alertBox.createAlertBox(loanList);
                try {
                    window.setScene(new ShipMainScene(window, gameEngine).getScene());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        button12.setAlignment(Pos.CENTER);

        Button button13 = buttonFactory.createButton();
        button13.setText("Buy\nA\nShip");
        button13.setTextAlignment(CENTER);
        button13.setOnAction(event -> {
            try {
                window.setScene(new BuyShipScene(window, gameEngine).getScene());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        button13.setAlignment(Pos.CENTER);

        Button button21 = buttonFactory.createButton();
        button21.setText("Find\nA Ship\nBy ID");
        button21.setTextAlignment(CENTER);
        button21.setOnAction(event -> {
            try {
                window.setScene(new FindAShipScene(window, gameEngine).getScene());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        button21.setAlignment(Pos.CENTER);

        Button button22 = buttonFactory.createButton();
        button22.setText("Buy Goods");
        button22.setTextAlignment(CENTER);

        button22.setOnAction(event -> {
            try {
                window.setScene(new BuyGoodsScene(window, gameEngine).getScene());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        button22.setAlignment(Pos.CENTER);

        Button button23 = buttonFactory.createButton();
        button23.setText(" ");
        button23.setTextAlignment(CENTER);

        button23.setOnAction(event -> {

        });
        button23.setAlignment(Pos.CENTER);

        VBox buttonCol1 = new VBox(20);
        buttonCol1.getChildren().addAll(button11, button21);
        buttonCol1.setAlignment(Pos.CENTER);

        VBox buttonCol2 = new VBox(20);
        buttonCol2.getChildren().addAll(button12, button22);
        buttonCol2.setAlignment(Pos.CENTER);

        VBox buttonCol3 = new VBox(20);
        buttonCol3.getChildren().addAll(button13, button23);
        buttonCol3.setAlignment(Pos.CENTER);

        HBox mainHBox = new HBox(20);
        mainHBox.getChildren().addAll(buttonCol1, buttonCol2, buttonCol3);
        mainHBox.setAlignment(Pos.CENTER);

        this.buttonFactory = new RedButtonFactory();
        Button buttonBack = buttonFactory.createButton();
        buttonBack.setText("< BACK");
        buttonBack.setOnAction(event -> {
            try {
                window.setScene(new MainMenuScene(window, gameEngine).getScene());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
//        buttonBack.setAlignment(Pos.BOTTOM_LEFT);

        BorderPane pane = new BorderPane();

        pane.getChildren().add(this.backgroundFactory.getBackground());

        pane.setTop(t);
        pane.setAlignment(t, Pos.BOTTOM_CENTER);

        pane.setBottom(buttonBack);
        pane.setAlignment(buttonBack, Pos.BOTTOM_LEFT);

        pane.setCenter(mainHBox);
        pane.setAlignment(mainHBox, Pos.CENTER);
        pane.setVisible(true);



        this.scene = new Scene(pane, 1280, 800);
    }

    public Scene getScene() {
        return this.scene;
    }
}
