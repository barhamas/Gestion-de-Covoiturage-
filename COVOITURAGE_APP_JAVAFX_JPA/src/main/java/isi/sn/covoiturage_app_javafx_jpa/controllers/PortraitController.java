package isi.sn.covoiturage_app_javafx_jpa.controllers;

import isi.sn.covoiturage_app_javafx_jpa.dao.DB;

import isi.sn.covoiturage_app_javafx_jpa.entities.Trajet;
import isi.sn.covoiturage_app_javafx_jpa.tools.Outils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PortraitController extends DB implements Initializable {
    @FXML
    private AnchorPane cardre_form;

    @FXML
    private Button etatBtn;

    @FXML
    private ImageView imageVieuw;

    @FXML
    private Label nom_art;

    @FXML
    private Label nom_img;

    @FXML
    private Label prix_img;

    @FXML
    private Button vendreBtn;
    private Image image;
    private Trajet trajet;

    private static String titre;
    private static String titres;

    public static String getTitre() {
        return titre;
    }

    public static void setTitre(String titre) {
        PortraitController.titre = titre;
    }

    public void setData(Trajet trajet) {
        this.trajet = trajet;
        nom_img.setText(trajet.getNomTrajet());
        prix_img.setText(String.valueOf(trajet.getPrix()));
        String etat = trajet.getEtat();

        titres=trajet.getNomTrajet();

        // Changer la couleur du bouton en fonction de l'état
        if (etat.equals("active")) {
            etatBtn.setStyle("-fx-background-color: green;");
            etatBtn.setText("Dispo");
            vendreBtn.setDisable(false);
        }
        if (trajet.getVehicule().getEtat().equals("plein")){
            etatBtn.setStyle("-fx-background-color: red;");
            etatBtn.setText("Occupe");
            vendreBtn.setDisable(true);
        }

        nom_art.setText(trajet.getUser().getPrenom() + " " + trajet.getUser().getNom());
        String path = trajet.getVehicule().getImage();
        image = new Image(path, 144, 112, false, true);
        imageVieuw.setImage(image);


    }


    @FXML
    void portraitData(MouseEvent event) {
        titre=titres;
    }

    @FXML
    void changeEtat(ActionEvent event) {

    }


    @FXML
    void vendre(ActionEvent event) {



        ExpositionController.setOeuvre(this.trajet);
        ExpositionController.setImg(this.trajet.getVehicule().getMatricule());
        ExpositionController.setIdVehicule(this.trajet.getVehicule().getVehiculeId());
        ExpositionController.setPrix(this.trajet.getPrix());
        ExpositionController.setTitres(this.trajet.getNomTrajet());
        ExpositionController.setConducteur(this.trajet.getUser().getPrenom()+ " "+ this.trajet.getUser().getNom());
        ExpositionController.setEtat(this.trajet.getEtat());
        ExpositionController.setCpt(this.trajet.getVehicule().getCptPlace());

        try {
            Outils.load(event,"PAGE DE GESTIONNAIRE ", "/pages/reservation.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
