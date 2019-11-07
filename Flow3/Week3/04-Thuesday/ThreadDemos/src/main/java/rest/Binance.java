/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import facade.BinanceFacade;
import java.net.ProtocolException;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author andreas
 */
@Path("binance")
public class Binance {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Binance
     */
    public Binance() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() throws Exception {
        BinanceFacade bf = new BinanceFacade();
        Map<String, Double> map = bf.getMap();
        
        Gson gson = new Gson();
        return gson.toJson(map);
    }
}
