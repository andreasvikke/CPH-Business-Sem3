package facades;

import entities.Employee;
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
public class EmployeeFacadeTest {

    private static EmployeeFacade ef;
    private static EntityManagerFactory emf;

    public EmployeeFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("puTest");
        ef = EmployeeFacade.getFacadeExample(emf);

        EntityManager em = emf.createEntityManager();

        List<Employee> employees = new ArrayList();
        employees.add(new Employee("Andreas", "Marievej 40 2740 Skovlunde", 1000));
        employees.add(new Employee("Martin", "Marievej 48 2740 Skovlunde", 1000));
        employees.add(new Employee("Martin", "Marievej 42 2740 Skovlunde", 700));
        employees.add(new Employee("Asger", "Marievej 44 2740 Skovlunde", 200));
        employees.add(new Employee("William", "Marievej 46 2740 Skovlunde", 500));

        try {
            for (Employee e : employees) {
                em.getTransaction().begin();
                em.persist(e);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }

    /**
     * Test of getEmployeeById method, of class EmployeeFacade.
     */
    @Test
    public void testGetEmployeeById() {
        System.out.println("getEmployeeById");
        long id = 1;
        Employee result = ef.getEmployeeById(id);
        assertNotNull(result);
        assertEquals("Andreas", result.getName());
    }

    /**
     * Test of getEmployeesByName method, of class EmployeeFacade.
     */
    @Test
    public void testGetEmployeesByName() {
        System.out.println("getEmployeesByName");
        String name = "Martin";
        List<Employee> result = ef.getEmployeesByName(name);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Marievej 48 2740 Skovlunde", result.get(0).getAddress());
    }

    /**
     * Test of getAllEmployees method, of class EmployeeFacade.
     */
    @Test
    public void testGetAllEmployees() {
        System.out.println("getAllEmployees");
        List<Employee> result = ef.getAllEmployees();
        assertNotNull(result);
        assertEquals(5, result.size());
    }

    /**
     * Test of getEmployeesWithHighestSalary method, of class EmployeeFacade.
     */
    @Test
    public void testGetEmployeesWithHighestSalary() {
        System.out.println("getEmployeesWithHighestSalary");
        List<Employee> result = ef.getEmployeesWithHighestSalary();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1000, result.get(0).getSalary());
    }

    /**
     * Test of createEmployee method, of class EmployeeFacade.
     */
    @Test
    public void testCreateEmployee() {
        System.out.println("createEmployee");
        Employee result = ef.createEmployee("Test1", "Test2", 555);

        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        Employee created = em.find(Employee.class, 6L);
        assertNotNull(result);
        assertNotNull(created);
        assertEquals(created.getName(), "Test1");

        try {
            Employee remove = em.find(Employee.class, 6L);
            em.remove(remove);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
