package model.dao;

import model.domain.Article;
import model.domain.Entity;
import model.domain.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArticleDao extends AbstractDao {
    Connection connection;
    Statement statement;


    public ArticleDao(DaoUtil daoUtil) {
        super(daoUtil);
        connection = daoUtil.getDatabaseConnection();
    }

    @Override
    public boolean addEntity(Entity entity) {
        Article article = null;
        try {
            statement = connection.createStatement();
            String sql =
                    "CREATE TABLE IF NOT EXISTS ARTICLE" +
                    "(ID CHAR(80)   PRIMARY KEY    NOT NULL," +
                    " TAG CHAR(50)               NOT NULL," +
                    " INFO CHAR(300));";
            statement.executeUpdate(sql);

            if (entity.getEntityType().equals("Article")) {
                article = (Article) entity;
                sql =
                        "INSERT INTO ARTICLE (ID, TAG, INFO) " +
                        "VALUES ('" + article.getId() + "', '" + article.getRelatedTag() + "', '" + article.getEntityInformation() + "');";
                System.out.println("sql: " + sql);
                statement.executeUpdate(sql);
            }
            statement.close();
            return true;
        } catch (Exception e) {
            System.err.println("Database add article failed!");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<String> getEntity(String filed, String searchString) {
        List<String> result = new ArrayList<>();
//        String result = "";
        try {
            statement = connection.createStatement();
            String sql =
                    "SELECT * FROM ARTICLE " +
                    "WHERE " + filed + " = '" + searchString + "';";
//            System.out.println("sql: " + sql);
            statement.executeUpdate(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String info = resultSet.getString("INFO");
                result.add(info);
                System.out.println("Info: " + info);
            }
        } catch (Exception e) {
            System.err.println("Database get user failed!");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateEntity(String token, String entityInfo) {
        try {
            statement = connection.createStatement();
            String sql =
                    "UPDATE USER SET INFO = '" + entityInfo + "' WHERE TOKEN = '" + token + "';";
            statement.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.err.println("Database update user failed!");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }
        return false;
    }

    @Override
    public boolean deleteEntity(String entityName) {
        try {
            statement = connection.createStatement();
            String sql =
                    "DELETE from USER where TOKEN = '" + entityName + "'; ";
            statement.executeUpdate(sql);
            System.out.println("Deleted");
            return true;
        } catch (Exception e) {
            System.err.println("Database delete user failed!");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        AbstractDao articleDAO = new ArticleDao(new DaoUtil());

//        String info = articleDAO.getEntity("TAG", "gay couple");
//        System.out.println("info " + info);
//        System.out.println(info.equals(""));

//        userDao.updateEntity("fake token1", "new fake token1");
//        userDao.deleteEntity("fake token1");
    }
}
