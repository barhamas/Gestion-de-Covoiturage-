package isi.sn.covoiturage_app_javafx_jpa.controllers;

import isi.sn.covoiturage_app_javafx_jpa.dao.DB;
import isi.sn.covoiturage_app_javafx_jpa.entities.User;
import isi.sn.covoiturage_app_javafx_jpa.tools.Outils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class ProfilePassagerController  extends DB implements Initializable {

    @FXML
    private Label Current_Form;

    @FXML
    private Button artisteBtn;

    @FXML
    private Button clientBtn;

    @FXML
    private Button dashboardBtn;

    @FXML
    private Label dateSystem;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button modifierBtn;

    @FXML
    private Label nav_adminID;

    @FXML
    private Label nav_username;

    @FXML
    private TextField nomTfd;

    @FXML
    private PasswordField passwordTfd;

    @FXML
    private TextField prenomTfd;

    @FXML
    private Button profile;

    @FXML
    private Button actualiserBtn;

    @FXML
    private TextField usernameTfd;

    @FXML
    private Button venteBtn;

    private int idU ;

    @FXML
    void goToHistorique(ActionEvent event) {
        try {
            Outils.load(event,"PAGE MES RESERVATIONS ", "/pages/historique.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void goToDashboard(ActionEvent event) {
        try {
            Outils.load(event,"DASHBOARD ", "/pages/exposition.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void deconnecter(ActionEvent event) {
        try {
            Outils.load(event,"PAGE DE CONNEXION ", "/pages/connexion.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void goToProfile(ActionEvent event) {
        try {
            Outils.load(event,"PAGE DE PROFILE ", "/pages/profilePassager.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




    @FXML
    void modifier(ActionEvent event) {
        try{
            idU= ConnexionController.getIdUser();
            String passH= hashPassword(passwordTfd.getText());
            transaction.begin();
            User user = entityManager.find(User.class, idU);
            user.setPrenom(prenomTfd.getText());
            user.setNom(nomTfd.getText());
            user.setPassword(passH);
            user.setUsername(usernameTfd.getText());

            entityManager.persist(user);
            transaction.commit();
            clear();

        }finally {
            if (transaction.isActive())
                transaction.rollback();
        }
    }


    @FXML
    void actualiser(ActionEvent event) {

        idU= ConnexionController.getIdUser();
        User user = entityManager.find(User.class, idU);

        prenomTfd.setText(user.getPrenom());
        nomTfd.setText(user.getNom());
        passwordTfd.setText(user.getPassword());
        usernameTfd.setText(user.getUsername());
    }
    public  void clear(){
        prenomTfd.setText("");
        nomTfd.setText("");
        passwordTfd.setText("");
        usernameTfd.setText("");
    }
    public void load(){
        prenomTfd.setText(ConnexionController.getUtilisateur().getPrenom());
        nomTfd.setText(ConnexionController.getUtilisateur().getNom());
        passwordTfd.setText(ConnexionController.getUtilisateur().getPassword());
        usernameTfd.setText(ConnexionController.getUtilisateur().getUsername());
    }

    public String hashPassword(String password){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] resultByteArray = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : resultByteArray) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            // Logger.getLogger(InterfaceInscriptionA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "";
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        load();
    }
}
