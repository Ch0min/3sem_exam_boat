package datafacades;

import entities.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.text.ParseException;

public class Populator {
    public static void populate() throws ParseException {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        Role userRole = new Role("user");
        Role adminRole = new Role("admin");

        User user = new User("user", "user@gmail.com","test123");
        User admin = new User("admin", "admin@gmail.com","test123");
        User user1 = new User("fid0", "fido@gmail.com","test123");
        User user2 = new User("marklundgaard", "marklundgaard@gmail.com","test123");
        User user3 = new User("ballademager", "bm@gmail.com","test123");
        User user4 = new User("lynper", "perper@gmail.com","test123");
        User user5 = new User("nickj", "nickjensen@gmail.com","test123");
        User user6 = new User("cecilien", "cecilien@gmail.com","test123");
        User user7 = new User("susj", "susjensen@gmail.com","test123");
        User user8 = new User("briand", "briand@gmail.com","test123");

        Harbour harbour1 = new Harbour("Kastrup Lystbådehavn", "Kastrup Strandpark 31", 776);
        Harbour harbour2 = new Harbour("Køge Marina", "Bådehavnen 2", 700);

        Owner owner1 = new Owner("Fido Odif", "Nørgaardsvej 30", 89898977, user1);
        Owner owner2 = new Owner("Mark Lundgaard", "Nørgaardsvej 30", 29842984, user2);
        Owner owner3 = new Owner("Hans Hansen", "Nørgaardsvej 30", 27271515, user3);
        Owner owner4 = new Owner("Per Persson", "Nørgaardsvej 30", 27442244, user4);
        Owner owner5 = new Owner("Nick Jensen", "Nørgaardsvej 30", 27272707, user5);
        Owner owner6 = new Owner("Cecilie Nielsen", "Nørgaardsvej 30", 55554444, user6);
        Owner owner7 = new Owner("Susanne Jensen", "Nørgaardsvej 30", 77887777, user7);
        Owner owner8 = new Owner("Briand Jensen", "Nørgaardsvej 30", 99887766, user8);

        Boat boat1 = new Boat("Bayliner", "285 Ciera", "Fidos Båd", "boat1.png", harbour1);
        Boat boat2 = new Boat("Beneteau", "Antares 780", "Marks Speed", "boat2.png", harbour1);
        Boat boat3 = new Boat("Chaparral", "270 Signature", "Lynet", "boat3.png", harbour1);
        Boat boat4 = new Boat("Cranchi", "60 HT", "Fisken", "boat4.png", harbour2);
        Boat boat5 = new Boat("Cranchi", "Endurance", "Stærken", "boat5.png", harbour2);

        if(admin.getUserPass().equals("test")||user.getUserPass().equals("test"))
            throw new UnsupportedOperationException("You have not changed the passwords");

        em.getTransaction().begin();
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

        em.getTransaction().commit();
        System.out.println("PW: " + user.getUserPass());
        System.out.println("Testing user with OK password: " + user.verifyPassword("test"));
        System.out.println("Testing user with wrong password: " + user.verifyPassword("test1"));
        System.out.println("Created TEST Users");

    }
    
    public static void main(String[] args) throws ParseException {
        populate();
    }
}
