package rest;

import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Bacon
 */
@Path("person")
public class PersonResource {

    @Context
    private UriInfo context;
    
    public class Person {
        String name;
        Person(String name) {
            this.name = name;
        }
    }
    
    public Person person = new Person("Andreas Vikke");

    public PersonResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson() {
        return Response.ok(new Gson().toJson(person)).build();
    }
}
