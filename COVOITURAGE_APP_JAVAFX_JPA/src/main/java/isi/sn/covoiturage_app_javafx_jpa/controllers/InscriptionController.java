package isi.sn.covoiturage_app_javafx_jpa.controllers;

import isi.sn.covoiturage_app_javafx_jpa.dao.DB;
import isi.sn.covoiturage_app_javafx_jpa.dao.IUser;
import isi.sn.covoiturage_app_javafx_jpa.dao.IUserlmpl;
import isi.sn.covoiturage_app_javafx_jpa.entities.User;

import isi.sn.covoiturage_app_javafx_jpa.tools.Notification;
import isi.sn.covoiturage_app_javafx_jpa.tools.Outils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class InscriptionController  extends DB {

    @FXML
    private Button RetourBtn;

    @FXML
    private TextField mailTfd;

    @FXML
    private TextField nomTfd;

    @FXML
    private TextField prenomTfd;

    @FXML
    private PasswordField CPasswordTfd;

    @FXML
    private PasswordField passwordTfd;

    @FXML
    private TextField loginTfd;

    @FXML
    private Button validerBtn;

    IUser dao = new IUserlmpl();



    @FXML
    void getCx(ActionEvent event) {
        try {
            Outils.load(event,"PAGE DE CONNEXION ", "/pages/connexion.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void getValider(ActionEvent event) {

        try {
            transaction.begin();
            User user = new User();

            String nom = nomTfd.getText();
            String prenom = prenomTfd.getText();

            String password = passwordTfd.getText();
            String confirmePwd = CPasswordTfd.getText();




            String email = mailTfd.getText();
            User userEmail = dao.verifEmailUser(email);

            String username = loginTfd.getText();
            User userLogin = dao.verifLogin(username);



                if (userEmail != null) {
                    Notification.NotifError("ERREUR", "cet EMAIL existe deja! Veuiilez choisir un autre ");
                    return;
                }
                if (userLogin != null) {
                    Notification.NotifError("ERREUR", "Login existe deja! Veuiilez choisir un autre ");
                    return;
                }
                if (!email.endsWith("@gmail.com")) {
                    Notification.NotifError("ERREUR", "Adresse e-mail incorrecte ! ");
                    return;
                }


                if (nom.isBlank() || prenom.isBlank()  || email.isBlank() || username.isBlank() || password.isBlank() || confirmePwd.isBlank()) {
                    Notification.NotifError("ERREUR", "Tous les champs sont obligatoires !");
                    return;
                }

            if (!password.equals(confirmePwd)){
                Notification.NotifError("ERREUR", " Mot de Passe incorrect !");
                return;
            }

                String passH= hashPassword(password);

            user.setNom(nomTfd.getText());
            user.setPrenom(prenomTfd.getText());
            user.setPassword(passH);
            user.setUsername(username);
            user.setEmail(email);
            user.setRole("passager");
            user.setStatut("desactive");

            entityManager.persist(user);
            transaction.commit();
            clear();




            Notification.NotifSuccess("SUCCES", "Inscription reussie");


        } catch (Exception e) {
            Notification.NotifError("ERREUR", "Xamo Dara");


        } finally {
            if (transaction.isActive())
                transaction.rollback();

        }
    }

    public void clear(){
        nomTfd.setText("");
        prenomTfd.setText("");
        mailTfd.setText("");
        loginTfd.setText("");
        passwordTfd.setText("");
        CPasswordTfd.setText("");
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




}
