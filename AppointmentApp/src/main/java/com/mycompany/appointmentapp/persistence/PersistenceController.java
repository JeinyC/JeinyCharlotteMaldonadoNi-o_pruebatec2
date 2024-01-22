package com.mycompany.appointmentapp.persistence;

import com.mycompany.appointmentapp.logic.Appointment;
import com.mycompany.appointmentapp.logic.Paperwork;
import com.mycompany.appointmentapp.logic.SecretUser;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jeiny/*
 */
public class PersistenceController {

    AppointmentJpaController appointmentJpa = new AppointmentJpaController();
    SecretUserJpaController secretUserJpa = new SecretUserJpaController();
    PaperworkJpaController paperworkJpa = new PaperworkJpaController();

    public void createAppointment(Appointment appointment) {
        appointmentJpa.create(appointment);
    }

    public List<Appointment> getAppointmentList() {
        return appointmentJpa.findAppointmentEntities();
    }

    public Appointment getAppointmentById(int id) {
        return appointmentJpa.findAppointment(id);
    }
    
    public void setAppointment(Appointment appointment) {
        try {
            appointmentJpa.edit(appointment);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createUser(SecretUser secretUser) {
        secretUserJpa.create(secretUser);
    }

    public List<SecretUser> getSecretUserList() {
        return secretUserJpa.findSecretUserEntities();
    }

    public void createPaperwork(Paperwork paperwork) {
        paperworkJpa.create(paperwork);
    }

    public Paperwork getPaperworkById(int id) {
        return paperworkJpa.findPaperwork(id);
    } 
}
