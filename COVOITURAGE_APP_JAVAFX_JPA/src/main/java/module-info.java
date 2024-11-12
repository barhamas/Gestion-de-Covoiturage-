module isi.sn.covoiturage_app_javafx_jpa {
    requires javafx.controls;
    requires javafx.fxml;
    requires TrayNotification;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires java.transaction;
    requires java.mail;
    requires java.activation;
    requires kernel;
    requires layout;
    requires io;


    opens isi.sn.covoiturage_app_javafx_jpa to javafx.fxml;
    exports isi.sn.covoiturage_app_javafx_jpa;

    exports isi.sn.covoiturage_app_javafx_jpa.tools;
    opens isi.sn.covoiturage_app_javafx_jpa.tools to javafx.fxml;
    exports isi.sn.covoiturage_app_javafx_jpa.dao;
    exports isi.sn.covoiturage_app_javafx_jpa.controllers;
    exports isi.sn.covoiturage_app_javafx_jpa.entities;
    opens isi.sn.covoiturage_app_javafx_jpa.controllers to javafx.fxml;
    opens isi.sn.covoiturage_app_javafx_jpa.entities to org.hibernate.orm.core;
    opens isi.sn.covoiturage_app_javafx_jpa.dao to org.hibernate.orm.core;
}