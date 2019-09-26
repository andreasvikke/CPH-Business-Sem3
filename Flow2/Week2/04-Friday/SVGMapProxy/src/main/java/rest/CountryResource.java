package rest;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;
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

/**
 * REST Web Service
 *
 * @author andreas
 */
@Path("country")
public class CountryResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CountryResource
     */
    public CountryResource() {
    }

    public class Country {
        public String name;
        public long population;
        public String area;

        public Country(String name, long population, String area) {
            this.name = name;
            this.population = population;
            this.area = area;
        }
    }
    
    @GET
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCountryData(@PathParam("code") String code) throws MalformedURLException, ProtocolException, IOException {
        URL url = new URL("https://restcountries.eu/rest/v1/alpha?codes=" + code);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        Scanner scan = new Scanner(con.getInputStream());
        String jsonStr=null;
        if (scan.hasNext()) {
           jsonStr = scan.nextLine();
        }
        scan.close();
        
        return Response.ok(jsonStr).build();
    }
}
