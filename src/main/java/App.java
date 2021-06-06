import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.stage.WindowEvent;
import model.dao.DaoUtil;
import util.*;
import javafx.application.Application;
import javafx.stage.Stage;
import view.WelcomeScene;

import java.beans.EventHandler;
import java.sql.SQLException;
import java.util.List;

/**
 * The main class for running the application
 * @author Rachel Yang
 */
public class App extends Application {
  private Stage window;
  private RequestMapping requestMapping;
  private GuardianAPIStrategy guardianAPIStrategy;
  private PastebinAPIStrategy pastebinAPIStrategy;
  private DaoUtil daoUtil;

  @Override
  public void start(Stage primaryStage) throws Exception {
    this.daoUtil = new DaoUtil();

    List<String> args = getParameters().getRaw();
    if (args.get(0).equals("online")) {
      this.guardianAPIStrategy = new GuardianOnlineAPIStrategy();
      requestMapping = new RequestMapping(guardianAPIStrategy, pastebinAPIStrategy, daoUtil);
    } else if (args.get(0).equals("offline")) {
      this.guardianAPIStrategy = new GuardianOfflineAPIStrategy();
      requestMapping = new RequestMapping(guardianAPIStrategy, pastebinAPIStrategy, daoUtil);
    } else {
      throw new Exception("Incorrect command line argument");
    }

    if (args.get(1).equals("online")) {
      this.pastebinAPIStrategy = new PastebinOnlineAPIStrategy();
      requestMapping = new RequestMapping(guardianAPIStrategy, pastebinAPIStrategy, daoUtil);
    } else if (args.get(1).equals("offline")) {
      this.pastebinAPIStrategy = new PastebinOfflineAPIStrategy();
      requestMapping = new RequestMapping(guardianAPIStrategy, pastebinAPIStrategy, daoUtil);
    } else {
      throw new Exception("Incorrect command line argument");
    }

    window = primaryStage;
    window.setTitle("Welcome");
    window.setScene(new WelcomeScene(this.window, requestMapping).getScene());
    window.show();

    /*
     Close database once the application closed
     */
    window.setOnCloseRequest(e -> {
      try {
        daoUtil.closeDatabaseConnection();
      } catch (SQLException throwables) {
        throwables.printStackTrace();
        System.err.println("Failed to close the database");
      }
      System.out.println("Database closed");
    });


  }

  public static void main(String[] args) throws Exception {
    if (args.length != 2) {
      throw new Exception("Incorrect number of command line arguments");
    }
    if (!args[0].equals("online")
        && !args[0].equals("offline")
        && !args[1].equals("online")
        && !args[1].equals("offline")) {
      throw new Exception("Incorrect command line argument");
    }
    launch(args);
  }
}
