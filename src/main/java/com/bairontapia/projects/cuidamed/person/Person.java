package com.bairontapia.projects.cuidamed.person;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(schema = "residence", name = "person")
public class Person {
    @Id
    @Column(name = "rut", unique = true, updatable = true, nullable = false)
    private String rut;

    @Column(name = "firts_names", unique = false, updatable = true, nullable = false)
    private String firtsNames;

    @Column(name = "last_name", unique = false, updatable = true, nullable = false)
    private String lastName;

    @Column(name = "second_last_name", unique = false, updatable = true, nullable = false)
    private String secondLastName;

    @Column(name = "birth_date", unique = false, updatable = false, nullable = false)
    private Date birthDate;

    @Column(name = "sex", unique = false, updatable = true, nullable = false)
    private int sex;

    @Override
    public String toString() {
        return "Person{" +
                "rut='" + rut + '\'' +
                ", firtsNames='" + firtsNames + '\'' +
                ", lastName='" + lastName + '\'' +
                ", secondLastName='" + secondLastName + '\'' +
                ", birthDate=" + birthDate +
                ", sex=" + sex +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(rut);
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rutID) {
        this.rut = rutID;
    }

    public String getFirtsNames() {
        return firtsNames;
    }

    public void setFirtsNames(String firtsNames) {
        this.firtsNames = firtsNames;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
