package com.daw.delta.DAO;

import com.daw.delta.DAO.exceptions.IllegalOrphanException;
import com.daw.delta.DAO.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.daw.delta.DTO.Articulo;
import com.daw.delta.DTO.Opinion;
import com.daw.delta.DTO.Usuario;
import com.daw.delta.DTO.Respuestas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class OpinionJpaController implements Serializable {

    public OpinionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Opinion opinion) throws IllegalOrphanException {
        if (opinion.getRespuestasList() == null) {
            opinion.setRespuestasList(new ArrayList<Respuestas>());
        }
        if (opinion.getRespuestasList1() == null) {
            opinion.setRespuestasList1(new ArrayList<Respuestas>());
        }
        List<String> illegalOrphanMessages = null;
        Articulo codArtOrphanCheck = opinion.getCodArt();
        if (codArtOrphanCheck != null) {
            Opinion oldOpinionOfCodArt = codArtOrphanCheck.getOpinion();
            if (oldOpinionOfCodArt != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Articulo " + codArtOrphanCheck + " already has an item of type Opinion whose codArt column cannot be null. Please make another selection for the codArt field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Articulo articulo = opinion.getArticulo();
            if (articulo != null) {
                articulo = em.getReference(articulo.getClass(), articulo.getCodArt());
                opinion.setArticulo(articulo);
            }
            Usuario email = opinion.getEmail();
            if (email != null) {
                email = em.getReference(email.getClass(), email.getEmail());
                opinion.setEmail(email);
            }
            Articulo codArt = opinion.getCodArt();
            if (codArt != null) {
                codArt = em.getReference(codArt.getClass(), codArt.getCodArt());
                opinion.setCodArt(codArt);
            }
            List<Respuestas> attachedRespuestasList = new ArrayList<Respuestas>();
            for (Respuestas respuestasListRespuestasToAttach : opinion.getRespuestasList()) {
                respuestasListRespuestasToAttach = em.getReference(respuestasListRespuestasToAttach.getClass(), respuestasListRespuestasToAttach.getCodRespuesta());
                attachedRespuestasList.add(respuestasListRespuestasToAttach);
            }
            opinion.setRespuestasList(attachedRespuestasList);
            List<Respuestas> attachedRespuestasList1 = new ArrayList<Respuestas>();
            for (Respuestas respuestasList1RespuestasToAttach : opinion.getRespuestasList1()) {
                respuestasList1RespuestasToAttach = em.getReference(respuestasList1RespuestasToAttach.getClass(), respuestasList1RespuestasToAttach.getCodRespuesta());
                attachedRespuestasList1.add(respuestasList1RespuestasToAttach);
            }
            opinion.setRespuestasList1(attachedRespuestasList1);
            em.persist(opinion);
            if (articulo != null) {
                Opinion oldOpinionOfArticulo = articulo.getOpinion();
                if (oldOpinionOfArticulo != null) {
                    oldOpinionOfArticulo.setArticulo(null);
                    oldOpinionOfArticulo = em.merge(oldOpinionOfArticulo);
                }
                articulo.setOpinion(opinion);
                articulo = em.merge(articulo);
            }
            if (email != null) {
                email.getOpinionList().add(opinion);
                email = em.merge(email);
            }
            if (codArt != null) {
                codArt.setOpinion(opinion);
                codArt = em.merge(codArt);
            }
            for (Respuestas respuestasListRespuestas : opinion.getRespuestasList()) {
                Opinion oldCodOpinionOfRespuestasListRespuestas = respuestasListRespuestas.getCodOpinion();
                respuestasListRespuestas.setCodOpinion(opinion);
                respuestasListRespuestas = em.merge(respuestasListRespuestas);
                if (oldCodOpinionOfRespuestasListRespuestas != null) {
                    oldCodOpinionOfRespuestasListRespuestas.getRespuestasList().remove(respuestasListRespuestas);
                    oldCodOpinionOfRespuestasListRespuestas = em.merge(oldCodOpinionOfRespuestasListRespuestas);
                }
            }
            for (Respuestas respuestasList1Respuestas : opinion.getRespuestasList1()) {
                Opinion oldCodEnganchaOfRespuestasList1Respuestas = respuestasList1Respuestas.getCodEngancha();
                respuestasList1Respuestas.setCodEngancha(opinion);
                respuestasList1Respuestas = em.merge(respuestasList1Respuestas);
                if (oldCodEnganchaOfRespuestasList1Respuestas != null) {
                    oldCodEnganchaOfRespuestasList1Respuestas.getRespuestasList1().remove(respuestasList1Respuestas);
                    oldCodEnganchaOfRespuestasList1Respuestas = em.merge(oldCodEnganchaOfRespuestasList1Respuestas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Opinion opinion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Opinion persistentOpinion = em.find(Opinion.class, opinion.getCodOpinion());
            Articulo articuloOld = persistentOpinion.getArticulo();
            Articulo articuloNew = opinion.getArticulo();
            Usuario emailOld = persistentOpinion.getEmail();
            Usuario emailNew = opinion.getEmail();
            Articulo codArtOld = persistentOpinion.getCodArt();
            Articulo codArtNew = opinion.getCodArt();
            List<Respuestas> respuestasListOld = persistentOpinion.getRespuestasList();
            List<Respuestas> respuestasListNew = opinion.getRespuestasList();
            List<Respuestas> respuestasList1Old = persistentOpinion.getRespuestasList1();
            List<Respuestas> respuestasList1New = opinion.getRespuestasList1();
            List<String> illegalOrphanMessages = null;
            if (articuloOld != null && !articuloOld.equals(articuloNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Articulo " + articuloOld + " since its opinion field is not nullable.");
            }
            if (codArtOld != null && !codArtOld.equals(codArtNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Articulo " + codArtOld + " since its opinion field is not nullable.");
            }
            if (codArtNew != null && !codArtNew.equals(codArtOld)) {
                Opinion oldOpinionOfCodArt = codArtNew.getOpinion();
                if (oldOpinionOfCodArt != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Articulo " + codArtNew + " already has an item of type Opinion whose codArt column cannot be null. Please make another selection for the codArt field.");
                }
            }
            for (Respuestas respuestasListOldRespuestas : respuestasListOld) {
                if (!respuestasListNew.contains(respuestasListOldRespuestas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Respuestas " + respuestasListOldRespuestas + " since its codOpinion field is not nullable.");
                }
            }
            for (Respuestas respuestasList1OldRespuestas : respuestasList1Old) {
                if (!respuestasList1New.contains(respuestasList1OldRespuestas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Respuestas " + respuestasList1OldRespuestas + " since its codEngancha field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (articuloNew != null) {
                articuloNew = em.getReference(articuloNew.getClass(), articuloNew.getCodArt());
                opinion.setArticulo(articuloNew);
            }
            if (emailNew != null) {
                emailNew = em.getReference(emailNew.getClass(), emailNew.getEmail());
                opinion.setEmail(emailNew);
            }
            if (codArtNew != null) {
                codArtNew = em.getReference(codArtNew.getClass(), codArtNew.getCodArt());
                opinion.setCodArt(codArtNew);
            }
            List<Respuestas> attachedRespuestasListNew = new ArrayList<Respuestas>();
            for (Respuestas respuestasListNewRespuestasToAttach : respuestasListNew) {
                respuestasListNewRespuestasToAttach = em.getReference(respuestasListNewRespuestasToAttach.getClass(), respuestasListNewRespuestasToAttach.getCodRespuesta());
                attachedRespuestasListNew.add(respuestasListNewRespuestasToAttach);
            }
            respuestasListNew = attachedRespuestasListNew;
            opinion.setRespuestasList(respuestasListNew);
            List<Respuestas> attachedRespuestasList1New = new ArrayList<Respuestas>();
            for (Respuestas respuestasList1NewRespuestasToAttach : respuestasList1New) {
                respuestasList1NewRespuestasToAttach = em.getReference(respuestasList1NewRespuestasToAttach.getClass(), respuestasList1NewRespuestasToAttach.getCodRespuesta());
                attachedRespuestasList1New.add(respuestasList1NewRespuestasToAttach);
            }
            respuestasList1New = attachedRespuestasList1New;
            opinion.setRespuestasList1(respuestasList1New);
            opinion = em.merge(opinion);
            if (articuloNew != null && !articuloNew.equals(articuloOld)) {
                Opinion oldOpinionOfArticulo = articuloNew.getOpinion();
                if (oldOpinionOfArticulo != null) {
                    oldOpinionOfArticulo.setArticulo(null);
                    oldOpinionOfArticulo = em.merge(oldOpinionOfArticulo);
                }
                articuloNew.setOpinion(opinion);
                articuloNew = em.merge(articuloNew);
            }
            if (emailOld != null && !emailOld.equals(emailNew)) {
                emailOld.getOpinionList().remove(opinion);
                emailOld = em.merge(emailOld);
            }
            if (emailNew != null && !emailNew.equals(emailOld)) {
                emailNew.getOpinionList().add(opinion);
                emailNew = em.merge(emailNew);
            }
            if (codArtNew != null && !codArtNew.equals(codArtOld)) {
                codArtNew.setOpinion(opinion);
                codArtNew = em.merge(codArtNew);
            }
            for (Respuestas respuestasListNewRespuestas : respuestasListNew) {
                if (!respuestasListOld.contains(respuestasListNewRespuestas)) {
                    Opinion oldCodOpinionOfRespuestasListNewRespuestas = respuestasListNewRespuestas.getCodOpinion();
                    respuestasListNewRespuestas.setCodOpinion(opinion);
                    respuestasListNewRespuestas = em.merge(respuestasListNewRespuestas);
                    if (oldCodOpinionOfRespuestasListNewRespuestas != null && !oldCodOpinionOfRespuestasListNewRespuestas.equals(opinion)) {
                        oldCodOpinionOfRespuestasListNewRespuestas.getRespuestasList().remove(respuestasListNewRespuestas);
                        oldCodOpinionOfRespuestasListNewRespuestas = em.merge(oldCodOpinionOfRespuestasListNewRespuestas);
                    }
                }
            }
            for (Respuestas respuestasList1NewRespuestas : respuestasList1New) {
                if (!respuestasList1Old.contains(respuestasList1NewRespuestas)) {
                    Opinion oldCodEnganchaOfRespuestasList1NewRespuestas = respuestasList1NewRespuestas.getCodEngancha();
                    respuestasList1NewRespuestas.setCodEngancha(opinion);
                    respuestasList1NewRespuestas = em.merge(respuestasList1NewRespuestas);
                    if (oldCodEnganchaOfRespuestasList1NewRespuestas != null && !oldCodEnganchaOfRespuestasList1NewRespuestas.equals(opinion)) {
                        oldCodEnganchaOfRespuestasList1NewRespuestas.getRespuestasList1().remove(respuestasList1NewRespuestas);
                        oldCodEnganchaOfRespuestasList1NewRespuestas = em.merge(oldCodEnganchaOfRespuestasList1NewRespuestas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = opinion.getCodOpinion();
                if (findOpinion(id) == null) {
                    throw new NonexistentEntityException("The opinion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Opinion opinion;
            try {
                opinion = em.getReference(Opinion.class, id);
                opinion.getCodOpinion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opinion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Articulo articuloOrphanCheck = opinion.getArticulo();
            if (articuloOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Opinion (" + opinion + ") cannot be destroyed since the Articulo " + articuloOrphanCheck + " in its articulo field has a non-nullable opinion field.");
            }
            Articulo codArtOrphanCheck = opinion.getCodArt();
            if (codArtOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Opinion (" + opinion + ") cannot be destroyed since the Articulo " + codArtOrphanCheck + " in its codArt field has a non-nullable opinion field.");
            }
            List<Respuestas> respuestasListOrphanCheck = opinion.getRespuestasList();
            for (Respuestas respuestasListOrphanCheckRespuestas : respuestasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Opinion (" + opinion + ") cannot be destroyed since the Respuestas " + respuestasListOrphanCheckRespuestas + " in its respuestasList field has a non-nullable codOpinion field.");
            }
            List<Respuestas> respuestasList1OrphanCheck = opinion.getRespuestasList1();
            for (Respuestas respuestasList1OrphanCheckRespuestas : respuestasList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Opinion (" + opinion + ") cannot be destroyed since the Respuestas " + respuestasList1OrphanCheckRespuestas + " in its respuestasList1 field has a non-nullable codEngancha field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario email = opinion.getEmail();
            if (email != null) {
                email.getOpinionList().remove(opinion);
                email = em.merge(email);
            }
            em.remove(opinion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Opinion> findOpinionEntities() {
        return findOpinionEntities(true, -1, -1);
    }

    public List<Opinion> findOpinionEntities(int maxResults, int firstResult) {
        return findOpinionEntities(false, maxResults, firstResult);
    }

    private List<Opinion> findOpinionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Opinion.class));
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

    public Opinion findOpinion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Opinion.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpinionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Opinion> rt = cq.from(Opinion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
