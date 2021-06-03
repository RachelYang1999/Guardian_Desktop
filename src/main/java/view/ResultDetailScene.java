//package view;
//
//import factory.backgroundfactory.BackgroundFactory;
//import factory.backgroundfactory.LightBackgroundFactory;
//import factory.buttonfactory.BrownButtonFactory;
//import factory.buttonfactory.ButtonFactory;
//import factory.buttonfactory.GrayButtonFactory;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.effect.Reflection;
//import javafx.scene.layout.*;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//import model.domain.Article;
//import model.domain.Entity;
//import model.service.ArticleService;
//import util.GuardianOnlineAPIStrategy;
//import util.RequestMapping;
//import view.alertbox.AlertBox;
//
//import java.util.List;
//
//public class ResultDetailScene {
//  private Stage window;
//  private Scene scene;
//  private BackgroundFactory backgroundFactory;
//  private ButtonFactory buttonFactory;
//  private AlertBox alertBox;
//
//  //    List<Entity> entities = new ArticleService(new
//  // GuardianOnlineAPIStrategy()).getAllArticles("1b0f84fb-9674-4fe2-b596-5836b2772fcb", "gay
//  // couple");
//  private List<Entity> returnedArticles;
//
//  public int itemsPerPage() {
//    return 10;
//  }
//
//  public ResultDetailScene(Stage window, RequestMapping requestMapping, String tag, Entity entity)
//      throws Exception {
//    this.window = window;
//    this.backgroundFactory = new LightBackgroundFactory();
//    this.buttonFactory = new BrownButtonFactory();
//
//    Text t = new Text();
//    t.setCache(true);
//    t.setText("Detailed Information");
//    t.setFill(Color.web("#704728"));
//    t.setFont(Font.font("Arial", FontWeight.BOLD, 60));
//    //        t.setLayoutX(250);
//    //        t.setLayoutY(230);
//    Reflection r = new Reflection();
//    r.setFraction(0.7f);
//    t.setEffect(r);
//
//    Text info = new Text(entity.getEntityInformation());
//    info.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
//    ScrollPane scrollPane = new ScrollPane();
//    scrollPane.setContent(info);
//
//    HBox hBox = new HBox(10);
//    Region region1 = new Region();
//    region1.setPrefWidth(400);
//    region1.setMaxWidth(400);
//    //        region1.setStyle("-fx-background-color:#000000");
//
//    Region region3 = new Region();
//    region3.setPrefWidth(400);
//    region3.setMaxWidth(400);
//    //        region3.setStyle("-fx-background-color:#138c31");
//
//    HBox.setHgrow(region1, Priority.ALWAYS);
//    hBox.getChildren().addAll(region1, scrollPane, region3);
//    hBox.setAlignment(Pos.BOTTOM_CENTER);
//    //        hBox.setStyle("-fx-background-color:#8b2727");
//
//    this.buttonFactory = new GrayButtonFactory();
//    Button backButton = buttonFactory.createButton();
//    backButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
//    backButton.setText("Back");
//    backButton.setOnAction(
//        event -> {
//          try {
//            window.setScene(new SearchResultScene(window, requestMapping, tag).getScene());
//            window.setTitle("Search By Tag");
//          } catch (Exception e) {
//            e.printStackTrace();
//          }
//        });
//
//    BorderPane pane = new BorderPane();
//    pane.getChildren().add(this.backgroundFactory.getBackground());
//    pane.setTop(t);
//    pane.setAlignment(t, Pos.BOTTOM_CENTER);
//
//    pane.setCenter(hBox);
//    pane.setAlignment(hBox, Pos.CENTER);
//
//    pane.setBottom(backButton);
//    pane.setAlignment(backButton, Pos.BOTTOM_LEFT);
//
//    Scene scene = new Scene(pane, 1280, 800);
//    this.scene = scene;
//  }
//
//  public Scene getScene() {
//    return this.scene;
//  }
//}
