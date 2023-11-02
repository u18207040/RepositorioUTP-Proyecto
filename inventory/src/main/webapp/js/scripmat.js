$(document).ready(function () {
    $("#miFormulariox").submit(function (e) {
        $(".error").empty(); // Limpiar mensajes de error anteriores

        // Validar los campos
        var nombre = $("#nombre").val().trim();
        var categoria = $("#categoria").val().trim();
        var detalle = $("#detalle").val().trim();
        var hasError = false;

        if (nombre === "") {
            $("#nombreError").text("El campo nombre está vacío.");
            hasError = true;
        }

        if (categoria === "") {
            $("#categoriaError").text("El campo categoria está vacío.");
            hasError = true;
        }

        if (detalle === "") {
            $("#detalleError").text("El campo detalle está vacío.");
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
    $("#miFormulariop").submit(function (e) {
        $(".error").empty(); 
        var tipo = $("#tipo").val().trim();
        var nombre = $("#nombre").val().trim();
        var descripcion = $("#descripcion").val().trim();
        var talla = $("#talla").val().trim();
        var modelo = $("#modelo").val().trim();
        var hasError = false;
        
        
        if (tipo === "") {
            $("#tipoError").text("El campo tipo está vacío.");
            hasError = true;
        }
        if (nombre === "") {
            $("#nombreError").text("El campo nombre está vacío.");
            hasError = true;
        }

        if (descripcion === "") {
            $("#descripcionError").text("El campo descripcion está vacío.");
            hasError = true;
        }

        if (talla === "") {
            $("#tallaError").text("El campo talla está vacío.");
            hasError = true;
        }
        if (modelo === "") {
            $("#modeloError").text("El campo modelo está vacío.");
            hasError = true;
        }

        if (hasError) {
            e.preventDefault(); 
        } else {
            if (!confirm("¿Está seguro que desea guardar estos datos?")) {
                e.preventDefault(); 
            }
        }
    });
    
});