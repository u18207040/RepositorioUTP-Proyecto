$(document).ready(function () {
    // Obtén el ID del inventario actual
    var idInventario = "<%=ex.getIdInventario()%>";

    // Verifica si la acción ya se ha realizado antes
    var actionKey = "actionPerformed_" + idInventario;
    var actionPerformed = localStorage.getItem(actionKey);

    if (actionPerformed) {
        // La acción ya se realizó, puedes evitar el evento del formulario
        $("#cantidadForm" + idInventario).submit(function (e) {
            e.preventDefault();
        });
    } else {
        // La acción aún no se ha realizado, maneja el evento del formulario
        $("#cantidadForm" + idInventario).submit(function (e) {
            $(".error").empty(); // Limpiar mensajes de error anteriores
           
            var cantidad = $("#cantidad").val().trim();
            var hasError = false;
          
            if (cantidad === "") {
                $("#cantidadError").text("El campo cantidad está vacío.");
                hasError = true;
            }
    
            if (hasError) {
                e.preventDefault(); // Evitar el envío del formulario si hay errores
            } else {
                // Mostrar un cuadro de confirmación
                if (confirm("¿Está seguro que desea guardar estos datos?")) {
                    // Marcar la acción como realizada
                    localStorage.setItem(actionKey, "true");
                } else {
                    e.preventDefault(); // Cancelar el envío del formulario si el usuario hace clic en "Cancelar"
                }
            }
        });
    }
});
