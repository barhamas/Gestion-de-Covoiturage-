package isi.sn.covoiturage_app_javafx_jpa.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQuery(name = "listVehicule", query = "SELECT v FROM Vehicule v  ORDER BY v.matricule ASC")

public class Vehicule {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "vehicule_id")
    private int vehiculeId;
    @Basic
    @Column(name = "marque")
    private String marque;
    @Basic
    @Column(name = "modele")
    private String modele;
    @Basic
    @Column(name = "matricule")
    private String matricule;
    @Basic
    @Column(name = "image")
    private String image;
    @Basic
    @Column(name = "nombrePlace")
    private int nombrePlace;

    @Basic
    @Column(name = "cptPlace")
    private int cptPlace;

    public int getCptPlace() {
        return cptPlace;
    }

    public void setCptPlace(int cptPlace) {
        this.cptPlace = cptPlace;
    }

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

    @OneToMany(mappedBy = "vehicule")
    private List<Trajet> trajets;

    @OneToMany(mappedBy = "vehicule")
    private List<Reservation> reservations;

    public int getVehiculeId() {
        return vehiculeId;
    }

    public void setVehiculeId(int vehiculeId) {
        this.vehiculeId = vehiculeId;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNombrePlace() {
        return nombrePlace;
    }

    public void setNombrePlace(int nombrePlace) {
        this.nombrePlace = nombrePlace;
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
        Vehicule vehicule = (Vehicule) o;
        return vehiculeId == vehicule.vehiculeId && cptPlace == vehicule.cptPlace && nombrePlace == vehicule.nombrePlace && Objects.equals(marque, vehicule.marque) && Objects.equals(modele, vehicule.modele) && Objects.equals(matricule, vehicule.matricule) && Objects.equals(image, vehicule.image) && Objects.equals(etat, vehicule.etat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehiculeId, marque, modele, matricule, image, nombrePlace,cptPlace, etat);
    }
}
