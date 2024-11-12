package isi.sn.covoiturage_app_javafx_jpa.controllers;

import isi.sn.covoiturage_app_javafx_jpa.dao.DB;
import isi.sn.covoiturage_app_javafx_jpa.entities.Reservation;
import isi.sn.covoiturage_app_javafx_jpa.entities.Trajet;
import isi.sn.covoiturage_app_javafx_jpa.entities.User;
import isi.sn.covoiturage_app_javafx_jpa.entities.Vehicule;
import isi.sn.covoiturage_app_javafx_jpa.tools.Outils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javax.persistence.TypedQuery;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardAdminController extends DB implements Initializable {

    @FXML
    private Label Current_Form;

    @FXML
    private Button adminBtn;

    @FXML
    private AnchorPane appointments_form;



    @FXML
    private Button collectionBtn;

    @FXML
    private Label vehiculeTfd;
    @FXML
    private Label trajetTfd;
    @FXML
    private Label passagerTfd;
    @FXML
    private Label conducteurTfd;

    @FXML
    private Button dashboardBtn;

    @FXML
    private Label dateSystem;

    @FXML
    private Label gestionnaireTfd;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Label nav_adminID;

    @FXML
    private Label nav_username;

    @FXML
    private Label oeuvreDTfd;

    @FXML
    private Label oeuvreVTfd;

    @FXML
    private Button profile;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField searchTfd;

    @FXML
    private Label soldeTfd;

    @FXML
    private Button venteBtn;

    private int nbVehicule, nbTrajet, solde, nbPassager, nbConducteur, nbGestionnaire;





    @FXML
    void goToDashboard(ActionEvent event) {
        try {
            Outils.load(event,"DASHBOARD ", "/pages/dashboard.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void goToUser(ActionEvent event) {
        try {
            Outils.load(event,"PAGE D' ADMINISTRATEUR ", "/pages/admin.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void goToProfile(ActionEvent event) {
        try {
            Outils.load(event,"PAGE DE PROFILE ", "/pages/profile.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void goToSolde(ActionEvent event) {
        try {
            Outils.load(event,"PAGE DE SOLDE ", "/pages/solde.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void deconnecter(ActionEvent event) {
        try {
            Outils.load(event,"PAGE DE DECONNEXION ","/pages/connexion.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private ObservableList<Vehicule> fetchVehicules() {

        TypedQuery<Vehicule> query = entityManager.createQuery("SELECT v FROM Vehicule v", Vehicule.class);
        List<Vehicule> results = query.getResultList();

        return FXCollections.observableArrayList(results);
    }

    private ObservableList<User> fetchUsers() {

        TypedQuery<User> query = entityManager.createQuery("SELECT c FROM User c", User.class);
        List<User> results = query.getResultList();

        return FXCollections.observableArrayList(results);
    }

    private ObservableList<Trajet> fetchTrajets() {

        TypedQuery<Trajet> query = entityManager.createQuery("SELECT t FROM Trajet t", Trajet.class);
        List<Trajet> results = query.getResultList();

        return FXCollections.observableArrayList(results);
    }

    private ObservableList<Reservation> fetchReservations() {

        TypedQuery<Reservation> query = entityManager.createQuery("SELECT r FROM Reservation r", Reservation.class);
        List<Reservation> results = query.getResultList();

        return FXCollections.observableArrayList(results);
    }

    public void loadVehicules() {
        nbVehicule = fetchVehicules().size();
        vehiculeTfd.setText(String.valueOf(nbVehicule));
    }

    public void loadTrajet() {
        nbTrajet = fetchTrajets().size();
        trajetTfd.setText(String.valueOf(nbTrajet));
    }


    public void loadUsers() {
        nbGestionnaire=nbPassager= nbConducteur = 0;
        ObservableList<User> users = fetchUsers();

        users.forEach(user -> {
            if ("gestionnaire".equals(user.getRole())) {
                nbGestionnaire++;
            } else if ("passager".equals(user.getRole())) {
                nbPassager++;
            } else if ("conducteur".equals(user.getRole())) {
                nbConducteur++;
            }
        });

        gestionnaireTfd.setText(String.valueOf(nbGestionnaire));
        conducteurTfd.setText(String.valueOf(nbConducteur));
        passagerTfd.setText(String.valueOf(nbPassager));
    }

    public void loadReservation() {
        solde=0;
        ObservableList<Reservation> reservations = fetchReservations();

        for (Reservation reservation: reservations){
            solde+=reservation.getPrix();
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadReservation();
        loadUsers();
        loadTrajet();
        loadVehicules();

    }
}
