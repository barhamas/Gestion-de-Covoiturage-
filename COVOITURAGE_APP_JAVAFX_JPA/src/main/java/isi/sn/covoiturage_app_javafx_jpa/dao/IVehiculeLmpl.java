package isi.sn.covoiturage_app_javafx_jpa.dao;

import isi.sn.covoiturage_app_javafx_jpa.entities.User;
import isi.sn.covoiturage_app_javafx_jpa.entities.Vehicule;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class IVehiculeLmpl extends DB implements IVehicule{
    @Override
    public Vehicule verifMat(String mat) {
        TypedQuery<Vehicule> query = entityManager.createQuery(
                "SELECT v FROM Vehicule v WHERE v.matricule = :mat", Vehicule.class);
        query.setParameter("mat", mat);

        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
