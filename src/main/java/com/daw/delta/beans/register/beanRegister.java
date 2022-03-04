package com.daw.delta.beans.register;

import com.daw.delta.DTO.Usuario;
import com.daw.delta.utils.EmailUtils;
import com.daw.delta.utils.Utilidades;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;

public class beanRegister {
    
    private String email;
    private String fecha_naci;
    private String pais;
    private String nombre;
    private String apellidos;
    private String passwd;
    
    public beanRegister() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFecha_naci() {
        return fecha_naci;
    }

    public void setFecha_naci(String fecha_naci) {
        this.fecha_naci = fecha_naci;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    
    public String guardarUsuario() {
        try {
            Utilidades utils_ = new Utilidades();
            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
            Date newDate = formatDate.parse(fecha_naci);
            Usuario v = new Usuario(
                    email,
                    utils_.lastId()+1,
                    newDate,
                    pais,
                    nombre,
                    apellidos,
                    utils_.encrypt(passwd)
            );
            utils_.getCtrUsuario().create(v);
            String motivo = "Bienvenido a Delta | Periodico Digital";
            String contenido;
            String root = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            try(BufferedReader br = new BufferedReader(new FileReader(root+"email.html"))) {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();
                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                contenido = sb.toString();
            }
            EmailUtils.enviarMail(utils_.getSTMP_host(), utils_.getSTMP_port(), utils_.getSTMP_user(), utils_.getSTMP_pass(), email, motivo, contenido);
            return "true";
        } catch (ParseException ex) {
            Logger.getLogger(beanRegister.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(beanRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "volver";
    }
}
