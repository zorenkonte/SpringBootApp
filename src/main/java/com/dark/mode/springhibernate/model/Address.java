package com.dark.mode.springhibernate.model;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.Entity;
import javax.persistence.Table;

@Lazy
@Entity
@Table(name = "address")
public class Address extends BaseEntity {

    private String street;
    private String city;
    private String state;
    private String postalCode;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append(super.toString())
                .append("street", this.getStreet())
                .append("city", this.getCity())
                .append("state", this.getState())
                .append("postalCode", this.getPostalCode())
                .toString();
    }
}
