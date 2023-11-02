<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/miestilo.css">
<script src="https://kit.fontawesome.com/65c558f159.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
</head>
<style>
	  td, th, h1,h5, .form-control, .lk {
    		font-family: 'Roboto Mono', monospace;
    
		}
</style>
<body>
	<div class="container d-flex justify-content-center align-items-center my-5" style="width:40%">
		<div class="row">
			<div
				class="col-xl-5 col-lg-4 col-md-4 col-sm-12 d-none d-sm-block px-0 h-100">
				<img src="img/FRONT.jpg" alt="Imagen" />
			</div>
			<div class="card-body col-sm-12 col-lg-8 col-md-8 col-xl-7 np py-5">
				<form class="form-sign" action="Controlador" id="miFormulariose"
					method="POST">
					<div class="form-group text-center">
						<img src="img/logito.png" alt="70" width="220" class="img-fluid" />
						<br>
						<h5 class="mb-5 mt-3">INGRESE AL SISTEMA</h5>
						<div id="mensajeBloqueo" class="lk" style="display: none; color: red;">
							<i class="fa-sharp fa-solid fa-triangle-exclamation mx-1"></i>Comunicarse con el Administrador, cuenta bloqueada.</div>
						<div id="mensajeError" class="lk" style="display: none; color: red;">
							<i class="fa-sharp fa-solid fa-triangle-exclamation mx-1"></i>Usuario o contraseña incorrectos. Verifique sus credenciales.</div>
					</div>
					<div class="form-group mb-4 px-5">
						<label>Usuario: </label> <input type="text" name="txtuser"
							id="user" placeholder="Nombre de usuario" class="form-control" required autofocus/>
						<span id="userError" class="error"></span>
					</div>
					<div class="px-5">
						<label>Password: </label> <input type="password"
							placeholder="Contraseña" id="pass" name="txtpass"
							class="form-control" required/> <span id="passError" class="error"></span>
					</div>
					<br>
					<div class="text-center">
						<button type="submit" name="accion" value="Ingresar"
							class="btn btn-outline-success lk">Ingresar<i class="fa-solid fa-user mx-1"></i></button>
							
					</div>
				</form>
			</div>
		</div>
	</div>
	<%
	Boolean mostrarMensajeBloqueo = (Boolean) request.getSession().getAttribute("mostrarMensajeBloqueo");
	%>
	<%
	if (mostrarMensajeBloqueo != null && mostrarMensajeBloqueo) {
	%>
	<script>
		document.getElementById("mensajeBloqueo").style.display = "block";
	</script>
	<%
	}
	%>
	<%
	Boolean mostrarMensajeError = (Boolean) request.getSession().getAttribute("mostrarMensajeError");
	%>
	<%
	if (mostrarMensajeError != null && mostrarMensajeError) {
	%>
	<script>
		document.getElementById("mensajeError").style.display = "block";
	</script>
	<%
	}
	%>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</html>