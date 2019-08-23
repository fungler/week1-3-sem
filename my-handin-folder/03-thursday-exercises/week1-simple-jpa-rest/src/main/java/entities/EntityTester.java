/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import facades.EmployeeFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Martin
 */
public class EntityTester {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EmployeeFacade ef = EmployeeFacade.getFacadeExample(emf);
        
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            em.persist(ef.CreateEmployee("Johnna boi", "qwerty vej 10", 0.f));
            em.persist(ef.CreateEmployee("Mark Doi", "boi vej 10", 200.f));
            em.getTransaction().commit();
        } finally
        {
            em.close();
        }
        
        List<Employee> el = ef.getAllEmployees();
        
        System.out.println("-- All employees --");
        for (Employee e : el) {
            System.out.println(e.getName() + " ID: " + e.getId() + " Address: " + e.getAddress() + " Salary: " + e.getSalary());
        }
        
        System.out.println("-- Highest salary goes to: --");
        System.out.println(ef.getHighestSalary().getName());
        
        System.out.println("-- First employee --");
        System.out.println(ef.getEmployeeById(1L).getName());
        
        System.out.println("-- by name --");
        System.out.println(ef.getEmployeesByName("Mark Doi"));
    }
}
