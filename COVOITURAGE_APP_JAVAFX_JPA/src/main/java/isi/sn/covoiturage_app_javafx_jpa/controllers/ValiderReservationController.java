package isi.sn.covoiturage_app_javafx_jpa.controllers;

import isi.sn.covoiturage_app_javafx_jpa.dao.DB;


import isi.sn.covoiturage_app_javafx_jpa.entities.Reservation;

import isi.sn.covoiturage_app_javafx_jpa.entities.Trajet;
import isi.sn.covoiturage_app_javafx_jpa.entities.User;
import isi.sn.covoiturage_app_javafx_jpa.entities.Vehicule;

import isi.sn.covoiturage_app_javafx_jpa.tools.Email;
import isi.sn.covoiturage_app_javafx_jpa.tools.Notification;
import isi.sn.covoiturage_app_javafx_jpa.tools.Outils;



import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

import javax.persistence.TypedQuery;
import java.io.IOException;
import java.net.URL;
import java.net.UnknownServiceException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ValiderReservationController extends DB implements Initializable {


    @FXML
    private TableColumn<Reservation, String> conducteurCol;

    @FXML
    private ComboBox<String > conducteurTfd;


    @FXML
    private TableColumn<?, ?> dashboad_col_doctorID;

    @FXML
    private TableColumn<?, ?> dashboad_col_name;

    @FXML
    private TableColumn<?, ?> dashboad_col_specialized;

    @FXML
    private TableColumn<?, ?> dashboad_col_status;

    @FXML
    private TableView<?> dashboad_tableView;

    @FXML
    private Label dashboard_AD;

    @FXML
    private Label dashboard_AP;

    @FXML
    private Label dashboard_TP;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_tA;

    @FXML
    private TableColumn<Reservation, String> dateCol;

    @FXML
    private DatePicker dateTfd;

    @FXML
    private Button enregistrerBtn;

    @FXML
    private TableColumn<Reservation, String> etatCol;

    @FXML
    private ComboBox<String> etatTfd;

    @FXML
    private TableColumn<Reservation, Integer> idCol;

    @FXML
    private AnchorPane main_form;

    @FXML
    private TableColumn<Reservation, String > numCol;

    @FXML
    private Button patients_Confirmbtn;

    @FXML
    private TextField patients_CurrentID;

    @FXML
    private Label patients_PA_CurrentID;

    @FXML
    private Label patients_PA_Password;

    @FXML
    private Label patients_PA_dateCreated;

    @FXML
    private Button patients_PI_addBtn;

    @FXML
    private ImageView patients_PI_address;

    @FXML
    private Label patients_PI_gender;

    @FXML
    private Label patients_PI_mobileNumber;

    @FXML
    private Label patients_PI_patientName;

    @FXML
    private Button patients_PI_recordBtn;

    @FXML
    private TextArea patients_address;

    @FXML
    private AnchorPane patients_form;

    @FXML
    private ComboBox<?> patients_gender;

    @FXML
    private TextField patients_mobileNumber;

    @FXML
    private TextField patients_password;

    @FXML
    private TextField patients_patientName;

    @FXML
    private Button searchBtn1;

    @FXML
    private TextField searchTfd1;

    @FXML
    private TableColumn<?, ?> statutCol1;

    @FXML
    private TableView<Reservation> table;

    @FXML
    private TextField tarifTfd;

    @FXML
    private TableColumn<Reservation, String > trafifCol;

    @FXML
    private TableColumn<Reservation, String> trajetCol;

    @FXML
    private ComboBox<String> trajetTfd;

    @FXML
    private ComboBox<String> vehiculeTfd;

    @FXML
    private TableColumn<Reservation, String> voitureCol;

    private int idO;
    private int id;
    private static int nbplace;





    @FXML
    void deconnecter(ActionEvent event) {
        try {
            Outils.load(event,"PAGE DE DECONNEXION ","/pages/connexion.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void goToUser(ActionEvent event) {
        try {
            Outils.load(event,"PAGE D' UTILISATEUR ","/pages/gestionnaire.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void goToDashboard(ActionEvent event) {
        try {
            Outils.load(event,"DASHBOARD ","/pages/dashboardGes.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void goToProfile(ActionEvent event) {
        try {
            Outils.load(event,"PAGE DE PROFILE ","/pages/profileGestionnaire.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void goToTrajet(ActionEvent event) {
        try {
            Outils.load(event,"PAGE DE TRAJET ","/pages/trajetGes.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void goToVehicule(ActionEvent event) {
        try {
            Outils.load(event,"PAGE DE VEHICULE ","/pages/vehicule.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void goToReservation(ActionEvent event) {
        try {
            Outils.load(event,"PAGE DE RESERVATION ","/pages/validerReservation.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    void modifier(ActionEvent event){
        try {

            if ( etatTfd.getValue().isBlank()  ) {
                Notification.NotifError("ERREUR", " Aucun element a ete selectionne !");
                return;
            }


            transaction.begin();
            Reservation reservation = entityManager.find(Reservation.class, idO);
            reservation.setEtat(etatTfd.getValue());
            entityManager.persist(reservation);

            transaction.commit();
            viderChamps();
            loadTable();
            table.refresh();

        } finally {
            if (transaction.isActive())
                transaction.rollback();
        }
    }

    @FXML
    void actualiser(ActionEvent event){
        viderChamps();
    }





    @FXML
    void getData(MouseEvent event) {
        Reservation reservation = table.getSelectionModel().getSelectedItem();

        if (reservation != null) {
            idO = reservation.getReservationId();

            etatTfd.getSelectionModel().select(reservation.getEtat() );

        }
    }



    public void viderChamps() {
        etatTfd.setValue("");

    }
    public ObservableList<Reservation> getReservation() {
        ObservableList<Reservation> reservations = FXCollections.observableArrayList();
        TypedQuery<Reservation> query = entityManager.createNamedQuery("listReservationEnCours", Reservation.class);
        reservations.addAll(query.getResultList());
        return reservations;
    }
    private void loadTable() {
        ObservableList<Reservation> Reservation = getReservation();
        table.setItems(Reservation);

        idCol.setCellValueFactory(new PropertyValueFactory<>("reservationId"));

        numCol.setCellValueFactory(new PropertyValueFactory<>("numeroReservation"));
        trafifCol.setCellValueFactory(new PropertyValueFactory<>("prix"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        conducteurCol.setCellValueFactory(cellData -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Reservation reservation = cellData.getValue();
            if (reservation.getUser() != null) {
                property.setValue(reservation.getUser().getPrenom() +" "+ reservation.getUser().getPrenom());
            } else {
                property.setValue("Aucun");
            }
            return property;
        });

        etatCol.setCellValueFactory(cellData -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Reservation reservation = cellData.getValue();
            if (reservation.getEtat() != null) {
                property.setValue(reservation.getEtat());
            } else {
                property.setValue("Aucun");
            }
            return property;
        });

        trajetCol.setCellValueFactory(cellData -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Reservation reservation = cellData.getValue();
            if (reservation.getTrajet() != null) {
                property.setValue(reservation.getTrajet().getNomTrajet() );
            } else {
                property.setValue("Aucun");
            }
            return property;
        });

        voitureCol.setCellValueFactory(cellData -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Reservation reservation = cellData.getValue();
            if (reservation.getVehicule() != null) {
                property.setValue(reservation.getVehicule().getMatricule() );
            } else {
                property.setValue("Aucun");
            }
            return property;
        });
    }






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        etatTfd.getItems().addAll("en cours...", "valide");
        loadTable();

    }
}
