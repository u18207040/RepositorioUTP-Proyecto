<%@page import="Modelo.Usuario"%>
<%@include file="verificaSesion.jsp"%>
		
<style>
.navbar-toggler-icon {
	background-image:
		url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 30 30'%3e%3cpath stroke='blue' stroke-linecap='round' stroke-miterlimit='10' stroke-width='2' d='M4 7h22M4 15h22M4 23h22'/%3e%3c/svg%3e");
}

.navbar-toggler {
	border: 2px solid blue;
}

.navbar-toggler:focus {
	box-shadow: none;
}

html, body {
	height: 100%;
	margin: 0;
	padding: 0;
}
 li {
    		font-family: 'Roboto Mono', monospace;
    
		}
</style>

<header>
	<nav class="navbar navbar-expand-lg np">
		<div class="container-fluid">
			<img src="img/logito.png" width="150">
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
				aria-controls="navbarNavAltMarkup" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
				<ul class="navbar-nav">
					<li class="nav-item"><a
						style="margin-left: 50px; border: none"
						class="btn btn-outline-info" href="Fronter.jsp">Inicio</a></li>
					<%
                        String rolUsuario = (String) session.getAttribute("rolUsuario");
                        if (rolUsuario != null && rolUsuario.equalsIgnoreCase("administrador")) {
                            %>
					<li class="nav-item">
						<div class="btn-group">
							<button style="margin-left: 50px; border: none" type="button"
								class="btn btn-outline-info dropdown-toggle"
								data-bs-toggle="dropdown" aria-expanded="false">
								Gestion Productos</button>
							<ul style="margin-left: 50px" class="dropdown-menu ">
								<li><a class="dropdown-item "
									href="Controlador?menu=Producto&accion=producto">Producto</a></li>
								<li><hr class="dropdown-divider"></li>
								<li><a class="dropdown-item"
									href="Controlador?menu=Producto&accion=cliente">Cliente</a></li>
							</ul>
						</div>
					</li>
					<li class="nav-item">
						<div class="btn-group">
							<button style="margin-left: 50px; border: none" type="button"
								class="btn btn-outline-info dropdown-toggle"
								data-bs-toggle="dropdown" aria-expanded="false">
								Gestion Materiales</button>
							<ul style="margin-left: 50px" class="dropdown-menu ">
								<li><a class="dropdown-item "
									href="Controlador?menu=Material&accion=Mat">Material</a></li>
								<li><hr class="dropdown-divider"></li>
								<li><a class="dropdown-item"
									href="Controlador?menu=Material&accion=pro">Proveedor</a></li>
							</ul>
						</div>
					</li>
					<li class="nav-item">
						<div class="btn-group">
							<button style="margin-left: 50px; border: none" type="button"
								class="btn btn-outline-info dropdown-toggle"
								data-bs-toggle="dropdown" aria-expanded="false">
								Gestion de Existencias</button>
							<ul style="margin-left: 50px" class="dropdown-menu ">
								<li><a class="dropdown-item "
									href="Controlador?menu=Material&accion=existenciaMaterial">Existencia
										de Materiales</a></li>
								<li><hr class="dropdown-divider"></li>
								<li><a class="dropdown-item"
									href="Controlador?menu=Producto&accion=existenciaProducto">Existencia
										de Productos</a></li>
							</ul>
						</div>
					</li>
					<li class="nav-item"><a
						style="margin-left: 50px; border: none"
						class="btn btn-outline-info"
						href="Controlador?menu=Usuario&accion=usuario">Gestión de
							Usuarios</a></li>
					<%
                        }
                        %>

				</ul>
			</div>
		</div>
		<div class="d-none d-lg-block text-center">
			<img src="img/persona.png" width="100"> 
			<label style="color: #80876c">
				<P ALIGN="right"> Bienvenido, <%=varNombresMostrar%>
			</label>
		</div>
		<div>

			<a href="sesionCerrar.jsp" class="btn btn-link m-1"> 
			<img src="img/salida.png" width="50">
			</a>

		</div>

	</nav>
</header>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
 