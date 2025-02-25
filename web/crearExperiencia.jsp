<%-- 
    Document   : crearExperiencia.jsp
    Created on : 25 feb 2025, 16:57:22
    Author     : juan-antonio
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Experiencias de Viaje</title>
    </head>
    <h1>${empty id?"Crear":"Editar"} Experiencia</h1>
    <br>
    <form method="post">
        <input type="hidden" name="id" value="${id}">
        <input type="hidden" name="idUsuario" value="${idUsuario}">
        <label for="titulo">Titulo</label> 
        <input type="text" name="titulo" value="${titulo}" maxlength="30" required="">
        <br>
        <label for="descripcion">Descripcion</label>
        <input type="text" name="descripcion" value="${descripcion}" maxlength="400" required="">
        <br>
        <label for="fechaInicio">Fecha de Inicio</label>
        <input type="date" name="fechaInicio" value="${fechaInicio}" required="">
        <br>
        <label>Publicada (Si:1 No:0)</label>
        <input type="number" name="publicada" min="0" max="1" value="${publicada != null ? (publicada ? '1' : '0') : '0'}" required>
        <br>
        <label>Actividades</label>
        <select name="actividades" multiple>
            <c:forEach var="actividad" items="${actividades}">
                <option value="${actividad.id}" ${selectedActividades.contains(act.id) ? "selected" : ""}>
                    <c:out value="${actividad.titulo}"/>
                </option>
            </c:forEach>
        </select>
        <br>
        <input type="submit" name="${empty id?'crear':'editar'}" value="Aceptar">
    </form>
    <br>
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <a href="${header.referer}">Volver</a>
</body>
</html>
