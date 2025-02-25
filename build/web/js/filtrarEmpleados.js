/* 
 * Filtrar empleados con ajax
 */

function filtrar() {
    filtro = document.getElementById("filtro").value;
    $.ajax({
        url: "ControladorFiltrarExperiencias",
        method: 'POST',
        data: {filtro : filtro}        
    }).done(function(datos) {
        $("#listado").html(datos);
    });
}
