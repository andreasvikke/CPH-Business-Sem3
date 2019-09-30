package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonDTO;
import dto.PersonsDTO;
import entities.Address;
import entities.Person;
import utils.EMF_Creator;
import facades.PersonFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/RestJaxRS",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    private static final PersonFacade FACADE = PersonFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @GET
    @Path("/setup")
    @Produces({MediaType.APPLICATION_JSON})
    public String setup() throws Throwable {
        EntityManager em = EMF.createEntityManager();
        em.getTransaction().begin();
        Address ma = new Address("Magleparken 53", "2750", "Ballerup");
        em.persist(new Person("Andreas", "Vikke", "12345678", ma));
        em.persist(new Person("Frederik", "Nielsen", "12345679", ma));
        em.persist(new Person("Martin", "Eli", "12345679", new Address("Vejnavn 53", "2740", "Skovlunde")));
        em.getTransaction().commit();

        return "{\"setup\":\"Completed\"}";
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getPerson(@PathParam("id") long id) throws Throwable  {
        return Response.ok(GSON.toJson(new PersonDTO(FACADE.getPerson(id)))).build();
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllPersons() throws Throwable {
        return Response.ok(GSON.toJson(new PersonsDTO(FACADE.getAllPersons()))).build();
    }

    @POST
    @Path("/add")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response addPerson(String person) throws Throwable  {
        PersonDTO p = GSON.fromJson(person, PersonDTO.class);
        Person addedPerson = FACADE.addPerson(p.getfName(), p.getlName(), p.getPhone(), p.getAddress().getStreet(), p.getAddress().getZip(), p.getAddress().getCity());
        return Response.ok(new PersonDTO(addedPerson)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response editPerson(String person, @PathParam("id") long id) throws Throwable {
        PersonDTO pdto = GSON.fromJson(person, PersonDTO.class);
        Person p = FACADE.getPerson(id);

        p.setFirstName(pdto.getfName());
        p.setLastName(pdto.getlName());
        p.setPhone(pdto.getPhone());
        Person updated = FACADE.editPerson(p);

        return Response.ok(GSON.toJson(new PersonDTO(updated))).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response deletePerson(@PathParam("id") long id) throws Throwable  {
        Person p = FACADE.deletePerson(id);

        return Response.status(200).entity("{\"removed\" : " + p.getId() + "}").type(MediaType.APPLICATION_JSON).build();
    }
}
