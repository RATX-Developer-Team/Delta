package com.daw.delta.DAO;

import com.daw.delta.DAO.exceptions.IllegalOrphanException;
import com.daw.delta.DAO.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.daw.delta.DTO.Subcategorias;
import java.util.ArrayList;
import java.util.List;
import com.daw.delta.DTO.Articulo;
import com.daw.delta.DTO.Categorias;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class CategoriasJpaController implements Serializable {

    public CategoriasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Categorias categorias) {
        if (categorias.getSubcategoriasList() == null) {
            categorias.setSubcategoriasList(new ArrayList<Subcategorias>());
        }
        if (categorias.getArticuloList() == null) {
            categorias.setArticuloList(new ArrayList<Articulo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Subcategorias> attachedSubcategoriasList = new ArrayList<Subcategorias>();
            for (Subcategorias subcategoriasListSubcategoriasToAttach : categorias.getSubcategoriasList()) {
                subcategoriasListSubcategoriasToAttach = em.getReference(subcategoriasListSubcategoriasToAttach.getClass(), subcategoriasListSubcategoriasToAttach.getCodSubcategoria());
                attachedSubcategoriasList.add(subcategoriasListSubcategoriasToAttach);
            }
            categorias.setSubcategoriasList(attachedSubcategoriasList);
            List<Articulo> attachedArticuloList = new ArrayList<Articulo>();
            for (Articulo articuloListArticuloToAttach : categorias.getArticuloList()) {
                articuloListArticuloToAttach = em.getReference(articuloListArticuloToAttach.getClass(), articuloListArticuloToAttach.getCodArt());
                attachedArticuloList.add(articuloListArticuloToAttach);
            }
            categorias.setArticuloList(attachedArticuloList);
            em.persist(categorias);
            for (Subcategorias subcategoriasListSubcategorias : categorias.getSubcategoriasList()) {
                Categorias oldCodCategoriaOfSubcategoriasListSubcategorias = subcategoriasListSubcategorias.getCodCategoria();
                subcategoriasListSubcategorias.setCodCategoria(categorias);
                subcategoriasListSubcategorias = em.merge(subcategoriasListSubcategorias);
                if (oldCodCategoriaOfSubcategoriasListSubcategorias != null) {
                    oldCodCategoriaOfSubcategoriasListSubcategorias.getSubcategoriasList().remove(subcategoriasListSubcategorias);
                    oldCodCategoriaOfSubcategoriasListSubcategorias = em.merge(oldCodCategoriaOfSubcategoriasListSubcategorias);
                }
            }
            for (Articulo articuloListArticulo : categorias.getArticuloList()) {
                Categorias oldCodCategoriaOfArticuloListArticulo = articuloListArticulo.getCodCategoria();
                articuloListArticulo.setCodCategoria(categorias);
                articuloListArticulo = em.merge(articuloListArticulo);
                if (oldCodCategoriaOfArticuloListArticulo != null) {
                    oldCodCategoriaOfArticuloListArticulo.getArticuloList().remove(articuloListArticulo);
                    oldCodCategoriaOfArticuloListArticulo = em.merge(oldCodCategoriaOfArticuloListArticulo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Categorias categorias) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categorias persistentCategorias = em.find(Categorias.class, categorias.getCodCategoria());
            List<Subcategorias> subcategoriasListOld = persistentCategorias.getSubcategoriasList();
            List<Subcategorias> subcategoriasListNew = categorias.getSubcategoriasList();
            List<Articulo> articuloListOld = persistentCategorias.getArticuloList();
            List<Articulo> articuloListNew = categorias.getArticuloList();
            List<String> illegalOrphanMessages = null;
            for (Subcategorias subcategoriasListOldSubcategorias : subcategoriasListOld) {
                if (!subcategoriasListNew.contains(subcategoriasListOldSubcategorias)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Subcategorias " + subcategoriasListOldSubcategorias + " since its codCategoria field is not nullable.");
                }
            }
            for (Articulo articuloListOldArticulo : articuloListOld) {
                if (!articuloListNew.contains(articuloListOldArticulo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Articulo " + articuloListOldArticulo + " since its codCategoria field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Subcategorias> attachedSubcategoriasListNew = new ArrayList<Subcategorias>();
            for (Subcategorias subcategoriasListNewSubcategoriasToAttach : subcategoriasListNew) {
                subcategoriasListNewSubcategoriasToAttach = em.getReference(subcategoriasListNewSubcategoriasToAttach.getClass(), subcategoriasListNewSubcategoriasToAttach.getCodSubcategoria());
                attachedSubcategoriasListNew.add(subcategoriasListNewSubcategoriasToAttach);
            }
            subcategoriasListNew = attachedSubcategoriasListNew;
            categorias.setSubcategoriasList(subcategoriasListNew);
            List<Articulo> attachedArticuloListNew = new ArrayList<Articulo>();
            for (Articulo articuloListNewArticuloToAttach : articuloListNew) {
                articuloListNewArticuloToAttach = em.getReference(articuloListNewArticuloToAttach.getClass(), articuloListNewArticuloToAttach.getCodArt());
                attachedArticuloListNew.add(articuloListNewArticuloToAttach);
            }
            articuloListNew = attachedArticuloListNew;
            categorias.setArticuloList(articuloListNew);
            categorias = em.merge(categorias);
            for (Subcategorias subcategoriasListNewSubcategorias : subcategoriasListNew) {
                if (!subcategoriasListOld.contains(subcategoriasListNewSubcategorias)) {
                    Categorias oldCodCategoriaOfSubcategoriasListNewSubcategorias = subcategoriasListNewSubcategorias.getCodCategoria();
                    subcategoriasListNewSubcategorias.setCodCategoria(categorias);
                    subcategoriasListNewSubcategorias = em.merge(subcategoriasListNewSubcategorias);
                    if (oldCodCategoriaOfSubcategoriasListNewSubcategorias != null && !oldCodCategoriaOfSubcategoriasListNewSubcategorias.equals(categorias)) {
                        oldCodCategoriaOfSubcategoriasListNewSubcategorias.getSubcategoriasList().remove(subcategoriasListNewSubcategorias);
                        oldCodCategoriaOfSubcategoriasListNewSubcategorias = em.merge(oldCodCategoriaOfSubcategoriasListNewSubcategorias);
                    }
                }
            }
            for (Articulo articuloListNewArticulo : articuloListNew) {
                if (!articuloListOld.contains(articuloListNewArticulo)) {
                    Categorias oldCodCategoriaOfArticuloListNewArticulo = articuloListNewArticulo.getCodCategoria();
                    articuloListNewArticulo.setCodCategoria(categorias);
                    articuloListNewArticulo = em.merge(articuloListNewArticulo);
                    if (oldCodCategoriaOfArticuloListNewArticulo != null && !oldCodCategoriaOfArticuloListNewArticulo.equals(categorias)) {
                        oldCodCategoriaOfArticuloListNewArticulo.getArticuloList().remove(articuloListNewArticulo);
                        oldCodCategoriaOfArticuloListNewArticulo = em.merge(oldCodCategoriaOfArticuloListNewArticulo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = categorias.getCodCategoria();
                if (findCategorias(id) == null) {
                    throw new NonexistentEntityException("The categorias with id " + id + " no longer exists.");
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
            Categorias categorias;
            try {
                categorias = em.getReference(Categorias.class, id);
                categorias.getCodCategoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categorias with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Subcategorias> subcategoriasListOrphanCheck = categorias.getSubcategoriasList();
            for (Subcategorias subcategoriasListOrphanCheckSubcategorias : subcategoriasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Categorias (" + categorias + ") cannot be destroyed since the Subcategorias " + subcategoriasListOrphanCheckSubcategorias + " in its subcategoriasList field has a non-nullable codCategoria field.");
            }
            List<Articulo> articuloListOrphanCheck = categorias.getArticuloList();
            for (Articulo articuloListOrphanCheckArticulo : articuloListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Categorias (" + categorias + ") cannot be destroyed since the Articulo " + articuloListOrphanCheckArticulo + " in its articuloList field has a non-nullable codCategoria field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(categorias);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Categorias> findCategoriasEntities() {
        return findCategoriasEntities(true, -1, -1);
    }

    public List<Categorias> findCategoriasEntities(int maxResults, int firstResult) {
        return findCategoriasEntities(false, maxResults, firstResult);
    }

    private List<Categorias> findCategoriasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Categorias.class));
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

    public Categorias findCategorias(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Categorias.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Categorias> rt = cq.from(Categorias.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
