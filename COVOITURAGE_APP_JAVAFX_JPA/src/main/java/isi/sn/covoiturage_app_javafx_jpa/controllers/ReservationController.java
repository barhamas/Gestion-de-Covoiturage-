package isi.sn.covoiturage_app_javafx_jpa.controllers;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import isi.sn.covoiturage_app_javafx_jpa.dao.DB;


import isi.sn.covoiturage_app_javafx_jpa.entities.Reservation;

import isi.sn.covoiturage_app_javafx_jpa.entities.Trajet;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

import javax.persistence.TypedQuery;
import java.io.IOException;
import java.net.URL;
import java.net.UnknownServiceException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ReservationController extends DB implements Initializable {


    @FXML
    private TableColumn<Reservation, String> conducteurCol;

    @FXML
    private ComboBox<String > conducteurTfd;


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
    private TableColumn<Reservation, String> dateCol;

    @FXML
    private DatePicker dateTfd;

    @FXML
    private Button enregistrerBtn;

    @FXML
    private TableColumn<Reservation, String> etatCol;

    @FXML
    private ComboBox<String> etatTfd;

    @FXML
    private TableColumn<Reservation, Integer> idCol;

    @FXML
    private AnchorPane main_form;

    @FXML
    private TableColumn<Reservation, String > numCol;

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
    private Button searchBtn1;

    @FXML
    private TextField searchTfd1;

    @FXML
    private TableColumn<?, ?> statutCol1;

    @FXML
    private TableView<Reservation> table;

    @FXML
    private TextField tarifTfd;

    @FXML
    private TableColumn<Reservation, String > trafifCol;

    @FXML
    private TableColumn<Reservation, String> trajetCol;

    @FXML
    private ComboBox<String> trajetTfd;

    @FXML
    private ComboBox<String> vehiculeTfd;

    @FXML
    private TableColumn<Reservation, String> voitureCol;

    private int idO;
    private int id;
    private static int nbplace;

    private static final String DOWNLOADS_PATH = "C:/Users/ADMIN/Downloads/";



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
    void annuler(ActionEvent event){
        try {
            Outils.load(event,"PAGE DE PROFILE ", "/pages/exposition.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void enregistrer(ActionEvent event) {
        try {


            if (etatTfd.getValue().isBlank()|| vehiculeTfd.getValue().isBlank()|| conducteurTfd.getValue().isBlank() || tarifTfd.getText().isBlank()|| trajetTfd.getValue().isBlank() || dateTfd.getValue()==null){
                Notification.NotifError("ERREUR", "Impossible d'effectuer cette reservation");
                return;
            }

            transaction.begin();

            Reservation reservation = new Reservation();
            id = ExpositionController.getIdVehicule();
            nbplace=ExpositionController.getCpt();
            Vehicule vehicules = entityManager.find(Vehicule.class, id);

            if(nbplace==vehicules.getNombrePlace()){
                vehicules.setEtat("plein");
                entityManager.persist(vehicules);
                reservation.setVehicule(vehicules);

            }else{
                nbplace++;
                ExpositionController.setCpt(nbplace);
            }

            if ("plein".equals(vehicules.getEtat())){
                Notification.NotifError("ERREUR","IL Y A PLUS DE PLACE DISPONIBLE" );
            }


            LocalDateTime dateEtHeure = LocalDateTime.now();
            DateTimeFormatter dateFormate = DateTimeFormatter.ofPattern("yyyy");
            String date = dateEtHeure.format(dateFormate);

            int annee = Integer.parseInt(date);
            annee++;

            String numeroReservation= generateNumReservation(ExpositionController.getImg(),ExpositionController.getIdVehicule(),annee,ConnexionController.getIdUser());



            reservation.setNumeroReservation(numeroReservation);
            reservation.setPrix(Integer.parseInt(tarifTfd.getText()));
            reservation.setEtat((etatTfd.getValue()));


            LocalDate localDate = dateTfd.getValue();
            if (localDate != null) {
                reservation.setDate(Date.valueOf(localDate));
            }

            String clientSelected = conducteurTfd.getValue();

                String[] nameParts = clientSelected.split(" ");
                if (nameParts.length == 2) {
                    String prenom = nameParts[0];
                    String nom = nameParts[1];

                    TypedQuery<User> clientQuery = entityManager.createQuery(
                            "SELECT c FROM User c WHERE c.nom = :nom AND c.prenom = :prenom",
                            User.class
                    );
                    clientQuery.setParameter("nom", nom);
                    clientQuery.setParameter("prenom", prenom);

                    List<User> users = clientQuery.getResultList();
                    if (!users.isEmpty()) {
                        User user = users.get(0);
                        reservation.setUser(user);
                    }

            String vehiculeSelected = vehiculeTfd.getValue();
            TypedQuery<Vehicule> vehiculeQuery = entityManager.createQuery("SELECT s FROM Vehicule s WHERE s.matricule = :matricule", Vehicule.class);
                    vehiculeQuery.setParameter("matricule", vehiculeSelected);
            Vehicule vehicule = vehiculeQuery.getSingleResult();
            reservation.setVehicule(vehicule);

            String trajetSelected = trajetTfd.getValue();
            TypedQuery<Trajet> trajetQuery = entityManager.createQuery("SELECT s FROM Trajet s WHERE s.nomTrajet = :nomTrajet", Trajet.class);
            trajetQuery.setParameter("nomTrajet", trajetSelected);
            Trajet trajet = trajetQuery.getSingleResult();
            reservation.setTrajet(trajet);



                        entityManager.persist(reservation);
                        transaction.commit();
                        viderChamps();
                        loadTable();
                        table.refresh();
                        enregistrerBtn.setDisable(false);
                    Notification.NotifSuccess("SUCCES","RESERVATION EFFECTUE AVEC SUCCES");

                } else {
                    Notification.NotifError("ERREUR","ERREUR");
                }

        } catch(Exception e) {
            e.printStackTrace();
            if (transaction.isActive())
                transaction.rollback();
        }
    }



    @FXML
    void getData(MouseEvent event) {
        Reservation reservation = table.getSelectionModel().getSelectedItem();

        if (reservation != null) {
            idO = reservation.getReservationId();
            tarifTfd.setText(String.valueOf(reservation.getPrix()));

            conducteurTfd.getSelectionModel().select(reservation.getUser().getNom() +" "+ reservation.getUser().getPrenom());
            trajetTfd.getSelectionModel().select(reservation.getTrajet().getNomTrajet() );
            vehiculeTfd.getSelectionModel().select(reservation.getVehicule().getMatricule() );
            etatTfd.getSelectionModel().select(reservation.getEtat() );

            if (reservation.getDate() != null) {
                dateTfd.setValue(reservation.getDate().toLocalDate());
            } else {
                dateTfd.setValue(null); // Réinitialiser la date si elle est null
            }



        }
    }

    @FXML
    void search(ActionEvent event) {

    }

    public void viderChamps() {
        etatTfd.setValue("");
        conducteurTfd.setValue("");
        vehiculeTfd.setValue("");
        trajetTfd.setValue("");
        tarifTfd.setText("");
        dateTfd.setValue(null);
    }
    public ObservableList<Reservation> getReservation() {
        ObservableList<Reservation> reservations = FXCollections.observableArrayList();
        TypedQuery<Reservation> query = entityManager.createNamedQuery("listReservation", Reservation.class);
        reservations.addAll(query.getResultList());
        return reservations;
    }
    private void loadTable() {
        ObservableList<Reservation> Reservation = getReservation();
        table.setItems(Reservation);

        idCol.setCellValueFactory(new PropertyValueFactory<>("reservationId"));

        numCol.setCellValueFactory(new PropertyValueFactory<>("numeroReservation"));
        trafifCol.setCellValueFactory(new PropertyValueFactory<>("prix"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        conducteurCol.setCellValueFactory(cellData -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Reservation reservation = cellData.getValue();
            if (reservation.getUser() != null) {
                property.setValue(reservation.getUser().getPrenom() +" "+ reservation.getUser().getPrenom());
            } else {
                property.setValue("Aucun");
            }
            return property;
        });

        etatCol.setCellValueFactory(cellData -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Reservation reservation = cellData.getValue();
            if (reservation.getEtat() != null) {
                property.setValue(reservation.getEtat());
            } else {
                property.setValue("Aucun");
            }
            return property;
        });

        trajetCol.setCellValueFactory(cellData -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Reservation reservation = cellData.getValue();
            if (reservation.getTrajet() != null) {
                property.setValue(reservation.getTrajet().getNomTrajet() );
            } else {
                property.setValue("Aucun");
            }
            return property;
        });

        voitureCol.setCellValueFactory(cellData -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Reservation reservation = cellData.getValue();
            if (reservation.getVehicule() != null) {
                property.setValue(reservation.getVehicule().getMatricule() );
            } else {
                property.setValue("Aucun");
            }
            return property;
        });
    }
    private void configureDatePicker() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Set the converter for the DatePicker
        dateTfd.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return date.format(formatter);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, formatter);
                } else {
                    return null;
                }
            }
        });

    }


    private void afficherOeuvre() {

        tarifTfd.setText(String.valueOf(ExpositionController.getPrix()));
        vehiculeTfd.setValue(String.valueOf(ExpositionController.getImg()));
        trajetTfd.setValue(String.valueOf(ExpositionController.getTitres()));
        conducteurTfd.setValue(String.valueOf(ExpositionController.getConducteur()));
        etatTfd.setValue("en cours...");



    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadTable();
        configureDatePicker();
        afficherOeuvre();
    }

    private String generateNumReservation(String mat, int id_vehicule, int annee, int id_conducteur) {
        String ccc = String.valueOf(mat);
        String xxxx = String.valueOf(id_vehicule);
        String yyyy = String.valueOf(annee);
        String zzzz = String.valueOf(id_conducteur);
        if (ccc.length() == 1) {
            ccc = "00" + ccc;
        } else if (ccc.length() == 2) {
            ccc = "0" + ccc;
        }
        switch (xxxx.length()) {
            case 1 ->
                    xxxx = "000" + xxxx;
            case 2 ->
                    xxxx = "00" + xxxx;
            case 3 ->
                    xxxx = "0" + xxxx;
            default -> {
            }
        }

        switch (zzzz.length()) {
            case 1 ->
                    zzzz = "000" + zzzz;
            case 2 ->
                    zzzz = "00" + zzzz;
            case 3 ->
                    zzzz = "0" + zzzz;
            default -> {
            }
        }

        return ccc + "-" + xxxx + "-" + yyyy + "-" + zzzz;
    }

}
