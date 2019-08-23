/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entities.Animal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import types.AnimalType;

/**
 * REST Web Service
 *
 * @author Martin
 */
@Path("animal")
public class AnimalResource {

    private final Random rand = new Random();
    private final List<Animal> animals = new ArrayList();
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalResource
     */
    public AnimalResource() {
    }

    /**
     * Retrieves representation of an instance of rest.AnimalResource
     * @return an instance of java.lang.String
     */
    @GET
    //@Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "Hello fro my first web service";
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
    public String someMethod()
    {
        animals.add(new Animal(AnimalType.CAT, Calendar.getInstance().getTime(), "Meow"));
        animals.add(new Animal(AnimalType.COW, Calendar.getInstance().getTime(), "Moo"));
        animals.add(new Animal(AnimalType.DOG, Calendar.getInstance().getTime(), "Woof"));
        animals.add(new Animal(AnimalType.FISH, Calendar.getInstance().getTime(), "BLUBLUB"));
        
        int rNum = rand.nextInt(animals.size());
        
        return new Gson().toJson(animals.get(rNum));
        
    }
}
