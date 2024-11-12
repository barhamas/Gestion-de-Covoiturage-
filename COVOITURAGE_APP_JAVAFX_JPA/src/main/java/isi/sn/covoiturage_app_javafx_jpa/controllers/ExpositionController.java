package isi.sn.covoiturage_app_javafx_jpa.controllers;

import isi.sn.covoiturage_app_javafx_jpa.dao.DB;
import isi.sn.covoiturage_app_javafx_jpa.entities.Trajet;
import isi.sn.covoiturage_app_javafx_jpa.entities.User;
import isi.sn.covoiturage_app_javafx_jpa.entities.Vehicule;
import isi.sn.covoiturage_app_javafx_jpa.tools.Outils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ExpositionController extends DB implements Initializable {

    @FXML
    private Label Current_Form;

    @FXML
    private AnchorPane appointments_form;

    @FXML
    private Button artisteBtn;

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
    private Button actualiserBtn;

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
    private GridPane grille;

    @FXML
    private AnchorPane main_form;

    @FXML
    private AnchorPane menu;

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
    private ScrollPane scrollPane;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField searchTfd;

    @FXML
    private Button venteBtn;

    private static Trajet trajet;
    private static String img;
    private static int prix;
    private static int cpt;

    public static int getCpt() {
        return cpt;
    }

    public static void setCpt(int cpt) {
        ExpositionController.cpt = cpt;
    }

    private static int idVehicule;

    public static int getIdVehicule() {
        return idVehicule;
    }

    public static void setIdVehicule(int idVehicule) {
        ExpositionController.idVehicule = idVehicule;
    }

    private static String conducteur;
    private static String etat;

    public static String getConducteur() {
        return conducteur;
    }

    public static void setConducteur(String conducteur) {
        ExpositionController.conducteur = conducteur;
    }

    public static String getEtat() {
        return etat;
    }

    public static void setEtat(String etat) {
        ExpositionController.etat = etat;
    }

    private static int idOeuvre;

    public static int getIdOeuvre() {
        return idOeuvre;
    }

    public static void setIdOeuvre(int idOeuvre) {
        ExpositionController.idOeuvre = idOeuvre;
    }

    private static String titres;

    public static Trajet getOeuvre() {
        return trajet;
    }

    public static void setOeuvre(Trajet trajet) {
        ExpositionController.trajet = trajet;
    }

    public static String getImg() {
        return img;
    }

    public static void setImg(String img) {
        ExpositionController.img = img;
    }

    public static int getPrix() {
        return prix;
    }

    public static void setPrix(int prix) {
        ExpositionController.prix = prix;
    }

    public static String getTitres() {
        return titres;
    }

    public static void setTitres(String titres) {
        ExpositionController.titres = titres;
    }



    private ObservableList<Trajet> listData = FXCollections.observableArrayList();

    private ObservableList<Trajet> fetchDataFromDatabase() {
        EntityManager em = getEntityManager();
        TypedQuery<Trajet> query = em.createQuery("SELECT t FROM Trajet t where t.etat!='inactive' ", Trajet.class);
        List<Trajet> results = query.getResultList();
        em.close();
        return FXCollections.observableArrayList(results);
    }

    public void menu() {
        listData.clear();
        listData.addAll(fetchDataFromDatabase());
        updateGrid(listData);
    }

    private void updateGrid(ObservableList<Trajet> data) {
        int row = 0;
        int column = 0;

        grille.getRowConstraints().clear();
        grille.getColumnConstraints().clear();
        grille.setHgap(10); // Horizontal gap
        grille.setVgap(10); // Vertical gap
        grille.setPadding(new Insets(10, 10, 10, 30)); // Padding around the grid

        grille.getChildren().clear(); // Clear previous content

        for (int i = 0; i < data.size(); i++) {
            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/pages/portrait.fxml"));
                AnchorPane pane = load.load();
                PortraitController portrait = load.getController();
                portrait.setData(data.get(i));

                if (column == 3) {
                    column = 0;
                    row += 1;
                }
                grille.add(pane, column++, row);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    void actualiser(ActionEvent event) {
        ObservableList<Trajet> filteredList = FXCollections.observableArrayList();

        for (Trajet trajet : listData) {
                filteredList.add(trajet);
        }

        updateGrid(filteredList);
        searchTfd.setText("");
    }

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
    void search(ActionEvent event) {
        String searchText = searchTfd.getText().trim().toLowerCase();
        ObservableList<Trajet> filteredList = FXCollections.observableArrayList();

        for (Trajet trajet : listData) {
            if (trajet.getNomTrajet().toLowerCase().startsWith(searchText)) {
                filteredList.add(trajet);
            }
        }

        updateGrid(filteredList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menu();
    }
}
