package com.daw.delta.DAO;

import com.daw.delta.DAO.exceptions.IllegalOrphanException;
import com.daw.delta.DAO.exceptions.NonexistentEntityException;
import com.daw.delta.DAO.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.daw.delta.DTO.Articulo;
import java.util.ArrayList;
import java.util.List;
import com.daw.delta.DTO.Opinion;
import com.daw.delta.DTO.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;


public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, Exception {
        if (usuario.getArticuloList() == null) {
            usuario.setArticuloList(new ArrayList<Articulo>());
        }
        if (usuario.getOpinionList() == null) {
            usuario.setOpinionList(new ArrayList<Opinion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Articulo> attachedArticuloList = new ArrayList<Articulo>();
            for (Articulo articuloListArticuloToAttach : usuario.getArticuloList()) {
                articuloListArticuloToAttach = em.getReference(articuloListArticuloToAttach.getClass(), articuloListArticuloToAttach.getCodArt());
                attachedArticuloList.add(articuloListArticuloToAttach);
            }
            usuario.setArticuloList(attachedArticuloList);
            List<Opinion> attachedOpinionList = new ArrayList<Opinion>();
            for (Opinion opinionListOpinionToAttach : usuario.getOpinionList()) {
                opinionListOpinionToAttach = em.getReference(opinionListOpinionToAttach.getClass(), opinionListOpinionToAttach.getCodOpinion());
                attachedOpinionList.add(opinionListOpinionToAttach);
            }
            usuario.setOpinionList(attachedOpinionList);
            em.persist(usuario);
            for (Articulo articuloListArticulo : usuario.getArticuloList()) {
                Usuario oldCodUsuarioOfArticuloListArticulo = articuloListArticulo.getCodUsuario();
                articuloListArticulo.setCodUsuario(usuario);
                articuloListArticulo = em.merge(articuloListArticulo);
                if (oldCodUsuarioOfArticuloListArticulo != null) {
                    oldCodUsuarioOfArticuloListArticulo.getArticuloList().remove(articuloListArticulo);
                    oldCodUsuarioOfArticuloListArticulo = em.merge(oldCodUsuarioOfArticuloListArticulo);
                }
            }
            for (Opinion opinionListOpinion : usuario.getOpinionList()) {
                Usuario oldEmailOfOpinionListOpinion = opinionListOpinion.getEmail();
                opinionListOpinion.setEmail(usuario);
                opinionListOpinion = em.merge(opinionListOpinion);
                if (oldEmailOfOpinionListOpinion != null) {
                    oldEmailOfOpinionListOpinion.getOpinionList().remove(opinionListOpinion);
                    oldEmailOfOpinionListOpinion = em.merge(oldEmailOfOpinionListOpinion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getEmail()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getEmail());
            List<Articulo> articuloListOld = persistentUsuario.getArticuloList();
            List<Articulo> articuloListNew = usuario.getArticuloList();
            List<Opinion> opinionListOld = persistentUsuario.getOpinionList();
            List<Opinion> opinionListNew = usuario.getOpinionList();
            List<String> illegalOrphanMessages = null;
            for (Articulo articuloListOldArticulo : articuloListOld) {
                if (!articuloListNew.contains(articuloListOldArticulo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Articulo " + articuloListOldArticulo + " since its codUsuario field is not nullable.");
                }
            }
            for (Opinion opinionListOldOpinion : opinionListOld) {
                if (!opinionListNew.contains(opinionListOldOpinion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Opinion " + opinionListOldOpinion + " since its email field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Articulo> attachedArticuloListNew = new ArrayList<Articulo>();
            for (Articulo articuloListNewArticuloToAttach : articuloListNew) {
                articuloListNewArticuloToAttach = em.getReference(articuloListNewArticuloToAttach.getClass(), articuloListNewArticuloToAttach.getCodArt());
                attachedArticuloListNew.add(articuloListNewArticuloToAttach);
            }
            articuloListNew = attachedArticuloListNew;
            usuario.setArticuloList(articuloListNew);
            List<Opinion> attachedOpinionListNew = new ArrayList<Opinion>();
            for (Opinion opinionListNewOpinionToAttach : opinionListNew) {
                opinionListNewOpinionToAttach = em.getReference(opinionListNewOpinionToAttach.getClass(), opinionListNewOpinionToAttach.getCodOpinion());
                attachedOpinionListNew.add(opinionListNewOpinionToAttach);
            }
            opinionListNew = attachedOpinionListNew;
            usuario.setOpinionList(opinionListNew);
            usuario = em.merge(usuario);
            for (Articulo articuloListNewArticulo : articuloListNew) {
                if (!articuloListOld.contains(articuloListNewArticulo)) {
                    Usuario oldCodUsuarioOfArticuloListNewArticulo = articuloListNewArticulo.getCodUsuario();
                    articuloListNewArticulo.setCodUsuario(usuario);
                    articuloListNewArticulo = em.merge(articuloListNewArticulo);
                    if (oldCodUsuarioOfArticuloListNewArticulo != null && !oldCodUsuarioOfArticuloListNewArticulo.equals(usuario)) {
                        oldCodUsuarioOfArticuloListNewArticulo.getArticuloList().remove(articuloListNewArticulo);
                        oldCodUsuarioOfArticuloListNewArticulo = em.merge(oldCodUsuarioOfArticuloListNewArticulo);
                    }
                }
            }
            for (Opinion opinionListNewOpinion : opinionListNew) {
                if (!opinionListOld.contains(opinionListNewOpinion)) {
                    Usuario oldEmailOfOpinionListNewOpinion = opinionListNewOpinion.getEmail();
                    opinionListNewOpinion.setEmail(usuario);
                    opinionListNewOpinion = em.merge(opinionListNewOpinion);
                    if (oldEmailOfOpinionListNewOpinion != null && !oldEmailOfOpinionListNewOpinion.equals(usuario)) {
                        oldEmailOfOpinionListNewOpinion.getOpinionList().remove(opinionListNewOpinion);
                        oldEmailOfOpinionListNewOpinion = em.merge(oldEmailOfOpinionListNewOpinion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = usuario.getEmail();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getEmail();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Articulo> articuloListOrphanCheck = usuario.getArticuloList();
            for (Articulo articuloListOrphanCheckArticulo : articuloListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Articulo " + articuloListOrphanCheckArticulo + " in its articuloList field has a non-nullable codUsuario field.");
            }
            List<Opinion> opinionListOrphanCheck = usuario.getOpinionList();
            for (Opinion opinionListOrphanCheckOpinion : opinionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Opinion " + opinionListOrphanCheckOpinion + " in its opinionList field has a non-nullable email field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List findById_(int c){
        EntityManager em = getEntityManager();
        TypedQuery query=em.createNamedQuery("Usuario.findByCodUsuario", Usuario.class);
        query.setParameter("codUsuario", c);
        return query.getResultList();
    }

}
