package dbfacade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author andreas
 */
public class CustomerFacadeTest {

    private static EntityManagerFactory emf;
    private static CustomerFacade cf;

    public CustomerFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("putest");
        cf = CustomerFacade.getCustomerFacade(emf);

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(new Customer("Test1", "Test1"));
            em.persist(new Customer("Test2", "Test2"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    /**
     * Test of findById method, of class CustomerFacade.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        long id = 1;
        Customer c = cf.findById(id);
        assertNotNull(c);
        assertEquals("Test1", c.getFirstName());
    }

    /**
     * Test of findByLastName method, of class CustomerFacade.
     */
    @Test
    public void testFindByLastName() {
        System.out.println("findByLastName");
        String name = "Test1";
        List<Customer> result = cf.findByLastName(name);
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    /**
     * Test of getNumberOfCustomers method, of class CustomerFacade.
     */
    @Test
    public void testGetNumberOfCustomers() {
        System.out.println("getNumberOfCustomers");
        int expResult = 2;
        int result = cf.getNumberOfCustomers();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllCustomers method, of class CustomerFacade.
     */
    @Test
    public void testGetAllCustomers() {
        System.out.println("getAllCustomers");
        List<Customer> result = cf.getAllCustomers();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    /**
     * Test of addCustomer method, of class CustomerFacade.
     */
    @Test
    public void testAddCustomer() {
        System.out.println("addCustomer");
        String fName = "Test3";
        String lName = "Test3";
        Customer result = cf.addCustomer(fName, lName);
        assertNotNull(result);

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Customer remove = em.find(Customer.class, 3L);
            em.remove(remove);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
