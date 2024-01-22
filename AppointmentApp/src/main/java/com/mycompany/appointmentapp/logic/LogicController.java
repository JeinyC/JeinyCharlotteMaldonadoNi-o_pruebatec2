package com.mycompany.appointmentapp.logic;

import com.mycompany.appointmentapp.persistence.PersistenceController;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author jeiny
 */
public class LogicController {

    private final PersistenceController persistenceController = new PersistenceController();

    public void addAppointment(int idPaperwork, int userSecretNumber) {

        SecretUser user = getUser(userSecretNumber);
        Paperwork paperwork = getPaperworkById(idPaperwork);

        Appointment appointment = new Appointment(false, null, user, paperwork);
        persistenceController.createAppointment(appointment);
        user.getUserAppointments().add(appointment);
    }

    public List<Appointment> getAppointmentList() {
        return persistenceController.getAppointmentList();
    }

    public Appointment getAppointmentById(int id) {
        return persistenceController.getAppointmentById(id);
    }

    public List<Appointment> getAppointmentFilterUserView(String dateFilterNewFormat, String status) {
        return persistenceController.getAppointmentList().stream()
                .filter(appointment -> appointment.getDate().equalsIgnoreCase(dateFilterNewFormat))
                .filter(appointment -> appointment.isStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

    public List<Appointment> getAppointmentFilterAdminView(String dateFilterNewFormat, String status) {
        return persistenceController.getAppointmentList().stream()
                .filter(appointment -> appointment.getDate().equalsIgnoreCase(dateFilterNewFormat))
                .filter(appointment -> appointment.isStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

    public List<Appointment> getAppointmentFilterAdminViewAge(int age) {
        return persistenceController.getAppointmentList().stream()
                .filter(Appointment -> Appointment.getUser().getAge() < age)
                .collect(Collectors.toList());
    }

    public void setAppointmentState(Appointment appointment) {

        if (appointment.isStatus().equalsIgnoreCase("Waiting")) {
            appointment.setStatus(true);
        } else {
            appointment.setStatus(false);
        }

        persistenceController.setAppointment(appointment);
    }

    public void addAdmin(int secretNumber) {
        persistenceController.createUser(new SecretUser(secretNumber, "ADMIN"));
    }

    public void addUser(int secretNumber, String name, int age) {
        persistenceController.createUser(new SecretUser(secretNumber, name, age, "USER", null));
    }

    public SecretUser getUser(int secretNumber) {
        return persistenceController.getSecretUserList().stream()
                .filter(secretUser -> secretUser.getSecretNumber() == secretNumber)
                .findFirst()
                .orElse(null);
    }

    public SecretUser getLoggedUser(int secretNumber) {
        return getUser(secretNumber) != null ? getUser(secretNumber) : null;
    }

    public List<SecretUser> getUserList() {
        return persistenceController.getSecretUserList();
    }

    public void initPaperwork(String name) {
        persistenceController.createPaperwork(new Paperwork(name));
    }

    public Paperwork getPaperworkById(int id) {
        return persistenceController.getPaperworkById(id);
    }

    public boolean paperworkExists(int paperworkId) {
        return Optional.ofNullable(persistenceController.getPaperworkById(paperworkId))
                .isPresent();
    }
}
