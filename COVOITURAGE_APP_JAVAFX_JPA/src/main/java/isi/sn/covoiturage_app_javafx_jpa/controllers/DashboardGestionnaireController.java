package isi.sn.covoiturage_app_javafx_jpa.controllers;

import isi.sn.covoiturage_app_javafx_jpa.dao.DB;

import isi.sn.covoiturage_app_javafx_jpa.entities.User;
import isi.sn.covoiturage_app_javafx_jpa.entities.Vehicule;
import isi.sn.covoiturage_app_javafx_jpa.tools.Outils;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import javax.persistence.TypedQuery;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardGestionnaireController extends DB implements Initializable {

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
    private ImageView image1;

    @FXML
    private ImageView image2;

    @FXML
    private ImageView image3;

    @FXML
    private ImageView image4;

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
    private Button patients_Confirmbtn;

    @FXML
    private TextField patients_CurrentID;

    @FXML
    private Label patients_PA_CurrentID;

    @FXML
    private Label patients_PA_Password;

    @FXML
    private Label passagerTfd;

    @FXML
    private Label vehiculeTfd;

    @FXML
    private Label conducteurTfd;
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
    private AnchorPane image_form;

    @FXML
    private AnchorPane A_form;

    @FXML
    private ComboBox<?> patients_gender;

    @FXML
    private TextField patients_mobileNumber;

    @FXML
    private TextField patients_password;

    @FXML
    private TextField patients_patientName;

    @FXML
    private Button profile;

    @FXML
    private Label soldeTfd;

    @FXML
    private Button venteBtn;

    private int nbConducteur, nbPassager, nbVehicule;





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




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        slider();
        loadUsers();
        loadVehicules();
    }

    public void slider(){

        new Thread(){

            public void run(){
                int count = 0;

                try {
                    while(true){

                        switch (count){

                            case 0 :

                                Thread.sleep(5000);

                                TranslateTransition slider1 = new TranslateTransition();
                                slider1.setNode(image1);
                                slider1.setDuration(Duration.seconds(1));
                                slider1.setToX(0);
                                slider1.setOnFinished(e -> updateVisibility());
                                slider1.play();

                                TranslateTransition slider2 = new TranslateTransition();
                                slider2.setNode(image2);
                                slider2.setDuration(Duration.seconds(1));
                                slider2.setToX(0);
                                slider2.setOnFinished(e -> updateVisibility());
                                slider2.play();

                                TranslateTransition slider3 = new TranslateTransition();
                                slider3.setNode(image3);
                                slider3.setDuration(Duration.seconds(1));
                                slider3.setToX(-600);
                                slider3.setOnFinished(e -> updateVisibility());
                                slider3.play();

                                TranslateTransition slider4 = new TranslateTransition();
                                slider4.setNode(image4);
                                slider4.setDuration(Duration.seconds(1));
                                slider4.setToX(-1200);
                                slider4.setOnFinished(e -> updateVisibility());
                                slider4.play();

                                count = 1;
                                break;

                            case 1 :

                                Thread.sleep(5000);

                                TranslateTransition slider5 = new TranslateTransition();
                                slider5.setNode(image1);
                                slider5.setDuration(Duration.seconds(1));
                                slider5.setToX(600);
                                slider5.setOnFinished(e -> updateVisibility());
                                slider5.play();

                                TranslateTransition slider6 = new TranslateTransition();
                                slider6.setNode(image2);
                                slider6.setDuration(Duration.seconds(1));
                                slider6.setToX(600);
                                slider6.setOnFinished(e -> updateVisibility());
                                slider6.play();

                                TranslateTransition slider7 = new TranslateTransition();
                                slider7.setNode(image3);
                                slider7.setDuration(Duration.seconds(1));
                                slider7.setToX(0);
                                slider7.setOnFinished(e -> updateVisibility());
                                slider7.play();

                                TranslateTransition slider8 = new TranslateTransition();
                                slider8.setNode(image4);
                                slider8.setDuration(Duration.seconds(1));
                                slider8.setToX(-600);
                                slider8.setOnFinished(e -> updateVisibility());
                                slider8.play();

                                count = 2;
                                break;

                            case 2 :

                                Thread.sleep(5000);

                                TranslateTransition slider9 = new TranslateTransition();
                                slider9.setNode(image1);
                                slider9.setDuration(Duration.seconds(1));
                                slider9.setToX(1200);
                                slider9.setOnFinished(e -> updateVisibility());
                                slider9.play();

                                TranslateTransition slider10 = new TranslateTransition();
                                slider10.setNode(image2);
                                slider10.setDuration(Duration.seconds(1));
                                slider10.setToX(1200);
                                slider10.setOnFinished(e -> updateVisibility());
                                slider10.play();

                                TranslateTransition slider11 = new TranslateTransition();
                                slider11.setNode(image3);
                                slider11.setDuration(Duration.seconds(1));
                                slider11.setToX(600);
                                slider11.setOnFinished(e -> updateVisibility());
                                slider11.play();

                                TranslateTransition slider12 = new TranslateTransition();
                                slider12.setNode(image4);
                                slider12.setDuration(Duration.seconds(1));
                                slider12.setToX(0);
                                slider12.setOnFinished(e -> updateVisibility());
                                slider12.play();

                                count = 3;
                                break;

                            case 3 :

                                Thread.sleep(5000);

                                TranslateTransition slider13 = new TranslateTransition();
                                slider13.setNode(image1);
                                slider13.setDuration(Duration.seconds(1));
                                slider13.setToX(1200);
                                slider13.setOnFinished(e -> updateVisibility());
                                slider13.play();

                                TranslateTransition slider14 = new TranslateTransition();
                                slider14.setNode(image2);
                                slider14.setDuration(Duration.seconds(1));
                                slider14.setToX(1200);
                                slider14.setOnFinished(e -> updateVisibility());
                                slider14.play();

                                TranslateTransition slider15 = new TranslateTransition();
                                slider15.setNode(image3);
                                slider15.setDuration(Duration.seconds(1));
                                slider15.setToX(1200);
                                slider15.setOnFinished(e -> updateVisibility());
                                slider15.play();

                                TranslateTransition slider16 = new TranslateTransition();
                                slider16.setNode(image4);
                                slider16.setDuration(Duration.seconds(1));
                                slider16.setToX(600);
                                slider16.setOnFinished(e -> updateVisibility());
                                slider16.play();

                                count = 0;
                                break;

                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }.start();
    }

    private void updateVisibility() {
        image1.setVisible(isInsideImageForm(image1));
        image2.setVisible(isInsideImageForm(image2));
        image3.setVisible(isInsideImageForm(image3));
        image4.setVisible(isInsideImageForm(image4));
    }

    private boolean isInsideImageForm(ImageView imageView) {
        double imageX = imageView.getLayoutX() + imageView.getTranslateX();
        double imageY = imageView.getLayoutY() + imageView.getTranslateY();
        double formX = image_form.getLayoutX();
        double formY = image_form.getLayoutY();
        double formWidth = image_form.getPrefWidth();
        double formHeight = image_form.getPrefHeight();

        return (imageX >= formX && imageX <= formX + formWidth) &&
                (imageY >= formY && imageY <= formY + formHeight);
    }


    public void loadUsers() {
        nbPassager= nbConducteur = 0;
        ObservableList<User> users = fetchUsers();

        users.forEach(user -> {
            if ("passager".equals(user.getRole())) {
                nbPassager++;
            } else if ("conducteur".equals(user.getRole())) {
                nbConducteur++;
            }
        });

        conducteurTfd.setText(String.valueOf(nbConducteur));
        passagerTfd.setText(String.valueOf(nbPassager));
    }


    public void loadVehicules() {
        nbVehicule = fetchVehicules().size();
        vehiculeTfd.setText(String.valueOf(nbVehicule));
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


}
