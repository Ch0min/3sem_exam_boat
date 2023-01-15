package datafacades;

import entities.Harbour;
import entities.User;
import errorhandling.API_Exception;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class HarbourFacade {

    private static EntityManagerFactory emf;
    private static HarbourFacade instance;

    public HarbourFacade() {
    }

    public static HarbourFacade getHarbourFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HarbourFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Harbour> getAllHarbours() throws API_Exception {
        EntityManager em = getEntityManager();

        try {
            TypedQuery<Harbour> query = em.createQuery("SELECT h FROM Harbour h", Harbour.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new API_Exception("Can't find any Harbours in the system", 404, e);
        }
    }

    public Harbour getHarbourByHarbourName(String harbourName) throws API_Exception {
        EntityManager em = getEntityManager();

        try {
            Harbour h = em.find(Harbour.class, harbourName);
            return h;
        } catch (Exception e){
            throw new API_Exception("Can't find a Harbour with the Harbour Name: " + harbourName, 404, e);
        }
    }
}
