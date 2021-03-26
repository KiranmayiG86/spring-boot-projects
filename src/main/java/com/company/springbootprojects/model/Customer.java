package com.company.springbootprojects.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;

    protected Customer() //default constructor exists only for the sake of JPA
    {

    }

    public Customer(String firstName, String lastName) //constructor is the one you use to
    // create instances of Customer to be saved to the database
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() //The toString() method returns the string representation of the object
    {
        return String.format("Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
