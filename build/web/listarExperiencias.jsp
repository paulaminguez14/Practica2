<%-- 
    Document   : listarExperiencias.jsp
    Created on : 24 feb 2025, 19:10:30
    Author     : juan-antonio
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>ExperienciaViaje</title>
    </head>
    <body>
        <h1>Experiencias de Viaje</h1>
        <table>
            <tr>
                <th>Titulo</th>
                <th>Descripcion</th>
                <th>Fecha</th>
                <th>Publicada</th>
            </tr>
            <c:forEach var="experiencia" items="${experiencias}">
                <tr>
                    <td>${experiencia.titulo}</td>
                    <td>${experiencia.descripcion}</td>
                    <td>${experiencia.fechaInicio}</td>
                    <td>${experiencia.publicada ? "Si" : "No"}</td>
                    <td><a href="ControladorDepartamento?id=${departamento.id}">Editar</a></td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <form>
            <input type="submit" name="crear" value="Crear Experiencia">
        </form>
        <br>
        <a href="/Practica2/Controladores/ControladorInicio">Volver</a>
    </body>
</html>
