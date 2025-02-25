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
import modelo.entidades.Actividad;
import modelo.entidades.ExperienciaViaje;
import modelo.entidades.Usuario;
import modelo.servicio.ServicioActividad;
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
            long idUsuario = usuario.getId();
            List<ExperienciaViaje> experiencias = sexp.findExperienciaPorIdUsuario(idUsuario);
            request.setAttribute("idUsuario", idUsuario);
            request.setAttribute("experiencias", experiencias);
        } else if (request.getParameter("crear") != null) { // Crear Experiencia
            vista = "/crearExperiencia.jsp";
            HttpSession sesion = request.getSession();
            Usuario usuario = (Usuario) sesion.getAttribute("usuario");
            long idUsuario = usuario.getId();
            ServicioActividad sa = new ServicioActividad(emf);
            List<Actividad> actividades = sa.findActividadEntities();
            request.setAttribute("actividades", actividades);
            request.setAttribute("idUsuario", idUsuario);

        } else if (request.getParameter("id") != null) { // Editar Experiencia
            try {
                HttpSession sesion = request.getSession();
                Usuario usuario = (Usuario) sesion.getAttribute("usuario");
                long idUsuario = usuario.getId();
                ServicioActividad sa = new ServicioActividad(emf);
                List<Actividad> actividades = sa.findActividadEntities();
                request.setAttribute("actividades", actividades);
                request.setAttribute("idUsuario", idUsuario);
                long id = Long.parseLong(request.getParameter("id"));
                ExperienciaViaje experiencia = sexp.findExperienciaViaje(id);
                request.setAttribute("id", id);
                request.setAttribute("titulo", experiencia.getTitulo());
                request.setAttribute("descripcion", experiencia.getDescripcion());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String fechaFormateada = (experiencia.getFechaInicio() != null) ? sdf.format(experiencia.getFechaInicio()) : "";
                request.setAttribute("fechaInicio", fechaFormateada);
                request.setAttribute("publicada", experiencia.isPublicada());
                vista = "/crearExperiencia.jsp";
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

        // Parseamos cuando sea distinto de null (editar o eliminar)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaInicio = null;
        if (fechaInicioStr != null && !fechaInicioStr.isEmpty()) {
            try {
                fechaInicio = sdf.parse(fechaInicioStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        String vista = "";
        String error = "";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Practica2PU");
        ServicioExperienciaViaje sexp = new ServicioExperienciaViaje(emf);

        if (request.getParameter("crear") != null) { // Crear
            try {
                Long idUsuario = Long.parseLong(request.getParameter("idUsuario"));
                HttpSession sesion = request.getSession();
                Usuario usuario = (Usuario) sesion.getAttribute("usuario");

                ExperienciaViaje experiencia = new ExperienciaViaje();
                experiencia.setDescripcion(descripcion);
                experiencia.setFechaInicio(fechaInicio);
                experiencia.setTitulo(titulo);
                experiencia.setPublicada(false);
                experiencia.setUsuario(usuario);

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
                experiencia.setPublicada("1".equals(publicada));
                sexp.edit(experiencia);
            } catch (Exception e) {
                error = "Error al editar la experiencia " + titulo;
                vista = "/crearExperiencia.jsp";
                request.setAttribute("titulo", titulo);
                request.setAttribute("descripcion", descripcion);
                request.setAttribute("fechaInicio", fechaInicioStr);
                request.setAttribute("publicada", publicada);
                request.setAttribute("error", error);
            }
        } else if (request.getParameter("eliminar") != null) { // Eliminar
            long id = Long.parseLong(request.getParameter("id"));
            try {
                sexp.destroy(id);  // Solo necesitamos el id para eliminar
            } catch (NonexistentEntityException e) {
                error = "La experiencia con ID " + id + " ya no existe";
            } catch (Exception e) {
                error = "No se puede eliminar la experiencia con ID " + id;
            }

            if (!error.isEmpty()) {
                vista = "/listarExperiencias.jsp";
            } else {
                response.sendRedirect("ControladorExperienciaViaje");  // Volver al listado de experiencias
                return;
            }
        }

        emf.close();
        // Si se ha realizado la acción con éxito, volvemos al listado
        if (error.isEmpty()) {
            response.sendRedirect("ControladorExperienciaViaje");
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
