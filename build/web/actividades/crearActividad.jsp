<%-- 
    Document   : crearActividad
    Created on : 26 feb 2025, 18:40:42
    Author     : juan-antonio
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Actividades de Viaje</title>
    </head>
    <h1>${empty id?"Crear":"Editar"} Actividad</h1>
    <br>
    <form method="post">
        <input type="hidden" name="id" value="${id}">
        <input type="hidden" name="idExperiencia" value="${idExperiencia}">
        <label for="titulo">Titulo</label> 
        <input type="text" name="titulo" value="${titulo}" maxlength="30" required="">
        <br>
        <label for="descripcion">Descripcion</label>
        <input type="text" name="descripcion" value="${descripcion}" maxlength="400" required="">
        <br>
        <label for="fecha">Fecha</label>
        <input type="date" name="fecha" value="${fecha}" required="">
        <br>
        <label>Imagenes</label>
        <input type="file" name="imagenes">
        <br>
        <input type="submit" name="${empty id?'crear':'editar'}" value="${empty id?'crear':'editar'}">
    </form>
    <br>
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <a href="ControladorActividades?listar=true&idExperiencia=${idExperiencia}">Volver</a>
</body>
</html>

