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
        String finalSQL = "";
        Article article = null;
        try {
            statement = connection.createStatement();
            String sql =
                    "CREATE TABLE IF NOT EXISTS ARTICLE" +
                    "(ID CHAR(80)   PRIMARY KEY    NOT NULL," +
                    " TAG CHAR(50)               NOT NULL," +
                    " TITLE CHAR(100)               NOT NULL," +

                    " INFO CHAR(1200));";
            statement.executeUpdate(sql);

            if (entity.getEntityType().equals("Article")) {
                article = (Article) entity;
                sql =
                        "INSERT INTO ARTICLE (ID, TAG, TITLE, INFO) " +
                        "VALUES ('" + article.getId() + "', '"
                                + article.getRelatedTag() + "', '"
                                + article.getWebTitle() + "', '"
                                + article.getEntityInformation().replace("'", "") + "');";
                finalSQL = sql;
                System.out.println("sql: " + sql);
                statement.executeUpdate(sql);
            }
            statement.close();
            return true;
        } catch (Exception e) {
            System.err.println("SQL: " + finalSQL);

            System.err.println("Database add article failed when adding " + entity.getEntityInformation());
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<String> getEntity(String matchField, String matchValue, String retrieveFiled) {
        List<String> result = new ArrayList<>();
//        String result = "";
        try {
            statement = connection.createStatement();
            String sql =
                    "CREATE TABLE IF NOT EXISTS ARTICLE" +
                            "(ID CHAR(80)   PRIMARY KEY    NOT NULL," +
                            " TAG CHAR(50)               NOT NULL," +
                            " TITLE CHAR(100)               NOT NULL," +

                            " INFO CHAR(300));";
            statement.executeUpdate(sql);

            statement = connection.createStatement();
            sql =
                    "SELECT * FROM ARTICLE " +
                            "WHERE " + matchField + " = '" + matchValue + "';";
//            System.out.println("sql: " + sql);
            statement.executeUpdate(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String retrievedResult = resultSet.getString(retrieveFiled);
                result.add(retrievedResult);
//                System.out.println(retrieveFiled + ": " + retrievedResult);
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
        Entity entity = new Article();
        articleDAO.addEntity(entity);


//        String info = articleDAO.getEntity("TAG", "gay couple");
//        System.out.println("info " + info);
//        System.out.println(info.equals(""));

//        userDao.updateEntity("fake token1", "new fake token1");
//        userDao.deleteEntity("fake token1");
    }
}
