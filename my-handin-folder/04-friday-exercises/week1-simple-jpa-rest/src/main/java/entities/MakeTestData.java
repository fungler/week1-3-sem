/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Martin
 */
public class MakeTestData {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
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

        
    }
}
