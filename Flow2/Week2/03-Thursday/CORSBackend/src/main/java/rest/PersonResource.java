package rest;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
        public String name;
        Person(String name) {
            this.name = name;
        }
    }
    
    public static List<Person> persons = new ArrayList();
    public Gson gson = new Gson();

    public PersonResource() {
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons() {
        return Response.ok(gson.toJson(persons)).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSinglePerson(@PathParam("id") int id) {
        return Response.ok(gson.toJson(persons.get(id-1))).build();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSinglePerson(String person) {
        Person newPerson = gson.fromJson(person, Person.class);
        persons.add(newPerson);
        return Response.ok(gson.toJson(newPerson)).build();
    }
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editSinglePerson(@PathParam("id") int id, String person) {
        persons.get(id-1).name = gson.fromJson(person, Person.class).name;
        return Response.ok(gson.toJson(persons.get(id-1))).build();
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeSinglePerson(@PathParam("id") int id) {
        persons.remove(id-1);
        return Response.ok("{\"msg\" : \"Succesfully removed Person with id " + id + "\"}").build();
    }
}
