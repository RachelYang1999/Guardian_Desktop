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
import model.Response;
import model.User;
import view.alertbox.AlertBox;
import view.alertbox.ErrorBox;
import view.alertbox.ResponseBox;

public class BuyFuelScene {
    private Stage window;
    private Scene scene;
    private BackgroundFactory backgroundFactory;
    private ButtonFactory buttonFactory;
    private AlertBox alertBox;

    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }


    public BuyFuelScene(Stage window, GameEngine gameEngine) throws Exception {
        this.window = window;
        this.backgroundFactory = new DeepBackgroundFactory();
        this.buttonFactory = new BrownButtonFactory();

        Text t = new Text();
        t.setCache(true);
        t.setText("Buy Fuel Here!");
        t.setFill(Color.web("#FFFFFF"));
        t.setFont(Font.font("Arial", FontWeight.BOLD, 70));
        t.setLayoutX(250);
        t.setLayoutY(230);
        Reflection r = new Reflection();
        r.setFraction(0.7f);
        t.setEffect(r);

        Label shipIDLabel = new Label("Ship ID");
        TextField shipIDTextField = new TextField("ckoqtx6ed765915s6lt4f8wou");
        shipIDLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        shipIDTextField.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        shipIDTextField.setMaxWidth(250);
//        HBox userNameHBox = new HBox();
//        userNameHBox.getChildren().addAll(shipLocationLabel, shipLocationTextField);
//        userNameHBox.setAlignment(Pos.CENTER);


        Label quantityLabel = new Label("Quantity");
        TextField quantityTextField = new TextField("1");
        quantityLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        quantityTextField.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        quantityTextField.setMaxWidth(250);
//        HBox tokenHBox = new HBox();
//        tokenHBox.getChildren().addAll(shipTypeLabel, shipTypeTextField);
//        tokenHBox.setAlignment(Pos.CENTER);

        Button buyButton = buttonFactory.createButton();
        buyButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        buyButton.setText("BUY IT");
        buyButton.setOnAction(event -> {
            String shipID = shipIDTextField.getText();
            String quantity = quantityTextField.getText();

            User currentUser =  gameEngine.getUser();
            Response response = null;
            ErrorInfo errorInfo = null;

            Entity returnedEntity = gameEngine.buyFuel(
                    currentUser.getUsername(),
                    currentUser.getToken(),
                    shipID,
                    quantity);

            if (returnedEntity == null) {
                System.err.println("[BuyFuelScene] Buy A Loan Fail\n No returnedEntity");
            } else {
                if (returnedEntity.getEntityType().equals("Response")) {
                    response = (Response) returnedEntity;

                    this.alertBox = new ResponseBox();
                    alertBox.createAlertBox(response);
                    try {
                        window.setScene(new BuyGoodsScene(window, gameEngine).getScene());
                        window.setTitle("Buy Good");
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
        labelBox.getChildren().addAll(shipIDLabel, quantityLabel);
        labelBox.setAlignment(Pos.CENTER_RIGHT);

        VBox textFieldBox = new VBox(15);
        textFieldBox.getChildren().addAll(shipIDTextField, quantityTextField);
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
