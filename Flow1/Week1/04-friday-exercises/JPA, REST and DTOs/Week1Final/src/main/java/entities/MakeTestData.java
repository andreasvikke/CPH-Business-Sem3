package entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author andreas
 */
public class MakeTestData {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        List<BankCustomer> bcs = new ArrayList();
        bcs.add(new BankCustomer("Andreas", "Vikke", "1234", 858473, 5, "Info 1"));
        bcs.add(new BankCustomer("Martin", "Frederiksen", "1235", 50000, 4, "Info 2"));
        bcs.add(new BankCustomer("Asger", "Klamydia", "1236", 200, 2, "Info 3"));
        bcs.add(new BankCustomer("William", "Huusfeldt", "1237", 400, 3, "Info 4"));
        
        em.getTransaction().begin();
        for(BankCustomer bc : bcs)
            em.persist(bc);
        em.getTransaction().commit();
        
        em.close();
        emf.close();
    }
}
