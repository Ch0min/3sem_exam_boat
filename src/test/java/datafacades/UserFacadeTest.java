package datafacades;

import entities.Role;
import entities.User;
import errorhandling.API_Exception;
import errorhandling.NotFoundException;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserFacadeTest {
    private static EntityManagerFactory emf;
    private static UserFacade facade;

    User u1, u2;


    public UserFacadeTest() throws ParseException {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = UserFacade.getUserFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {

    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        Role userRole = new Role("user");
        u1 = new User();
        u2 = new User();
        u1.setUserName("Mark");
        u1.setUserPass("test123");
        u1.setUserEmail("mark@gmail.com");
        u1.addRole(userRole);
        u2.setUserName("Fido");
        u2.setUserPass("test123");
        u2.setUserEmail("fido@gmail.com");
        u2.addRole(userRole);
        try {
            em.getTransaction().begin();
            em.createNamedQuery("User.deleteAllRows").executeUpdate();
            em.createNamedQuery("Role.deleteAllRows").executeUpdate();
            em.persist(userRole);
            em.persist(u1);
            em.persist(u2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    void getAllUsersTest() throws API_Exception {
        List<User> actual = facade.getAllUsers();
        int expected = 2;
        assertEquals(expected, actual.size());
    }

    @Test
    void getUserByUsernameTest() throws API_Exception {
        User user = facade.getUserByUserName(u1.getUserName());
        assertEquals(u1, user);
    }

    @Test
    void createUserTest() throws NotFoundException, API_Exception {
        User user = new User("Chris", "test123");
        facade.createUser(user);
        assertNotNull(user.getUserName());
        int actualSize = facade.getAllUsers().size();
        assertEquals(3, actualSize);
    }

    @Test
    void createNoDuplicateUsersTest() throws API_Exception {
        User user = new User("Mark", "test123");
        assertThrows(API_Exception.class, () -> facade.createUser(user));
    }

    @Test
    void updateUserTest() throws API_Exception {
        User expected = new User(u1.getUserName(),"Testefar@test.com","test123");
        User actual = facade.updateUser(expected);
        assertEquals(expected,actual);
    }

    @Test
    void deleteUserTest() throws API_Exception, NotFoundException {
        facade.deleteUser("Mark");
        int actualSize = facade.getAllUsers().size();
        assertEquals(1, actualSize);
    }

    @Test
    void cantFindUserToDeleteTest() {
        assertThrows(API_Exception.class, () -> facade.deleteUser("TestBruger"));
    }
}
