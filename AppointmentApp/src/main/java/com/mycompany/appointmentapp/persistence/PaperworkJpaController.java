/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appointmentapp.persistence;

import com.mycompany.appointmentapp.logic.Paperwork;
import com.mycompany.appointmentapp.persistence.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author jeiny
 */
public class PaperworkJpaController implements Serializable {

    public PaperworkJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public PaperworkJpaController() {
        emf = Persistence.createEntityManagerFactory("appointmentUnit");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Paperwork paperwork) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(paperwork);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Paperwork paperwork) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            paperwork = em.merge(paperwork);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = paperwork.getId();
                if (findPaperwork(id) == null) {
                    throw new NonexistentEntityException("The paperwork with id " + id + " no longer exists.");
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
            Paperwork paperwork;
            try {
                paperwork = em.getReference(Paperwork.class, id);
                paperwork.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paperwork with id " + id + " no longer exists.", enfe);
            }
            em.remove(paperwork);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Paperwork> findPaperworkEntities() {
        return findPaperworkEntities(true, -1, -1);
    }

    public List<Paperwork> findPaperworkEntities(int maxResults, int firstResult) {
        return findPaperworkEntities(false, maxResults, firstResult);
    }

    private List<Paperwork> findPaperworkEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Paperwork.class));
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

    public Paperwork findPaperwork(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Paperwork.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaperworkCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Paperwork> rt = cq.from(Paperwork.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
