package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class CustomerFacade {

    public static CustomerFacade instance;
    public static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private CustomerFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CustomerFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CustomerDTO getCustomerByID(Long id) {
        EntityManager em = emf.createEntityManager();
        BankCustomer bc = em.find(BankCustomer.class, id);

        CustomerDTO dto = new CustomerDTO(bc);
        
       return dto;
    }
    
    public List<CustomerDTO> getCustomerByName(String name) {
        EntityManager em = emf.createEntityManager();
        List<BankCustomer> bcl = em.createQuery("SELECT c FROM BankCustomer c WHERE c.name = '" + name + "'").getResultList();
        List<CustomerDTO> bcdtol = new ArrayList();
        
        for (BankCustomer bc : bcl) {
            bcdtol.add(new CustomerDTO(bc));
        }
        
        return bcdtol;
    }
    
    public BankCustomer addCustomer(BankCustomer c) {
        return new BankCustomer(c.getFirstname(), c.getFirstname(), c.getAccoutNumber());
    }
    
    public List<BankCustomer> getAllBankCustomers() {
        EntityManager em = emf.createEntityManager();
        List<BankCustomer> el = em.createQuery("SELECT bc FROM BankCustomer bc").getResultList();
        
        return el;
    }

}
