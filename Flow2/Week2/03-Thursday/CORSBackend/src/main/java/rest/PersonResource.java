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
    
    @GET
    @Path("/swagger")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSwagger() {
        return Response.ok("{\n" +
"  \"swagger\" : \"2.0\",\n" +
"  \"info\" : {\n" +
"    \"description\" : \"CA-2 API documentation\",\n" +
"    \"version\" : \"1.1\",\n" +
"    \"title\" : \"Course Assignment 2\"\n" +
"  },\n" +
"  \"host\" : \"virtserver.swaggerhub.com\",\n" +
"  \"basePath\" : \"/Out-of-Memory/CourseAssignmentTwo/1\",\n" +
"  \"schemes\" : [ \"https\" ],\n" +
"  \"paths\" : {\n" +
"    \"/person\" : {\n" +
"      \"post\" : {\n" +
"        \"tags\" : [ \"person\" ],\n" +
"        \"summary\" : \"Add a new person\",\n" +
"        \"description\" : \"\",\n" +
"        \"operationId\" : \"addPerson\",\n" +
"        \"consumes\" : [ \"application/json\" ],\n" +
"        \"produces\" : [ \"application/json\" ],\n" +
"        \"parameters\" : [ {\n" +
"          \"in\" : \"body\",\n" +
"          \"name\" : \"body\",\n" +
"          \"description\" : \"Person object that needs to be added\",\n" +
"          \"required\" : true,\n" +
"          \"schema\" : {\n" +
"            \"$ref\" : \"#/definitions/Person\"\n" +
"          }\n" +
"        } ],\n" +
"        \"responses\" : {\n" +
"          \"200\" : {\n" +
"            \"description\" : \"Successful operation\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"405\" : {\n" +
"            \"description\" : \"Invalid input\",\n" +
"            \"schema\" : { }\n" +
"          }\n" +
"        }\n" +
"      },\n" +
"      \"put\" : {\n" +
"        \"tags\" : [ \"person\" ],\n" +
"        \"summary\" : \"Edit a person\",\n" +
"        \"description\" : \"\",\n" +
"        \"operationId\" : \"editPerson\",\n" +
"        \"consumes\" : [ \"application/json\" ],\n" +
"        \"produces\" : [ \"application/json\" ],\n" +
"        \"parameters\" : [ {\n" +
"          \"in\" : \"body\",\n" +
"          \"name\" : \"body\",\n" +
"          \"description\" : \"Person object that needs to be edited\",\n" +
"          \"required\" : true,\n" +
"          \"schema\" : {\n" +
"            \"$ref\" : \"#/definitions/Person\"\n" +
"          }\n" +
"        } ],\n" +
"        \"responses\" : {\n" +
"          \"200\" : {\n" +
"            \"description\" : \"Successful operation\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"400\" : {\n" +
"            \"description\" : \"Invalid Id supplied\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"404\" : {\n" +
"            \"description\" : \"Person not found\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"405\" : {\n" +
"            \"description\" : \"Invalid input\",\n" +
"            \"schema\" : { }\n" +
"          }\n" +
"        }\n" +
"      }\n" +
"    },\n" +
"    \"/person/all\" : {\n" +
"      \"get\" : {\n" +
"        \"tags\" : [ \"person\" ],\n" +
"        \"summary\" : \"Get all persons\",\n" +
"        \"description\" : \"\",\n" +
"        \"operationId\" : \"getAllPersons\",\n" +
"        \"produces\" : [ \"application/json\" ],\n" +
"        \"parameters\" : [ ],\n" +
"        \"responses\" : {\n" +
"          \"200\" : {\n" +
"            \"description\" : \"Succesful operation\",\n" +
"            \"schema\" : {\n" +
"              \"type\" : \"array\",\n" +
"              \"items\" : {\n" +
"                \"$ref\" : \"#/definitions/Person\"\n" +
"              }\n" +
"            }\n" +
"          }\n" +
"        }\n" +
"      }\n" +
"    },\n" +
"    \"/person/{personId}\" : {\n" +
"      \"get\" : {\n" +
"        \"tags\" : [ \"person\" ],\n" +
"        \"summary\" : \"Get person by id\",\n" +
"        \"description\" : \"\",\n" +
"        \"operationId\" : \"getPersonById\",\n" +
"        \"produces\" : [ \"application/json\" ],\n" +
"        \"parameters\" : [ {\n" +
"          \"name\" : \"personId\",\n" +
"          \"in\" : \"path\",\n" +
"          \"description\" : \"Id of person to return\",\n" +
"          \"required\" : true,\n" +
"          \"type\" : \"integer\",\n" +
"          \"format\" : \"int64\"\n" +
"        } ],\n" +
"        \"responses\" : {\n" +
"          \"200\" : {\n" +
"            \"description\" : \"Succesful operation\",\n" +
"            \"schema\" : {\n" +
"              \"$ref\" : \"#/definitions/Person\"\n" +
"            }\n" +
"          },\n" +
"          \"400\" : {\n" +
"            \"description\" : \"Invalid Id supplied\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"404\" : {\n" +
"            \"description\" : \"Person not found\",\n" +
"            \"schema\" : { }\n" +
"          }\n" +
"        }\n" +
"      },\n" +
"      \"delete\" : {\n" +
"        \"tags\" : [ \"person\" ],\n" +
"        \"summary\" : \"Deletes a person by id\",\n" +
"        \"description\" : \"\",\n" +
"        \"operationId\" : \"deletePersonById\",\n" +
"        \"produces\" : [ \"application/json\" ],\n" +
"        \"parameters\" : [ {\n" +
"          \"name\" : \"personId\",\n" +
"          \"in\" : \"path\",\n" +
"          \"description\" : \"Id of person to delete\",\n" +
"          \"required\" : true,\n" +
"          \"type\" : \"integer\",\n" +
"          \"format\" : \"int64\"\n" +
"        } ],\n" +
"        \"responses\" : {\n" +
"          \"200\" : {\n" +
"            \"description\" : \"Succesful operation\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"400\" : {\n" +
"            \"description\" : \"Invalid Id supplied\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"404\" : {\n" +
"            \"description\" : \"Person not found\",\n" +
"            \"schema\" : { }\n" +
"          }\n" +
"        }\n" +
"      }\n" +
"    },\n" +
"    \"/person/findByPhone/{phone}\" : {\n" +
"      \"get\" : {\n" +
"        \"tags\" : [ \"person get\" ],\n" +
"        \"summary\" : \"Get a person by phone number\",\n" +
"        \"description\" : \"\",\n" +
"        \"operationId\" : \"getPersonByPhone\",\n" +
"        \"produces\" : [ \"application/json\" ],\n" +
"        \"parameters\" : [ {\n" +
"          \"name\" : \"phone\",\n" +
"          \"in\" : \"path\",\n" +
"          \"description\" : \"Phone of person to return\",\n" +
"          \"required\" : true,\n" +
"          \"type\" : \"string\"\n" +
"        } ],\n" +
"        \"responses\" : {\n" +
"          \"200\" : {\n" +
"            \"description\" : \"Succesful operation\",\n" +
"            \"schema\" : {\n" +
"              \"$ref\" : \"#/definitions/Person\"\n" +
"            }\n" +
"          },\n" +
"          \"400\" : {\n" +
"            \"description\" : \"Invalid phone supplied\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"404\" : {\n" +
"            \"description\" : \"Person not found\",\n" +
"            \"schema\" : { }\n" +
"          }\n" +
"        }\n" +
"      }\n" +
"    },\n" +
"    \"/person/findByHobby/{hobby}\" : {\n" +
"      \"get\" : {\n" +
"        \"tags\" : [ \"person get\" ],\n" +
"        \"summary\" : \"Get all persons by hobbies\",\n" +
"        \"description\" : \"\",\n" +
"        \"operationId\" : \"getAllPersonsByHobby\",\n" +
"        \"produces\" : [ \"application/json\" ],\n" +
"        \"parameters\" : [ {\n" +
"          \"name\" : \"hobby\",\n" +
"          \"in\" : \"path\",\n" +
"          \"description\" : \"Hobby of persons to return\",\n" +
"          \"required\" : true,\n" +
"          \"type\" : \"string\"\n" +
"        } ],\n" +
"        \"responses\" : {\n" +
"          \"200\" : {\n" +
"            \"description\" : \"Succesful operation\",\n" +
"            \"schema\" : {\n" +
"              \"$ref\" : \"#/definitions/Person\"\n" +
"            }\n" +
"          },\n" +
"          \"400\" : {\n" +
"            \"description\" : \"Invalid hobby supplied\",\n" +
"            \"schema\" : { }\n" +
"          }\n" +
"        }\n" +
"      }\n" +
"    },\n" +
"    \"/person/findByCity\" : {\n" +
"      \"get\" : {\n" +
"        \"tags\" : [ \"person get\" ],\n" +
"        \"summary\" : \"Get all persons by hobby\",\n" +
"        \"description\" : \"\",\n" +
"        \"operationId\" : \"getAllPersonsByCity\",\n" +
"        \"produces\" : [ \"application/json\" ],\n" +
"        \"parameters\" : [ {\n" +
"          \"in\" : \"body\",\n" +
"          \"name\" : \"body\",\n" +
"          \"description\" : \"Hobby of persons to return\",\n" +
"          \"required\" : true,\n" +
"          \"schema\" : {\n" +
"            \"$ref\" : \"#/definitions/CityInfo\"\n" +
"          }\n" +
"        } ],\n" +
"        \"responses\" : {\n" +
"          \"200\" : {\n" +
"            \"description\" : \"Succesful operation\",\n" +
"            \"schema\" : {\n" +
"              \"type\" : \"array\",\n" +
"              \"items\" : {\n" +
"                \"$ref\" : \"#/definitions/Person\"\n" +
"              }\n" +
"            }\n" +
"          },\n" +
"          \"400\" : {\n" +
"            \"description\" : \"Invalid hobby supplied\",\n" +
"            \"schema\" : { }\n" +
"          }\n" +
"        }\n" +
"      }\n" +
"    },\n" +
"    \"/person/countByHobby\" : {\n" +
"      \"get\" : {\n" +
"        \"tags\" : [ \"person get\" ],\n" +
"        \"summary\" : \"Get count of persons by hobby\",\n" +
"        \"description\" : \"\",\n" +
"        \"operationId\" : \"getCountOfPersonsByHobby\",\n" +
"        \"produces\" : [ \"application/json\" ],\n" +
"        \"parameters\" : [ {\n" +
"          \"in\" : \"body\",\n" +
"          \"name\" : \"body\",\n" +
"          \"description\" : \"Hobby of persons to count\",\n" +
"          \"required\" : true,\n" +
"          \"schema\" : {\n" +
"            \"$ref\" : \"#/definitions/Hobby\"\n" +
"          }\n" +
"        } ],\n" +
"        \"responses\" : {\n" +
"          \"200\" : {\n" +
"            \"description\" : \"Succesful operation\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"400\" : {\n" +
"            \"description\" : \"Invalid hobby supplied\",\n" +
"            \"schema\" : { }\n" +
"          }\n" +
"        }\n" +
"      }\n" +
"    },\n" +
"    \"/company\" : {\n" +
"      \"post\" : {\n" +
"        \"tags\" : [ \"company\" ],\n" +
"        \"summary\" : \"Add a new company\",\n" +
"        \"description\" : \"\",\n" +
"        \"operationId\" : \"addCompany\",\n" +
"        \"consumes\" : [ \"application/json\" ],\n" +
"        \"produces\" : [ \"application/json\" ],\n" +
"        \"parameters\" : [ {\n" +
"          \"in\" : \"body\",\n" +
"          \"name\" : \"body\",\n" +
"          \"description\" : \"Company object that needs to be added\",\n" +
"          \"required\" : true,\n" +
"          \"schema\" : {\n" +
"            \"$ref\" : \"#/definitions/Company\"\n" +
"          }\n" +
"        } ],\n" +
"        \"responses\" : {\n" +
"          \"200\" : {\n" +
"            \"description\" : \"Successful operation\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"405\" : {\n" +
"            \"description\" : \"Invalid input\",\n" +
"            \"schema\" : { }\n" +
"          }\n" +
"        }\n" +
"      },\n" +
"      \"put\" : {\n" +
"        \"tags\" : [ \"company\" ],\n" +
"        \"summary\" : \"Edit a company\",\n" +
"        \"description\" : \"\",\n" +
"        \"operationId\" : \"editCompany\",\n" +
"        \"consumes\" : [ \"application/json\" ],\n" +
"        \"produces\" : [ \"application/json\" ],\n" +
"        \"parameters\" : [ {\n" +
"          \"in\" : \"body\",\n" +
"          \"name\" : \"body\",\n" +
"          \"description\" : \"Company object that needs to be edited\",\n" +
"          \"required\" : true,\n" +
"          \"schema\" : {\n" +
"            \"$ref\" : \"#/definitions/Company\"\n" +
"          }\n" +
"        } ],\n" +
"        \"responses\" : {\n" +
"          \"200\" : {\n" +
"            \"description\" : \"Successful operation\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"400\" : {\n" +
"            \"description\" : \"Invalid Id supplied\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"404\" : {\n" +
"            \"description\" : \"Company not found\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"405\" : {\n" +
"            \"description\" : \"Invalid input\",\n" +
"            \"schema\" : { }\n" +
"          }\n" +
"        }\n" +
"      }\n" +
"    },\n" +
"    \"/company/all\" : {\n" +
"      \"get\" : {\n" +
"        \"tags\" : [ \"company\" ],\n" +
"        \"summary\" : \"Get all companies\",\n" +
"        \"description\" : \"\",\n" +
"        \"operationId\" : \"getAllCompanies\",\n" +
"        \"produces\" : [ \"application/json\" ],\n" +
"        \"parameters\" : [ ],\n" +
"        \"responses\" : {\n" +
"          \"200\" : {\n" +
"            \"description\" : \"Succesful operation\",\n" +
"            \"schema\" : {\n" +
"              \"type\" : \"array\",\n" +
"              \"items\" : {\n" +
"                \"$ref\" : \"#/definitions/Company\"\n" +
"              }\n" +
"            }\n" +
"          }\n" +
"        }\n" +
"      }\n" +
"    },\n" +
"    \"/company/{compId}\" : {\n" +
"      \"get\" : {\n" +
"        \"tags\" : [ \"company\" ],\n" +
"        \"summary\" : \"Get company by id\",\n" +
"        \"description\" : \"\",\n" +
"        \"operationId\" : \"getCompanyById\",\n" +
"        \"produces\" : [ \"application/json\" ],\n" +
"        \"parameters\" : [ {\n" +
"          \"name\" : \"compId\",\n" +
"          \"in\" : \"path\",\n" +
"          \"description\" : \"Id of company to return\",\n" +
"          \"required\" : true,\n" +
"          \"type\" : \"integer\",\n" +
"          \"format\" : \"int64\"\n" +
"        } ],\n" +
"        \"responses\" : {\n" +
"          \"200\" : {\n" +
"            \"description\" : \"Succesful operation\",\n" +
"            \"schema\" : {\n" +
"              \"$ref\" : \"#/definitions/Company\"\n" +
"            }\n" +
"          },\n" +
"          \"400\" : {\n" +
"            \"description\" : \"Invalid Id supplied\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"404\" : {\n" +
"            \"description\" : \"Company not found\",\n" +
"            \"schema\" : { }\n" +
"          }\n" +
"        }\n" +
"      },\n" +
"      \"delete\" : {\n" +
"        \"tags\" : [ \"company\" ],\n" +
"        \"summary\" : \"Deletes a company by id\",\n" +
"        \"description\" : \"\",\n" +
"        \"operationId\" : \"deleteCompanyById\",\n" +
"        \"produces\" : [ \"application/json\" ],\n" +
"        \"parameters\" : [ {\n" +
"          \"name\" : \"compId\",\n" +
"          \"in\" : \"path\",\n" +
"          \"description\" : \"Id of company to delete\",\n" +
"          \"required\" : true,\n" +
"          \"type\" : \"integer\",\n" +
"          \"format\" : \"int64\"\n" +
"        } ],\n" +
"        \"responses\" : {\n" +
"          \"200\" : {\n" +
"            \"description\" : \"Succesful operation\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"400\" : {\n" +
"            \"description\" : \"Invalid Id supplied\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"404\" : {\n" +
"            \"description\" : \"Company not found\",\n" +
"            \"schema\" : { }\n" +
"          }\n" +
"        }\n" +
"      }\n" +
"    },\n" +
"    \"/company/findByPhone/{phone}\" : {\n" +
"      \"get\" : {\n" +
"        \"tags\" : [ \"company get\" ],\n" +
"        \"summary\" : \"Get company by phone\",\n" +
"        \"description\" : \"\",\n" +
"        \"operationId\" : \"getCompanyByPhone\",\n" +
"        \"produces\" : [ \"application/json\" ],\n" +
"        \"parameters\" : [ {\n" +
"          \"name\" : \"phone\",\n" +
"          \"in\" : \"path\",\n" +
"          \"description\" : \"Phone of company to return\",\n" +
"          \"required\" : true,\n" +
"          \"type\" : \"string\"\n" +
"        } ],\n" +
"        \"responses\" : {\n" +
"          \"200\" : {\n" +
"            \"description\" : \"Succesful operation\",\n" +
"            \"schema\" : {\n" +
"              \"$ref\" : \"#/definitions/Company\"\n" +
"            }\n" +
"          },\n" +
"          \"400\" : {\n" +
"            \"description\" : \"Invalid Id supplied\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"404\" : {\n" +
"            \"description\" : \"Company not found\",\n" +
"            \"schema\" : { }\n" +
"          }\n" +
"        }\n" +
"      }\n" +
"    },\n" +
"    \"/company/findByCVR/{cvr}\" : {\n" +
"      \"get\" : {\n" +
"        \"tags\" : [ \"company get\" ],\n" +
"        \"summary\" : \"Get company by cvr\",\n" +
"        \"description\" : \"\",\n" +
"        \"operationId\" : \"getCompanyByCVR\",\n" +
"        \"produces\" : [ \"application/json\" ],\n" +
"        \"parameters\" : [ {\n" +
"          \"name\" : \"cvr\",\n" +
"          \"in\" : \"path\",\n" +
"          \"description\" : \"CVR of company to return\",\n" +
"          \"required\" : true,\n" +
"          \"type\" : \"string\"\n" +
"        } ],\n" +
"        \"responses\" : {\n" +
"          \"200\" : {\n" +
"            \"description\" : \"Succesful operation\",\n" +
"            \"schema\" : {\n" +
"              \"$ref\" : \"#/definitions/Company\"\n" +
"            }\n" +
"          },\n" +
"          \"400\" : {\n" +
"            \"description\" : \"Invalid Id supplied\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"404\" : {\n" +
"            \"description\" : \"Company not found\",\n" +
"            \"schema\" : { }\n" +
"          }\n" +
"        }\n" +
"      }\n" +
"    },\n" +
"    \"/address\" : {\n" +
"      \"post\" : {\n" +
"        \"tags\" : [ \"address\" ],\n" +
"        \"summary\" : \"Add a new address\",\n" +
"        \"description\" : \"\",\n" +
"        \"operationId\" : \"addAddress\",\n" +
"        \"consumes\" : [ \"application/json\" ],\n" +
"        \"produces\" : [ \"application/json\" ],\n" +
"        \"parameters\" : [ {\n" +
"          \"in\" : \"body\",\n" +
"          \"name\" : \"body\",\n" +
"          \"description\" : \"Address object that needs to be added\",\n" +
"          \"required\" : true,\n" +
"          \"schema\" : {\n" +
"            \"$ref\" : \"#/definitions/Address\"\n" +
"          }\n" +
"        } ],\n" +
"        \"responses\" : {\n" +
"          \"200\" : {\n" +
"            \"description\" : \"Successful operation\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"405\" : {\n" +
"            \"description\" : \"Invalid input\",\n" +
"            \"schema\" : { }\n" +
"          }\n" +
"        }\n" +
"      },\n" +
"      \"put\" : {\n" +
"        \"tags\" : [ \"address\" ],\n" +
"        \"summary\" : \"Edit an address\",\n" +
"        \"description\" : \"\",\n" +
"        \"operationId\" : \"editAddress\",\n" +
"        \"consumes\" : [ \"application/json\" ],\n" +
"        \"produces\" : [ \"application/json\" ],\n" +
"        \"parameters\" : [ {\n" +
"          \"in\" : \"body\",\n" +
"          \"name\" : \"body\",\n" +
"          \"description\" : \"Address object that needs to be edited\",\n" +
"          \"required\" : true,\n" +
"          \"schema\" : {\n" +
"            \"$ref\" : \"#/definitions/Address\"\n" +
"          }\n" +
"        } ],\n" +
"        \"responses\" : {\n" +
"          \"200\" : {\n" +
"            \"description\" : \"Successful operation\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"400\" : {\n" +
"            \"description\" : \"Invalid Id supplied\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"404\" : {\n" +
"            \"description\" : \"Address not found\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"405\" : {\n" +
"            \"description\" : \"Invalid input\",\n" +
"            \"schema\" : { }\n" +
"          }\n" +
"        }\n" +
"      }\n" +
"    },\n" +
"    \"/address/all\" : {\n" +
"      \"get\" : {\n" +
"        \"tags\" : [ \"address\" ],\n" +
"        \"summary\" : \"Get all addresses\",\n" +
"        \"description\" : \"\",\n" +
"        \"operationId\" : \"getAllAddresses\",\n" +
"        \"produces\" : [ \"application/json\" ],\n" +
"        \"parameters\" : [ ],\n" +
"        \"responses\" : {\n" +
"          \"200\" : {\n" +
"            \"description\" : \"Succesful operation\",\n" +
"            \"schema\" : {\n" +
"              \"type\" : \"array\",\n" +
"              \"items\" : {\n" +
"                \"$ref\" : \"#/definitions/Address\"\n" +
"              }\n" +
"            }\n" +
"          }\n" +
"        }\n" +
"      }\n" +
"    },\n" +
"    \"/address/{addressId}\" : {\n" +
"      \"get\" : {\n" +
"        \"tags\" : [ \"address\" ],\n" +
"        \"summary\" : \"Get address by id\",\n" +
"        \"description\" : \"\",\n" +
"        \"operationId\" : \"getAddressById\",\n" +
"        \"produces\" : [ \"application/json\" ],\n" +
"        \"parameters\" : [ {\n" +
"          \"name\" : \"addressId\",\n" +
"          \"in\" : \"path\",\n" +
"          \"description\" : \"Id of address to return\",\n" +
"          \"required\" : true,\n" +
"          \"type\" : \"integer\",\n" +
"          \"format\" : \"int64\"\n" +
"        } ],\n" +
"        \"responses\" : {\n" +
"          \"200\" : {\n" +
"            \"description\" : \"Succesful operation\",\n" +
"            \"schema\" : {\n" +
"              \"$ref\" : \"#/definitions/Address\"\n" +
"            }\n" +
"          },\n" +
"          \"400\" : {\n" +
"            \"description\" : \"Invalid Id supplied\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"404\" : {\n" +
"            \"description\" : \"Address not found\",\n" +
"            \"schema\" : { }\n" +
"          }\n" +
"        }\n" +
"      },\n" +
"      \"delete\" : {\n" +
"        \"tags\" : [ \"address\" ],\n" +
"        \"summary\" : \"Deletes a address by id\",\n" +
"        \"description\" : \"\",\n" +
"        \"operationId\" : \"deleteAddressById\",\n" +
"        \"produces\" : [ \"application/json\" ],\n" +
"        \"parameters\" : [ {\n" +
"          \"name\" : \"addressId\",\n" +
"          \"in\" : \"path\",\n" +
"          \"description\" : \"Id of address to delete\",\n" +
"          \"required\" : true,\n" +
"          \"type\" : \"integer\",\n" +
"          \"format\" : \"int64\"\n" +
"        } ],\n" +
"        \"responses\" : {\n" +
"          \"200\" : {\n" +
"            \"description\" : \"Succesful operation\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"400\" : {\n" +
"            \"description\" : \"Invalid Id supplied\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"404\" : {\n" +
"            \"description\" : \"Address not found\",\n" +
"            \"schema\" : { }\n" +
"          }\n" +
"        }\n" +
"      }\n" +
"    },\n" +
"    \"/hobby\" : {\n" +
"      \"post\" : {\n" +
"        \"tags\" : [ \"hobby\" ],\n" +
"        \"summary\" : \"Add a new hobby\",\n" +
"        \"description\" : \"\",\n" +
"        \"operationId\" : \"addHobby\",\n" +
"        \"consumes\" : [ \"application/json\" ],\n" +
"        \"produces\" : [ \"application/json\" ],\n" +
"        \"parameters\" : [ {\n" +
"          \"in\" : \"body\",\n" +
"          \"name\" : \"body\",\n" +
"          \"description\" : \"Hobby object that needs to be added\",\n" +
"          \"required\" : true,\n" +
"          \"schema\" : {\n" +
"            \"$ref\" : \"#/definitions/Hobby\"\n" +
"          }\n" +
"        } ],\n" +
"        \"responses\" : {\n" +
"          \"200\" : {\n" +
"            \"description\" : \"Successful operation\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"405\" : {\n" +
"            \"description\" : \"Invalid input\",\n" +
"            \"schema\" : { }\n" +
"          }\n" +
"        }\n" +
"      },\n" +
"      \"put\" : {\n" +
"        \"tags\" : [ \"hobby\" ],\n" +
"        \"summary\" : \"Edit an hobby\",\n" +
"        \"description\" : \"\",\n" +
"        \"operationId\" : \"editHobby\",\n" +
"        \"consumes\" : [ \"application/json\" ],\n" +
"        \"produces\" : [ \"application/json\" ],\n" +
"        \"parameters\" : [ {\n" +
"          \"in\" : \"body\",\n" +
"          \"name\" : \"body\",\n" +
"          \"description\" : \"Hobby object that needs to be edited\",\n" +
"          \"required\" : true,\n" +
"          \"schema\" : {\n" +
"            \"$ref\" : \"#/definitions/Hobby\"\n" +
"          }\n" +
"        } ],\n" +
"        \"responses\" : {\n" +
"          \"200\" : {\n" +
"            \"description\" : \"Successful operation\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"400\" : {\n" +
"            \"description\" : \"Invalid Id supplied\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"404\" : {\n" +
"            \"description\" : \"Hobby not found\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"405\" : {\n" +
"            \"description\" : \"Invalid input\",\n" +
"            \"schema\" : { }\n" +
"          }\n" +
"        }\n" +
"      }\n" +
"    },\n" +
"    \"/hobby/all\" : {\n" +
"      \"get\" : {\n" +
"        \"tags\" : [ \"hobby\" ],\n" +
"        \"summary\" : \"Get all hobbies\",\n" +
"        \"description\" : \"\",\n" +
"        \"operationId\" : \"getAllHobbies\",\n" +
"        \"produces\" : [ \"application/json\" ],\n" +
"        \"parameters\" : [ ],\n" +
"        \"responses\" : {\n" +
"          \"200\" : {\n" +
"            \"description\" : \"Succesful operation\",\n" +
"            \"schema\" : {\n" +
"              \"type\" : \"array\",\n" +
"              \"items\" : {\n" +
"                \"$ref\" : \"#/definitions/Hobby\"\n" +
"              }\n" +
"            }\n" +
"          }\n" +
"        }\n" +
"      }\n" +
"    },\n" +
"    \"/hobby/{hobbyId}\" : {\n" +
"      \"get\" : {\n" +
"        \"tags\" : [ \"hobby\" ],\n" +
"        \"summary\" : \"Get a hobby by id\",\n" +
"        \"description\" : \"\",\n" +
"        \"operationId\" : \"getHobbyById\",\n" +
"        \"produces\" : [ \"application/json\" ],\n" +
"        \"parameters\" : [ {\n" +
"          \"name\" : \"hobbyId\",\n" +
"          \"in\" : \"path\",\n" +
"          \"description\" : \"Id of hobby to return\",\n" +
"          \"required\" : true,\n" +
"          \"type\" : \"integer\",\n" +
"          \"format\" : \"int64\"\n" +
"        } ],\n" +
"        \"responses\" : {\n" +
"          \"200\" : {\n" +
"            \"description\" : \"Succesful operation\",\n" +
"            \"schema\" : {\n" +
"              \"$ref\" : \"#/definitions/Hobby\"\n" +
"            }\n" +
"          },\n" +
"          \"400\" : {\n" +
"            \"description\" : \"Invalid Id supplied\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"404\" : {\n" +
"            \"description\" : \"Hobby not found\",\n" +
"            \"schema\" : { }\n" +
"          }\n" +
"        }\n" +
"      },\n" +
"      \"delete\" : {\n" +
"        \"tags\" : [ \"hobby\" ],\n" +
"        \"summary\" : \"Deletes an hobby by id\",\n" +
"        \"description\" : \"\",\n" +
"        \"operationId\" : \"deleteHobbyById\",\n" +
"        \"produces\" : [ \"application/json\" ],\n" +
"        \"parameters\" : [ {\n" +
"          \"name\" : \"hobbyId\",\n" +
"          \"in\" : \"path\",\n" +
"          \"description\" : \"Id of hobby to delete\",\n" +
"          \"required\" : true,\n" +
"          \"type\" : \"integer\",\n" +
"          \"format\" : \"int64\"\n" +
"        } ],\n" +
"        \"responses\" : {\n" +
"          \"200\" : {\n" +
"            \"description\" : \"Succesful operation\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"400\" : {\n" +
"            \"description\" : \"Invalid Id supplied\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"404\" : {\n" +
"            \"description\" : \"Hobby not found\",\n" +
"            \"schema\" : { }\n" +
"          }\n" +
"        }\n" +
"      }\n" +
"    },\n" +
"    \"/phone\" : {\n" +
"      \"post\" : {\n" +
"        \"tags\" : [ \"phone\" ],\n" +
"        \"summary\" : \"Add a new phone\",\n" +
"        \"description\" : \"\",\n" +
"        \"operationId\" : \"addPhone\",\n" +
"        \"consumes\" : [ \"application/json\" ],\n" +
"        \"produces\" : [ \"application/json\" ],\n" +
"        \"parameters\" : [ {\n" +
"          \"in\" : \"body\",\n" +
"          \"name\" : \"body\",\n" +
"          \"description\" : \"Phone object that needs to be added\",\n" +
"          \"required\" : true,\n" +
"          \"schema\" : {\n" +
"            \"$ref\" : \"#/definitions/Phone\"\n" +
"          }\n" +
"        } ],\n" +
"        \"responses\" : {\n" +
"          \"200\" : {\n" +
"            \"description\" : \"Successful operation\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"405\" : {\n" +
"            \"description\" : \"Invalid input\",\n" +
"            \"schema\" : { }\n" +
"          }\n" +
"        }\n" +
"      },\n" +
"      \"put\" : {\n" +
"        \"tags\" : [ \"phone\" ],\n" +
"        \"summary\" : \"Edit an phone\",\n" +
"        \"description\" : \"\",\n" +
"        \"operationId\" : \"editPhone\",\n" +
"        \"consumes\" : [ \"application/json\" ],\n" +
"        \"produces\" : [ \"application/json\" ],\n" +
"        \"parameters\" : [ {\n" +
"          \"in\" : \"body\",\n" +
"          \"name\" : \"body\",\n" +
"          \"description\" : \"Phone object that needs to be edited\",\n" +
"          \"required\" : true,\n" +
"          \"schema\" : {\n" +
"            \"$ref\" : \"#/definitions/Phone\"\n" +
"          }\n" +
"        } ],\n" +
"        \"responses\" : {\n" +
"          \"200\" : {\n" +
"            \"description\" : \"Successful operation\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"400\" : {\n" +
"            \"description\" : \"Invalid Id supplied\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"404\" : {\n" +
"            \"description\" : \"Phone not found\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"405\" : {\n" +
"            \"description\" : \"Invalid input\",\n" +
"            \"schema\" : { }\n" +
"          }\n" +
"        }\n" +
"      }\n" +
"    },\n" +
"    \"/phone/all\" : {\n" +
"      \"get\" : {\n" +
"        \"tags\" : [ \"phone\" ],\n" +
"        \"summary\" : \"Get all phones\",\n" +
"        \"description\" : \"\",\n" +
"        \"operationId\" : \"getAllPhones\",\n" +
"        \"produces\" : [ \"application/json\" ],\n" +
"        \"parameters\" : [ ],\n" +
"        \"responses\" : {\n" +
"          \"200\" : {\n" +
"            \"description\" : \"Succesful operation\",\n" +
"            \"schema\" : {\n" +
"              \"type\" : \"array\",\n" +
"              \"items\" : {\n" +
"                \"$ref\" : \"#/definitions/Phone\"\n" +
"              }\n" +
"            }\n" +
"          }\n" +
"        }\n" +
"      }\n" +
"    },\n" +
"    \"/phone/{phoneId}\" : {\n" +
"      \"get\" : {\n" +
"        \"tags\" : [ \"phone\" ],\n" +
"        \"summary\" : \"Get a phone by id\",\n" +
"        \"description\" : \"\",\n" +
"        \"operationId\" : \"getPhoneById\",\n" +
"        \"produces\" : [ \"application/json\" ],\n" +
"        \"parameters\" : [ {\n" +
"          \"name\" : \"phoneId\",\n" +
"          \"in\" : \"path\",\n" +
"          \"description\" : \"Id of phone to return\",\n" +
"          \"required\" : true,\n" +
"          \"type\" : \"integer\",\n" +
"          \"format\" : \"int64\"\n" +
"        } ],\n" +
"        \"responses\" : {\n" +
"          \"200\" : {\n" +
"            \"description\" : \"Succesful operation\",\n" +
"            \"schema\" : {\n" +
"              \"$ref\" : \"#/definitions/Phone\"\n" +
"            }\n" +
"          },\n" +
"          \"400\" : {\n" +
"            \"description\" : \"Invalid Id supplied\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"404\" : {\n" +
"            \"description\" : \"Phone not found\",\n" +
"            \"schema\" : { }\n" +
"          }\n" +
"        }\n" +
"      },\n" +
"      \"delete\" : {\n" +
"        \"tags\" : [ \"phone\" ],\n" +
"        \"summary\" : \"Deletes an phone by id\",\n" +
"        \"description\" : \"\",\n" +
"        \"operationId\" : \"deletePhoneById\",\n" +
"        \"produces\" : [ \"application/json\" ],\n" +
"        \"parameters\" : [ {\n" +
"          \"name\" : \"phoneId\",\n" +
"          \"in\" : \"path\",\n" +
"          \"description\" : \"Id of phone to delete\",\n" +
"          \"required\" : true,\n" +
"          \"type\" : \"integer\",\n" +
"          \"format\" : \"int64\"\n" +
"        } ],\n" +
"        \"responses\" : {\n" +
"          \"200\" : {\n" +
"            \"description\" : \"Succesful operation\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"400\" : {\n" +
"            \"description\" : \"Invalid Id supplied\",\n" +
"            \"schema\" : { }\n" +
"          },\n" +
"          \"404\" : {\n" +
"            \"description\" : \"Phone not found\",\n" +
"            \"schema\" : { }\n" +
"          }\n" +
"        }\n" +
"      }\n" +
"    }\n" +
"  },\n" +
"  \"definitions\" : {\n" +
"    \"Person\" : {\n" +
"      \"type\" : \"object\",\n" +
"      \"properties\" : {\n" +
"        \"id\" : {\n" +
"          \"type\" : \"integer\",\n" +
"          \"format\" : \"int64\"\n" +
"        },\n" +
"        \"firstName\" : {\n" +
"          \"type\" : \"string\"\n" +
"        },\n" +
"        \"lastName\" : {\n" +
"          \"type\" : \"string\"\n" +
"        },\n" +
"        \"hobbies\" : {\n" +
"          \"type\" : \"array\",\n" +
"          \"items\" : {\n" +
"            \"$ref\" : \"#/definitions/Hobby\"\n" +
"          }\n" +
"        },\n" +
"        \"infoEntity\" : { }\n" +
"      }\n" +
"    },\n" +
"    \"Address\" : {\n" +
"      \"type\" : \"object\",\n" +
"      \"properties\" : {\n" +
"        \"id\" : {\n" +
"          \"type\" : \"integer\",\n" +
"          \"format\" : \"int64\"\n" +
"        },\n" +
"        \"street\" : {\n" +
"          \"type\" : \"string\"\n" +
"        },\n" +
"        \"city\" : {\n" +
"          \"$ref\" : \"#/definitions/CityInfo\"\n" +
"        }\n" +
"      }\n" +
"    },\n" +
"    \"CityInfo\" : {\n" +
"      \"type\" : \"object\",\n" +
"      \"properties\" : {\n" +
"        \"id\" : {\n" +
"          \"type\" : \"integer\",\n" +
"          \"format\" : \"int64\"\n" +
"        },\n" +
"        \"zip\" : {\n" +
"          \"type\" : \"string\"\n" +
"        },\n" +
"        \"city\" : {\n" +
"          \"type\" : \"string\"\n" +
"        }\n" +
"      }\n" +
"    },\n" +
"    \"Hobby\" : {\n" +
"      \"type\" : \"object\",\n" +
"      \"properties\" : {\n" +
"        \"id\" : {\n" +
"          \"type\" : \"integer\",\n" +
"          \"format\" : \"int64\"\n" +
"        },\n" +
"        \"name\" : {\n" +
"          \"type\" : \"string\"\n" +
"        },\n" +
"        \"description\" : {\n" +
"          \"type\" : \"string\"\n" +
"        }\n" +
"      }\n" +
"    },\n" +
"    \"Phone\" : {\n" +
"      \"type\" : \"object\",\n" +
"      \"properties\" : {\n" +
"        \"id\" : {\n" +
"          \"type\" : \"integer\",\n" +
"          \"format\" : \"int64\"\n" +
"        },\n" +
"        \"number\" : {\n" +
"          \"type\" : \"string\"\n" +
"        },\n" +
"        \"description\" : {\n" +
"          \"type\" : \"string\"\n" +
"        }\n" +
"      }\n" +
"    },\n" +
"    \"InfoEntity\" : {\n" +
"      \"type\" : \"object\",\n" +
"      \"properties\" : {\n" +
"        \"id\" : {\n" +
"          \"type\" : \"integer\",\n" +
"          \"format\" : \"int64\"\n" +
"        },\n" +
"        \"email\" : {\n" +
"          \"type\" : \"string\"\n" +
"        },\n" +
"        \"phones\" : {\n" +
"          \"type\" : \"array\",\n" +
"          \"items\" : {\n" +
"            \"$ref\" : \"#/definitions/Phone\"\n" +
"          }\n" +
"        },\n" +
"        \"address\" : {\n" +
"          \"$ref\" : \"#/definitions/Address\"\n" +
"        }\n" +
"      }\n" +
"    },\n" +
"    \"Company\" : {\n" +
"      \"type\" : \"object\",\n" +
"      \"properties\" : {\n" +
"        \"id\" : {\n" +
"          \"type\" : \"integer\",\n" +
"          \"format\" : \"int64\"\n" +
"        },\n" +
"        \"name\" : {\n" +
"          \"type\" : \"string\"\n" +
"        },\n" +
"        \"description\" : {\n" +
"          \"type\" : \"string\"\n" +
"        },\n" +
"        \"cvr\" : {\n" +
"          \"type\" : \"string\"\n" +
"        },\n" +
"        \"employeeCount\" : {\n" +
"          \"type\" : \"integer\",\n" +
"          \"format\" : \"int32\"\n" +
"        },\n" +
"        \"makertValue\" : {\n" +
"          \"type\" : \"integer\",\n" +
"          \"format\" : \"int64\"\n" +
"        },\n" +
"        \"infoEntity\" : { }\n" +
"      }\n" +
"    },\n" +
"    \"ApiResponse\" : {\n" +
"      \"type\" : \"object\",\n" +
"      \"properties\" : {\n" +
"        \"code\" : {\n" +
"          \"type\" : \"integer\",\n" +
"          \"format\" : \"int32\"\n" +
"        },\n" +
"        \"message\" : {\n" +
"          \"type\" : \"string\"\n" +
"        }\n" +
"      }\n" +
"    }\n" +
"  }\n" +
"}").build();
    }
}
