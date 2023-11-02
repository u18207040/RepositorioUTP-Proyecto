$(document).ready(function () {
    $("#miFormularioExistencia").submit(function (e) {
        $(".error").empty(); // Limpiar mensajes de error anteriores

        // Validar los campos
        var inicial = $("#inicial").val().trim();
        var final = $("#final").val().trim();
        var precio= $("#precio").val().trim();
        var date= $("#date").val().trim();
        var responsable= $("#responsable").val().trim();
        var material= $("#material").val().trim();
        var proveedor= $("#proveedor").val().trim();
        var hasError = false;

        if (inicial === "") {
            $("#inicialError").text("El campo Existencia inicial está vacío.");
            hasError = true;
        }

        if (final === "") {
            $("#finalError").text("El campo Existencia final está vacío.");
            hasError = true;
        }

        if (precio === "") {
            $("#precioError").text("El campo precio está vacío.");
            hasError = true;
        }
        if (date === "") {
            $("#dateError").text("El campo fecha está vacío.");
            hasError = true;
        }
        if (responsable === "") {
            $("#responsableError").text("El campo responsable está vacío.");
            hasError = true;
        }
        if (material === "") {
            $("#materialError").text("El campo material está vacío.");
            hasError = true;
        }
        if (proveedor === "") {
            $("#proveedorError").text("El campo proveedor está vacío.");
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
    $("#miFormularioExistenciap").submit(function (e) {
        $(".error").empty();
        var inicial = $("#inicial").val().trim();
        var estado = $("#estado").val().trim();
        var precio= $("#precio").val().trim();
        var date= $("#date").val().trim();
        var responsable= $("#responsable").val().trim();
        var producto= $("#producto").val().trim();
        var cliente= $("#cliente").val().trim();
        var hasError = false;

        if (inicial === "") {
            $("#inicialError").text("El campo Existencia está vacío.");
            hasError = true;
        }

        if (estado === "") {
            $("#finalError").text("El campo Estado está vacío.");
            hasError = true;
        }

        if (precio === "") {
            $("#precioError").text("El campo precio está vacío.");
            hasError = true;
        }
        if (date === "") {
            $("#dateError").text("El campo fecha está vacío.");
            hasError = true;
        }
        if (responsable === "") {
            $("#responsableError").text("El campo responsable está vacío.");
            hasError = true;
        }
        if (producto=== "") {
            $("#materialError").text("El campo producto está vacío.");
            hasError = true;
        }
        if (cliente === "") {
            $("#proveedorError").text("El campo cliente está vacío.");
            hasError = true;
        }

        if (hasError) {
            e.preventDefault(); // Evitar el envío del formulario si hay errores
        } else {
            if (!confirm("¿Está seguro que desea guardar estos datos?")) {
                e.preventDefault(); // Cancelar el envío del formulario si el usuario hace clic en "Cancelar"
            }
        }
    });
});
    