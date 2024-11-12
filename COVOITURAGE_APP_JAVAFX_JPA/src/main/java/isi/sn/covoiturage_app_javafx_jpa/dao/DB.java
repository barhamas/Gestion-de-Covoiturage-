package isi.sn.covoiturage_app_javafx_jpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DB {

    protected EntityManagerFactory managerFactory;
    protected EntityManager entityManager;
    protected EntityTransaction transaction;

    public DB() {
        // Initialisation de l'EntityManagerFactory
        managerFactory = Persistence.createEntityManagerFactory("default");
        // Création de l'EntityManager
        entityManager = managerFactory.createEntityManager();
        // Initialisation de la transaction
        transaction = entityManager.getTransaction();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public EntityTransaction getTransaction() {
        return transaction;
    }

}
