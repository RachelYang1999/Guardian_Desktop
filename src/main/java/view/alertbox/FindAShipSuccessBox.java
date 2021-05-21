package view.alertbox;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Entity;
import utility.ClipboardUtil;

import java.util.List;

public class FindAShipSuccessBox extends AlertBox {

    @Override
    public void createAlertBox(Entity entity) {
        String loanInfo = entity.getEntityInformation();
        System.out.println("Find ship successfully");
        window.setTitle("Find Ship Successfully");
        Button gotButton = new Button("Got It!");
        gotButton.setOnAction(e -> window.close());
        gotButton.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        Button copyButton = new Button("Copy Ship Information");

        String finalLoanInfo = loanInfo;
        copyButton.setOnAction(e -> {
            ClipboardUtil clipboardUtil = new ClipboardUtil();
            clipboardUtil.setClipboardString(finalLoanInfo);
            String copiedText = clipboardUtil.getClipboardString();
            System.out.println("Copied text to clipboard: " + copiedText);
        });

        copyButton.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        HBox buttonHBox = new HBox(10);
        buttonHBox.getChildren().addAll(copyButton, gotButton);
        buttonHBox.setAlignment(Pos.CENTER);

        Text text = new Text(loanInfo);
        text.setFont(Font.font("Arial", FontWeight.NORMAL, 20));


        VBox layout = new VBox(10);
        layout.getChildren().addAll(text, buttonHBox);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        // The user cannot do anything till he or she close the alert window
        window.showAndWait();
    }

    @Override
    public void createAlertBox(List<Entity> entity) {
        System.out.println("??????????");

    }
}
