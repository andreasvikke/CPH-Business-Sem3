package dto;

import entities.Person;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andreas
 */
 public class PersonsDTO {
    
    List<PersonDTO> all = new ArrayList();

    public PersonsDTO(List<Person> personEntities) {
         for(Person p : personEntities)
            all.add(new PersonDTO(p));
    }
 }
