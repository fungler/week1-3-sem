/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.BankCustomer;

/**
 *
 * @author Martin
 */
public class CustomerDTO {
    Long customerID;
    String fullName; 
    String accountNumber;
    double balance;

    public CustomerDTO(BankCustomer c) {
        this.customerID = c.getId();
        this.fullName = c.getFirstname() + " " + c.getLastname();
        this.accountNumber = c.getAccoutNumber();
        this.balance = c.getBalance();
    }
    
}
