package com.mycompany.appointmentapp.persistence;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.appointmentapp.logic.Appointment;
import com.mycompany.appointmentapp.logic.SecretUser;
import com.mycompany.appointmentapp.persistence.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jeiny
 */
public class SecretUserJpaController implements Serializable {

    public SecretUserJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public SecretUserJpaController() {
        emf = Persistence.createEntityManagerFactory("appointmentUnit");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SecretUser secretUser) {
        if (secretUser.getUserAppointments() == null) {
            secretUser.setUserAppointments(new ArrayList<Appointment>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Appointment> attachedUserAppointments = new ArrayList<Appointment>();
            for (Appointment userAppointmentsAppointmentToAttach : secretUser.getUserAppointments()) {
                userAppointmentsAppointmentToAttach = em.getReference(userAppointmentsAppointmentToAttach.getClass(), userAppointmentsAppointmentToAttach.getId());
                attachedUserAppointments.add(userAppointmentsAppointmentToAttach);
            }
            secretUser.setUserAppointments(attachedUserAppointments);
            em.persist(secretUser);
            for (Appointment userAppointmentsAppointment : secretUser.getUserAppointments()) {
                SecretUser oldUserOfUserAppointmentsAppointment = userAppointmentsAppointment.getUser();
                userAppointmentsAppointment.setUser(secretUser);
                userAppointmentsAppointment = em.merge(userAppointmentsAppointment);
                if (oldUserOfUserAppointmentsAppointment != null) {
                    oldUserOfUserAppointmentsAppointment.getUserAppointments().remove(userAppointmentsAppointment);
                    oldUserOfUserAppointmentsAppointment = em.merge(oldUserOfUserAppointmentsAppointment);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SecretUser secretUser) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SecretUser persistentSecretUser = em.find(SecretUser.class, secretUser.getId());
            List<Appointment> userAppointmentsOld = persistentSecretUser.getUserAppointments();
            List<Appointment> userAppointmentsNew = secretUser.getUserAppointments();
            List<Appointment> attachedUserAppointmentsNew = new ArrayList<Appointment>();
            for (Appointment userAppointmentsNewAppointmentToAttach : userAppointmentsNew) {
                userAppointmentsNewAppointmentToAttach = em.getReference(userAppointmentsNewAppointmentToAttach.getClass(), userAppointmentsNewAppointmentToAttach.getId());
                attachedUserAppointmentsNew.add(userAppointmentsNewAppointmentToAttach);
            }
            userAppointmentsNew = attachedUserAppointmentsNew;
            secretUser.setUserAppointments(userAppointmentsNew);
            secretUser = em.merge(secretUser);
            for (Appointment userAppointmentsOldAppointment : userAppointmentsOld) {
                if (!userAppointmentsNew.contains(userAppointmentsOldAppointment)) {
                    userAppointmentsOldAppointment.setUser(null);
                    userAppointmentsOldAppointment = em.merge(userAppointmentsOldAppointment);
                }
            }
            for (Appointment userAppointmentsNewAppointment : userAppointmentsNew) {
                if (!userAppointmentsOld.contains(userAppointmentsNewAppointment)) {
                    SecretUser oldUserOfUserAppointmentsNewAppointment = userAppointmentsNewAppointment.getUser();
                    userAppointmentsNewAppointment.setUser(secretUser);
                    userAppointmentsNewAppointment = em.merge(userAppointmentsNewAppointment);
                    if (oldUserOfUserAppointmentsNewAppointment != null && !oldUserOfUserAppointmentsNewAppointment.equals(secretUser)) {
                        oldUserOfUserAppointmentsNewAppointment.getUserAppointments().remove(userAppointmentsNewAppointment);
                        oldUserOfUserAppointmentsNewAppointment = em.merge(oldUserOfUserAppointmentsNewAppointment);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = secretUser.getId();
                if (findSecretUser(id) == null) {
                    throw new NonexistentEntityException("The secretUser with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SecretUser secretUser;
            try {
                secretUser = em.getReference(SecretUser.class, id);
                secretUser.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The secretUser with id " + id + " no longer exists.", enfe);
            }
            List<Appointment> userAppointments = secretUser.getUserAppointments();
            for (Appointment userAppointmentsAppointment : userAppointments) {
                userAppointmentsAppointment.setUser(null);
                userAppointmentsAppointment = em.merge(userAppointmentsAppointment);
            }
            em.remove(secretUser);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SecretUser> findSecretUserEntities() {
        return findSecretUserEntities(true, -1, -1);
    }

    public List<SecretUser> findSecretUserEntities(int maxResults, int firstResult) {
        return findSecretUserEntities(false, maxResults, firstResult);
    }

    private List<SecretUser> findSecretUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SecretUser.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public SecretUser findSecretUser(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SecretUser.class, id);
        } finally {
            em.close();
        }
    }

    public int getSecretUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SecretUser> rt = cq.from(SecretUser.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
