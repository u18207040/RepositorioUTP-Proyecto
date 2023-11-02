 $(document).ready(function () {
    $("#miFormularioUser").submit(function (e) {
        $(".error").empty(); // Limpiar mensajes de error anteriores

        // Validar los campos
        var nombre = $("#nombre").val().trim();
        var cargo = $("#cargo").val().trim();
        var telefono = $("#telefono").val().trim();
        var usuario = $("#usuario").val().trim();
        var email = $("#email").val().trim();
        var dni = $("#dni").val().trim();
        
        var hasError = false;

        if (nombre === "") {
            $("#nombreError").text("El campo nombre está vacío.");
            hasError = true;
        }

        if (cargo === "") {
            $("#cargoError").text("El campo cargo está vacío.");
            hasError = true;
        }
        

        if (telefono === "") {
            $("#telefonoError").text("El campo teléfono está vacío.");
            hasError = true;
        }

        if (usuario === "") {
            $("#usuarioError").text("El campo usuario está vacío.");
            hasError = true;
        }
        if (dni === "") {
            $("#dniError").text("El campo DNI está vacío.");
            hasError = true;
        }
        if (email === "") {
            $("#emailError").text("El campo correo está vacío.");
            hasError = true;
        }

        if (hasError) {
            e.preventDefault(); // Evitar el envío del formulario si hay errores
        } else {
            // Mostrar un cuadro de confirmación
            if (!confirm("¿Está seguro que desea guardar estos datos?")) {
                e.preventDefault(); // Cancelar el envío del formulario si el usuario hace clic en "Cancelar"
            }
        }
    });
    
});