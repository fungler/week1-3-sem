package rest.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CustomerDTO;
import entities.BankCustomer;
import facades.CustomerFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
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


@Path("bankcustomer")
public class BankCustomerResource {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    CustomerFacade facade =  CustomerFacade.getFacadeExample(emf);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String defualtMsg() {
        return "{\"msg\":\"succes\"}";
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getID(@PathParam("id") int id) {
        CustomerDTO bc = facade.getCustomerByID(new Long(id));
        
        return new Gson().toJson(bc);
    }
    
    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        List<BankCustomer> bc = facade.getAllBankCustomers();
        
        return Response.ok().entity(gson.toJson(bc)).build();
    }

    @GET
    @Path("/makecust")
    @Produces({MediaType.APPLICATION_JSON})
    public String makecust(@PathParam("id") int id) {
        
        EntityManager em = emf.createEntityManager();
        
        BankCustomer c1 = new BankCustomer("Martin", "Doi", "1111");
        BankCustomer c2 = new BankCustomer("Mark", "Boi", "2222");
        BankCustomer c3 = new BankCustomer("Mike", "Dude", "3333");
        
        try {
            em.getTransaction().begin();
            em.persist(c1);
            em.persist(c2);
            em.persist(c3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        
        return "making customers";
    }
    
}
