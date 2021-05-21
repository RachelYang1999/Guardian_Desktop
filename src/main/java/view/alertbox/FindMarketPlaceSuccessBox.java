package view.alertbox;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Entity;
import utility.ClipboardUtil;

import java.util.List;

public class FindMarketPlaceSuccessBox extends AlertBox {

    @Override
    public void createAlertBox(Entity entity) {
        String shipInfo = entity.getEntityInformation();

        window.setTitle("Market Location Information");
        Button gotButton = new Button("Got It!");
        gotButton.setOnAction(e -> window.close());
        gotButton.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        Button copyButton = new Button("Copy Information");

        String finalShipInfo = shipInfo;
        copyButton.setOnAction(e -> {
            ClipboardUtil clipboardUtil = new ClipboardUtil();
            clipboardUtil.setClipboardString(finalShipInfo);
            String copiedText = clipboardUtil.getClipboardString();
            System.out.println("Copied text to clipboard: " + copiedText);
        });

        copyButton.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        Text info = new Text(finalShipInfo);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(info);
        scrollPane.setMaxHeight(800);
        scrollPane.setMaxWidth(800);

        HBox buttonHBox = new HBox(10);
        buttonHBox.getChildren().addAll(copyButton, gotButton);
        buttonHBox.setAlignment(Pos.CENTER);

        Text text = new Text(finalShipInfo);
        text.setFont(Font.font("Arial", FontWeight.NORMAL, 20));

        Scene scene = new Scene(new Group());

        VBox root = new VBox();
        root.getChildren().addAll(scrollPane, buttonHBox);
        scene.setRoot(root);

        window.setScene(scene);

        // The user cannot do anything till he or she close the alert window
        window.showAndWait();
    }

    @Override
    public void createAlertBox(List<Entity> entity) {
        String shipInfo = "";

        for (Entity e : entity) {
            shipInfo += e.getEntityInformation();
        }
        window.setTitle("Market Location Information");
        Button gotButton = new Button("Got It!");
        gotButton.setOnAction(e -> window.close());
        gotButton.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        Button copyButton = new Button("Copy Information");

        String finalShipInfo = shipInfo;
        copyButton.setOnAction(e -> {
            ClipboardUtil clipboardUtil = new ClipboardUtil();
            clipboardUtil.setClipboardString(finalShipInfo);
            String copiedText = clipboardUtil.getClipboardString();
            System.out.println("Copied text to clipboard: " + copiedText);
        });

        copyButton.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        Text info = new Text(finalShipInfo);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(info);
        scrollPane.setMaxHeight(800);
        scrollPane.setMaxWidth(800);

        HBox buttonHBox = new HBox(10);
        buttonHBox.getChildren().addAll(copyButton, gotButton);
        buttonHBox.setAlignment(Pos.CENTER);

        Text text = new Text(finalShipInfo);
        text.setFont(Font.font("Arial", FontWeight.NORMAL, 20));

        Scene scene = new Scene(new Group());

        VBox root = new VBox();
        root.getChildren().addAll(scrollPane, buttonHBox);
        scene.setRoot(root);

        window.setScene(scene);

        // The user cannot do anything till he or she close the alert window
        window.showAndWait();
    }
}
