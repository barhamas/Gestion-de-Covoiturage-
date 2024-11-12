package isi.sn.covoiturage_app_javafx_jpa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;



public class App extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/connexion.fxml")));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle(" PAGE D' INSCRIPTION ");
        stage.  show();
    }

}

