package com.daw.delta.beans.administracion;

import com.daw.delta.DTO.Usuario;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name="beanAdministracion")
@SessionScoped
public class beanAdministracion implements Serializable{
    
    @ManagedProperty("#{beanLogin.usu}")
    private Usuario usu;
    
    public beanAdministracion() {
    }

    public Usuario getUsu() {
        if (this.usu==null) {
            redireccionar("logreg.jsp");
        } else if (this.usu.getPermiso()==null || this.usu.getPermiso()==0) {
            redireccionar("index.jsp");
        }
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }
    
    public boolean isAdmin() {
        return this.usu.getPermiso()!=null && this.usu.getPermiso()!=0;
    }
    
    private static void redireccionar (String url) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ctx.redirect(url);
        } catch (IOException ex) {}
    }

}
