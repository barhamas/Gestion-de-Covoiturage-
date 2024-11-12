package isi.sn.covoiturage_app_javafx_jpa.controllers;

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

public class ConnexionController {

    @FXML
    private Button btn;

    @FXML
    private Button btn1;

    @FXML
    private PasswordField passwordTfd;

    @FXML
    private TextField usernameTfd;
    public static int getIdUser() {
        return idUser;
    }

    private static int idUser;

    public static void setIdUser(int idUser) {
        ConnexionController.idUser = idUser;
    }

    private static String nomUser;

    public static String getNomUser() {
        return nomUser;
    }

    public static void setNomUser(String nomUser) {
        ConnexionController.nomUser = nomUser;
    }

    private static int User_id;

    public static int getUser_id() {
        return User_id;
    }

    public static void setUser_id(int user_id) {
        User_id = user_id;
    }

    private static User utilisateur;

    public static User getUtilisateur() {
        return utilisateur;
    }

    public static void setUtilisateur(User utilisateur) {
        ConnexionController.utilisateur = utilisateur;
    }

    private static String usernameConducteur;
    public String prenom;
    public String nom;

    public static String getUsernameConducteur() {
        return usernameConducteur;
    }

    public static void setUsernameConducteur(String usernameConducteur) {
        ConnexionController.usernameConducteur = usernameConducteur;
    }

    IUser dao = new IUserlmpl();

    @FXML
    void getC(ActionEvent event) { String username = usernameTfd.getText();
        String password = passwordTfd.getText();

        if (username.isBlank() || password.isBlank()) {
            Notification.NotifError("ERREUR", "Tous les champs sont obligatoires !");
            return;
        }
        String passH=hashPassword(password);
        try {
            User user = dao.Connexion(username, passH);



            if (user == null) {
                Notification.NotifError("ERREUR", "Email ou/et Mot de passe incorrect(s) !");
            } else {

                if (!user.getStatut().equals("active")) {
                    Outils.load(event, "PAGE D' ALERTE ", "/pages/alerte.fxml");
                    return;
                }

                utilisateur=user;

                nomUser= user.getPrenom() + " " + user.getNom();
                idUser=user.getUtilisateurId();

                Notification.NotifSuccess("SUCCESS", "Connexion reussie !");
                String role = user.getRole();
                if (role.equals("passager")) {
                    Outils.load(event, "PAGE  PASSAGER ", "/pages/exposition.fxml");
                } else if (role.equals("gestionnaire")){
                    Outils.load(event, "PAGE  GESTIONNAIRE", "/pages/dashboardGes.fxml");
                }else if (role.equals("admin")){
                    Outils.load(event, "PAGE  ADMINISTRATEUR", "/pages/dashboard.fxml");
                }else {
                    usernameConducteur= user.getUsername();
                    User_id=user.getUtilisateurId();
                    Outils.load(event, "PAGE  CONDUCTEUR", "/pages/soldeConducteur.fxml");

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void getIns(ActionEvent event) {
        try {
            Outils.load(event,"PAGE D' INSCRIPTION ","/pages/inscription.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
