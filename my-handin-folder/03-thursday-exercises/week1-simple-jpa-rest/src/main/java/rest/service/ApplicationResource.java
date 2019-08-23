package rest.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.EmployeeDTO;
import entities.Employee;
import facades.EmployeeFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/employee")
public class ApplicationResource {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    EmployeeFacade facade =  EmployeeFacade.getFacadeExample(emf);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String defaultString() {
        return "Default API message";
    }
    
    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response allEmp() {
        
        List<Employee> allEmp = facade.getAllEmployees();
        List<EmployeeDTO> edto = new ArrayList();
        
        for (Employee e : allEmp) 
        {
            edto.add(new EmployeeDTO(e));
        }
        
        return Response.ok().entity(gson.toJson(edto)).build();
    }
    
    

//    @POST
//    @Consumes({MediaType.APPLICATION_JSON})
//    public void create(RenameMe entity) {
//        throw new UnsupportedOperationException();
//    }
//    
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getID(@PathParam("id") int id) {
        
        try {
            Long idToLong = Long.valueOf(id);
            Employee e = facade.getEmployeeById(idToLong);
                    
            return Response.ok().entity(gson.toJson(new EmployeeDTO(e))).build();
        } catch(Exception e)
        {
            return Response.ok().entity(gson.toJson("No employee found with the specified id.")).build();
        }
    }
    
    @GET
    @Path("/highestpaid")
    @Produces({MediaType.APPLICATION_JSON})
    public Response highestEmp() {
        
        Employee e = facade.getHighestSalary();
        EmployeeDTO edto = new EmployeeDTO(e);
        
        return Response.ok().entity(gson.toJson(edto)).build();
    }
    
    @GET
    @Path("/name/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response byName(@PathParam("name") String name) {
        
        List<Employee> el = facade.getEmployeesByName(name);
        List<EmployeeDTO> edto = new ArrayList();
        
        for (Employee e : el)
        {
            edto.add(new EmployeeDTO(e));
        }
        
        return Response.ok().entity(gson.toJson(edto)).build();
    }
    
    @GET
    @Path("/makeemps")
    @Produces({MediaType.APPLICATION_JSON})
    public String makeEmps() {
        
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            em.persist(facade.CreateEmployee("Johnna boi", "qwerty vej 10", 0.f));
            em.persist(facade.CreateEmployee("Mark Doi", "boi vej 10", 200.f));
            em.getTransaction().commit();
        } finally
        {
            em.close();
        }
        
        return "Add emps";
    }
}
