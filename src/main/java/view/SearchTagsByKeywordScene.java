package view;

import factory.backgroundfactory.BackgroundFactory;
import factory.backgroundfactory.LightBackgroundFactory;
import factory.buttonfactory.BrownButtonFactory;
import factory.buttonfactory.ButtonFactory;
import factory.buttonfactory.GrayButtonFactory;
import javafx.concurrent.Task;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.domain.Entity;
import model.domain.ErrorInfo;
import util.RequestMapping;
import view.alertbox.AlertBox;
import view.alertbox.ErrorResponseBox;
import view.alertbox.ResponseBox;

import java.util.List;

public class SearchTagsByKeywordScene {
  private Stage window;
  private Scene scene;
  private BackgroundFactory backgroundFactory;
  private ButtonFactory buttonFactory;
  private AlertBox alertBox;

  public SearchTagsByKeywordScene(Stage window, RequestMapping requestMapping) throws Exception {
    this.window = window;
    this.backgroundFactory = new LightBackgroundFactory();
    this.buttonFactory = new BrownButtonFactory();

    Text t = new Text();
    t.setCache(true);
    t.setText("Search Tags By Keyword");
    t.setFill(Color.web("#704728"));
    t.setFont(Font.font("Arial", FontWeight.BOLD, 70));
    t.setLayoutX(250);
    t.setLayoutY(230);
    Reflection r = new Reflection();
    r.setFraction(0.7f);
    t.setEffect(r);

    //        Label tagLabel = new Label();

    TextField tagTextField = new TextField("Input the keyword here");
    //        tagTextField.setStyle("-fx-text-fill:#777777");
    tagTextField.setPrefHeight(40);
    tagTextField.setPrefWidth(600);
    tagTextField.setMaxWidth(600);
    tagTextField.setFont(Font.font("Arial", FontWeight.BOLD, 20));

    Button searchOnePage = buttonFactory.createButton();
    searchOnePage.setFont(Font.font("Arial", FontWeight.BOLD, 20));
    searchOnePage.setText("Search 1x\nDefault Page Size\nResults From API");
    searchOnePage.setOnAction(
            event -> {
              String inputKeyword = tagTextField.getText();
              try {
                if (inputKeyword.equals("Input the keyword here")) {
                  ErrorInfo errorInfo = new ErrorInfo();
                  errorInfo.setMessage("Please don't input empty character");
                  this.alertBox = new ErrorResponseBox();
                  alertBox.createAlertBox(errorInfo);
                } else {

                  Task<List<Entity>> task = new Task<List<Entity>>() {
                    @Override
                    protected List<Entity> call() throws Exception {
                      return requestMapping.searchOnePageTagsByKeyword(requestMapping.getUser().getToken(), inputKeyword);
                    }

                    @Override
                    protected void succeeded() {
                      try {
                        window.setScene(new TagsResultScene(
                                window,
                                requestMapping,
                                inputKeyword,
                                getValue()).getScene());
                      } catch (Exception e) {
                        e.printStackTrace();
                      }

                    }

                  };
                  new Thread(task).start();

                }
              } catch (Exception e) {
                e.printStackTrace();
              }
            });
    searchOnePage.setTextAlignment(TextAlignment.CENTER);

    Button searchButton = buttonFactory.createButton();
    searchButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
    searchButton.setText("Search\nAll Results\nFrom API");
    searchButton.setOnAction(
        event -> {
          String inputKeyword = tagTextField.getText();
          try {
            if (inputKeyword.equals("Input the keyword here")) {
              ErrorInfo errorInfo = new ErrorInfo();
              errorInfo.setMessage("Please don't input empty character");
              this.alertBox = new ErrorResponseBox();
              alertBox.createAlertBox(errorInfo);
            } else {
//              List<Entity> returnedTags = requestMapping.searchAllTagsByKeyword(requestMapping.getUser().getToken(), inputKeyword);

              Task<List<Entity>> task = new Task<List<Entity>>() {
                @Override
                protected List<Entity> call() throws Exception {
                    return requestMapping.searchAllTagsByKeyword(requestMapping.getUser().getToken(), inputKeyword);
                }

                @Override
                protected void running() {
                  AlertBox alertBox = new ResponseBox();
                  alertBox.createAlertBox("Processing", "Please wait", "The data volume is too large.\n" +
                          "You will be directed to the result page once the system finish processing");
                }

                @Override
                protected void succeeded() {
                  try {
                    window.setScene(new TagsResultScene(
                            window,
                            requestMapping,
                            inputKeyword,
                            getValue()).getScene());
                  } catch (Exception e) {
                    e.printStackTrace();
                  }

                }

              };
              new Thread(task).start();

            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        });
    searchButton.setTextAlignment(TextAlignment.CENTER);

    Button searchCachedButton = buttonFactory.createButton();
    searchCachedButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
    searchCachedButton.setText("Search\nIn Local\nDatabase");
    searchCachedButton.setTextAlignment(TextAlignment.CENTER);
    searchCachedButton.setOnAction(
        event -> {
          String inputKeyword = tagTextField.getText();
          System.out.println("inputTag is: " + inputKeyword);
          try {
            if (inputKeyword.equals("Input the keyword here")) {
              ErrorInfo errorInfo = new ErrorInfo();
              errorInfo.setMessage("Please don't input empty character");
              this.alertBox = new ErrorResponseBox();
              alertBox.createAlertBox(errorInfo);
            } else {
              Task<List<Entity>> task = new Task<List<Entity>>() {
                @Override
                protected List<Entity> call() throws Exception {
                  return requestMapping.searchCachedTagsByKeyword(requestMapping.getUser().getToken(), inputKeyword);
                }

                @Override
                protected void succeeded() {
                  try {
                    window.setScene(
                            new CachedTagResultScene(
                                    window,
                                    requestMapping,
                                    inputKeyword, getValue()).getScene());
                    window.setTitle("Search Result");
                  } catch (Exception e) {
                    e.printStackTrace();
                  }

                }

              };
              new Thread(task).start();
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        });


    this.buttonFactory = new GrayButtonFactory();
    Button backButton = buttonFactory.createButton();
    backButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
    backButton.setText(" \nBack\n ");
    backButton.setOnAction(
        event -> {
          try {
            window.setScene(new MainMenuScene(window, requestMapping).getScene());
            window.setTitle("Welcome");
          } catch (Exception e) {
            e.printStackTrace();
          }
        });
    backButton.setTextAlignment(TextAlignment.CENTER);

    VBox textFieldBox = new VBox(15);
    textFieldBox.getChildren().addAll(tagTextField);
    textFieldBox.setAlignment(Pos.CENTER);

    HBox buttonBox = new HBox(15);
    buttonBox.getChildren().addAll(searchOnePage, searchButton, searchCachedButton);
    buttonBox.setAlignment(Pos.CENTER);
    HBox backButtonBox = new HBox(15);
    backButtonBox.getChildren().addAll(backButton);
    backButtonBox.setAlignment(Pos.CENTER);

    VBox totalForm = new VBox(20);
    totalForm.getChildren().addAll(textFieldBox, buttonBox, backButtonBox);
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
