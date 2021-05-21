package view;

import engine.GameEngine;
import factory.backgroundfactory.BackgroundFactory;
import factory.backgroundfactory.DeepBackgroundFactory;
import factory.buttonfactory.BrownButtonFactory;
import factory.buttonfactory.ButtonFactory;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
import model.ExistShip;
import model.User;
import view.alertbox.AlertBox;
import view.alertbox.BuyShipSuccessBox;
import view.alertbox.ErrorBox;

public class BuyShipScene {
    private Stage window;
    private Scene scene;
    private BackgroundFactory backgroundFactory;
    private ButtonFactory buttonFactory;
    private AlertBox alertBox;

    public BuyShipScene(Stage window, GameEngine gameEngine) throws Exception {
        this.window = window;
        this.backgroundFactory = new DeepBackgroundFactory();
        this.buttonFactory = new BrownButtonFactory();

        Button buttonVisitor = buttonFactory.createButton();
        buttonVisitor.setText("I AM A VISITOR");
        buttonVisitor.setPrefWidth(250);
        buttonVisitor.setLayoutX(328);
        buttonVisitor.setLayoutY(630);

        Text t = new Text();
        t.setCache(true);
        t.setText("Buy A Ship Here!");
        t.setFill(Color.web("#FFFFFF"));
        t.setFont(Font.font("Arial", FontWeight.BOLD, 70));
        t.setLayoutX(250);
        t.setLayoutY(230);
        Reflection r = new Reflection();
        r.setFraction(0.7f);
        t.setEffect(r);

        Label shipLocationLabel = new Label("Ship Location");
        TextField shipLocationTextField = new TextField("OE-PM-TR");
        shipLocationLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        shipLocationTextField.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        shipLocationTextField.setMaxWidth(250);
//        HBox userNameHBox = new HBox();
//        userNameHBox.getChildren().addAll(shipLocationLabel, shipLocationTextField);
//        userNameHBox.setAlignment(Pos.CENTER);


        Label shipTypeLabel = new Label("Ship Type");
        TextField shipTypeTextField = new TextField("EM-MK-I");
        shipTypeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        shipTypeTextField.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        shipTypeTextField.setMaxWidth(250);
//        HBox tokenHBox = new HBox();
//        tokenHBox.getChildren().addAll(shipTypeLabel, shipTypeTextField);
//        tokenHBox.setAlignment(Pos.CENTER);

        Button buyButton = buttonFactory.createButton();
        buyButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        buyButton.setText("BUY IT");
        buyButton.setOnAction(event -> {
            String inputShipLocation = shipLocationTextField.getText();
            String inputShipType = shipTypeTextField.getText();

            User currentUser =  gameEngine.getUser();
            ExistShip existShip = null;
            ErrorInfo errorInfo = null;

            Entity returnedEntity = gameEngine.buyShip(
                    currentUser.getUsername(),
                    currentUser.getToken(),
                    inputShipLocation,
                    inputShipType);

            if (returnedEntity == null) {
                System.err.println("[ShipMainScene] Buy A Loan Fail\n No returnedEntity");
            } else {
                if (returnedEntity.getEntityType().equals("ExistShip")) {
                    existShip = (ExistShip) returnedEntity;
//                        System.out.println("Before adding loan manually: ");
//                        System.out.println(gameEngine.getUser().getEntityInformation());
//                        gameEngine.getUser().addLoan(loan);
//                        System.out.println("After adding loan manually: ");
//                        System.out.println(gameEngine.getUser().getEntityInformation());
                    this.alertBox = new BuyShipSuccessBox();
                    alertBox.createAlertBox(existShip);
                    try {
                        window.setScene(new ShipMainScene(window, gameEngine).getScene());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (returnedEntity.getEntityType().equals("ErrorInfo")) {
                    assert returnedEntity instanceof ErrorInfo;
                    errorInfo = (ErrorInfo) returnedEntity;
                    this.alertBox = new ErrorBox();
                    alertBox.createAlertBox(errorInfo);
                    try {
                        window.setScene(new ShipMainScene(window, gameEngine).getScene());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Button backButton = buttonFactory.createButton();
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        backButton.setText("Back");
        backButton.setOnAction(event -> {
            try {
                window.setScene(new ShipMainScene(window, gameEngine).getScene());
                window.setTitle("Ship Page");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        VBox labelBox = new VBox(20);
        labelBox.getChildren().addAll(shipLocationLabel, shipTypeLabel);
        labelBox.setAlignment(Pos.CENTER_RIGHT);

        VBox textFieldBox = new VBox(15);
        textFieldBox.getChildren().addAll(shipLocationTextField, shipTypeTextField);
        textFieldBox.setAlignment(Pos.CENTER_LEFT);

        HBox hBox = new HBox(3);
        hBox.getChildren().addAll(labelBox, textFieldBox);
        hBox.setAlignment(Pos.CENTER);

        HBox buttonBox = new HBox(15);
        buttonBox.getChildren().addAll(buyButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox totalForm = new VBox(20);
        totalForm.getChildren().addAll(hBox, buttonBox);
        totalForm.setAlignment(Pos.CENTER);

        BorderPane pane = new BorderPane();
        pane.getChildren().add(this.backgroundFactory.getBackground());
        pane.setTop(t);
        pane.setAlignment(t, Pos.BOTTOM_CENTER);

        pane.setCenter(totalForm);
        pane.setAlignment(totalForm, Pos.CENTER);

        this.scene = new Scene(pane, 1280, 800);
    }

    public Scene getScene() {
        return this.scene;
    }
}
