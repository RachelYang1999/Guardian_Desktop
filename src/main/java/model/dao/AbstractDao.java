package model.dao;

import model.domain.Entity;

import java.sql.Connection;

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

    public abstract String getEntity(String entityName);

    public abstract boolean updateEntity(String entityName, String entityInfo);

    public abstract boolean deleteEntity(String entityName);
}
