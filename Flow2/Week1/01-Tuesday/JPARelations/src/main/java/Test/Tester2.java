package Test;

import entities.Address;
import entities.Customer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author andreas
 */
public class Tester2 {
    public static void main(String[] args) {
        //Persistence.generateSchema("pu", null);
        
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
        
//        Address a = new Address("Test", "Test");
//        c1.setAddress(a);
//        c2.setAddress(a);
        
        Address a1 = new Address("Marievej 40", "Skovlunde");
        Address a2 = new Address("Magleparken 53", "Ballerup");
        Address a3 = new Address("Vejnavn1", "Nærum");

        c1.addAddress(a1);
        c1.addAddress(a2);
        c2.addAddress(a3);
        c2.addAddress(a2);
        
//        a1.setCustomer(c1);
//        a2.setCustomer(c1);
//        a3.setCustomer(c2);
        
        em.getTransaction().begin();
        em.persist(c1);
        em.persist(c2);
        em.getTransaction().commit();
    }
}
