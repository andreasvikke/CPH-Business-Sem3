package facades;

import entities.Person;
import exceptions.MissingInputException;
import exceptions.PersonNotFoundException;
import java.util.List;

/**
 *
 * @author andreas
 */
public interface IPersonFacade {

    public Person addPerson(String fName, String lName, String phone, String street, String zip, String city) throws MissingInputException;

    public Person deletePerson(long id) throws PersonNotFoundException;

    public Person getPerson(long id) throws PersonNotFoundException;

    public List<Person> getAllPersons();

    public Person editPerson(Person p) throws PersonNotFoundException, MissingInputException;

}
