/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entities.Animal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author andreas
 */
@Path("animal")
public class AnimalResource {

    @Context
    private UriInfo context;

    List<Animal> animals;
    /**
     * Creates a new instance of AnimalResource
     */
    public AnimalResource() {
        animals = new ArrayList();
        animals.add(new Animal("Dog", 2002, "Bark"));
        animals.add(new Animal("Cat", 2005, "Meow"));
        animals.add(new Animal("Duck", 2003, "Quack"));
        animals.add(new Animal("Turtle", 2001, "Aaah"));
    }

    /**
     * Retrieves representation of an instance of rest.AnimalResource
     * @return an instance of java.lang.String
     */
    @GET
    //@Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "Hello from my first web service";
    }

    /**
     * PUT method for updating or creating an instance of AnimalResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandom() {
        Random randomizer = new Random();
        Animal random = animals.get(randomizer.nextInt(animals.size()));
        return new Gson().toJson(random);
    }
}
