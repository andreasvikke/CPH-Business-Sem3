package dbfacade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author andreas
 */
public class CustomerFacade {
    private static EntityManagerFactory emf;
    private static CustomerFacade instance;
    
    private CustomerFacade() {}
    
    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if(instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }
    
    public Customer findById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Customer.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Customer> findByLastName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Customer c WHERE c.lastName = :lName", Customer.class)
                    .setParameter("lName", name).getResultList();
        } finally {
            em.close();
        }
    }
    
    public int getNumberOfCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT count(c) FROM Customer c", Long.class).getSingleResult().intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Customer> getAllCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    public Customer addCustomer(String fName, String lName) {
        EntityManager em = emf.createEntityManager();
        try {
            Customer c = new Customer(fName, lName);
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return c;
        } finally {
            em.close();
        }
    }
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade cf = CustomerFacade.getCustomerFacade(emf);
        
        cf.addCustomer("Test1", "Test1");
        cf.addCustomer("Test2", "Test2");
        
        System.out.println("-------------------------------Find By Id 2-------------------------------------");
        System.out.println(cf.findById(2));
        System.out.println("-------------------------------Find By lName Test2-------------------------------------");
        for(Customer c : cf.findByLastName("Test2"))
            System.out.println(c);
        System.out.println("-------------------------------Number of Customers-------------------------------------");
        System.out.println(cf.getNumberOfCustomers());
        System.out.println("-------------------------------All Customers-------------------------------------");
        for(Customer c : cf.getAllCustomers())
            System.out.println(c);
    }
}
