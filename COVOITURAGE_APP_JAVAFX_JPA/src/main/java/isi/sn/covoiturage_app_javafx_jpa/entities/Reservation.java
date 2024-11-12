package isi.sn.covoiturage_app_javafx_jpa.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@NamedQuery(name = "listReservation", query = "SELECT r FROM Reservation r  ORDER BY r.etat ASC")
@NamedQuery(name = "listReservationEnCours", query = "SELECT r FROM Reservation r WHERE r.etat= 'en cours...' ")


public class Reservation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "reservation_id")
    private int reservationId;
    @Basic
    @Column(name = "numeroReservation")
    private String numeroReservation;
    @Basic
    @Column(name = "date")
    private Date date;
    @Basic
    @Column(name = "etat")
    private String etat;

    @Basic
    @Column(name = "prix")
    private int prix;

    @ManyToOne
    @JoinColumn(name = "utilisateurId", referencedColumnName = "utilisateur_id")

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "voitureId", referencedColumnName = "vehicule_id")

    private Vehicule vehicule;

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }


    @ManyToOne
    @JoinColumn(name = "trajetId", referencedColumnName = "trajet_id")

    private Trajet trajet;

    public Trajet getTrajet() {
        return trajet;
    }

    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getNumeroReservation() {
        return numeroReservation;
    }

    public void setNumeroReservation(String numeroReservation) {
        this.numeroReservation = numeroReservation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return reservationId == that.reservationId && numeroReservation == that.numeroReservation && Objects.equals(date, that.date) && Objects.equals(etat, that.etat) && Objects.equals(prix, that.prix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId, numeroReservation, date, etat,prix);
    }
}
