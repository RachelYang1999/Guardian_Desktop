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
import model.*;
import view.alertbox.*;

import java.io.IOException;
import java.util.List;

import static javafx.scene.text.TextAlignment.CENTER;

public class LoanMainScene {
    private Stage window;
    private Scene scene;
    private BackgroundFactory backgroundFactory;
    private ButtonFactory buttonFactory;
    private AlertBox alertBox;

    public LoanMainScene(Stage window, GameEngine gameEngine) throws Exception {
        this.window = window;
        this.backgroundFactory = new LightBackgroundFactory();
        this.buttonFactory = new BrownButtonFactory();

        Text t = new Text();
        t.setCache(true);
        t.setText("Loan Page");
        t.setFill(Color.web("#FFFFFF"));
        t.setFont(Font.font("Arial", FontWeight.BOLD, 60));
        t.setLayoutX(250);
        t.setLayoutY(230);
        Reflection r = new Reflection();
        r.setFraction(0.7f);
        t.setEffect(r);

        this.buttonFactory = new MenuButtonFactory();
        Button button11 = buttonFactory.createButton();
        button11.setText("My Loan");
        button11.setOnAction(event -> {
            User user = gameEngine.getUser();
            List<Entity> loanList = null;


            try {
                loanList = gameEngine.getMyLoan(user.getUsername(), user.getToken());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (loanList == null) {
                this.alertBox = new LoginUnsuccessfullyBox();
                alertBox.createAlertBox(loanList);
                System.out.println("[LoanMainScene] The Loan list is null");
            } else {
                this.alertBox = new MyLoanBox();
                alertBox.createAlertBox(loanList);
                try {
                    window.setScene(new LoanMainScene(window, gameEngine).getScene());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        button11.setAlignment(Pos.CENTER);

        Button button12 = buttonFactory.createButton();
        button12.setText("Available\nLoan");
        button12.setTextAlignment(CENTER);

        button12.setOnAction(event -> {
            User user = gameEngine.getUser();
            AvailableLoan loan = null;
            ErrorInfo errorInfo = null;
            List<Entity> loanList = null;
            try {
                List<Entity> returnedEntity = gameEngine.getAvailableLoan(user.getToken());
                if (returnedEntity.size() == 0) {
                    System.err.println("[LoanMainScene] Request A Loan Fail\n No returnedEntity");
                } else {
                    if (returnedEntity.get(0).getEntityType().equals("AvailableLoan")) {
                        loan = (AvailableLoan) returnedEntity.get(0);
                        this.alertBox = new AvailableLoanBox();
                        alertBox.createAlertBox(returnedEntity);
                        try {
                            window.setScene(new LoanMainScene(window, gameEngine).getScene());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (returnedEntity.get(0).getEntityType().equals("ErrorInfo")) {
                        assert returnedEntity.get(0) instanceof ErrorInfo;
                        errorInfo = (ErrorInfo) returnedEntity.get(0);
                        this.alertBox = new ErrorBox();
                        alertBox.createAlertBox(errorInfo);
                        try {
                            window.setScene(new LoanMainScene(window, gameEngine).getScene());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        button12.setAlignment(Pos.CENTER);

        Button button21 = buttonFactory.createButton();
        button21.setText("Request\nA\nLoan");
        button21.setTextAlignment(CENTER);
        button21.setOnAction(event -> {
            User user = gameEngine.getUser();
            ExistLoan loan = null;
            ErrorInfo errorInfo = null;
            try {
                Entity returnedEntity = gameEngine.requestNewLoan(user.getUsername(), user.getToken());
                if (returnedEntity == null) {
                    System.err.println("[LoanMainScene] Request A Loan Fail\n No returnedEntity");
                } else {
                    if (returnedEntity.getEntityType().equals("ExistLoan")) {
                        loan = (ExistLoan) returnedEntity;
                        System.out.println("Before adding loan manually: ");
                        System.out.println(gameEngine.getUser().getEntityInformation());
                        gameEngine.getUser().addLoan(loan);
                        System.out.println("After adding loan manually: ");
                        System.out.println(gameEngine.getUser().getEntityInformation());

                        this.alertBox = new RequestLoanBox();
                        alertBox.createAlertBox(loan);
                        try {
                            window.setScene(new LoanMainScene(window, gameEngine).getScene());
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
                            window.setScene(new LoanMainScene(window, gameEngine).getScene());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        button21.setAlignment(Pos.CENTER);

        Button button22 = buttonFactory.createButton();
        button22.setText("Pay Off");
        button22.setOnAction(event -> {
            User user = gameEngine.getUser();
            ExistLoan loan = null;
            ErrorInfo errorInfo = null;
            try {
                Entity returnedEntity = null;
                if (user.getLoans().size() != 0) {
                    returnedEntity = gameEngine.payOffLoan(user.getUsername(), user.getToken(), user.getLoans().get(0).getId());
                } else {
                    returnedEntity = gameEngine.payOffLoan(user.getUsername(), user.getToken(), "");
                }
                if (returnedEntity == null) {
                    System.err.println("[LoanMainScene] Request A Loan Fail\n No returnedEntity");
                } else {
                    /*
                    ------------------------------------------------------------------------------------------------------------------------
                    Need further modification since IDK what's the response look like after paying off a loan successfully lol
                     */
                    if (returnedEntity.getEntityType().equals("ExistLoan")) {
                        loan = (ExistLoan) returnedEntity;
                        this.alertBox = new RequestLoanBox();
                        alertBox.createAlertBox(loan);
                        try {
                            window.setScene(new LoanMainScene(window, gameEngine).getScene());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    /*
                    ------------------------------------------------------------------------------------------------------------------------
                     */
                    if (returnedEntity.getEntityType().equals("ErrorInfo")) {
                        assert returnedEntity instanceof ErrorInfo;
                        errorInfo = (ErrorInfo) returnedEntity;
                        this.alertBox = new ErrorBox();
                        alertBox.createAlertBox(errorInfo);
                        try {
                            window.setScene(new LoanMainScene(window, gameEngine).getScene());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        button22.setAlignment(Pos.CENTER);

        VBox buttonCol1 = new VBox(20);
        buttonCol1.getChildren().addAll(button11, button21);
        buttonCol1.setAlignment(Pos.CENTER);

        VBox buttonCol2 = new VBox(20);
        buttonCol2.getChildren().addAll(button12, button22);
        buttonCol2.setAlignment(Pos.CENTER);

        HBox mainHBox = new HBox(20);
        mainHBox.getChildren().addAll(buttonCol1, buttonCol2);
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
