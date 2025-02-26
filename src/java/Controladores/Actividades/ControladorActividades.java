/*
 * ControladorActividades
 */
package Controladores.Actividades;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
@WebServlet(name = "ControladorActividades", urlPatterns = {"/Controladores.Actividades/ControladorActividades"})
public class ControladorActividades extends HttpServlet {

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
        ServicioActividad sa = new ServicioActividad(emf);
        String vista = "/actividades/listarActividadPorExperiencia.jsp";
        if (request.getParameter("id") == null && request.getParameter("crear") == null) {//Mostrara las actividades de la experiencia que se le pasa por parametro
            long idExperiencia = Long.parseLong(request.getParameter("idExperiencia"));
            ServicioExperienciaViaje sexp = new ServicioExperienciaViaje(emf);
            ExperienciaViaje experiencia = sexp.findExperienciaViaje(idExperiencia);
            List<Actividad> actividades = sa.findActividadPorIdExperiencia(idExperiencia);
            request.setAttribute("idExperiencia", idExperiencia);
            request.setAttribute("actividades", actividades);
        } else if (request.getParameter("crear") != null) { // Crear Actividad
            vista = "/actividades/crearActividad.jsp";
            long idExperiencia = Long.parseLong(request.getParameter("idExperiencia"));
            ServicioExperienciaViaje sexp = new ServicioExperienciaViaje(emf);
            ExperienciaViaje experiencia = sexp.findExperienciaViaje(idExperiencia);
            request.setAttribute("idExperiencia", idExperiencia);

        } else if (request.getParameter("editar") != null) { // Editar Actividad
            try {
                long id = Long.parseLong(request.getParameter("id"));
                Actividad actividad = sa.findActividad(id);
                if (actividad != null) {
                    long idExperiencia = actividad.getExperiencia().getId(); // Obtener el id de la experiencia de la actividad
                    request.setAttribute("idExperiencia", idExperiencia);
                    request.setAttribute("id", id);
                    request.setAttribute("titulo", actividad.getTitulo());
                    request.setAttribute("descripcion", actividad.getDescripcion());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String fechaFormateada = (actividad.getFecha() != null) ? sdf.format(actividad.getFecha()) : "";
                    request.setAttribute("fecha", fechaFormateada);
                    request.setAttribute("imagenes", actividad.getImagenes());
                    vista = "/actividades/crearActividad.jsp"; // Aquí se establece la vista correcta para la edición
                }
            } catch (Exception e) {
                e.printStackTrace(); // Añadir logging para errores si es necesario
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
        Long idExperiencia = Long.parseLong(request.getParameter("idExperiencia"));
        String idStr = request.getParameter("id");
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String fechaStr = request.getParameter("fecha");
        String imagenesStr = request.getParameter("imagenes");
        List<String> imagenes = imagenesStr != null ? Arrays.asList(imagenesStr.split(",")) : new ArrayList<>();

        // Parseamos cuando sea distinto de null (editar o eliminar)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        if (fechaStr != null && !fechaStr.isEmpty()) {
            try {
                fecha = sdf.parse(fechaStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        String vista = "";
        String error = "";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Practica2PU");
        ServicioActividad sa = new ServicioActividad(emf);

        if (request.getParameter("crear") != null) { // Crear
            try {
                idExperiencia = Long.parseLong(request.getParameter("idExperiencia"));
                ServicioExperienciaViaje sexp = new ServicioExperienciaViaje(emf);
                ExperienciaViaje experiencia = sexp.findExperienciaViaje(idExperiencia);

                Actividad actividad = new Actividad();
                actividad.setDescripcion(descripcion);
                actividad.setFecha(fecha);
                actividad.setTitulo(titulo);
                actividad.setImagenes(imagenes);
                actividad.setExperiencia(experiencia);

                sa.create(actividad);
            } catch (Exception e) {
                error = "Error al crear la actividad " + titulo;
                vista = "/actividades/crearActividad.jsp";
                request.setAttribute("titulo", titulo);
                request.setAttribute("descripcion", descripcion);
                request.setAttribute("fecha", fechaStr);
                request.setAttribute("error", error);
            }
        } else if (request.getParameter("editar") != null) { // Editar
            try {
                long id = Long.parseLong(request.getParameter("id")); // Obtener el ID de la actividad a editar
                Actividad actividad = sa.findActividad(id); // Buscar la actividad existente

                if (actividad != null) { // Si la actividad existe
                    // Asignar los nuevos valores a la actividad existente
                    actividad.setDescripcion(descripcion);
                    actividad.setFecha(fecha);
                    actividad.setTitulo(titulo);
                    actividad.setImagenes(imagenes);

                    // Obtener la experiencia actual para no perderla
                    ServicioExperienciaViaje sexp = new ServicioExperienciaViaje(emf);
                    ExperienciaViaje experiencia = sexp.findExperienciaViaje(idExperiencia);
                    actividad.setExperiencia(experiencia); // Asignar la experiencia a la actividad

                    // Guardar la actividad editada
                    sa.edit(actividad);
                } else {
                    error = "La actividad no existe";
                    vista = "/actividades/crearActividad.jsp"; // Redirigir a la vista de creación con mensaje de error
                    request.setAttribute("titulo", titulo);
                    request.setAttribute("descripcion", descripcion);
                    request.setAttribute("fecha", fechaStr);
                    request.setAttribute("error", error);
                }
            } catch (Exception e) {
                error = "Error al editar la actividad " + titulo;
                vista = "/actividades/crearActividad.jsp"; // Redirigir a la vista de edición con mensaje de error
                request.setAttribute("titulo", titulo);
                request.setAttribute("descripcion", descripcion);
                request.setAttribute("fecha", fechaStr);
                request.setAttribute("error", error);
                e.printStackTrace();
            }
        } else if (request.getParameter("eliminar") != null) { // Eliminar
            long id = Long.parseLong(request.getParameter("id"));
            try {
                sa.destroy(id);  // Solo necesitamos el id para eliminar
            } catch (NonexistentEntityException e) {
                error = "La actividad con ID " + id + " ya no existe";
            } catch (Exception e) {
                error = "No se puede eliminar la actividad con ID " + id;
            }

            if (!error.isEmpty()) {
                vista = "/actividades/listarActividadPorExperiencia.jsp";
            } else {
                response.sendRedirect("ControladorActividades?listar=true&idExperiencia=" + idExperiencia);
                return;
            }
        }

        emf.close();
        // Si se ha realizado la acción con éxito, volvemos al listado
        if (error.isEmpty()) {
            response.sendRedirect("ControladorActividades?listar=true&idExperiencia=" + idExperiencia);
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
