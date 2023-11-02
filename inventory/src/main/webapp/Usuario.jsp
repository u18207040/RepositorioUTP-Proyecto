<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ModeloDAO.UsuarioDAO" %>
<%@ page import="Modelo.Usuario" %>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/miestilo.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://kit.fontawesome.com/65c558f159.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style>
        .error {
            color: #f44336;
            font-size: 14px;
            font-weight: bold;
            position:relative;
           
           
        }
        .errorx {
            color: #f44336;
            font-size: 14px;
            font-weight: bold;
            position:relative;
            
           
           
        }
        .form-group {
            margin-bottom: 5px; 
        }
         td, th, h1,h5, .form-control, .lk {
    		font-family: 'Roboto Mono', monospace;
    
		}
    </style>
</head>
<body>
	<jsp:include page="Principal.jsp" />
	<div class="container-fluid">
		<div class="row">
			<div class="container col-xl-6 col-lg-6 col-md-12 col-sm-12 px-5" style="background: #ecf7e1">
				<div class="d-flex align-items-center justify-content-center pt-5 pb-3">
					<img src="img/user-interface.png" style="width: 80px"
						alt="Proveedor Imagen">
					<h2 class="ml-4">USUARIO</h2>
				</div>
				<form class="d-none d-lg-block" action="Controlador" method="POST" id="miFormularioUser">
					<div class="row" >
						<div class="col-md-3">
							<div class="form-group">
								<label>Nombre:</label> 
								<input class="form-control form-control-sm" type="text"
									placeholder="Ingrese nombre" id="nombre" name="txtNom" autofocus>
								<span id="nombreError" class="error"></span>
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group">
								<label>Cargo:</label> <select
									class="form-control form-control-sm" id="cargo" name="txtCargo">
									<option value="Administrador">Administrador</option>
									<option value="Usuario">Usuario</option>
								</select> <span id="cargoError" class="error"></span>
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group">
								<label>Telefono:</label> <input
									class="form-control form-control-sm" type="text"
									placeholder="Ingrese cargo" id="telefono" name="txtTelefono">
								<span id="telefonoError" class="error"></span>
							</div>
						</div>
						<div class="col-md-3 form-group">
							<label>Usuario: </label> <input
								class="form-control form-control-sm" type="text"
								placeholder="User" id="usuario" name="txtUsuario"><br>
							<span id="usuarioError" class="errorx"></span>
						</div>
					</div>
					<div class="row">
						
						<!-- comment -->
						<div class="col-md-3 form-group">
							<label>Dni: </label> 
							<input class="form-control form-control-sm" type="text" placeholder="Contraseña" id="dni" name="txtDni"><br>
							<span id="dniError" class="errorx"></span>
						</div>
						<div class="col-md-3 form-group">
                            <label>Correo:</label>
                            <input class="form-control form-control-sm" type="email" id="email" placeholder="@gmail.com" name="txtEmail" required><br>
                            <span id="emailError" class="error"></span>
                        </div>

						<div class="col-md-3 form-group">
							<br>
							<div>
							<!--<input class="btn btn-outline-success" type="submit" name="accion" value="AgregarProveedor" id="btnAgregar"><br>-->
							<button type="submit" class="btn btn-outline-info lk" name="accion" value="AgregarUsuario" id="btnAgregar">Guardar datos<i class="fa-solid fa-database mx-2"></i></button>
							
							</div>
						</div>
					</div>
				
				</form>
				<script src="js/usuario.js"></script>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12 col-xl-12 bg-white px-5">
				<div class="navbar my-5 text-center ">
					<form action="Controlador" method="POST" class="form-inline mx-auto d-flex">
						<input type="search" name="txtBuscar" placeholder="Search..." class="form-control w-120">
						<button type="submit" class="btn btn-outline-success lk" name="accion" value="BuscarUsuario"><i class="fa-solid fa-magnifying-glass mx-1"></i>Buscar</button>
						
					</form>
				</div>
				<div class="table-responsive">
				<table class="table table-sm table-striped table-bordered table-hover mb-5 ">
					<thead class="table-success">
						<tr>
							<th  class="text-center" >ID</th>
							<th  class="text-center" >Nombre</th>
							<th class="text-center">Usuario</th>
							<th class="text-center">Dni</th>
							<th class="text-center">Cargo</th>
							<th class="text-center">Permisos</th>
							<th class="text-center">Telefono</th>
							<th class="text-center">Correo</th>
							<th class="text-center">EnLinea</th>
							<th class="text-center">UltimoAcceso</th>
							<th class="text-center">HoraAccesso</th>
							<th class="text-center">Acciones</th>
						</tr>
					</thead>
					<%
					UsuarioDAO dao = new UsuarioDAO();
					List<Usuario>lista=dao.listar();
					List<Usuario> datos = (List<Usuario>) request.getAttribute("datos"); 
					
					%>
					<c:choose>
						<c:when test="${not empty datos}">
							<c:set var="lista" value="${datos}" />
						</c:when>
						<c:otherwise>
							<c:set var="lista" value="<%=lista%>" />
						</c:otherwise>
					</c:choose>

					<c:forEach var="usuario" items="${lista}">
						<tbody class="table-group-divider">
							<tr>
								<td class="font-weight-bold text-center">${usuario.id}</td>
								<td class="text-center">${usuario.nom}</td>
								<td class="text-center">${usuario.user}</td>
								<td class="text-center">${usuario.dni}</td>
								<td class="text-center">${usuario.estado}</td>
								<td class="text-center">${usuario.permisos}</td>
								<td class="text-center">${usuario.tel}</td>
								<td class="text-center">${usuario.email}</td>
								<td class="text-center">
									<c:choose>
										<c:when test="${usuario.varLinea == 1}">Sí</c:when>
										<c:otherwise>No</c:otherwise>
									</c:choose>
								</td>
								<td class="text-center">${usuario.ultimoAcceso}</td>
								<td class="text-center">${usuario.horaUltimoAcceso}</td>
								<td class="text-center">
										<div class="d-flex justify-content-center">
											<a href="#exampleModal${usuario.id}" class="btn btn-outline-dark" data-bs-toggle="modal" style="text-decoration: none; border: none"> 
												<i class="fa-solid fa-pen-to-square"></i>
											</a> 
											
											<button class="mx-1 btn btn-outline-danger confirm-delete-empleado" data-id="${usuario.id}" style="text-decoration: none; border: none">
										<i class="fa-solid fa-trash-can"></i>
									</button>
											

											<form method="post" action="Controlador">
												<input type="hidden" name="accion" value="enviarCorreo">
												<input type="hidden" name="usuarioId" value="${usuario.id}">
												<button class="btn btn-outline-warning" type="submit" style="text-decoration: none; border: none;">
													<i class="fa-solid fa-envelope"></i>
												</button>
											</form>
										</div>
								</td>
								</tr>
							<div class="modal fade" id="exampleModal${usuario.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
								<div class="modal-dialog modal-lg">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalLabel">Editar Usuario</h5>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body">
											<h1>Modificar Usuario</h1>
												<form id="myForm" action="Controlador" method="POST">
													<div class="row">
														<div class="col-md-6">
															<label>Id:</label> <input class="form-control"
																type="text" name="txtid" value="${usuario.id}" readonly>
															<label>Nombre:</label> <input class="form-control"
																type="text" name="txtNom" value="${usuario.nom}">
															<label>Permiso:</label> 
																<select class="form-control" name="txtPermiso">
																	<option value="ACTIVO" ${usuario.permisos == "ACTIVO" ? "selected" : ""}>ACTIVO</option>
																	<option value="BLOQUEADO" ${usuario.permisos == "BLOQUEADO" ? "selected" : ""}>BLOQUEADO</option>
																</select>
														</div>
														<div class="col-md-6">
															<label>Cargo:</label> <select class="form-control"
																name="txtCargo">
																<option value="Administrador"
																	${usuario.estado.equals("Administrador") ? "selected" : ""}>Administrador</option>
																<option value="Usuario"
																	${usuario.estado.equals("Usuario") ? "selected" : ""}>Usuario</option>
															</select> <label>Telefono:</label> <input class="form-control"
																type="text" name="txtTelefono" value="${usuario.tel}">
															<label>Usuario:</label> <input class="form-control"
																type="text" name="txtUsuario" value="${usuario.user}">
															<label>Dni:</label> <input class="form-control"
																type="text" name="txtDni" value="${usuario.dni}"><br>
														</div>
														<input type="hidden" name="txtid" value="${usuario.id}">
														<!--<input class="btn-primary" type="submit" name="accion" value="Actualizar"><br>-->
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-outline-secondary lk" data-bs-dismiss="modal">Cancelar<i class="fa-solid fa-ban mx-1"></i></button>
														<button type="submit" class="btn btn-outline-info lk" name="accion" value="ActualizarUsuario">Guardar cambios<i class="fa-solid fa-database mx-2"></i></button>
													</div>
												</form>
											</div>
									</div>
								</div>
							</div>
						</tbody>
					</c:forEach>
				</table>
				</div>
			</div>
		</div>
	</div>
	<%@include file="WEB-INF/jspf/foot.jspf" %>
	<script src="js/scriptdelete.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	
</body>
</html>