package isi.sn.covoiturage_app_javafx_jpa.controllers;

import isi.sn.covoiturage_app_javafx_jpa.dao.DB;
import isi.sn.covoiturage_app_javafx_jpa.entities.Reservation;
import isi.sn.covoiturage_app_javafx_jpa.tools.Outils;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javax.persistence.TypedQuery;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class SoldeConducteurController  extends DB implements Initializable{

    @FXML
    private Label Current_Form;

    @FXML
    private AnchorPane appointments_form;

    @FXML
    private Button artisteBtn;

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
    private PieChart pieChart;

    @FXML
    private Button profile;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField searchTfd;

    @FXML
    private Button venteBtn;

    

    double totalJanvier, totalFevrier, totalMars, totalAvril, totalMai, totalJuin,
            totalJuillet, totalAout, totalSeptembre, totalOctobre, totalNovembre, totalDecembre;


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

            if ("valide".equals(reservation.getEtat()) && (reservation.getUser().getUsername() == ConnexionController.getUsernameConducteur())) {
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
    


    // Mettre à jour le graphique avec les nouvelles données
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Juillet", totalJuillet),
                new PieChart.Data("Aout", totalAout),
                new PieChart.Data("Septembre", totalSeptembre),
                new PieChart.Data("Octobre", totalOctobre),
                new PieChart.Data("Novembre", totalNovembre),
                new PieChart.Data("Decembre", totalDecembre),
                new PieChart.Data("Janvier", totalJanvier),
                new PieChart.Data("Fevrier", totalFevrier),
                new PieChart.Data("Mars", totalMars),
                new PieChart.Data("Avril", totalAvril),
                new PieChart.Data("Mai", totalMai),
                new PieChart.Data("Juin", totalJuin)
        );

        pieChart.getData().setAll(pieChartData);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadReservations();  // Charger les oeuvres et mettre à jour le graphique

        // Ajouter les données au graphique
        pieChart.getData().forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), " ", data.pieValueProperty()
                        )
                )
        );
    }
}
