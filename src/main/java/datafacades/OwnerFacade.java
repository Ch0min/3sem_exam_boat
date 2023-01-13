package datafacades;


import entities.Owner;
import errorhandling.API_Exception;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class OwnerFacade {

    private static EntityManagerFactory emf;
    private static OwnerFacade instance;

    public OwnerFacade() {
    }

    public static OwnerFacade getOwnerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new OwnerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // US-1
    public List<Owner> getAllOwners() throws API_Exception {
        EntityManager em = getEntityManager();

        try {
            TypedQuery<Owner> query = em.createQuery("SELECT o FROM Owner o", Owner.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new API_Exception("Can't find any Owners in the system", 404, e);
        }
    }

    // US-3
    public List<Owner> getOwnersByBoat(String boatName) throws API_Exception {
        EntityManager em = getEntityManager();

        try {
            TypedQuery<Owner> query = em.createQuery("SELECT o FROM Owner o JOIN o.boats b " +
                    "WHERE b.boatName = :boatName", Owner.class);
            query.setParameter("boatName", boatName);
            List<Owner> owners = query.getResultList();
            return owners;
        } finally {
            em.close();
        }
    }
}
