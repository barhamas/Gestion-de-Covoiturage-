package isi.sn.covoiturage_app_javafx_jpa.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity

public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "utilisateur_id")
    private int utilisateurId;
    @Basic
    @Column(name = "prenom")
    private String prenom;
    @Basic
    @Column(name = "nom")
    private String nom;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "role")
    private String role;
    @Basic
    @Column(name = "Statut")
    private String statut;
    @Basic
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "user")
    private List<Vehicule> vehicules;

    @OneToMany(mappedBy = "user")
    private List<Trajet> trajets;
    public int getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(int utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return utilisateurId == user.utilisateurId && Objects.equals(prenom, user.prenom) && Objects.equals(nom, user.nom) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(role, user.role) && Objects.equals(statut, user.statut) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(utilisateurId, prenom, nom, username, password, role, statut, email);
    }
}
