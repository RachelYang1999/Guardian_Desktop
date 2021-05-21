package view.alertbox;//package view.alertbox;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//import model.User;
//import utility.ClipboardUtil;
//
//public class ConcreteAlertBox {
//    Stage window;
//
//    public ConcreteAlertBox() {
//        this.window = new Stage();
//        //modality要使用Modality.APPLICATION_MODEL
//        window.initModality(Modality.APPLICATION_MODAL);
//        window.setMinWidth(450);
//        window.setMinHeight(300);
//    }
//
////    public void loginUnsuccessfullyWindow(User user){
////        window.setTitle("Auth Warning");
////
////        Button button = new Button("Try Again");
////        button.setOnAction(e -> window.close());
////        button.setFont(Font.font("Arial", FontWeight.BOLD, 15));
////
////        String warningMessage = "The username or token is incorrect";
////        Label label = new Label(warningMessage);
////        label.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
////
////        VBox layout = new VBox(10);
////        layout.getChildren().addAll(label , button);
////        layout.setAlignment(Pos.CENTER);
////
////        Scene scene = new Scene(layout);
////        window.setScene(scene);
////        // The user cannot do anything till he or she close the alert window
////        window.showAndWait();
////    }
//
////    public void loginSuccessfullyWindow(User user){
////        window.setTitle("User Information");
////
////        Button button = new Button("Got It!");
////        button.setOnAction(e -> window.close());
////        button.setFont(Font.font("Arial", FontWeight.BOLD, 15));
////
////        String userInfo = "Username: " + user.getUsername() + "\n" +
////                "Token: " + user.getToken() + "\n" +
////                "Loans:" + "\n" +
////                "Credits: " + user.getCredit() + "\n" +
////                "Ships: " + "" + "\n";
////        Label label = new Label(userInfo);
////        label.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
////
////        VBox layout = new VBox(10);
////        layout.getChildren().addAll(label , button);
////        layout.setAlignment(Pos.CENTER);
////
////        Scene scene = new Scene(layout);
////        window.setScene(scene);
////        // The user cannot do anything till he or she close the alert window
////        window.showAndWait();
////    }
//
////    public void signUpSuccessfullyWindow(User user){
////        window.setTitle("Sign Up Successfully");
////
////        Button gotButton = new Button("Got It!");
////        gotButton.setOnAction(e -> window.close());
////        gotButton.setFont(Font.font("Arial", FontWeight.BOLD, 15));
////
////        Button copyButton = new Button("Copy Token");
////        copyButton.setOnAction(e -> {
////            ClipboardUtil clipboardUtil = new ClipboardUtil();
////            clipboardUtil.setClipboardString(user.getToken());
////            String copiedText = clipboardUtil.getClipboardString();
////            System.out.println("Copied text to clipboard: " + copiedText);
////        });
////
////        copyButton.setFont(Font.font("Arial", FontWeight.BOLD, 15));
////
////        HBox buttonHBox = new HBox(10);
////        buttonHBox.getChildren().addAll(copyButton, gotButton);
////        buttonHBox.setAlignment(Pos.CENTER);
////
////        String userInfo =
////                "Username: " + user.getUsername() + "\n" +
////                "Token: " + user.getToken() + "\n";
////        Label label = new Label(userInfo);
////        label.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
////
////        VBox layout = new VBox(10);
////        layout.getChildren().addAll(label, buttonHBox);
////        layout.setAlignment(Pos.CENTER);
////
////        Scene scene = new Scene(layout);
////        window.setScene(scene);
////        // The user cannot do anything till he or she close the alert window
////        window.showAndWait();
////    }
//
////    public void signUpUnsuccessfullyWindow(User user){
////        window.setTitle("Warning");
////
////        Button button = new Button("Got It!");
////        button.setOnAction(e -> window.close());
////        button.setFont(Font.font("Arial", FontWeight.BOLD, 15));
////
////        String userInfo = "The username has already been used, please change another one";
////        Label label = new Label(userInfo);
////        label.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
////
////        VBox layout = new VBox(10);
////        layout.getChildren().addAll(label , button);
////        layout.setAlignment(Pos.CENTER);
////
////        Scene scene = new Scene(layout);
////        window.setScene(scene);
////        // The user cannot do anything till he or she close the alert window
////        window.showAndWait();
////    }
//}
