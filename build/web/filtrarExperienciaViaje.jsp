<%-- 
    Document   : filtrarExperienciaViaje
    Created on : 25 feb 2025, 20:30:09
    Author     : juan-antonio
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<table>
    <tr>
        <th>Titulo</th>
        <th>Descripcion</th>
        <th>Fecha</th>
        <th>Usuario</th>
        <th>Acciones</th>
    </tr>
    <c:forEach var="experiencia" items="${experiencias}">
        <tr>
            <td>${experiencia.titulo}</td>
            <td>${experiencia.descripcion}</td>
            <td><fmt:formatDate pattern="dd/MM/yyyy" value="${experiencia.fechaInicio}"/></td>
            <td>${experiencia.usuario.nombre}</td>
            <td><a href="Controladores.Actividades/ControladorActividades?id=${experiencia.id}">Ver Actividades</a></td>
        </tr>
    </c:forEach>
</table>
