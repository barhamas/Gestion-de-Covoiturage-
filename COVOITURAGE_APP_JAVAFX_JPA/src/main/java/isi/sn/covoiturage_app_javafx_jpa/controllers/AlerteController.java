package isi.sn.covoiturage_app_javafx_jpa.controllers;



import isi.sn.covoiturage_app_javafx_jpa.tools.Outils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AlerteController {

    @FXML
    private Button returnBtn;

    @FXML
    void retour(ActionEvent event) {
        try {
            Outils.load(event, "PAGE  DE CONNEXION ", "/pages/connexion.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
