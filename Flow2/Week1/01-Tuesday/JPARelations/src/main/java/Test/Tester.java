package Test;

import entities.Customer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author andreas
 */
public class Tester {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        Customer c1 = new Customer("Andreas", "Vikke");
        Customer c2 = new Customer("Martin", "Frederiksen");
        
        c1.addHobby("Computer");
        c1.addHobby("3D Printer");
        c2.addHobby("Computer");
        c2.addHobby("Whiskey");
        
        c1.addPhone("28438851", "Home");
        c1.addPhone("28438852", "Phone");
        c2.addPhone("12345678", "Home");
        c2.addPhone("12345679", "Phone");
        
        em.getTransaction().begin();
        em.persist(c1);
        em.persist(c2);
        em.getTransaction().commit();
    }
}
