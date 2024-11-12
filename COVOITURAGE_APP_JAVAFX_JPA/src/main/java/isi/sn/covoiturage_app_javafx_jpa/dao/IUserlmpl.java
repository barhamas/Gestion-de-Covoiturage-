package isi.sn.covoiturage_app_javafx_jpa.dao;

import isi.sn.covoiturage_app_javafx_jpa.entities.User;


import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Transactional
public class IUserlmpl extends DB implements IUser {


    @Override
    public User Connexion(String username, String password) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);

        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public User verifEmailUser(String email) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.email = :email", User.class);
        query.setParameter("email", email);

        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }




    @Override
    public User verifLogin(String login) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.username = :login", User.class);
        query.setParameter("login", login);

        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }







}
