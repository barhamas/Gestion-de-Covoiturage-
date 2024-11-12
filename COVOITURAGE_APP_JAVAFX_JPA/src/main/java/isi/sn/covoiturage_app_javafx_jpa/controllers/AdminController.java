package isi.sn.covoiturage_app_javafx_jpa.controllers;


import isi.sn.covoiturage_app_javafx_jpa.dao.DB;
import isi.sn.covoiturage_app_javafx_jpa.entities.User;
import isi.sn.covoiturage_app_javafx_jpa.tools.Outils;
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
import java.util.ResourceBundle;

public class AdminController extends DB implements Initializable {

    @FXML
    private Label Current_Form;

    @FXML
    private AnchorPane appointments_form;

    @FXML
    private Button userBtn;

    @FXML
    private Button collectionBtn;

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
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_tA;

    @FXML
    private Label dateSystem;

    @FXML
    private Button effacerBtn;

    @FXML
    private TableColumn<User, String> emailCol;

    @FXML
    private TextField emailTfd;

    @FXML
    private Button enregistrerBtn;

    @FXML
    private TableColumn<User, Integer> idCol;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button modifierBtn;

    @FXML
    private Label nav_adminID;

    @FXML
    private Label nav_username;

    @FXML
    private Label nameTfd;

    @FXML
    private Label idTfd;

    @FXML
    private TableColumn<User, String> nomCol;

    @FXML
    private TextField nomTfd;

    @FXML
    private TableColumn<User, String> passwordCol;

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
    private TableColumn<User, String> prenomCol;

    @FXML
    private Button profile;

    @FXML
    private TableColumn<User, String> roleCol;

    @FXML
    private ComboBox<String> roleTfd;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField searchTfd;

    @FXML
    private TableColumn<User, String> statutCol;

    @FXML
    private ComboBox<String> statutTfd;

    @FXML
    private Button supprimerBtn;

    @FXML
    private TableView<User> table;

    @FXML
    private TableColumn<User, String> usernameCol;

    @FXML
    private Button venteBtn;

    private int idU;




    @FXML
    void deconnecter(ActionEvent event) {
        try {
            Outils.load(event,"PAGE DE DECONNEXION ","/pages/connexion.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



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
    void effacer(ActionEvent event) {
        clearFd();
    }

    @FXML
    void modifier(ActionEvent event) {
        try{

            transaction.begin();
            User user = entityManager.find(User.class, idU);
            user.setRole(roleTfd.getValue());
            user.setStatut(statutTfd.getValue());
            entityManager.persist(user);
            transaction.commit();
            clearFd();
            loadTable();
            table.refresh();
        }finally {
            if (transaction.isActive())
                transaction.rollback();
        }
    }

//    @FXML
//    void supprimer(ActionEvent event) {
//        try{
//            transaction.begin();
//            User user = entityManager.find(User.class, idU);
//            entityManager.remove(user);
//            transaction.commit();
//            clearFd();
//            loadTable();
//        }finally {
//            if (transaction.isActive())
//                transaction.rollback();
//        }
//    }

    public void clearFd() {
        nomTfd.setText("");
        roleTfd.setValue("");
        statutTfd.setValue("");
        emailTfd.setText("");

    }

    @FXML
    void getData(MouseEvent event) {
        User b =  table.getSelectionModel().getSelectedItem();
        idU  = b.getUtilisateurId();

        nomTfd.setText(b.getNom());
        roleTfd.setValue(b.getRole());
        statutTfd.setValue(b.getStatut());
        emailTfd.setText(b.getEmail());
    }

    public void loadTable(){
        table.setItems(getUsers());
        idCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("utilisateur_id"));
        nomCol.setCellValueFactory(new PropertyValueFactory<User, String>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<User, String>("prenom"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        roleCol.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
        statutCol.setCellValueFactory(new PropertyValueFactory<User, String>("Statut"));
        emailCol.setCellValueFactory(new PropertyValueFactory<User, String>("email"));

    }



    private ObservableList<User> getUsers(){

        ObservableList<User> users = FXCollections.observableArrayList();
        try {
            TypedQuery<User> query = entityManager.createQuery(
                    "SELECT v FROM User v WHERE v.utilisateurId != :userId", User.class);
            query.setParameter("userId", ConnexionController.getUtilisateur().getUtilisateurId());
            users.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @FXML
    void search(ActionEvent event) {
        String nom = searchTfd.getText();
        if (!nom.isBlank()) {
            ObservableList<User> users = searchClientByNom(nom + "%"); // Ajouter le caractère de joker '%' pour rechercher les prénoms commençant par 'prenom'

            if (!users.isEmpty()) {
                table.setItems(users);
            }
        }
    }

    private ObservableList<User> searchClientByNom(String nom) {
        ObservableList<User> users = FXCollections.observableArrayList();
        try {
            TypedQuery<User> query = entityManager.createQuery(
                    "SELECT u FROM User u WHERE u.nom LIKE :nom", User.class);
            query.setParameter("nom", nom);
            users.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roleTfd.getItems().addAll("admin", "gestionnaire", "conducteur", "passager");
        statutTfd.getItems().addAll("active", "desactive");
        loadTable();
    }


}
