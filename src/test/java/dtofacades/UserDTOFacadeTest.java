package dtofacades;

import dtos.UserDTO;
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

public class UserDTOFacadeTest {
    private static EntityManagerFactory emf;
    private static UserDTOFacade facade;

    Role userRole, adminRole;
    User u1, u2;

    UserDTO udto1, udto2;

    public UserDTOFacadeTest() throws ParseException {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = UserDTOFacade.getInstance(emf);
    }

    @AfterAll
    public static void tearDownClass() {
        System.out.println("EXECUTION OF ALL TESTS IN USERDTOFACADETEST DONE");
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.createNamedQuery("User.deleteAllRows").executeUpdate();
            em.createNamedQuery("Role.deleteAllRows").executeUpdate();

            userRole = new Role("user");
            adminRole = new Role("admin");

            u1 = new User("fid0", "fido@gmail.com", "test123");
            u2 = new User("marklundgaard", "marklundgaard@gmail.com", "test123");
            u1.addRole(userRole);
            u2.addRole(userRole);

            em.persist(userRole);
            em.persist(adminRole);

            em.persist(u1);
            em.persist(u2);

            em.getTransaction().commit();
        } finally {
            udto1 = new UserDTO(u1);
            udto2 = new UserDTO(u2);

            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
        System.out.println("EXECUTION OF TEST DONE");
    }

    @Test
    void getAllUserDTOs() throws API_Exception {
        List<UserDTO> actual = facade.getAllUsers();
        int expected = 2;
        assertEquals(expected, actual.size());
    }

    @Test
    void getUserDTOByUsernameTest() throws API_Exception {
        UserDTO userDTO = facade.getUserByUserName(udto1.getUserName());
        assertEquals(udto1, userDTO);
    }

    @Test
    void createUserDTOTest() throws API_Exception {
        UserDTO userDTO = new UserDTO(new User("NyBruger", "nybruger@gmail.com", "test123"));
        facade.createUser(userDTO);
        assertNotNull(userDTO.getUserName());
        int actualSize = facade.getAllUsers().size();
        assertEquals(3, actualSize);
    }

    @Test
    void createNoDuplicateUserDTOsTest() throws API_Exception {
        UserDTO userDTO = new UserDTO(new User("fid0", "mark@gmail.com", "test123"));
        assertThrows(API_Exception.class, () -> facade.createUser(userDTO));
    }

    @Test
    void updateUserDTOTest() throws API_Exception {
        UserDTO expected = new UserDTO(udto1.getEntity());
        expected.setUserEmail("testefar@gmail.com");
        UserDTO actual = facade.updateUser(expected);
        assertEquals(expected, actual);
    }

    @Test
    void deleteUserDTOTest() throws API_Exception{
        facade.deleteUser("fid0");
        int actualSize = facade.getAllUsers().size();
        assertEquals(1, actualSize);
    }

    @Test
    void CantFindUserDTOToDelete() {
        assertThrows(API_Exception.class, () -> facade.deleteUser("TestBruger"));
    }
}


