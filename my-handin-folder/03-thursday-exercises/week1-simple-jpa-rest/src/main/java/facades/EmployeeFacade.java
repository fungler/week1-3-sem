package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    public EmployeeFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }
    
    public Employee getEmployeeById(Long id)
    {
       EntityManager em = emf.createEntityManager();
       Employee e = em.find(Employee.class, id);

       return e;
    }
    
    public List<Employee> getEmployeesByName(String name)
    {
        EntityManager em = emf.createEntityManager();
        List<Employee> el = em.createQuery("SELECT e FROM Employee e WHERE e.name = '" + name + "'").getResultList();
        
        return el;
    }
    
    public List<Employee> getAllEmployees()
    {
        EntityManager em = emf.createEntityManager();
        List<Employee> el = em.createQuery("SELECT e FROM Employee e").getResultList();
        
        return el;
    }
    
    public Employee getHighestSalary()
    {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery ("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(x.salary) FROM Employee x)");
        Employee result = (Employee)q.getSingleResult();
        return result;
    }
    
    public Employee CreateEmployee(String name, String address, float salary)
    {
        Employee newEmp = new Employee(name, address, salary);
        
        return newEmp;
    }
    
}
