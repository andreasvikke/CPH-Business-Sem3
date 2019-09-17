package facades;

import entities.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author andreas
 */
public class CustomerFacade {
    
    private EntityManagerFactory emf;

    public CustomerFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    
    private EntityManager createEnitityManager() {
        return emf.createEntityManager();
    }
    
    public Customer getCustomer(long id) {
        EntityManager em = createEnitityManager();
        return em.find(Customer.class, id);
    }
    
    public List<Customer> getCustomers() {
        EntityManager em = createEnitityManager();
        return em.createQuery("SELECT c FROM Customer c").getResultList();
    }
    
    public Customer addCustomer(Customer cust) {
        EntityManager em = createEnitityManager();
        em.getTransaction().begin();
        em.persist(cust);
        em.getTransaction().commit();
        return cust;
    }
    
    public Customer deleteCustomer(long id) {
        EntityManager em = createEnitityManager();
        em.getTransaction().begin();
        Customer rcust = em.find(Customer.class, id);
        em.remove(rcust);
        em.getTransaction().commit();
        return rcust;
    }
    
    public Customer editCustomer(Customer cust) {
        EntityManager em = createEnitityManager();
        em.getTransaction().begin();
        em.merge(cust);
        em.getTransaction().commit();
        return cust;
    }
}
