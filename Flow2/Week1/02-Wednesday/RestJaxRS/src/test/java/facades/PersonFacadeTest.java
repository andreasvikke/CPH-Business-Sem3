package facades;

import entities.Address;
import utils.EMF_Creator;
import entities.Person;
import exceptions.MissingInputException;
import exceptions.PersonNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class PersonFacadeTest {

    private static EntityManagerFactory emf;
    private static PersonFacade facade;
    private static List<Person> persons = new ArrayList();

    public PersonFacadeTest() {
    }

    @BeforeAll
    public static void setUpClassV2() {
       emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST,Strategy.DROP_AND_CREATE);
       facade = PersonFacade.getFacadeExample(emf);
       persons.add(new Person("Andreas", "Vikke", "12345678", new Address("Magleparken 53", "2750", "Ballerup")));
       persons.add(new Person("Martin", "Eli", "12345679", new Address("Vejnavn 53", "2740", "Skovlunde")));
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.createNamedQuery("Address.deleteAllRows").executeUpdate();
            for(Person p : persons)
                em.persist(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    @Test
    public void testAddPerson() throws MissingInputException {
        EntityManager em = emf.createEntityManager();
        
        Person p = facade.addPerson("Test", "Test", "12345678", "Test", "2740", "Test");
        Person databaseP = em.find(Person.class, p.getId());
        
        assertNotNull(databaseP);
        assertEquals(p, databaseP);
    }
    
    @Test
    public void testDeletePerson() throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();
        
        Person p = facade.deletePerson(persons.get(0).getId());
        Person databaseP = em.find(Person.class, p.getId());
        
        assertNull(databaseP);
    }
    
    @Test
    public void testGetPerson() throws PersonNotFoundException {
        Person p = facade.getPerson(persons.get(0).getId());
        
        assertEquals(persons.get(0).getId(), p.getId());
        assertEquals(persons.get(0).getFirstName(), p.getFirstName());
        assertEquals(persons.get(0).getLastName(), p.getLastName());
        assertEquals(persons.get(0).getPhone(), p.getPhone());
    }
    
    @Test
    public void testGetAllPersons() {
        List<Person> p = facade.getAllPersons();
        
        assertNotNull(p);
        assertEquals(persons.size(), p.size());
    }
    
    @Test
    public void testEditPerson() throws PersonNotFoundException, MissingInputException {
        EntityManager em = emf.createEntityManager();
        
        Person databaseP = em.find(Person.class, persons.get(0).getId());
        databaseP.setFirstName("Test");
        databaseP.setLastName("Test");
        Date ledit = databaseP.getLastEdited();
        
        Person p = facade.editPerson(databaseP);
        databaseP = em.find(Person.class, p.getId());
        
        assertNotNull(databaseP);
        assertEquals(databaseP, p);
        assertNotEquals(ledit, databaseP.getLastEdited());
    }
}
