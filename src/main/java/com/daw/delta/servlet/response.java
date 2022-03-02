package com.daw.delta.servlet;

import com.daw.delta.DTO.Articulo;
import com.daw.delta.DTO.Categorias;
import com.daw.delta.DTO.Subcategorias;
import com.daw.delta.utils.Utilidades;
import org.json.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class response extends HttpServlet {
    private final JSONObject obj = new JSONObject();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Utilidades utils_ = new Utilidades();
            String categorias = request.getParameter("categorias");
            String articulo = request.getParameter("articulo");
            if (categorias!=null) {
                if (categorias.equals("todas")) {
                    List<Categorias> v = utils_.getCtrCategorias().findCategoriasEntities();
                    for (Categorias k:v) {
                        Categorias x = k;
                        obj.put(x.getCodCategoria()+"", x.getCategoria());
                    }
                } else if(categorias.equals("sub")) {
                    List<Subcategorias> v = utils_.getCtrSubcategorias().findSubcategoriasEntities();
                    for (Subcategorias k:v) {
                        Subcategorias x = k;
                        JSONObject obj2 = new JSONObject();
                        obj2.put(x.getNombre(), x.getCodCategoria().getCodCategoria()+"");
                        obj.put(x.getCodSubcategoria()+"", obj2.toString());
                    }
                }
            } else if (articulo!=null) {
                if (articulo.equals("todos")) {
                    List<Articulo> v = utils_.getCtrArticulo().findArticuloEntities();
                    for (Articulo k:v) {
                        Articulo x = k;                        
                        obj.put(x.getCodArt()+"", x.toString());
                    }
                } else {
                    Articulo x = utils_.getCtrArticulo().findArticulo(Integer.parseInt(articulo));
                    obj.put(x.getCodArt()+"", x.toString());
                }
            }
            
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(obj.toString());
            out.flush();
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(response.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servidor de Respuesta del Proyecto Delta, hacia jQuery con JSON";
    }

}
