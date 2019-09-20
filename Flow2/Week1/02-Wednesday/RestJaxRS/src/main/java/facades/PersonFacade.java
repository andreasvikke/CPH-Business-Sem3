package facades;

import entities.Address;
import entities.Person;
import exceptions.MissingInputException;
import exceptions.PersonNotFoundException;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PersonFacade implements IPersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PersonFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Person addPerson(String fName, String lName, String phone, String street, String zip, String city) throws MissingInputException {
        if(fName == null || fName.isEmpty() ||
            lName == null || lName.isEmpty() )
            throw new MissingInputException("First Name and/or Last Name is missing");
        
        EntityManager em = getEntityManager();
        try {
            List<Address> dataA = em.createQuery("SELECT a FROM Address a WHERE a.street = :street AND a.city = :city AND a.zip = :zip", Address.class)
                    .setParameter("street", street)
                    .setParameter("city", city)
                    .setParameter("zip", zip)
                    .getResultList();
            Person p;
            if(dataA.size() > 0)
                p = new Person(fName, lName, phone, dataA.get(0));
            else
                p = new Person(fName, lName, phone, new Address(street, zip, city));
            
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }

    @Override
    public Person deletePerson(long id) throws PersonNotFoundException {
        EntityManager em = getEntityManager();
        try {
            Person p = em.find(Person.class, id);
            if (p == null) 
                throw new PersonNotFoundException("Could not delete, provided id does not exist");
            
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }

    @Override
    public Person getPerson(long id) throws PersonNotFoundException {
        EntityManager em = getEntityManager();
        try {
            Person p = em.find(Person.class, id);
            if (p == null)
                throw new PersonNotFoundException("No person with provided id found");
            
            return p;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Person> getAllPersons() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Person p").getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Person editPerson(Person p) throws PersonNotFoundException, MissingInputException {
        if(p.getFirstName() == null || p.getFirstName().isEmpty() ||
           p.getLastName() == null || p.getLastName().isEmpty())
            throw new MissingInputException("First Name and/or Last Name is missing");
        
        EntityManager em = getEntityManager();
        try {
            Person check = em.find(Person.class, p.getId());
            if (check == null)
                throw new PersonNotFoundException("No person with provided id found");
            
            p.setLastEdited(new Date());
            em.getTransaction().begin();
            p = em.merge(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }
}
