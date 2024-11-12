package isi.sn.covoiturage_app_javafx_jpa.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQuery(name = "listTrajet", query = "SELECT t FROM Trajet t  ORDER BY t.nomTrajet ASC")


public class Trajet {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "trajet_id")
    private int trajetId;
    @Basic
    @Column(name = "nomTrajet")
    private String nomTrajet;
    @Basic
    @Column(name = "villeDepart")
    private String villeDepart;
    @Basic
    @Column(name = "villeArrive")
    private String villeArrive;
    @Basic
    @Column(name = "date")
    private Date date;
    @Basic
    @Column(name = "prix")
    private int prix;
    @Basic
    @Column(name = "etat")
    private String etat;



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

    @OneToMany(mappedBy = "trajet")
    private List<Reservation> reservations;

    public int getTrajetId() {
        return trajetId;
    }

    public void setTrajetId(int trajetId) {
        this.trajetId = trajetId;
    }

    public String getNomTrajet() {
        return nomTrajet;
    }

    public void setNomTrajet(String nomTrajet) {
        this.nomTrajet = nomTrajet;
    }

    public String getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    public String getVilleArrive() {
        return villeArrive;
    }

    public void setVilleArrive(String villeArrive) {
        this.villeArrive = villeArrive;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trajet trajet = (Trajet) o;
        return trajetId == trajet.trajetId && prix == trajet.prix  && Objects.equals(nomTrajet, trajet.nomTrajet) && Objects.equals(villeDepart, trajet.villeDepart) && Objects.equals(villeArrive, trajet.villeArrive) && Objects.equals(date, trajet.date) && Objects.equals(etat, trajet.etat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trajetId, nomTrajet, villeDepart, villeArrive, date, prix, etat);
    }
}
