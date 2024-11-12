package isi.sn.covoiturage_app_javafx_jpa.dao;


import isi.sn.covoiturage_app_javafx_jpa.entities.User;


public interface IUser {

    public User Connexion(String username, String password);
    public User verifEmailUser(String email);
    public User verifLogin( String login );



}
