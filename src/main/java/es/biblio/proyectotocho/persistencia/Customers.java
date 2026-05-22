/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.biblio.proyectotocho.persistencia;

import java.util.Objects;

/**
 *
 * @author Alberto
 */
public class Customers implements Comparable {

    private Integer customerId;
    private String name, address, website;
    private double creditLimit;

    public Customers(Integer customerId, String name, String address, String website, double credit_limit) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.website = website;
        this.creditLimit = creditLimit;
    }

    public Integer getCustomer_id() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getWebsite() {
        return website;
    }

    public double getCredit_limit() {
        return creditLimit;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.customerId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customers other = (Customers) obj;
        return Objects.equals(this.customerId, other.customerId);
    }

    @Override
    public String toString() {
        return "Customers{" + "customer_id=" + customerId + ", name=" + name + ", address=" + address + ", website=" + website + ", credit_limit=" + creditLimit + '}';
    }

    @Override
    public int compareTo(Object o) {
        Customers other = (Customers) o;
        return this.customerId - other.customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }
    
    

}
