package rest;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
@Path("jokes")
public class GenericResource {

    @Context
    private UriInfo context;
    private List<Joke> jokes = new ArrayList();

    public GenericResource() {
        jokes.add(new Joke(1, "A SQL query goes into a bar, walks up to two tables and asks, \"Can I join you?\""));
        jokes.add(new Joke(2, "Q: Whats the object-oriented way to become wealthy? A: Inheritance"));
        jokes.add(new Joke(3, "Q: How many programmers does it take to change a light bulb? A: None, that''s a hardware problem"));
        jokes.add(new Joke(4, "Unix is user friendly. It's just very particular about who its friends are."));
    }

    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRandom() {
        Random random = new Random();
        return Response.ok(new Gson().toJson(jokes.get(random.nextInt(jokes.size()))))
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }
    
    public class Joke {
        long id;
        String joke;
        
        public Joke(long id, String joke) {
            this.id = id;
            this.joke = joke;
        }
    }
}
