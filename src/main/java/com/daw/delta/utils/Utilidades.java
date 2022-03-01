package com.daw.delta.utils;

import com.daw.delta.DAO.ArticuloJpaController;
import com.daw.delta.DAO.CategoriasJpaController;
import com.daw.delta.DAO.OpinionJpaController;
import com.daw.delta.DAO.RespuestasJpaController;
import com.daw.delta.DAO.SubcategoriasJpaController;
import com.daw.delta.DAO.UsuarioJpaController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Utilidades {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("deltaPU");
    private final ArticuloJpaController ctrArticulo = new ArticuloJpaController(emf);
    private final CategoriasJpaController ctrCategorias = new CategoriasJpaController(emf);
    private final OpinionJpaController ctrOpinion = new OpinionJpaController(emf);
    private final RespuestasJpaController ctrRespuestas = new RespuestasJpaController(emf);
    private final SubcategoriasJpaController ctrSubcategorias = new SubcategoriasJpaController(emf);
    private final UsuarioJpaController ctrUsuario = new UsuarioJpaController(emf);

    public ArticuloJpaController getCtrArticulo() {
        return ctrArticulo;
    }

    public CategoriasJpaController getCtrCategorias() {
        return ctrCategorias;
    }

    public OpinionJpaController getCtrOpinion() {
        return ctrOpinion;
    }

    public RespuestasJpaController getCtrRespuestas() {
        return ctrRespuestas;
    }

    public SubcategoriasJpaController getCtrSubcategorias() {
        return ctrSubcategorias;
    }

    public UsuarioJpaController getCtrUsuario() {
        return ctrUsuario;
    }
    
}
