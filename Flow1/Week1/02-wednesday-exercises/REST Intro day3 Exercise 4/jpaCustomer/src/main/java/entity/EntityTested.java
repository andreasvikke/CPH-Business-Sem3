package entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author andreas
 */
public class EntityTested {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        List<Customer> customers = new ArrayList();
        customers.add(new Customer("Andreas", "Vikke"));
        customers.add(new Customer("William", "Huusfeldt"));
        customers.add(new Customer("Martin", "Frederiksen"));
        customers.add(new Customer("Asger" ,"Klamydia"));
        
        try {
            em.getTransaction().begin();
            for(Customer c : customers)
                em.persist(c);
            em.getTransaction().commit();
            
            TypedQuery<Customer> q = em.createQuery("SELECT c FROM Customer c", Customer.class);
            for(Customer c : q.getResultList())
                System.out.println(c);
        } finally {
            em.close();
            emf.close();
        }
    }
}
