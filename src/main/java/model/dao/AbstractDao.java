package model.dao;

import model.domain.Entity;

import java.sql.Connection;
import java.util.List;

public abstract class AbstractDao {

    protected Connection connection;
    protected DaoUtil daoUtil;


    public AbstractDao(DaoUtil daoUtil) {
        this.daoUtil = daoUtil;
    }

    public DaoUtil getDaoUtil() {
        return this.daoUtil;
    }
    public abstract boolean addEntity(Entity entity);

    public abstract List<String> getEntity(String matchField, String matchValue, String retrieveFiled);

    public abstract boolean updateEntity(String entityName, String entityInfo);

    public abstract boolean deleteEntity(String entityName);
}
