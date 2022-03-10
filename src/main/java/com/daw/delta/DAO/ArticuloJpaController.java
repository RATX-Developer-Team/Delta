package com.daw.delta.DAO;

import com.daw.delta.DAO.exceptions.IllegalOrphanException;
import com.daw.delta.DAO.exceptions.NonexistentEntityException;
import com.daw.delta.DTO.Articulo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.daw.delta.DTO.Categorias;
import com.daw.delta.DTO.Usuario;
import com.daw.delta.DTO.Subcategorias;
import com.daw.delta.DTO.Opinion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class ArticuloJpaController implements Serializable {

    public ArticuloJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Articulo articulo) {
        if (articulo.getOpinionList() == null) {
            articulo.setOpinionList(new ArrayList<Opinion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categorias codCategoria = articulo.getCodCategoria();
            if (codCategoria != null) {
                codCategoria = em.getReference(codCategoria.getClass(), codCategoria.getCodCategoria());
                articulo.setCodCategoria(codCategoria);
            }
            Usuario codUsuario = articulo.getCodUsuario();
            if (codUsuario != null) {
                codUsuario = em.getReference(codUsuario.getClass(), codUsuario.getEmail());
                articulo.setCodUsuario(codUsuario);
            }
            Subcategorias codSubcategoria = articulo.getCodSubcategoria();
            if (codSubcategoria != null) {
                codSubcategoria = em.getReference(codSubcategoria.getClass(), codSubcategoria.getCodSubcategoria());
                articulo.setCodSubcategoria(codSubcategoria);
            }
            List<Opinion> attachedOpinionList = new ArrayList<Opinion>();
            for (Opinion opinionListOpinionToAttach : articulo.getOpinionList()) {
                opinionListOpinionToAttach = em.getReference(opinionListOpinionToAttach.getClass(), opinionListOpinionToAttach.getCodOpinion());
                attachedOpinionList.add(opinionListOpinionToAttach);
            }
            articulo.setOpinionList(attachedOpinionList);
            em.persist(articulo);
            if (codCategoria != null) {
                codCategoria.getArticuloList().add(articulo);
                codCategoria = em.merge(codCategoria);
            }
            if (codUsuario != null) {
                codUsuario.getArticuloList().add(articulo);
                codUsuario = em.merge(codUsuario);
            }
            if (codSubcategoria != null) {
                codSubcategoria.getArticuloList().add(articulo);
                codSubcategoria = em.merge(codSubcategoria);
            }
            for (Opinion opinionListOpinion : articulo.getOpinionList()) {
                Articulo oldCodArtOfOpinionListOpinion = opinionListOpinion.getCodArt();
                opinionListOpinion.setCodArt(articulo);
                opinionListOpinion = em.merge(opinionListOpinion);
                if (oldCodArtOfOpinionListOpinion != null) {
                    oldCodArtOfOpinionListOpinion.getOpinionList().remove(opinionListOpinion);
                    oldCodArtOfOpinionListOpinion = em.merge(oldCodArtOfOpinionListOpinion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Articulo articulo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Articulo persistentArticulo = em.find(Articulo.class, articulo.getCodArt());
            Categorias codCategoriaOld = persistentArticulo.getCodCategoria();
            Categorias codCategoriaNew = articulo.getCodCategoria();
            Usuario codUsuarioOld = persistentArticulo.getCodUsuario();
            Usuario codUsuarioNew = articulo.getCodUsuario();
            Subcategorias codSubcategoriaOld = persistentArticulo.getCodSubcategoria();
            Subcategorias codSubcategoriaNew = articulo.getCodSubcategoria();
            List<Opinion> opinionListOld = persistentArticulo.getOpinionList();
            List<Opinion> opinionListNew = articulo.getOpinionList();
            List<String> illegalOrphanMessages = null;
            for (Opinion opinionListOldOpinion : opinionListOld) {
                if (!opinionListNew.contains(opinionListOldOpinion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Opinion " + opinionListOldOpinion + " since its codArt field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codCategoriaNew != null) {
                codCategoriaNew = em.getReference(codCategoriaNew.getClass(), codCategoriaNew.getCodCategoria());
                articulo.setCodCategoria(codCategoriaNew);
            }
            if (codUsuarioNew != null) {
                codUsuarioNew = em.getReference(codUsuarioNew.getClass(), codUsuarioNew.getEmail());
                articulo.setCodUsuario(codUsuarioNew);
            }
            if (codSubcategoriaNew != null) {
                codSubcategoriaNew = em.getReference(codSubcategoriaNew.getClass(), codSubcategoriaNew.getCodSubcategoria());
                articulo.setCodSubcategoria(codSubcategoriaNew);
            }
            List<Opinion> attachedOpinionListNew = new ArrayList<Opinion>();
            for (Opinion opinionListNewOpinionToAttach : opinionListNew) {
                opinionListNewOpinionToAttach = em.getReference(opinionListNewOpinionToAttach.getClass(), opinionListNewOpinionToAttach.getCodOpinion());
                attachedOpinionListNew.add(opinionListNewOpinionToAttach);
            }
            opinionListNew = attachedOpinionListNew;
            articulo.setOpinionList(opinionListNew);
            articulo = em.merge(articulo);
            if (codCategoriaOld != null && !codCategoriaOld.equals(codCategoriaNew)) {
                codCategoriaOld.getArticuloList().remove(articulo);
                codCategoriaOld = em.merge(codCategoriaOld);
            }
            if (codCategoriaNew != null && !codCategoriaNew.equals(codCategoriaOld)) {
                codCategoriaNew.getArticuloList().add(articulo);
                codCategoriaNew = em.merge(codCategoriaNew);
            }
            if (codUsuarioOld != null && !codUsuarioOld.equals(codUsuarioNew)) {
                codUsuarioOld.getArticuloList().remove(articulo);
                codUsuarioOld = em.merge(codUsuarioOld);
            }
            if (codUsuarioNew != null && !codUsuarioNew.equals(codUsuarioOld)) {
                codUsuarioNew.getArticuloList().add(articulo);
                codUsuarioNew = em.merge(codUsuarioNew);
            }
            if (codSubcategoriaOld != null && !codSubcategoriaOld.equals(codSubcategoriaNew)) {
                codSubcategoriaOld.getArticuloList().remove(articulo);
                codSubcategoriaOld = em.merge(codSubcategoriaOld);
            }
            if (codSubcategoriaNew != null && !codSubcategoriaNew.equals(codSubcategoriaOld)) {
                codSubcategoriaNew.getArticuloList().add(articulo);
                codSubcategoriaNew = em.merge(codSubcategoriaNew);
            }
            for (Opinion opinionListNewOpinion : opinionListNew) {
                if (!opinionListOld.contains(opinionListNewOpinion)) {
                    Articulo oldCodArtOfOpinionListNewOpinion = opinionListNewOpinion.getCodArt();
                    opinionListNewOpinion.setCodArt(articulo);
                    opinionListNewOpinion = em.merge(opinionListNewOpinion);
                    if (oldCodArtOfOpinionListNewOpinion != null && !oldCodArtOfOpinionListNewOpinion.equals(articulo)) {
                        oldCodArtOfOpinionListNewOpinion.getOpinionList().remove(opinionListNewOpinion);
                        oldCodArtOfOpinionListNewOpinion = em.merge(oldCodArtOfOpinionListNewOpinion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = articulo.getCodArt();
                if (findArticulo(id) == null) {
                    throw new NonexistentEntityException("The articulo with id " + id + " no longer exists.");
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
            Articulo articulo;
            try {
                articulo = em.getReference(Articulo.class, id);
                articulo.getCodArt();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The articulo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Opinion> opinionListOrphanCheck = articulo.getOpinionList();
            for (Opinion opinionListOrphanCheckOpinion : opinionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Articulo (" + articulo + ") cannot be destroyed since the Opinion " + opinionListOrphanCheckOpinion + " in its opinionList field has a non-nullable codArt field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Categorias codCategoria = articulo.getCodCategoria();
            if (codCategoria != null) {
                codCategoria.getArticuloList().remove(articulo);
                codCategoria = em.merge(codCategoria);
            }
            Usuario codUsuario = articulo.getCodUsuario();
            if (codUsuario != null) {
                codUsuario.getArticuloList().remove(articulo);
                codUsuario = em.merge(codUsuario);
            }
            Subcategorias codSubcategoria = articulo.getCodSubcategoria();
            if (codSubcategoria != null) {
                codSubcategoria.getArticuloList().remove(articulo);
                codSubcategoria = em.merge(codSubcategoria);
            }
            em.remove(articulo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Articulo> findArticuloEntities() {
        return findArticuloEntities(true, -1, -1);
    }

    public List<Articulo> findArticuloEntities(int maxResults, int firstResult) {
        return findArticuloEntities(false, maxResults, firstResult);
    }

    private List<Articulo> findArticuloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Articulo.class));
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

    public Articulo findArticulo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Articulo.class, id);
        } finally {
            em.close();
        }
    }

    public int getArticuloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Articulo> rt = cq.from(Articulo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
