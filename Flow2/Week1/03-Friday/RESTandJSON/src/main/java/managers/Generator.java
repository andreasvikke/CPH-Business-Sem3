package managers;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author andreas
 */
public class Generator {
    public static String Generate(int qty, long startId) {
        Faker faker = new Faker();
        List<Person> persons = new ArrayList<Person>();
        for(int i = 0; i < qty; i++) {
            persons.add(
                    new Person(faker.name().firstName(), 
                            faker.name().lastName(), 
                            startId + i, 
                            new Random().nextInt(54) + 17));
        }
        return new GsonBuilder().setPrettyPrinting().create().toJson(persons);
    }
    
    private static class Person {
        public String fname;
        public String lname;
        public long id;
        public int age;

        public Person(String fname, String lname, long id, int age) {
            this.fname = fname;
            this.lname = lname;
            this.id = id;
            this.age = age;
        }
    }
}
