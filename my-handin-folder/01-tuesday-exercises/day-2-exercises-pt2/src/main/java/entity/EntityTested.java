package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import facade.CustomerFacade;

public class EntityTested {
    
    public static void main(String[] args)
    {
        CustomerFacade cf = new CustomerFacade();
        
        // Manuelt
        Customer c1 = new Customer("Dicke", "Mang");
        
        Customer c2 = cf.addCustomer("Mark", "Boi");
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pointpu");
        
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(c1);
        em.persist(c2);
        em.getTransaction().commit();
        
        cf.findByID(1);
        cf.findByLastname("mang");
        cf.getNumOfCustomers();
        
    }
    
}
