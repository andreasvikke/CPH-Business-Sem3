/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import managers.Generator;

/**
 * REST Web Service
 *
 * @author andreas
 */
@Path("data")
public class GenerateResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenerateResource() {
    }
    
    @GET
    @Path("/{qty}/{startId}")
    @Produces(MediaType.APPLICATION_XML)
    public Response generateFakeData(@PathParam("qty") int qty, @PathParam("startid") long startId) {
        return Response.ok(Generator.Generate(qty, startId)).build();
    }
}
