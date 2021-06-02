// import javafx.application.Application;
// import javafx.scene.Scene;
// import javafx.scene.control.Label;
// import javafx.scene.control.Pagination;
// import javafx.scene.layout.AnchorPane;
// import javafx.scene.layout.VBox;
// import javafx.scene.text.Font;
// import javafx.scene.text.FontWeight;
// import javafx.stage.Stage;
// import model.domain.Entity;
// import model.service.ArticleService;
// import util.GuardianOnlineAPIStrategy;
//
// import java.util.List;
//
// public class SplitArticle extends Application{
//    private Pagination pagination;
//    String[] fonts = new String[]{};
//    List<Entity> entities = new ArticleService(new
// GuardianOnlineAPIStrategy()).getAllArticles("1b0f84fb-9674-4fe2-b596-5836b2772fcb", "gay
// couple");
//
//    public static void main(String[] args) throws Exception {
//        launch(args);
//    }
//
//    public int itemsPerPage() {
//        return 10;
//    }
//
//    public VBox createPage(int pageIndex) {
//        VBox box = new VBox(5);
//        int page = pageIndex * itemsPerPage();
//        for (int i = page; i < page + itemsPerPage() && i < entities.size(); i++) {
//            Label font = new Label("test");
//            font.setFont(Font.font("Arial", FontWeight.BOLD, 10));
//            box.getChildren().add(font);
//        }
//        return box;
//    }
//
//    @Override
//    public void start(final Stage stage) throws Exception {
//        System.out.println("Page count: " +
// (int)Math.ceil((double)entities.size()/itemsPerPage()));
//        pagination = new Pagination((int)Math.ceil((double)entities.size()/itemsPerPage()), 0);
//        pagination.setPageFactory((Integer p) -> createPage(p));
//        pagination.setStyle("-fx-font-family: Arial");
//
//        AnchorPane anchor = new AnchorPane();
//        AnchorPane.setTopAnchor(pagination, 10.0);
//        AnchorPane.setRightAnchor(pagination, 10.0);
//        AnchorPane.setBottomAnchor(pagination, 10.0);
//        AnchorPane.setLeftAnchor(pagination, 10.0);
//        anchor.getChildren().addAll(pagination);
//        Scene scene = new Scene(anchor, 400, 450);
//        stage.setScene(scene);
//        stage.show();
//    }
// }
