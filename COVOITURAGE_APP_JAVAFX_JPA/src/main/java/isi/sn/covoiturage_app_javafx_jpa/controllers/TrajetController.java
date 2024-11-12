package isi.sn.covoiturage_app_javafx_jpa.controllers;

import isi.sn.covoiturage_app_javafx_jpa.dao.*;
import isi.sn.covoiturage_app_javafx_jpa.entities.User;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class TrajetController extends DB implements Initializable {

    @FXML
    private Label Current_Form;

    @FXML
    private AnchorPane appointments_form;

    @FXML
    private Button artisteBtn;


    @FXML
    private TableColumn<isi.sn.covoiturage_app_javafx_jpa.entities.Trajet, String> marqueCol;

    @FXML
    private TextField marqueTfd;

    @FXML
    private TableColumn<isi.sn.covoiturage_app_javafx_jpa.entities.Trajet, String> matCol;

    @FXML
    private TextField matTfd;

    @FXML
    private TableColumn<isi.sn.covoiturage_app_javafx_jpa.entities.Trajet, String> modeleCol;

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
    private TableColumn<Trajet, String> imageCol;

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
    private TableColumn<isi.sn.covoiturage_app_javafx_jpa.entities.Trajet, String> placeCol;
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
    private TableColumn<Trajet, String> prixCol;

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
    private TextField arriveeTfd;

    @FXML
    private TextField tarifTfd;

    @FXML
    private TextField departTfd;

    @FXML
    private ComboBox<String> vehiculeTfd;


    @FXML
    private TableColumn<Trajet, Integer> tarifCol;

    @FXML
    private TableColumn<Trajet, String > voitureCol;

    @FXML
    private TableColumn<Trajet, String> nomCol;


    @FXML
    private TableColumn<Trajet, String> departCol;

    @FXML
    private TableColumn<Trajet, String> arriveeCol;

    @FXML
    private TableView<Trajet> table;

    @FXML
    private TableColumn<Trajet, String> titreCol;

    @FXML
    private ComboBox<String> conducteurTfd;

    @FXML
    private Button venteBtn;

    private static Trajet Trajet;

    public static Trajet getTrajet() {
        return Trajet;
    }
    public static void setTrajet(Trajet Trajet) {
        TrajetController.Trajet = Trajet;
    }

    private int id;
    private Date date;
    private String imagePath;




    @FXML
    void goToTrajet(ActionEvent event) {
        try {
            Outils.load(event,"PAGE DE TRAJET ", "/pages/trajet.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void goToDashboard(ActionEvent event) {
        try {
            Outils.load(event,"DASHBOARD ", "/pages/trajetConducteur.fxml");
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
            Outils.load(event,"PAGE DE PROFILE ", "/pages/profileConducteur.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void goToSolde(ActionEvent event) {
        try {
            Outils.load(event,"PAGE DE SOLDE ", "/pages/soldeConducteur.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    void effacer(ActionEvent event) {
        etatTfd.setDisable(true);
        enregistrerBtn.setDisable(false);
        viderChamps();
        loadTable();


    }

    @FXML
    void enregistrer(ActionEvent event) {
        try {

            if (nomTfd.getText().isBlank() || arriveeTfd.getText().isBlank() || departTfd.getText().isBlank() || tarifTfd.getText().isBlank()  || conducteurTfd.getValue().isBlank() || vehiculeTfd.getValue().isBlank() ) {
                Notification.NotifError("ERREUR", " Tous les champs sont obligatoires!");
                return;
            }


            transaction.begin();
            // Trajet
            Trajet Trajet = new Trajet();
            Trajet.setNomTrajet(nomTfd.getText());
            Trajet.setVilleArrive(arriveeTfd.getText());
            Trajet.setVilleDepart(departTfd.getText());
            Trajet.setDate( Date.valueOf(LocalDate.now()));
            Trajet.setPrix(Integer.parseInt(tarifTfd.getText()));
            Trajet.setEtat("inactive");

            // Conducteur
            String conducteurSelected = conducteurTfd.getValue();
            String[] conducteurParts = conducteurSelected.split(" ");
            if (conducteurParts.length < 2) {
                throw new IllegalArgumentException("Le format du nom du conducteur sélectionné est incorrect");
            }
            String nom = conducteurParts[0];
            String prenom = conducteurParts[1];

            TypedQuery<User> conducteurQuery = entityManager.createQuery(
                    "SELECT a FROM User a WHERE a.nom = :name AND a.prenom = :prenom", User.class);
            conducteurQuery.setParameter("name", nom);
            conducteurQuery.setParameter("prenom", prenom);

            User user;
            try {
                user = conducteurQuery.getSingleResult();
            } catch (NoResultException e) {
                // Créer un nouvel conducteur si aucun résultat trouvé
                user = new User();
                user.setNom(nom);
                user.setPrenom(prenom);
                entityManager.persist(user);
            }

            Trajet.setUser(user); // Association entre Trajet et Conducteur

            //  VOITURE

            String vehiculeSelected = vehiculeTfd.getValue();
            TypedQuery<Vehicule> vehiculedQuery = entityManager.createQuery("SELECT s FROM Vehicule s WHERE s.matricule = :mat", Vehicule.class);
            vehiculedQuery.setParameter("mat", vehiculeSelected);
            Vehicule vehicule = vehiculedQuery.getSingleResult();

            Trajet.setVehicule(vehicule);




            entityManager.persist(Trajet);
            transaction.commit();
            viderChamps();
            loadTable();
            table.refresh();



        } catch (Exception e) {
            throw new RuntimeException(e);


        } finally {
            if (transaction.isActive())
                transaction.rollback();
        }
    }




    @FXML
    void getData(MouseEvent event) {
        Trajet Trajet = table.getSelectionModel().getSelectedItem();

        id = Trajet.getTrajetId();
        arriveeTfd.setText(Trajet.getVilleArrive());
        departTfd.setText(Trajet.getVilleDepart());
        nomTfd.setText(Trajet.getNomTrajet());
        etatTfd.setValue(Trajet.getEtat());
        tarifTfd.setText(String.valueOf(Trajet.getPrix()));

        conducteurTfd.getSelectionModel().select(Trajet.getUser().getNom() +" "+ Trajet.getUser().getPrenom());
        vehiculeTfd.getSelectionModel().select(Trajet.getVehicule().getMatricule());
        enregistrerBtn.setDisable(true);
        etatTfd.setDisable(true);
        vehiculeTfd.setDisable(true);
        conducteurTfd.setDisable(true);

    }

    @FXML
    void modifier(ActionEvent event) {
        try {

            if (nomTfd.getText().isBlank() || arriveeTfd.getText().isBlank() || departTfd.getText().isBlank() || tarifTfd.getText().isBlank()  || conducteurTfd.getValue().isBlank() || vehiculeTfd.getValue().isBlank() ) {
                Notification.NotifError("ERREUR", " Aucun element a ete selectionne");
                return;
            }

            transaction.begin();
            Trajet Trajet = entityManager.find(Trajet.class, id);
            Trajet.setNomTrajet(nomTfd.getText());
            Trajet.setVilleArrive(arriveeTfd.getText());
            Trajet.setVilleDepart(departTfd.getText());
            Trajet.setPrix(Integer.parseInt(tarifTfd.getText()));

            entityManager.persist(Trajet);
            transaction.commit();
            viderChamps();
            loadTable();
            table.refresh();
            enregistrerBtn.setDisable(false);
            vehiculeTfd.setDisable(false);
            conducteurTfd.setDisable(false);
        } finally {
            if (transaction.isActive())
                transaction.rollback();
        }
    }

    @FXML
    void supprimer(ActionEvent event) {
        try{
            transaction.begin();
            Trajet trajet = entityManager.find(Trajet.class, id);
            entityManager.remove(trajet);
            transaction.commit();
            viderChamps();
            loadTable();
        }finally {
            if (transaction.isActive())
                transaction.rollback();
        }
    }







    public void viderChamps() {
        nomTfd.setText("");
        arriveeTfd.setText("");
        departTfd.setText("");
        tarifTfd.setText("");

        etatTfd.setValue("");

        searchTfd.setText("");

        conducteurTfd.getSelectionModel().clearSelection();
        conducteurTfd.getSelectionModel().clearSelection();
        enregistrerBtn.setDisable(false);
        etatTfd.setDisable(true);
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
        setListeConducteur();
        setListeVoiture();
        date = Date.valueOf(LocalDate.now());
        loadTable();

    }

    public void setListeConducteur() {
        List<String> conducteurList = new ArrayList<>();
        try {
            transaction.begin();
            TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.role = 'conducteur' " , User.class);
            List<User> users = query.getResultList();
            for (User user : users) {
                conducteurList.add(user.getNom() + " " + user.getPrenom());
            }
            transaction.commit();
        } finally {
            if (transaction.isActive())
                transaction.rollback();
        }
        conducteurTfd.getItems().addAll(conducteurList);
    }

    public void setListeVoiture() {
        List<String> voitureList = new ArrayList<>();
        try {
            transaction.begin();
            TypedQuery<Vehicule> query = entityManager.createQuery("SELECT u FROM Vehicule u  " , Vehicule.class);
            List<Vehicule> vehicules = query.getResultList();
            for (Vehicule vehicule : vehicules) {
                voitureList.add(vehicule.getMatricule());
            }
            transaction.commit();
        } finally {
            if (transaction.isActive())
                transaction.rollback();
        }
        vehiculeTfd.getItems().addAll(voitureList);
    }


}
