package com.store.store.model;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer number;
    private String street;
    private String postcode;

    public Address() {}

    public Address(Integer number, String street, String postcode) {
        this.number = number;
        this.street = street;
        this.postcode = postcode;
    }


    public Integer getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void move(Address newLocation){
        this.number = newLocation.getNumber();
        this.street = newLocation.getStreet();
        this.postcode = newLocation.getPostcode();
    }
}
