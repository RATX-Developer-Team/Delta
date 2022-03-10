package com.daw.delta.DAO;

import com.daw.delta.DAO.exceptions.IllegalOrphanException;
import com.daw.delta.DAO.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.daw.delta.DTO.Categorias;
import com.daw.delta.DTO.Articulo;
import com.daw.delta.DTO.Subcategorias;
import java.util.ArrayList;
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
        if (subcategorias.getArticuloList() == null) {
            subcategorias.setArticuloList(new ArrayList<Articulo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categorias codCategoria = subcategorias.getCodCategoria();
            if (codCategoria != null) {
                codCategoria = em.getReference(codCategoria.getClass(), codCategoria.getCodCategoria());
                subcategorias.setCodCategoria(codCategoria);
            }
            List<Articulo> attachedArticuloList = new ArrayList<Articulo>();
            for (Articulo articuloListArticuloToAttach : subcategorias.getArticuloList()) {
                articuloListArticuloToAttach = em.getReference(articuloListArticuloToAttach.getClass(), articuloListArticuloToAttach.getCodArt());
                attachedArticuloList.add(articuloListArticuloToAttach);
            }
            subcategorias.setArticuloList(attachedArticuloList);
            em.persist(subcategorias);
            if (codCategoria != null) {
                codCategoria.getSubcategoriasList().add(subcategorias);
                codCategoria = em.merge(codCategoria);
            }
            for (Articulo articuloListArticulo : subcategorias.getArticuloList()) {
                Subcategorias oldCodSubcategoriaOfArticuloListArticulo = articuloListArticulo.getCodSubcategoria();
                articuloListArticulo.setCodSubcategoria(subcategorias);
                articuloListArticulo = em.merge(articuloListArticulo);
                if (oldCodSubcategoriaOfArticuloListArticulo != null) {
                    oldCodSubcategoriaOfArticuloListArticulo.getArticuloList().remove(articuloListArticulo);
                    oldCodSubcategoriaOfArticuloListArticulo = em.merge(oldCodSubcategoriaOfArticuloListArticulo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Subcategorias subcategorias) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Subcategorias persistentSubcategorias = em.find(Subcategorias.class, subcategorias.getCodSubcategoria());
            Categorias codCategoriaOld = persistentSubcategorias.getCodCategoria();
            Categorias codCategoriaNew = subcategorias.getCodCategoria();
            List<Articulo> articuloListOld = persistentSubcategorias.getArticuloList();
            List<Articulo> articuloListNew = subcategorias.getArticuloList();
            List<String> illegalOrphanMessages = null;
            for (Articulo articuloListOldArticulo : articuloListOld) {
                if (!articuloListNew.contains(articuloListOldArticulo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Articulo " + articuloListOldArticulo + " since its codSubcategoria field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codCategoriaNew != null) {
                codCategoriaNew = em.getReference(codCategoriaNew.getClass(), codCategoriaNew.getCodCategoria());
                subcategorias.setCodCategoria(codCategoriaNew);
            }
            List<Articulo> attachedArticuloListNew = new ArrayList<Articulo>();
            for (Articulo articuloListNewArticuloToAttach : articuloListNew) {
                articuloListNewArticuloToAttach = em.getReference(articuloListNewArticuloToAttach.getClass(), articuloListNewArticuloToAttach.getCodArt());
                attachedArticuloListNew.add(articuloListNewArticuloToAttach);
            }
            articuloListNew = attachedArticuloListNew;
            subcategorias.setArticuloList(articuloListNew);
            subcategorias = em.merge(subcategorias);
            if (codCategoriaOld != null && !codCategoriaOld.equals(codCategoriaNew)) {
                codCategoriaOld.getSubcategoriasList().remove(subcategorias);
                codCategoriaOld = em.merge(codCategoriaOld);
            }
            if (codCategoriaNew != null && !codCategoriaNew.equals(codCategoriaOld)) {
                codCategoriaNew.getSubcategoriasList().add(subcategorias);
                codCategoriaNew = em.merge(codCategoriaNew);
            }
            for (Articulo articuloListNewArticulo : articuloListNew) {
                if (!articuloListOld.contains(articuloListNewArticulo)) {
                    Subcategorias oldCodSubcategoriaOfArticuloListNewArticulo = articuloListNewArticulo.getCodSubcategoria();
                    articuloListNewArticulo.setCodSubcategoria(subcategorias);
                    articuloListNewArticulo = em.merge(articuloListNewArticulo);
                    if (oldCodSubcategoriaOfArticuloListNewArticulo != null && !oldCodSubcategoriaOfArticuloListNewArticulo.equals(subcategorias)) {
                        oldCodSubcategoriaOfArticuloListNewArticulo.getArticuloList().remove(articuloListNewArticulo);
                        oldCodSubcategoriaOfArticuloListNewArticulo = em.merge(oldCodSubcategoriaOfArticuloListNewArticulo);
                    }
                }
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            List<Articulo> articuloListOrphanCheck = subcategorias.getArticuloList();
            for (Articulo articuloListOrphanCheckArticulo : articuloListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Subcategorias (" + subcategorias + ") cannot be destroyed since the Articulo " + articuloListOrphanCheckArticulo + " in its articuloList field has a non-nullable codSubcategoria field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
