package view;

import factory.backgroundfactory.BackgroundFactory;
import factory.backgroundfactory.LightBackgroundFactory;
import factory.buttonfactory.BrownButtonFactory;
import factory.buttonfactory.ButtonFactory;
import factory.buttonfactory.GrayButtonFactory;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.domain.Article;
import model.domain.Entity;
import util.RequestMapping;
import view.alertbox.AlertBox;
import view.alertbox.ResponseBoxWithPastebin;

import java.util.List;

public class ArticlesResultScene {
  private Stage window;
  private Scene scene;
  private BackgroundFactory backgroundFactory;
  private ButtonFactory buttonFactory;
  private AlertBox alertBox;

  //    List<Entity> entities = new ArticleService(new
  // GuardianOnlineAPIStrategy()).getAllArticles("1b0f84fb-9674-4fe2-b596-5836b2772fcb", "gay
  // couple");
  private List<Entity> returnedArticles;

  public int itemsPerPage() {
    return 7;
  }

  public ArticlesResultScene(Stage window, RequestMapping requestMapping, String tag, List<Entity> returnedArticles)
      throws Exception {
    this.window = window;
    this.backgroundFactory = new LightBackgroundFactory();
    this.buttonFactory = new BrownButtonFactory();

    this.returnedArticles = returnedArticles;
//            requestMapping.searchAllArticlesByTag(requestMapping.getUser().getToken(), tag);

    Text t = new Text();
    t.setCache(true);
    t.setText("Article Result");
    t.setFill(Color.web("#704728"));
    t.setFont(Font.font("Arial", FontWeight.BOLD, 60));

    Reflection r = new Reflection();
    r.setFraction(0.7f);
    t.setEffect(r);

    Label searchResultInfo = new Label();
    searchResultInfo.setText(
        "There are "
            + returnedArticles.size()
            + " articles found "
            + "\n"
            + "with the tag \""
            + tag
            + "\":");
    searchResultInfo.setFont(Font.font("Arial", FontWeight.BOLD, 20));

    Pagination pagination =
        new Pagination((int) Math.ceil((double) returnedArticles.size() / itemsPerPage()), 0);
    pagination.setStyle("-fx-font-family: Arial");
    if (returnedArticles.size() == 0) {
      pagination = new Pagination(0, 0);
    }

    pagination.setPageFactory(
        (Integer p) -> {
          VBox box = new VBox(5);
          box.getChildren().add(searchResultInfo);
          int page = p * itemsPerPage();
          for (int i = page; i < page + itemsPerPage() && i < returnedArticles.size(); i++) {
            Entity currentEntity = returnedArticles.get(i);
            FlowPane flow = new FlowPane();
            Text info = new Text(((Article) currentEntity).getWebTitle());
            info.setFont(Font.font("Arial", FontWeight.BOLD, 14));

            Hyperlink showDetailLink = new Hyperlink("Show Detail");
            showDetailLink.setOnAction(
                actionEvent -> {
                  String boxInfo = currentEntity.getEntityInformation();
                  ResponseBoxWithPastebin responseBoxWithPastebin =
                      new ResponseBoxWithPastebin(requestMapping);
                  responseBoxWithPastebin.createAlertBox(
                      "Result Information", "Here is the detailed article information", boxInfo);
                });

            VBox articleRoughInfoWithDetailLink = new VBox();
            articleRoughInfoWithDetailLink.getChildren().addAll(info, showDetailLink);

            flow.getChildren().addAll(articleRoughInfoWithDetailLink);
            //                flow.getChildren().addAll(info, showDetailLink);
            flow.setStyle("-fx-font-family: Arial");
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(flow);
            scrollPane.setMinHeight(65);
            box.getChildren().add(scrollPane);
          }
          box.setAlignment(Pos.TOP_LEFT);
          return box;
        });

    pagination.setPrefWidth(480);
    pagination.setMaxWidth(480);
    pagination.setMinWidth(480);

    pagination.setPrefHeight(600);
    pagination.setMaxHeight(600);
    pagination.setMinHeight(600);
    pagination.setStyle("-fx-background-color: rgba(169,166,166,0.7)");

    HBox hBox = new HBox(10);
    Region region1 = new Region();
    region1.setPrefWidth(400);
    region1.setMaxWidth(400);
    //        region1.setStyle("-fx-background-color:#000000");

    Region region3 = new Region();
    region3.setPrefWidth(400);
    region3.setMaxWidth(400);
    //        region3.setStyle("-fx-background-color:#138c31");

    HBox.setHgrow(region1, Priority.ALWAYS);
    hBox.getChildren().addAll(region1, pagination, region3);
    hBox.setAlignment(Pos.BOTTOM_CENTER);
    //        hBox.setStyle("-fx-background-color:#8b2727");

    this.buttonFactory = new GrayButtonFactory();
    Button backButton = buttonFactory.createButton();
    backButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
    backButton.setText("Back");
    backButton.setOnAction(
        event -> {
          try {
            window.setScene(new SearchTagsByKeywordScene(window, requestMapping).getScene());
            window.setTitle("Search Tag By Keyword");
          } catch (Exception e) {
            e.printStackTrace();
          }
        });

    BorderPane pane = new BorderPane();
    pane.getChildren().add(this.backgroundFactory.getBackground());
    pane.setTop(t);
    pane.setAlignment(t, Pos.BOTTOM_CENTER);

    pane.setCenter(hBox);
    pane.setAlignment(hBox, Pos.CENTER);

    pane.setBottom(backButton);
    pane.setAlignment(backButton, Pos.BOTTOM_LEFT);

    Scene scene = new Scene(pane, 1280, 800);
    this.scene = scene;
  }

  public Scene getScene() {
    return this.scene;
  }
}
