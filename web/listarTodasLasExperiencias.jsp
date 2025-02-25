<%-- 
    Document   : listarExperiencias.jsp
    Created on : 24 feb 2025, 19:10:30
    Author     : juan-antonio y Paula
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FiltroExperiencias</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="../js/filtrarEmpleados.js"></script>
    </head>
    <body onload="filtrar()">
        <h1>Consulta de Experiencias</h1>
        <br>
        <div>
            <label for="filtro">Filtrar</label>
            <input type="text" name="filtro" id="filtro" onkeyup="filtrar()">
        </div><br><br>
        <div id="listado">
            
        </div><br><br>
        
        <a href="../Controladores/ControladorExperienciaViaje">Volver</a>
    </body>
</html>
