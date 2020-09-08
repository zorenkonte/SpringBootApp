package com.dark.mode.springhibernate.model;

import org.springframework.core.style.ToStringCreator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student extends Person {

    @Column(name = "email")
    private String email;

    public Student() {
    }

    public Student(String firstName, String lastName, String email) {
        super(firstName, lastName);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("email", this.getEmail())
                .append(super.toString())
                .toString();
    }
}
