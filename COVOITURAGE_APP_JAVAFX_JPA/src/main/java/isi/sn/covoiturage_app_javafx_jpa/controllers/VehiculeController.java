package isi.sn.covoiturage_app_javafx_jpa.controllers;

import isi.sn.covoiturage_app_javafx_jpa.dao.*;
import isi.sn.covoiturage_app_javafx_jpa.entities.User;
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


public class VehiculeController extends DB implements Initializable {

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
    private TableColumn<Vehicule, String> conducteurCol;

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
    private TableColumn<Vehicule, String> dateCol;

    @FXML
    private Label dateSystem;

    @FXML
    private Button effacerBtn;

    @FXML
    private Button enregistrerBtn;

    @FXML
    private TableColumn<Vehicule, String> etatCol;

    @FXML
    private TableColumn<Vehicule, Integer> idCol;

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
    private TableView<Vehicule> table;

    @FXML
    private TableColumn<Vehicule, String> titreCol;

    @FXML
    private ComboBox<String> conducteurTfd;

    @FXML
    private Button venteBtn;

    private static Vehicule Vehicule;

    public static Vehicule getVehicule() {
        return Vehicule;
    }
    public static void setVehicule(Vehicule Vehicule) {
        VehiculeController.Vehicule = Vehicule;
    }

    private int id;
    private Date date;
    private String imagePath;
    IUser dao = new IUserlmpl();
    IVehicule daoV = new IVehiculeLmpl();

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
    void effacer(ActionEvent event) {
        etatTfd.setDisable(true);
        enregistrerBtn.setDisable(false);
        viderChamps();
        loadTable();
    }

    @FXML
    void enregistrer(ActionEvent event) {
        try {

            if (marqueTfd.getText().isBlank() || matTfd.getText().isBlank() || modeleTfd.getText().isBlank() || nbPlaceTfd.getText().isBlank()  || conducteurTfd.getValue().isBlank() || imageView.getImage() == null) {
                Notification.NotifError("ERREUR", " Tous les champs sont obligatoires!");
                return;
            }
            String mat = matTfd.getText();
            Vehicule vehiculeMat = daoV.verifMat(mat);



            if (vehiculeMat != null) {
                Notification.NotifError("ERREUR", "cette Matricule existe deja! Veuiilez choisir un autre ");
                return;
            }

            transaction.begin();
            // Vehicule
            Vehicule vehicule = new Vehicule();
            vehicule.setMarque(marqueTfd.getText());
            vehicule.setMatricule(matTfd.getText());
            vehicule.setModele(modeleTfd.getText());
            vehicule.setCptPlace(0);
            vehicule.setNombrePlace(Integer.parseInt(nbPlaceTfd.getText()));
            vehicule.setImage(imagePath);
            vehicule.setEtat("disponible");

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

            vehicule.setUser(user); // Association entre Vehicule et Conducteur


            entityManager.persist(vehicule);
            transaction.commit();
            viderChamps();
            loadTable();
            table.refresh();
            enregistrerBtn.setDisable(false);


        } catch (Exception e) {
                    throw new RuntimeException(e);


        } finally {
            if (transaction.isActive())
                transaction.rollback();
        }
    }

