package datafacades;

import entities.*;
import errorhandling.API_Exception;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OwnerFacadeTest {
    private static EntityManagerFactory emf;
    private static OwnerFacade facade;

    Role userRole, adminRole;
    User user, admin, user1, user2, user3, user4, user5, user6, user7, user8;
    Harbour harbour1, harbour2;
    Owner owner1, owner2, owner3, owner4, owner5, owner6, owner7, owner8;
    Boat boat1, boat2, boat3, boat4, boat5;

    public OwnerFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = OwnerFacade.getOwnerFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
        System.out.println("EXECUTION OF ALL TESTS IN OWNERFACADETEST DONE");
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Owner.deleteAllRows").executeUpdate();
            em.createNamedQuery("User.deleteAllRows").executeUpdate();
            em.createNamedQuery("Role.deleteAllRows").executeUpdate();
            em.createNamedQuery("Boat.deleteAllRows").executeUpdate();
            em.createNamedQuery("Harbour.deleteAllRows").executeUpdate();

            userRole = new Role("user");
            adminRole = new Role("admin");

            user = new User("user", "user@gmail.com", "test123");
            admin = new User("admin", "admin@gmail.com", "test123");
            user1 = new User("fid0", "fido@gmail.com", "test123");
            user2 = new User("marklundgaard", "marklundgaard@gmail.com", "test123");
            user3 = new User("ballademager", "bm@gmail.com", "test123");
            user4 = new User("lynper", "perper@gmail.com", "test123");
            user5 = new User("nickj", "nickjensen@gmail.com", "test123");
            user6 = new User("cecilien", "cecilien@gmail.com", "test123");
            user7 = new User("susj", "susjensen@gmail.com", "test123");
            user8 = new User("briand", "briand@gmail.com", "test123");

            harbour1 = new Harbour("Kastrup Lystbådehavn", "Kastrup Strandpark 31", 776);
            harbour2 = new Harbour("Køge Marina", "Bådehavnen 2", 700);

            owner1 = new Owner("Fido Odif", "Nørgaardsvej 30", 89898977, user1);
            owner2 = new Owner("Mark Lundgaard", "Nørgaardsvej 30", 29842984, user2);
            owner3 = new Owner("Hans Hansen", "Nørgaardsvej 30", 27271515, user3);
            owner4 = new Owner("Per Persson", "Nørgaardsvej 30", 27442244, user4);
            owner5 = new Owner("Nick Jensen", "Nørgaardsvej 30", 27272707, user5);
            owner6 = new Owner("Cecilie Nielsen", "Nørgaardsvej 30", 55554444, user6);
            owner7 = new Owner("Susanne Jensen", "Nørgaardsvej 30", 77887777, user7);
            owner8 = new Owner("Briand Jensen", "Nørgaardsvej 30", 99887766, user8);

            boat1 = new Boat("Bayliner", "285 Ciera", "Fidos Båd", "boat1.png", harbour1);
            boat2 = new Boat("Beneteau", "Antares 780", "Marks Speed", "boat2.png", harbour1);
            boat3 = new Boat("Chaparral", "270 Signature", "Lynet", "boat3.png", harbour1);
            boat4 = new Boat("Cranchi", "60 HT", "Fisken", "boat4.png", harbour2);
            boat5 = new Boat("Cranchi", "Endurance", "Stærken", "boat5.png", harbour2);

            // Adding
            admin.addRole(adminRole);
            user.addRole(userRole);
            user1.addRole(userRole);
            user2.addRole(userRole);
            user3.addRole(userRole);
            user4.addRole(userRole);
            user5.addRole(userRole);

            boat2.addOwner(owner1);

            owner1.addBoat(boat1);
            owner2.addBoat(boat2);
            owner3.addBoat(boat3);
            owner4.addBoat(boat4);
            owner5.addBoat(boat5);
            owner6.addBoat(boat1);
            owner7.addBoat(boat1);
            owner8.addBoat(boat1);

            // Persisting
            em.persist(userRole);
            em.persist(adminRole);

            em.persist(user);
            em.persist(admin);
            em.persist(user1);
            em.persist(user2);
            em.persist(user3);
            em.persist(user4);
            em.persist(user5);
            em.persist(user6);
            em.persist(user7);
            em.persist(user8);

            em.persist(harbour1);
            em.persist(harbour2);

            em.persist(owner1);
            em.persist(owner2);
            em.persist(owner3);
            em.persist(owner4);
            em.persist(owner5);
            em.persist(owner6);
            em.persist(owner7);
            em.persist(owner8);

            em.persist(boat1);
            em.persist(boat2);
            em.persist(boat3);
            em.persist(boat4);
            em.persist(boat5);

            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
        System.out.println("EXECUTION OF TEST DONE");
    }


    @Test
    void getAllOwners() throws API_Exception {
        System.out.println("Testing getAllOwners...");
        List<Owner> actual = facade.getAllOwners();
        int expected = 8;
        assertEquals(expected, actual.size());
    }

    @Test
    void getOwnersByBoatTest() throws API_Exception {
        System.out.println("Testing getOwnersByBoat...");
        List<Owner> owners = facade.getOwnersByBoat(String.valueOf(boat3));
        assertEquals(boat3.getOwners(), owners);
    }
}
