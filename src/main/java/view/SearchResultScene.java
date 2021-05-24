package view;

import facade.EngineFacade;
import factory.backgroundfactory.BackgroundFactory;
import factory.backgroundfactory.LightBackgroundFactory;
import factory.buttonfactory.BrownButtonFactory;
import factory.buttonfactory.ButtonFactory;
import factory.buttonfactory.GrayButtonFactory;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import view.alertbox.AlertBox;

public class SearchResultScene {
    private Stage window;
    private Scene scene;
    private BackgroundFactory backgroundFactory;
    private ButtonFactory buttonFactory;
    private AlertBox alertBox;

    public int itemsPerPage() {
        return 15;
    }

    public SearchResultScene(Stage window, EngineFacade gameEngine) throws Exception {
        this.window = window;
        this.backgroundFactory = new LightBackgroundFactory();
        this.buttonFactory = new BrownButtonFactory();

        Text t = new Text();
        t.setCache(true);
        t.setText("Search By Tag");
        t.setFill(Color.web("#704728"));
        t.setFont(Font.font("Arial", FontWeight.BOLD, 70));
        t.setLayoutX(250);
        t.setLayoutY(230);
        Reflection r = new Reflection();
        r.setFraction(0.7f);
        t.setEffect(r);

//        Label tagLabel = new Label();

        TextField tagTextField = new TextField("Input the tag here");
//        tagTextField.setStyle("-fx-text-fill:#777777");
        tagTextField.setPrefHeight(40);
        tagTextField.setPrefWidth(600);
        tagTextField.setMaxWidth(600);
        tagTextField.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Button searchButton = buttonFactory.createButton();
        searchButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        searchButton.setText("Search");
        searchButton.setOnAction(event -> {
            String inputTag = tagTextField.getText();

        });

        this.buttonFactory = new GrayButtonFactory();
        Button backButton = buttonFactory.createButton();
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        backButton.setText("Back");
        backButton.setOnAction(event -> {
            try {
                window.setScene(new MainMenuScene(window, gameEngine).getScene());
                window.setTitle("Welcome");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        VBox textFieldBox = new VBox(15);
        textFieldBox.getChildren().addAll(tagTextField);
        textFieldBox.setAlignment(Pos.CENTER);

        HBox buttonBox = new HBox(15);
        buttonBox.getChildren().addAll(searchButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox totalForm = new VBox(20);
        totalForm.getChildren().addAll(textFieldBox, buttonBox);
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
