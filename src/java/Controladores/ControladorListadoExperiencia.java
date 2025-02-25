/*
 * ControladorListarExperiencia
 */
package Controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.entidades.ExperienciaViaje;
import modelo.entidades.Usuario;
import modelo.servicio.ServicioExperienciaViaje;
import modelo.servicio.ServicioUsuario;

/**
 *
 * @author juan-antonio
 */
@WebServlet(name = "ControladorListadoExperiencia", urlPatterns = {"/Controladores/ControladorListadoExperiencia"})
public class ControladorListadoExperiencia extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Practica2PU");
        ServicioExperienciaViaje sexp = new ServicioExperienciaViaje(emf);
        String vista = "/listarTodasLasExperiencias.jsp";
        ServicioUsuario su = new ServicioUsuario(emf);

        // Recuperamos todas las experiencias
        List<ExperienciaViaje> experiencias = sexp.findExperienciaViajeEntities();

        // Cargamos el usuario asociado a cada experiencia
        for (ExperienciaViaje experiencia : experiencias) {
            Long usuarioId = experiencia.getUsuario().getId();
            Usuario usuario = su.findUsuario(usuarioId);
            experiencia.setUsuario(usuario); 
        }

        request.setAttribute("experiencias", experiencias);
        emf.close();
        getServletContext().getRequestDispatcher(vista).forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
