package com.travnich.util;

import javax.persistence.EntityManager;

public interface DatabaseManager {
    EntityManager getEntityManager();
}

