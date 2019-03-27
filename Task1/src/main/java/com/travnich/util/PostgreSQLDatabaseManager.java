package com.travnich.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

public class PostgreSQLDatabaseManager implements DatabaseManager {

    @PersistenceUnit(unitName = "StorePersistenceUnit")
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("StorePersistenceUnit");

    private EntityManager entityManager;

    private PostgreSQLDatabaseManager() {
        entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    private static class Holder {
        private final static DatabaseManager INSTANCE = new PostgreSQLDatabaseManager();
    }

    public static DatabaseManager getInstance() {
        return Holder.INSTANCE;
    }
}
