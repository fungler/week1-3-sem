package facade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CustomerFacade {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pointpu");
        EntityManager em = emf.createEntityManager();
        
        public Customer findByID(int id) 
        {
            Customer c = null;
           
            c = em.find(Customer.class, Long.valueOf(id));
            return c;
        }
        
        public List<Customer> findByLastname(String ls)
        {
            List<Customer> c = em.createQuery("SELECT c FROM Customer c WHERE c.lastname = '" + ls + "'").getResultList();
            
            return c;
        }
        
        public int getNumOfCustomers()
        {
            List<Customer> c = em.createQuery("SELECT c FROM Customer c").getResultList();
            
            return c.size();
        }
        
        public List<Customer> getAllCustomers()
        {
            return em.createQuery("SELECT c FROM Customer c").getResultList();
        }
        
        public Customer addCustomer(String fName, String lName)
        {
            Customer c = new Customer(fName, lName);
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            
            return c;
        }
}
