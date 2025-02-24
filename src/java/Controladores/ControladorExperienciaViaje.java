/*
 * ControladorExperienciaViaje 
 */
package Controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import modelo.servicio.exceptions.NonexistentEntityException;

/**
 *
 * @author juan-antonio
 */
@WebServlet(name = "ControladorExperienciaViaje", urlPatterns = {"/Controladores/ControladorExperienciaViaje"})
public class ControladorExperienciaViaje extends HttpServlet {

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
        String vista = "/listarExperiencias.jsp";

        if (request.getParameter("id") == null && request.getParameter("crear") == null) {//Mostrara las experiencias del usuario que se le pasa parametro
            HttpSession sesion = request.getSession();
            Usuario usuario = (Usuario) sesion.getAttribute("usuario");
            long id = usuario.getId();
            List<ExperienciaViaje> experiencias = sexp.findExperienciaPorIdUsuario(id);
            request.setAttribute("idUsuario", id);
            request.setAttribute("experiencias", experiencias);
        } else if (request.getParameter("crear") != null) { // Crear Experiencia
            vista = "/crearExperiencia.jsp";
        } else if (request.getParameter("id") != null) { // Editar Experiencia
            try {
                long id = Long.parseLong(request.getParameter("id"));
                ExperienciaViaje experiencia = sexp.findExperienciaViaje(id);
                request.setAttribute("id", id);
                request.setAttribute("titulo", experiencia.getTitulo());
                request.setAttribute("descripcion", experiencia.getDescripcion());
                request.setAttribute("fechaInicio", experiencia.getFechaInicio());
                request.setAttribute("publicada", experiencia.isPublicada());
                vista = "/editarExperiencia.jsp";
            } catch (Exception e) {
            }
        }
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
        String idStr = request.getParameter("id");
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String fechaInicioStr = request.getParameter("fechaInicio");
        String publicada = request.getParameter("publicada");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaInicio = new Date();
                try {
                    fechaInicio = sdf.parse(fechaInicioStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
        String vista = "";
        String error = "";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Practica2PU");
        ServicioExperienciaViaje sexp = new ServicioExperienciaViaje(emf);
        if (request.getParameter("crear") != null) { // Crear
            try {
                ExperienciaViaje experiencia = new ExperienciaViaje();
                experiencia.setDescripcion(descripcion);
                experiencia.setFechaInicio(fechaInicio);
                experiencia.setTitulo(titulo);
                experiencia.setPublicada(false);
                sexp.create(experiencia);
            } catch (Exception e) {
                error = "Error al crear la experiencia " + titulo;
                vista = "/crearExperiencia.jsp";
                request.setAttribute("titulo", titulo);
                request.setAttribute("descripcion", descripcion);
                request.setAttribute("fechaInicio", fechaInicioStr);
                request.setAttribute("publicada", publicada);
                request.setAttribute("error", error);
            }

        } else if (request.getParameter("editar") != null) { // Editar
            try {
                long id = Long.parseLong(request.getParameter("id"));
                ExperienciaViaje experiencia = sexp.findExperienciaViaje(id);
                experiencia.setTitulo(titulo);
                experiencia.setDescripcion(descripcion);
                experiencia.setFechaInicio(fechaInicio);
                experiencia.setPublicada(Boolean.parseBoolean(publicada));
                sexp.edit(experiencia);
            } catch (Exception e) {
                error = "Error a editar la experiencia " + titulo;
                vista = "/editarExperiencia.jsp";
                request.setAttribute("titulo", titulo);
                request.setAttribute("descripcion", descripcion);
                request.setAttribute("fechaInicio", fechaInicioStr);
                request.setAttribute("publicada", publicada);
                request.setAttribute("error", error);
            }
        } else if (request.getParameter("eliminar") != null) {  // Eliminar
            try {
                long id = Long.parseLong(request.getParameter("id"));
                sexp.destroy(id);
            } catch (NonexistentEntityException e) {
                error = "La exeriencia " + titulo + " ya no existe";
            } catch (Exception e) {
                error = "No se puede eliminar la experiencia" + titulo;
            }
            if (!error.isEmpty()) {
                vista = "/admin/editarDepartamento.jsp";
            }
        }
        emf.close();
        // Si se ha realizado la acción con éxito, volvemos al listado
        if (error.isEmpty()) {
            response.sendRedirect("ControladorDepartamento");
            return;
        }
        getServletContext().getRequestDispatcher(vista).forward(request, response);
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
