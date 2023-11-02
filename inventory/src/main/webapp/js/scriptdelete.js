
$(document).ready(function() {
	  $('.confirm-delete-materiales').on('click', function(e) {
        e.preventDefault(); // Prevenir el comportamiento predeterminado del enlace

        var id = $(this).data('id');

        var confirmacion = confirm("¿Seguro que deseas eliminar este registro de materiales?");

        if (confirmacion) {
            window.location.href = 'Controlador?accion=eliminar&id=' + id + '&tabla=materiales';
        }
    });
 $('.confirm-delete-proveedores').on('click', function(e) {
        e.preventDefault(); 

        var id = $(this).data('id');

        var confirmacion = confirm("¿Seguro que deseas eliminar este registro de proveedores?");

        if (confirmacion) {
            window.location.href = 'Controlador?accion=eliminar&id=' + id + '&tabla=proveedores';
        }
    });
   
   $('.confirm-delete-empleado').on('click', function(e) {
        e.preventDefault(); 

        var id = $(this).data('id');

        var confirmacion = confirm("¿Seguro que deseas eliminar este registro de usuarios?");

        if (confirmacion) {
            window.location.href = 'Controlador?accion=eliminar&id=' + id + '&tabla=empleado';
        }
    });
    
    $('.confirm-delete-inventario-productos').on('click', function(e) {
        e.preventDefault(); // Prevenir el comportamiento predeterminado del enlace

        var id = $(this).data('id');

        var confirmacion = confirm("¿Seguro que deseas eliminar este registro de inventario de productos?");

        if (confirmacion) {
            window.location.href = 'Controlador?accion=eliminar&id=' + id + '&tabla=inventario_productos';
        }
    });
    
   
    $('.confirm-delete-inventario-material').on('click', function(e) {
        e.preventDefault(); 

        var id = $(this).data('id');

        var confirmacion = confirm("¿Seguro que deseas eliminar este registro de inventario de material?");

        if (confirmacion) {
            window.location.href = 'Controlador?accion=eliminar&id=' + id + '&tabla=inventario_material';
        }
    });
    $('.confirm-delete-cliente').on('click', function(e) {
        e.preventDefault(); // Prevenir el comportamiento predeterminado del enlace

        var id = $(this).data('id');

        var confirmacion = confirm("¿Seguro que deseas eliminar este registro de clientes?");

        if (confirmacion) {
            window.location.href = 'Controlador?accion=eliminar&id=' + id + '&tabla=cliente';
        }
	});
	$('.confirm-delete-productos').on('click', function(e) {
        e.preventDefault(); // Prevenir el comportamiento predeterminado del enlace

        var id = $(this).data('id');

        var confirmacion = confirm("¿Seguro que deseas eliminar este registro de productos?");

        if (confirmacion) {
            window.location.href = 'Controlador?accion=eliminar&id=' + id + '&tabla=productos';
        }
    });
  

});