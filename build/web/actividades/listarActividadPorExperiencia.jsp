<%-- 
    Document   : listarActividadPorExperiencia
    Created on : 26 feb 2025, 18:40:28
    Author     : juan-antonio
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Actividad</title>
    </head>
    <body>
        <h1>Actividades</h1>
        <table border="2px">
            <tr>
                <th>Titulo</th>
                <th>Descripcion</th>
                <th>Fecha</th>
                <th>Imagenes</th>
                <th>Experiencia</th>
            </tr>
            <c:forEach var="actividad" items="${actividades}">
                <tr>
                    <td>${actividad.titulo}</td>
                    <td>${actividad.descripcion}</td>
                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${actividad.fecha}"/></td>
                    <td>
                        <c:forEach var="imagen" items="${actividad.imagenes}">
                            <img src="${imagen}" alt="Imagen de actividad" width="100px" height="100px">
                        </c:forEach>
                    </td>
                    <td>${actividad.experiencia}</td>

                    <td>
                        <a href="ControladorActividades?editar=true&id=${actividad.id}&idExperiencia=${actividad.experiencia.id}">Editar</a>
                        <br>
                        <a href="ControladorActividades?crear=true&idExperiencia=${actividad.experiencia.id}">Crear Actividad</a>
                        <form action="ControladorActividades?listar=true&idExperiencia=${actividad.experiencia.id}" method="POST">
                            <input type="hidden" name="id" value="${actividad.id}">
                            <input type="submit" name="eliminar" value="Eliminar" 
                                   onclick="return confirm('¿Estás seguro de que deseas eliminar la actividad ${actividad.id}?')">
                        </form>

                    </td>

                </tr>
            </c:forEach>
        </table>
        <br><br>
        <a href="/Practica2/Controladores/ControladorExperienciaViaje">Volver</a>
    </body>
</html>
