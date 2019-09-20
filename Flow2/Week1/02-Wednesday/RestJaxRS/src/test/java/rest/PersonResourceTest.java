package rest;

import com.google.gson.Gson;
import dto.PersonDTO;
import entities.Address;
import entities.Person;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class PersonResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    //Read this line from a settings-file  since used several places
    private static final String TEST_DB = "jdbc:mysql://localhost:3307/startcode_test";

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;
    private static List<Person> persons = new ArrayList();

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        httpServer = startServer();
        persons.add(new Person("Andreas", "Vikke", "12345678", new Address("Magleparken 53", "2750", "Ballerup")));
        persons.add(new Person("Martin", "Eli", "12345679", new Address("Vejnavn 53", "2740", "Skovlunde")));

        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;

        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.createNamedQuery("Address.deleteAllRows").executeUpdate();
            for (Person p : persons) {
                em.persist(p);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/person").then().statusCode(200);
    }

    @Test
    public void testGetPerson() throws Exception {
        given()
                .contentType("application/json")
                .get("/person/" + persons.get(0).getId()).then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("fName", equalTo(persons.get(0).getFirstName()))
                .body("lName", equalTo(persons.get(0).getLastName()))
                .body("phone", equalTo(persons.get(0).getPhone()));
    }
    
    @Test
    public void testGetPersonError() throws Exception {
        given()
                .contentType("application/json")
                .get("/person/0").then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND_404.getStatusCode());
    }

    @Test
    public void testGetAllPersons() throws Exception {
        given()
                .contentType("application/json")
                .get("/person/all").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("all.size()", equalTo(2));
    }

    @Test
    public void testAddPerson() throws Exception {
        given()
                .contentType("application/json")
                .body(new Gson().toJson(new PersonDTO("Test", "Test2", "12312345")))
                .post("/person/add").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("fName", equalTo("Test"))
                .body("lName", equalTo("Test2"))
                .body("phone", equalTo("12312345"));
    }
    
    @Test
    public void testAddPersonError() throws Exception {
        given()
                .contentType("application/json")
                .body(new Gson().toJson(new PersonDTO(null, "Test2", "12312345")))
                .post("/person/add").then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST_400.getStatusCode());
    }

    @Test
    public void testEditPerson() throws Exception {
        persons.get(0).setFirstName("Test");
        given()
                .contentType("application/json")
                .body(new Gson().toJson(new PersonDTO(persons.get(0))))
                .put("/person/" + persons.get(0).getId()).then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("fName", equalTo("Test"));
    }
    
    @Test
    public void testEditPersonError() throws Exception {
        given()
                .contentType("application/json")
                .put("/person/0").then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND_404.getStatusCode());
    }
    
    @Test
    public void testEditPersonError2() throws Exception {
        given()
                .contentType("application/json")
                .body(new Gson().toJson(new PersonDTO(null, "Test", "1234")))
                .put("/person/" + persons.get(0).getId()).then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST_400.getStatusCode());
    }

    @Test
    public void testDeletePerson() throws Exception {
        given()
                .contentType("application/json")
                .delete("/person/" + persons.get(0).getId()).then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("removed", equalTo(Integer.parseInt(persons.get(0).getId().toString())));
    }
    
    @Test
    public void testDeletePersonError() throws Exception {
        given()
                .contentType("application/json")
                .delete("/person/0").then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND_404.getStatusCode());
    }
}
