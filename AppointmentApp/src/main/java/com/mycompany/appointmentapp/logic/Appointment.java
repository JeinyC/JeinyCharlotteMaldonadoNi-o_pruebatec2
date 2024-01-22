package com.mycompany.appointmentapp.logic;

import com.mycompany.appointmentapp.utils.DateUtil;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jeinyChar
 */
@Entity
public class Appointment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean status;

    @Temporal(TemporalType.DATE)
    private Calendar appointDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private SecretUser user;

    @ManyToOne(targetEntity = Paperwork.class)
    private Paperwork paperwork;

    public Appointment(boolean status, Calendar appointDate, SecretUser user, Paperwork paperwork) {
        this.status = status;
        this.appointDate = Calendar.getInstance();
        this.user = user;
        this.paperwork = paperwork;
    }

    public Appointment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String isStatus() {
        return status ? "Attended" : "Waiting";
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public SecretUser getUser() {
        return user;
    }

    public void setUser(SecretUser user) {
        this.user = user;
    }

    public Paperwork getPaperwork() {
        return paperwork;
    }

    public void setPaperwork(Paperwork paperwork) {
        this.paperwork = paperwork;
    }

    public String getDate() {
        return DateUtil.getFormattedDateFromCalendar(this.appointDate);
    }

    public void setDate(Calendar date) {
        this.appointDate = date;
    }

    @Override
    public String toString() {
        return "Appointment{" + "id=" + id + ", status=" + status + ", appointDate=" + appointDate + ", user=" + user + ", paperwork=" + paperwork + '}';
    }
}
