package com.daw.delta.DAO;

import com.daw.delta.DAO.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.daw.delta.DTO.Opinion;
import com.daw.delta.DTO.Respuestas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class RespuestasJpaController implements Serializable {

    public RespuestasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Respuestas respuestas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Opinion codOpinion = respuestas.getCodOpinion();
            if (codOpinion != null) {
                codOpinion = em.getReference(codOpinion.getClass(), codOpinion.getCodOpinion());
                respuestas.setCodOpinion(codOpinion);
            }
            Opinion codEngancha = respuestas.getCodEngancha();
            if (codEngancha != null) {
                codEngancha = em.getReference(codEngancha.getClass(), codEngancha.getCodOpinion());
                respuestas.setCodEngancha(codEngancha);
            }
            em.persist(respuestas);
            if (codOpinion != null) {
                codOpinion.getRespuestasList().add(respuestas);
                codOpinion = em.merge(codOpinion);
            }
            if (codEngancha != null) {
                codEngancha.getRespuestasList().add(respuestas);
                codEngancha = em.merge(codEngancha);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Respuestas respuestas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Respuestas persistentRespuestas = em.find(Respuestas.class, respuestas.getCodRespuesta());
            Opinion codOpinionOld = persistentRespuestas.getCodOpinion();
            Opinion codOpinionNew = respuestas.getCodOpinion();
            Opinion codEnganchaOld = persistentRespuestas.getCodEngancha();
            Opinion codEnganchaNew = respuestas.getCodEngancha();
            if (codOpinionNew != null) {
                codOpinionNew = em.getReference(codOpinionNew.getClass(), codOpinionNew.getCodOpinion());
                respuestas.setCodOpinion(codOpinionNew);
            }
            if (codEnganchaNew != null) {
                codEnganchaNew = em.getReference(codEnganchaNew.getClass(), codEnganchaNew.getCodOpinion());
                respuestas.setCodEngancha(codEnganchaNew);
            }
            respuestas = em.merge(respuestas);
            if (codOpinionOld != null && !codOpinionOld.equals(codOpinionNew)) {
                codOpinionOld.getRespuestasList().remove(respuestas);
                codOpinionOld = em.merge(codOpinionOld);
            }
            if (codOpinionNew != null && !codOpinionNew.equals(codOpinionOld)) {
                codOpinionNew.getRespuestasList().add(respuestas);
                codOpinionNew = em.merge(codOpinionNew);
            }
            if (codEnganchaOld != null && !codEnganchaOld.equals(codEnganchaNew)) {
                codEnganchaOld.getRespuestasList().remove(respuestas);
                codEnganchaOld = em.merge(codEnganchaOld);
            }
            if (codEnganchaNew != null && !codEnganchaNew.equals(codEnganchaOld)) {
                codEnganchaNew.getRespuestasList().add(respuestas);
                codEnganchaNew = em.merge(codEnganchaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = respuestas.getCodRespuesta();
                if (findRespuestas(id) == null) {
                    throw new NonexistentEntityException("The respuestas with id " + id + " no longer exists.");
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
            Respuestas respuestas;
            try {
                respuestas = em.getReference(Respuestas.class, id);
                respuestas.getCodRespuesta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The respuestas with id " + id + " no longer exists.", enfe);
            }
            Opinion codOpinion = respuestas.getCodOpinion();
            if (codOpinion != null) {
                codOpinion.getRespuestasList().remove(respuestas);
                codOpinion = em.merge(codOpinion);
            }
            Opinion codEngancha = respuestas.getCodEngancha();
            if (codEngancha != null) {
                codEngancha.getRespuestasList().remove(respuestas);
                codEngancha = em.merge(codEngancha);
            }
            em.remove(respuestas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Respuestas> findRespuestasEntities() {
        return findRespuestasEntities(true, -1, -1);
    }

    public List<Respuestas> findRespuestasEntities(int maxResults, int firstResult) {
        return findRespuestasEntities(false, maxResults, firstResult);
    }

    private List<Respuestas> findRespuestasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Respuestas.class));
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

    public Respuestas findRespuestas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Respuestas.class, id);
        } finally {
            em.close();
        }
    }

    public int getRespuestasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Respuestas> rt = cq.from(Respuestas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
