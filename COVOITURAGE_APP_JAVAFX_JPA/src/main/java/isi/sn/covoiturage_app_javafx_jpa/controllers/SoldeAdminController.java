package isi.sn.covoiturage_app_javafx_jpa.controllers;

import isi.sn.covoiturage_app_javafx_jpa.dao.DB;

import isi.sn.covoiturage_app_javafx_jpa.entities.Reservation;
import isi.sn.covoiturage_app_javafx_jpa.tools.Outils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import javax.persistence.TypedQuery;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class SoldeAdminController extends DB implements Initializable {

    @FXML
    private Label Current_Form;

    @FXML
    private AnchorPane appointments_form;

    @FXML
    private Button artisteBtn;

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private Button clientBtn;

    @FXML
    private Button dashboardBtn;

    @FXML
    private Label dateSystem;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Label nav_adminID;

    @FXML
    private Label nav_username;

    @FXML
    private Button profile;

    @FXML
    private Button venteBtn;

    // Variables to store the total price of sold artworks for each month
    double totalJanvier, totalFevrier, totalMars, totalAvril, totalMai, totalJuin,
            totalJuillet, totalAout, totalSeptembre, totalOctobre, totalNovembre, totalDecembre;


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


    private ObservableList<Reservation> fetchReservations() {

        TypedQuery<Reservation> query = entityManager.createQuery("SELECT r FROM Reservation r", Reservation.class);
        List<Reservation> results = query.getResultList();

        return FXCollections.observableArrayList(results);
    }

    public void loadReservations() {
        ObservableList<Reservation> reservations = fetchReservations();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Reservation reservation : reservations) {
            LocalDate dateCreation = LocalDate.parse(reservation.getDate().toString(), formatter);
            int month = dateCreation.getMonthValue();
            int year = dateCreation.getYear();

            if ("valide".equals(reservation.getEtat())) {
                double prix = reservation.getPrix();

                if (year == 2024 && month >= 7 && month <= 12) {
                    switch (month) {
                        case 7:
                            totalJuillet += prix;
                            break;
                        case 8:
                            totalAout += prix;
                            break;
                        case 9:
                            totalSeptembre += prix;
                            break;
                        case 10:
                            totalOctobre += prix;
                            break;
                        case 11:
                            totalNovembre += prix;
                            break;
                        case 12:
                            totalDecembre += prix;
                            break;
                    }
                } else if (year == 2025 && month >= 1 && month <= 6) {
                    switch (month) {
                        case 1:
                            totalJanvier += prix;
                            break;
                        case 2:
                            totalFevrier += prix;
                            break;
                        case 3:
                            totalMars += prix;
                            break;
                        case 4:
                            totalAvril += prix;
                            break;
                        case 5:
                            totalMai += prix;
                            break;
                        case 6:
                            totalJuin += prix;
                            break;
                    }
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadReservations();

        XYChart.Series<String, Integer> series2 = new XYChart.Series<>();
        series2.setName("Prix Total");

        series2.getData().add(new XYChart.Data<>("Juillet", (int) totalJuillet));
        series2.getData().add(new XYChart.Data<>("Aout", (int) totalAout));
        series2.getData().add(new XYChart.Data<>("Septembre", (int) totalSeptembre));
        series2.getData().add(new XYChart.Data<>("Octobre", (int) totalOctobre));
        series2.getData().add(new XYChart.Data<>("Novembre", (int) totalNovembre));
        series2.getData().add(new XYChart.Data<>("Decembre", (int) totalDecembre));
        series2.getData().add(new XYChart.Data<>("Janvier", (int) totalJanvier));
        series2.getData().add(new XYChart.Data<>("Fevrier", (int) totalFevrier));
        series2.getData().add(new XYChart.Data<>("Mars", (int) totalMars));
        series2.getData().add(new XYChart.Data<>("Avril", (int) totalAvril));
        series2.getData().add(new XYChart.Data<>("Mai", (int) totalMai));
        series2.getData().add(new XYChart.Data<>("Juin", (int) totalJuin));

        barChart.getData().addAll(series2);
    }
}
