package com.mycompany.appointmentapp.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author jeiny
 */
@Entity
public class SecretUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int secretNumber;
    private String name;
    private int age;
    private String rol;

    @OneToMany(mappedBy = "user")
    private List<Appointment> userAppointments;

    public SecretUser(int secretNumber, String name, int age, String rol, List<Appointment> userAppointments) {
        this.secretNumber = secretNumber;
        this.name = name;
        this.age = age;
        this.rol = rol;
        this.userAppointments = new ArrayList<>();
    }

    public SecretUser() {

    }

    SecretUser(int secretNumber, String rol) {
        this.secretNumber = secretNumber;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSecretNumber() {
        return secretNumber;
    }

    public void setSecretNumber(int secretNumber) {
        this.secretNumber = secretNumber;
    }

    public List<Appointment> getUserAppointments() {
        return userAppointments;
    }

    public void setUserAppointments(List<Appointment> userAppointments) {
        this.userAppointments = userAppointments;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "SecretUser{" + "id=" + id + ", secretNumber=" + secretNumber + ", name=" + name + ", age=" + age + ", rol=" + rol + ", userAppointments=" + userAppointments + '}';
    }
}
