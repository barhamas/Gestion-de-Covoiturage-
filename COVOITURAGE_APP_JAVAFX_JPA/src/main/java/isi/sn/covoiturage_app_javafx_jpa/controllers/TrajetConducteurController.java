package isi.sn.covoiturage_app_javafx_jpa.controllers;

import isi.sn.covoiturage_app_javafx_jpa.dao.DB;
import isi.sn.covoiturage_app_javafx_jpa.entities.Trajet;
import isi.sn.covoiturage_app_javafx_jpa.entities.Vehicule;
import isi.sn.covoiturage_app_javafx_jpa.tools.Outils;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
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

public class TrajetConducteurController extends DB implements Initializable {

    @FXML
    private Label Current_Form;

    @FXML
    private AnchorPane appointments_form;

    @FXML
    private Button artisteBtn;

    @FXML
    private Button clientBtn;

    @FXML
    private BarChart<String, Integer> barChart;
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

    int nbJanvier, nbFevrier, nbMars, nbAvril, nbMai, nbJuin,
            nbJuillet, nbAout, nbSeptembre, nbOctobre, nbNovembre, nbDecembre;

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

    private ObservableList<Trajet> fetchOeuvres() {
        ObservableList<Trajet> trajets = FXCollections.observableArrayList();
        try {
            TypedQuery<Trajet> query = entityManager.createQuery(
                    "SELECT t FROM Trajet t WHERE t.user.utilisateurId=:idUser ", Trajet.class);
            query.setParameter("idUser", ConnexionController.getUser_id());
            trajets.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trajets;
    }

    public void loadOeuvres() {
        ObservableList<Trajet> trajets = fetchOeuvres();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Trajet trajet : trajets) {
            LocalDate dateCreation = LocalDate.parse(trajet.getDate().toString(), formatter);
            int month = dateCreation.getMonthValue();
            int year = dateCreation.getYear();

            if (year == 2024 && month >= 7 && month <= 12) {
                switch (month) {
                    case 7: nbJuillet++; break;
                    case 8: nbAout++; break;
                    case 9: nbSeptembre++; break;
                    case 10: nbOctobre++; break;
                    case 11: nbNovembre++; break;
                    case 12: nbDecembre++; break;
                }
            } else if (year == 2025 && month >= 1 && month <= 6) {
                switch (month) {
                    case 1: nbJanvier++; break;
                    case 2: nbFevrier++; break;
                    case 3: nbMars++; break;
                    case 4: nbAvril++; break;
                    case 5: nbMai++; break;
                    case 6: nbJuin++; break;
                }
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadOeuvres();  // Charger les oeuvres et mettre à jour le graphique

        loadOeuvres();

        XYChart.Series<String, Integer> series2 = new XYChart.Series<>();
        series2.setName("NOMBRE TRAJET");

        series2.getData().add(new XYChart.Data<>("Juillet", (int) nbJuillet));
        series2.getData().add(new XYChart.Data<>("Aout", (int) nbAout));
        series2.getData().add(new XYChart.Data<>("Septembre", (int) nbSeptembre));
        series2.getData().add(new XYChart.Data<>("Octobre", (int) nbOctobre));
        series2.getData().add(new XYChart.Data<>("Novembre", (int) nbNovembre));
        series2.getData().add(new XYChart.Data<>("Decembre", (int) nbDecembre));
        series2.getData().add(new XYChart.Data<>("Janvier", (int) nbJanvier));
        series2.getData().add(new XYChart.Data<>("Fevrier", (int) nbFevrier));
        series2.getData().add(new XYChart.Data<>("Mars", (int) nbMars));
        series2.getData().add(new XYChart.Data<>("Avril", (int) nbAvril));
        series2.getData().add(new XYChart.Data<>("Mai", (int) nbMai));
        series2.getData().add(new XYChart.Data<>("Juin", (int) nbJuin));

        barChart.getData().addAll(series2);

    }
}