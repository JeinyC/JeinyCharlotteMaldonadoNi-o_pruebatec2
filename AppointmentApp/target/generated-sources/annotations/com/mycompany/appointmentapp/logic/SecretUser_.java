package com.mycompany.appointmentapp.logic;

import com.mycompany.appointmentapp.logic.Appointment;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-22T20:26:43")
@StaticMetamodel(SecretUser.class)
public class SecretUser_ { 

    public static volatile SingularAttribute<SecretUser, Integer> secretNumber;
    public static volatile SingularAttribute<SecretUser, String> name;
    public static volatile ListAttribute<SecretUser, Appointment> userAppointments;
    public static volatile SingularAttribute<SecretUser, Integer> id;
    public static volatile SingularAttribute<SecretUser, Integer> age;
    public static volatile SingularAttribute<SecretUser, String> rol;

}