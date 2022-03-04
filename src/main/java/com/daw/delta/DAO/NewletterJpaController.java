package com.daw.delta.DAO;

import com.daw.delta.DAO.exceptions.NonexistentEntityException;
import com.daw.delta.DTO.Newletter;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class NewletterJpaController implements Serializable {

    public NewletterJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Newletter newletter) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(newletter);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Newletter newletter) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            newletter = em.merge(newletter);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = newletter.getId();
                if (findNewletter(id) == null) {
                    throw new NonexistentEntityException("The newletter with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Newletter newletter;
            try {
                newletter = em.getReference(Newletter.class, id);
                newletter.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The newletter with id " + id + " no longer exists.", enfe);
            }
            em.remove(newletter);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Newletter> findNewletterEntities() {
        return findNewletterEntities(true, -1, -1);
    }

    public List<Newletter> findNewletterEntities(int maxResults, int firstResult) {
        return findNewletterEntities(false, maxResults, firstResult);
    }

    private List<Newletter> findNewletterEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Newletter.class));
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

    public Newletter findNewletter(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Newletter.class, id);
        } finally {
            em.close();
        }
    }

    public int getNewletterCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Newletter> rt = cq.from(Newletter.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
