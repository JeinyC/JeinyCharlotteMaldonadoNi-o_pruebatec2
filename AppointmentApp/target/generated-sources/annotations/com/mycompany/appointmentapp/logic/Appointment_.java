package com.mycompany.appointmentapp.logic;

import com.mycompany.appointmentapp.logic.Paperwork;
import com.mycompany.appointmentapp.logic.SecretUser;
import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-22T20:26:43")
@StaticMetamodel(Appointment.class)
public class Appointment_ { 

    public static volatile SingularAttribute<Appointment, Paperwork> paperwork;
    public static volatile SingularAttribute<Appointment, Integer> id;
    public static volatile SingularAttribute<Appointment, Calendar> appointDate;
    public static volatile SingularAttribute<Appointment, SecretUser> user;
    public static volatile SingularAttribute<Appointment, Boolean> status;

}