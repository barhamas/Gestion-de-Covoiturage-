package isi.sn.covoiturage_app_javafx_jpa.controllers;

import isi.sn.covoiturage_app_javafx_jpa.dao.*;
import isi.sn.covoiturage_app_javafx_jpa.entities.Trajet;
import isi.sn.covoiturage_app_javafx_jpa.entities.Vehicule;

import isi.sn.covoiturage_app_javafx_jpa.tools.Notification;
import isi.sn.covoiturage_app_javafx_jpa.tools.Outils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javax.persistence.TypedQuery;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class TrajetGestionnaireController extends DB implements Initializable {

    @FXML
    private Label Current_Form;

    @FXML
    private AnchorPane appointments_form;

    @FXML
    private Button artisteBtn;


    @FXML
    private TableColumn<isi.sn.covoiturage_app_javafx_jpa.entities.Vehicule, String> marqueCol;

    @FXML
    private TextField marqueTfd;

    @FXML
    private TableColumn<isi.sn.covoiturage_app_javafx_jpa.entities.Vehicule, String> matCol;

    @FXML
    private TextField matTfd;

    @FXML
    private TableColumn<isi.sn.covoiturage_app_javafx_jpa.entities.Vehicule, String> modeleCol;

    @FXML
    private TextField modeleTfd;

    @FXML
    private TextField nbPlaceTfd;


    @FXML
    private TableColumn<Trajet, String> conducteurCol;

    @FXML
    private TextField artisteTfd;

    @FXML
    private ComboBox<String> etatTfd;
    @FXML
    private Button clientBtn;

    @FXML
    private BarChart<?, ?> dashboad_chart_DD;

    @FXML
    private AreaChart<?, ?> dashboad_chart_PD;

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
    private Button dashboardBtn;

    @FXML
    private Label dashboard_AD;

    @FXML
    private Label dashboard_AP;

    @FXML
    private Label dashboard_TP;

    @FXML
    private TextField nomTfd;

    @FXML
    private TextField emailTfd;

    @FXML
    private TextField telTfd;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_tA;

    @FXML
    private TableColumn<Trajet, String> dateCol;

    @FXML
    private Label dateSystem;

    @FXML
    private Button effacerBtn;

    @FXML
    private Button enregistrerBtn;

    @FXML
    private TableColumn<Trajet, String> etatCol;

    @FXML
    private TableColumn<Trajet, Integer> idCol;

    @FXML
    private TableColumn<Vehicule, String> imageCol;

    @FXML
    private ImageView imageView;

    @FXML
    private Button importerBtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button modifierBtn;

    @FXML
    private Label nav_adminID;

    @FXML
    private Label nav_username;

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
    private TableColumn<isi.sn.covoiturage_app_javafx_jpa.entities.Vehicule, String> placeCol;
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
    private TextField prenomTfd;

    @FXML
    private TableColumn<Vehicule, String> prixCol;

    @FXML
    private TextField prixTfd;


    @FXML
    private Button profile;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField searchTfd;

    @FXML
    private TableColumn<?, ?> statutCol;

    @FXML
    private Button supprimerBtn;

    @FXML
    private TableView<Trajet> table;

    @FXML
    private TableColumn<Vehicule, String> titreCol;

    @FXML
    private ComboBox<String> conducteurTfd;

    @FXML
    private Button venteBtn;

    @FXML
    private TableColumn<Trajet, Integer> tarifCol;

    @FXML
    private TableColumn<Trajet, String > voitureCol;

    @FXML
    private TableColumn<Trajet, String> nomCol;

    @FXML
    private TableColumn<?, ?> dateCole;

    @FXML
    private TableColumn<Trajet, String> departCol;

    @FXML
    private TableColumn<Trajet, String> arriveeCol;


    private int id;
    private Date date;


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
    void actualiser(ActionEvent event) {
        viderChamps();
        loadTable();

    }






    @FXML
    void getData(MouseEvent event) {
        Trajet trajet = table.getSelectionModel().getSelectedItem();

        id = trajet.getTrajetId();

        etatTfd.setValue(trajet.getEtat());
    }

    @FXML
    void modifier(ActionEvent event) {
        try {

            if ( etatTfd.getValue().isBlank()  ) {
                Notification.NotifError("ERREUR", " Aucun element a ete selectionne !");
                return;
            }


            transaction.begin();
            Trajet trajet = entityManager.find(Trajet.class, id);
            trajet.setEtat(etatTfd.getValue());
            trajet.setDate(Date.valueOf(LocalDate.now()));
            entityManager.persist(trajet);
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
    void search(ActionEvent event) {
        String trajet = searchTfd.getText();
        if (!trajet.isBlank()) {
            ObservableList<Trajet> trajets = searchTajet(trajet + "%");

            if (!trajet.isEmpty()) {
                table.setItems(trajets);
            }
        }
    }

    private ObservableList<Trajet> searchTajet(String trajet) {
        ObservableList<Trajet> trajets = FXCollections.observableArrayList();
        try {
            TypedQuery<Trajet> query = entityManager.createQuery(
                    "SELECT t FROM Trajet t WHERE t.nomTrajet LIKE :trajet OR t.villeDepart LIKE :trajet OR t.villeArrive LIKE :trajet ", Trajet.class);
            query.setParameter("trajet", trajet);
            trajets.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trajets;
    }



    public void viderChamps() {

        etatTfd.setValue("");

        searchTfd.setText("");

    }

    public void loadTable() {

        ObservableList<Trajet> trajets = getTrajets();
        table.setItems(trajets);

        idCol.setCellValueFactory(new PropertyValueFactory<Trajet, Integer>("oeuvreId"));
        nomCol.setCellValueFactory(new PropertyValueFactory<Trajet, String>("nomTrajet"));
        departCol.setCellValueFactory(new PropertyValueFactory<Trajet, String>("villeDepart"));
        arriveeCol.setCellValueFactory(new PropertyValueFactory<Trajet, String>("villeArrive"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Trajet, String>("date"));
        tarifCol.setCellValueFactory(new PropertyValueFactory<Trajet, Integer>("prix"));
        etatCol.setCellValueFactory(new PropertyValueFactory<Trajet, String>("etat"));
        conducteurCol.setCellValueFactory(cellData -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Trajet trajet = cellData.getValue();
            if (trajet.getUser() != null) {
                property.setValue(trajet.getUser().getNom() + " " + trajet.getUser().getPrenom());
            } else {
                property.setValue("Aucun");
            }
            return property;
        });

        voitureCol.setCellValueFactory(cellData -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Trajet trajet = cellData.getValue();
            if (trajet.getUser() != null) {
                property.setValue(trajet.getVehicule().getMatricule() );
            } else {
                property.setValue("Aucun");
            }
            return property;
        });
    }


    public ObservableList<Trajet> getTrajets() {
        ObservableList<Trajet> trajets = FXCollections.observableArrayList();
        TypedQuery<Trajet> query = entityManager.createNamedQuery("listTrajet", Trajet.class);
        trajets.addAll(query.getResultList());
        return trajets;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        etatTfd.getItems().addAll("active", "inactive");
        date = Date.valueOf(LocalDate.now());
        loadTable();

    }

}
