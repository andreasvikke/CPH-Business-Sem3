/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.service;

import com.google.gson.Gson;
import entities.BankCustomer;
import facades.BankCustomerFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author andreas
 */
@Path("bankcustomer")
public class BankCustomerResource {

    @Context
    private UriInfo context;
    
    private BankCustomerFacade bcf;

    /**
     * Creates a new instance of GenericResource
     */
    public BankCustomerResource() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        bcf = BankCustomerFacade.getBankCustomerFacade(emf);
    }
    
    @GET
    @Path("/setupDatabase")
    public String setupDatabase() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        List<BankCustomer> bcs = new ArrayList();
        bcs.add(new BankCustomer("Andreas", "Vikke", "1234", 858473, 5, "Info 1"));
        bcs.add(new BankCustomer("Martin", "Frederiksen", "1235", 50000, 4, "Info 2"));
        bcs.add(new BankCustomer("Asger", "Klamydia", "1236", 200, 2, "Info 3"));
        bcs.add(new BankCustomer("William", "Huusfeldt", "1237", 400, 3, "Info 4"));
        
        em.getTransaction().begin();
        for(BankCustomer bc : bcs)
            em.persist(bc);
        em.getTransaction().commit();
        
        em.close();
        emf.close();
        return "Setup Completed!";
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllBankCustomers(
            @PathParam("id") long id) {
        return new Gson().toJson(bcf.getCustomerByID(id));
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllBankCustomers() {
        return new Gson().toJson(bcf.getAllBankCustomers());
    }
}