    @FXML
    void importer(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Importer une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            imagePath = selectedFile.toURI().toString();
            Image image = new Image(imagePath);
            imageView.setImage(image);
        }
    }


    @FXML
    void getData(MouseEvent event) {
        Vehicule vehicule = table.getSelectionModel().getSelectedItem();

        id = vehicule.getVehiculeId();
        matTfd.setText(vehicule.getMatricule());
        modeleTfd.setText(vehicule.getModele());
        marqueTfd.setText(vehicule.getMarque());
        nbPlaceTfd.setText(String.valueOf(vehicule.getNombrePlace()));
        etatTfd.setValue(vehicule.getEtat());
        imagePath = vehicule.getImage();
        if (imagePath != null) {
            Image image = new Image(imagePath);
            imageView.setImage(image);
        }
        conducteurTfd.getSelectionModel().select(vehicule.getUser().getNom() +" "+ vehicule.getUser().getPrenom());
        enregistrerBtn.setDisable(true);
        etatTfd.setDisable(false);

    }

    @FXML
    void modifier(ActionEvent event) {
        try {

            if (marqueTfd.getText().isBlank() || matTfd.getText().isBlank() || modeleTfd.getText().isBlank() || etatTfd.getValue().isBlank()  || nbPlaceTfd.getText().isBlank()  || conducteurTfd.getValue().isBlank() || imageView.getImage() == null) {
                Notification.NotifError("ERREUR", " Tous les champs sont obligatoires!");
                return;
            }
            String mat = matTfd.getText();
            User vehiculeMat = dao.verifLogin(mat);



            if (vehiculeMat != null) {
                Notification.NotifError("ERREUR", "cette Matricule existe deja! Veuiilez choisir un autre ");
                return;
            }


            transaction.begin();

            Vehicule vehicule = entityManager.find(Vehicule.class, id);

            if ("disponible".equals(etatTfd.getValue()) && "plein".equals(vehicule.getEtat())){
               vehicule.setCptPlace(0);
            }
            vehicule.setMarque(marqueTfd.getText());
            vehicule.setMatricule(matTfd.getText());
            vehicule.setModele(modeleTfd.getText());
            vehicule.setNombrePlace(Integer.parseInt(nbPlaceTfd.getText()));
            vehicule.setImage(imagePath);
            vehicule.setEtat(etatTfd.getValue());

            entityManager.persist(vehicule);
            transaction.commit();
            viderChamps();
            loadTable();
            table.refresh();
            enregistrerBtn.setDisable(false);
        } finally {
            if (transaction.isActive())
                transaction.rollback();
        }
    }

        @FXML
    void supprimer(ActionEvent event) {
        try{
            transaction.begin();
            Vehicule vehicule = entityManager.find(Vehicule.class, id);
            entityManager.remove(vehicule);
            transaction.commit();
            viderChamps();
            loadTable();
        }finally {
            if (transaction.isActive())
                transaction.rollback();
        }
    }

    @FXML
    void search(ActionEvent event) {
        String vehicule = searchTfd.getText();
        if (!vehicule.isBlank()) {
            ObservableList<Vehicule> Vehicule = searchVoiture(vehicule + "%");

            if (!Vehicule.isEmpty()) {
                table.setItems(Vehicule);
            }
        }
    }

    private ObservableList<Vehicule> searchVoiture(String vehicule) {
        ObservableList<Vehicule> Vehicule = FXCollections.observableArrayList();
        try {
            TypedQuery<Vehicule> query = entityManager.createQuery(
                    "SELECT v FROM Vehicule v WHERE v.matricule LIKE :vehicule OR v.marque LIKE :vehicule OR v.modele LIKE :vehicule", Vehicule.class);
            query.setParameter("vehicule", vehicule);
            Vehicule.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Vehicule;
    }



    public void viderChamps() {
        modeleTfd.setText("");
        marqueTfd.setText("");
        matTfd.setText("");
        nbPlaceTfd.setText("");

        etatTfd.setValue("");

        searchTfd.setText("");
        imageView.setImage(null);
        imagePath = null;
        conducteurTfd.getSelectionModel().clearSelection();
        enregistrerBtn.setDisable(false);
        etatTfd.setDisable(true);
    }

    public void loadTable() {

        ObservableList<Vehicule> vehicules = getVehicules();
        table.setItems(vehicules);

        idCol.setCellValueFactory(new PropertyValueFactory<Vehicule, Integer>("oeuvreId"));
        marqueCol.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("marque"));
        imageCol.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("image"));
        modeleCol.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("modele"));
        matCol.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("matricule"));
        placeCol.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("nombrePlace"));
        etatCol.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("etat"));
        conducteurCol.setCellValueFactory(cellData -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Vehicule vehicule = cellData.getValue();
            if (vehicule.getUser() != null) {
                property.setValue(vehicule.getUser().getNom() + " " + vehicule.getUser().getPrenom());
            } else {
                property.setValue("Aucun");
            }
            return property;
        });
    }


        public ObservableList<Vehicule> getVehicules() {
        ObservableList<Vehicule> vehicules = FXCollections.observableArrayList();
        TypedQuery<Vehicule> query = entityManager.createNamedQuery("listVehicule", Vehicule.class);
        vehicules.addAll(query.getResultList());
        return vehicules;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        etatTfd.getItems().addAll("disponible", "plein");
        date = Date.valueOf(LocalDate.now());
        setListeConducteur();
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
}
