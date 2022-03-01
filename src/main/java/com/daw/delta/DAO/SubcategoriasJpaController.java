package com.daw.delta.DAO;

import com.daw.delta.DAO.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.daw.delta.DTO.Categorias;
import com.daw.delta.DTO.Subcategorias;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class SubcategoriasJpaController implements Serializable {

    public SubcategoriasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Subcategorias subcategorias) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categorias codCategoria = subcategorias.getCodCategoria();
            if (codCategoria != null) {
                codCategoria = em.getReference(codCategoria.getClass(), codCategoria.getCodCategoria());
                subcategorias.setCodCategoria(codCategoria);
            }
            em.persist(subcategorias);
            if (codCategoria != null) {
                codCategoria.getSubcategoriasList().add(subcategorias);
                codCategoria = em.merge(codCategoria);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Subcategorias subcategorias) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Subcategorias persistentSubcategorias = em.find(Subcategorias.class, subcategorias.getCodSubcategoria());
            Categorias codCategoriaOld = persistentSubcategorias.getCodCategoria();
            Categorias codCategoriaNew = subcategorias.getCodCategoria();
            if (codCategoriaNew != null) {
                codCategoriaNew = em.getReference(codCategoriaNew.getClass(), codCategoriaNew.getCodCategoria());
                subcategorias.setCodCategoria(codCategoriaNew);
            }
            subcategorias = em.merge(subcategorias);
            if (codCategoriaOld != null && !codCategoriaOld.equals(codCategoriaNew)) {
                codCategoriaOld.getSubcategoriasList().remove(subcategorias);
                codCategoriaOld = em.merge(codCategoriaOld);
            }
            if (codCategoriaNew != null && !codCategoriaNew.equals(codCategoriaOld)) {
                codCategoriaNew.getSubcategoriasList().add(subcategorias);
                codCategoriaNew = em.merge(codCategoriaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = subcategorias.getCodSubcategoria();
                if (findSubcategorias(id) == null) {
                    throw new NonexistentEntityException("The subcategorias with id " + id + " no longer exists.");
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
            Subcategorias subcategorias;
            try {
                subcategorias = em.getReference(Subcategorias.class, id);
                subcategorias.getCodSubcategoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The subcategorias with id " + id + " no longer exists.", enfe);
            }
            Categorias codCategoria = subcategorias.getCodCategoria();
            if (codCategoria != null) {
                codCategoria.getSubcategoriasList().remove(subcategorias);
                codCategoria = em.merge(codCategoria);
            }
            em.remove(subcategorias);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Subcategorias> findSubcategoriasEntities() {
        return findSubcategoriasEntities(true, -1, -1);
    }

    public List<Subcategorias> findSubcategoriasEntities(int maxResults, int firstResult) {
        return findSubcategoriasEntities(false, maxResults, firstResult);
    }

    private List<Subcategorias> findSubcategoriasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Subcategorias.class));
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

    public Subcategorias findSubcategorias(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Subcategorias.class, id);
        } finally {
            em.close();
        }
    }

    public int getSubcategoriasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Subcategorias> rt = cq.from(Subcategorias.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
