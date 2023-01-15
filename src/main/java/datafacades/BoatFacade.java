package datafacades;

import entities.Boat;
import entities.Harbour;
import entities.Role;
import errorhandling.API_Exception;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class BoatFacade {

    private static EntityManagerFactory emf;
    private static BoatFacade instance;

    public BoatFacade() {
    }

    public static BoatFacade getBoatFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BoatFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public List<Boat> getAllBoats() throws API_Exception {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Boat> query = em.createQuery("SELECT b FROM Boat b", Boat.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new API_Exception("Can't find any boats in the system", 404, e);
        }
    }

    // US-2
    public List<Boat> getBoatsByHarbour(String harbourName) throws API_Exception {
        EntityManager em = getEntityManager();

        try {
            TypedQuery<Boat> query = em.createQuery("SELECT b FROM Boat b JOIN b.harbour h " +
                    "WHERE h.harbourName = :harbourName", Boat.class);
            query.setParameter("harbourName", harbourName);
            List<Boat> boats = query.getResultList();
            return boats;
        } catch (Exception e) {
            throw new API_Exception("Can't find harbour with the name: " + harbourName, 404, e);
        } finally {
            em.close();
        }
    }

    // US-4
    public Boat createBoat(Boat boat) throws API_Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(boat);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new API_Exception("Can't create Boat: " + boat.getBoatName() + " in the system!");
        } finally {
            em.close();
        }
        return boat;
    }

    // US-5
    public Boat assignBoatToHarbour(int boatID, int harbourID) throws API_Exception {
        EntityManager em = getEntityManager();
        Boat boat = em.find(Boat.class, boatID);
        Harbour harbour = em.find(Harbour.class, harbourID);

        try {
            em.getTransaction().begin();
            boat.assignHarbour(harbour);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (boat == null) {
                throw new API_Exception("Can't find Boat with the ID: " + boat.getBoatID(), 400, e);
            }
            if (harbour == null) {
                throw new API_Exception("Can't find Harbour with the ID: " + harbour.getHarbourID(), 400, e);
            }
        } finally {
            em.close();
        }
        return boat;
    }

    // US-6 - Starter med at update Boat, og så senere finde ud af om jeg kan update alt dvs. owner og harbour.
//    public Boat updateBoat(Boat boat) throws API_Exception {
//        EntityManager em = getEntityManager();
//
//        try {
//            em.find(Boat.class, boat.getBoatID());
//            em.getTransaction().begin();
//            Boat b = em.merge(boat);
//            em.getTransaction().commit();
//            return b;
//        } catch (Exception e) {
//            throw new API_Exception("Can't update Boat with the ID: " + boat.getBoatID(), 400, e);
//        } finally {
//            em.close();
//        }
//    }

    public Boat updateBoat(Boat boat) throws API_Exception {
        EntityManager em = getEntityManager();

        try {
            em.find(Boat.class, boat.getBoatID());
            em.getTransaction().begin();
            Boat b = em.merge(boat);
            em.getTransaction().commit();
            return b;
        } catch (Exception e) {
            throw new API_Exception("Can't update Boat with the ID: " + boat.getBoatID(), 400, e);
        } finally {
            em.close();
        }
    }

    // US-7
    public Boat deleteBoat(int boatID) throws API_Exception {
        EntityManager em = getEntityManager();
        Boat boat = em.find(Boat.class, boatID);

        try {
            em.getTransaction().begin();
            em.remove(boat);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (boat == null) {
                throw new API_Exception("Can't delete Boat with the ID: " + boat.getBoatID(), 400, e);
            }
        } finally {
            em.close();
        }
        return boat;
    }

    // Virker uden cascade
//    public Boat deleteBoat(int boatID) throws API_Exception {
//        EntityManager em = getEntityManager();
//        try {
//            Boat b = em.find(Boat.class, boatID);
//            if (b == null)
//                throw new API_Exception("Can't delete Boat with the ID: " + boatID);
//            em.getTransaction().begin();
//            // detach dangling children
//            b.getOwners().forEach(owner -> {
//                if (owner.getOwnerID() != 0)
//                    owner.setBoats(null);
//                em.merge(owner);
//            });
//            em.remove(b);
//            em.getTransaction().commit();
//            return b;
//        } finally {
//            em.close();
//        }
//    }
}