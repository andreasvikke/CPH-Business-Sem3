/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import dbfacade.CustomerFacade;
import entity.Customer;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author andreas
 */
@Path("customer")
public class CustomerResource {

    @Context
    private UriInfo context;
    
    private CustomerFacade cf;

    /**
     * Creates a new instance of CustomerResource
     */
    public CustomerResource() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        cf = CustomerFacade.getCustomerFacade(emf);
    }

    /**
     * Retrieves representation of an instance of rest.CustomerResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCustomers() {
        List<Customer> allCustomers = cf.getAllCustomers();
        return new Gson().toJson(allCustomers);
    }
    
    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandomCustomers() {
        Random randomizer = new Random();
        List<Customer> allCustomers = cf.getAllCustomers();
        Customer random = allCustomers.get(randomizer.nextInt(allCustomers.size()));
        return new Gson().toJson(random);
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSpecificCustomerById(
            @PathParam("id") int id) {
        return new Gson().toJson(cf.findById(id));
    }
    
    @GET
    @Path("/count")
    //@Produces(MediaType.APPLICATION_JSON)
    public String getCountOfCustomer() {
        return "" + cf.getNumberOfCustomers();
    }

    /**
     * PUT method for updating or creating an instance of CustomerResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
