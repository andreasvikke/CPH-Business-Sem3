package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.ArrayList;
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
public class BankCustomerFacadeTest {

    private static BankCustomerFacade bcf;
    private static EntityManagerFactory emf;

    public BankCustomerFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("puTest");
        bcf = BankCustomerFacade.getBankCustomerFacade(emf);

        EntityManager em = emf.createEntityManager();
        List<BankCustomer> bcs = new ArrayList();
        bcs.add(new BankCustomer("Andreas", "Vikke", "1234", 858473, 5, "Info 1"));
        bcs.add(new BankCustomer("Martin", "Frederiksen", "1235", 50000, 4, "Info 2"));
        bcs.add(new BankCustomer("Asger", "Klamydia", "1236", 200, 2, "Info 3"));
        bcs.add(new BankCustomer("William", "Huusfeldt", "1237", 400, 3, "Info 4"));

        try {
            for (BankCustomer bc : bcs) {
                em.getTransaction().begin();
                em.persist(bc);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }

    /**
     * Test of getCustomerByID method, of class BankCustomerFacade.
     */
    @Test
    public void testGetCustomerByID() {
        System.out.println("getCustomerByID");
        long id = 1;
        CustomerDTO result = bcf.getCustomerByID(id);
        assertNotNull(result);
        assertEquals("Andreas Vikke", result.getFullName());
    }

    /**
     * Test of getCustomersByName method, of class BankCustomerFacade.
     */
    @Test
    public void testGetCustomersByName() {
        System.out.println("getCustomersByName");
        String name = "Andreas";
        List<CustomerDTO> expResult = null;
        List<CustomerDTO> result = bcf.getCustomersByName(name);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("1234", result.get(0).getAccountNumber());
    }

    /**
     * Test of addCustomer method, of class BankCustomerFacade.
     */
    @Test
    public void testAddCustomer() {
        System.out.println("addCustomer");
        BankCustomer cust = new BankCustomer("Test1", "Test2", "Test3", 10, 3, "Test4");
        BankCustomer result = bcf.addCustomer(cust);
        assertNotNull(result);
        
        EntityManager em = emf.createEntityManager();
        
        BankCustomer created = em.find(BankCustomer.class, 5L);
        assertNotNull(created);
        assertEquals(created.getFirstName(), cust.getFirstName());

        try {
            em.getTransaction().begin();
            BankCustomer remove = em.find(BankCustomer.class, 5L);
            em.remove(remove);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    /**
     * Test of getAllBankCustomers method, of class BankCustomerFacade.
     */
    @Test
    public void testGetAllBankCustomers() {
        System.out.println("getAllBankCustomers");
        List<BankCustomer> result = bcf.getAllBankCustomers();
        assertNotNull(result);
        assertEquals(4, result.size());
    }
}
