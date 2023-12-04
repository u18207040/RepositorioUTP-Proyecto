<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ModeloDAO.GestionProducto" %>
<%@ page import="Modelo.Cliente" %>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Clientes</title>
 <link rel="stylesheet" href="css/miestilo.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://kit.fontawesome.com/65c558f159.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
</head>
<style>
        .error {
            color: #f44336;
            font-size: 14px;
            font-weight: bold;
            position:relative;
            top: -20px;
        }
        .form-group {
            margin-bottom: 5px; 
        }
         td, th, h1,h5,.form-control, .lk {
    		font-family: 'Roboto Mono', monospace;
    
		}
    </style>
<body>
        <jsp:include page="Principal.jsp" />   
        <div class="container-fluid">
            <div class="row mb-0" > 
                <div class="col-xl-3 col-sm-12 col-md-12 col-lg-4 pt-5 px-5 " style="background:#ecf7e1">
                    <div class="d-flex align-items-center justify-content-center" >
                        <img src="img/customer.png" style="width:80px" alt="Proveedor Imagen">
                        <h2 class="mx-4">CLIENTE</h2>
                    </div>
                    <form class="py-5 d-none d-lg-block" action="ControladorGestionProducto" method="POST" id="miFormulario" >
                        <div class="form-group">
                            <label>Nombre:</label>
                            <input class="form-control form-control-sm" type="text" placeholder="Ingrese nombre"  id="nombre" name="txtNom" autofocus ><br>
                            <span id="nombreError" class="error"></span>
                        </div>
                        
                        <div class="form-group">
                            <label>Correo:</label>
                            <input class="form-control form-control-sm" type="email" id="email" placeholder="@gmail.com" name="txtCorreo"><br>
                            <span id="emailError" class="error"></span>
                        </div>
                        <div class="form-group">
                            <label>Telefono:</label>
                            <input class="form-control form-control-sm" type="text" id="telefono" placeholder="Ingrese telefono" name="txtTel"><br>
                            <span id="telefonoError" class="error"></span>
                        </div>
                        <div class="form-group">
                            <label>Direccion:</label>
                            <input class="form-control form-control-sm" type="text" id="direccion" placeholder="Ingrese direccion"  name="txtDirec"><br>
                            <span id="direccionError" class="error"></span>
                        </div>
                        <div class="text-center">
                            <!--<input class="btn btn-outline-success" type="submit" name="accion" value="AgregarProveedor" id="btnAgregar"><br>-->
                            <button type="submit" class="btn btn-outline-info lk" name="accion" value="AgregarCliente" id="btnAgregar">Guardar datos<i class="fa-solid fa-database mx-2"></i></button>
                        </div>
                    </form>
                   <script src="js/scrip.js"></script>
                </div>
                <div class="col-sm-12 col-md-12 col-lg-8 col-xl-9 bg-white px-5">
                    <div class="navbar mt-5 text-center ">
                        <form  action="ControladorGestionProducto" method="POST" class="form-inline mx-auto d-flex" > 
                        <input type="search" name="txtBuscar" placeholder="Search..." class="form-control w-120">
                        <!--<input type="submit" name="accion" value="buscar" class="btn btn-outline-success">-->
                        <button type="submit" class="btn btn-outline-success lk" name="accion" value="BuscarCliente"><i class="fa-solid fa-magnifying-glass mx-1"></i>Buscar</button>
                        </form>
                    </div>
                <table class="table table-striped table-bordered table-hover mt-5">
                    <thead class="table-success">
                        <tr>
                            <th class="text-center">ID</th>
                            <th class="text-center">Nombre</th>
                            
                            <th class="text-center">Correo</th>
                            <th class="text-center">Telefono</th>
                            <th class="text-center">Direccion</th><!-- comment -->
                            <th class="text-center">Acciones</th>
                        </tr>
                    </thead>
                    <%
					GestionProducto dao=new GestionProducto();
                    List<Cliente>lista=dao.listarC();
                    List<Cliente> datos = (List<Cliente>) request.getAttribute("datos"); 

                    %>
                    <c:choose>
						<c:when test="${not empty datos}">
							<c:set var="lista" value="${datos}" />
						</c:when>
						<c:otherwise>
							<c:set var="lista" value="<%=lista%>" />
						</c:otherwise>
					</c:choose>
                    <c:forEach var="cliente" items="${lista}">
                    <tbody class="table-group-divider">
                        <tr>
                            <td class="text-center">${cliente.id}</td>
                            <td class="text-center">${cliente.nombre}</td>
                            
                            <td class="text-center">${cliente.correo}</td>
                            <td class="text-center">${cliente.telefono}</td>
                            <td class="text-center">${cliente.direccion}</td>
                            <td class="text-center">
                                
                                <a href="#exampleModal${cliente.id}"  class="btn btn-outline-dark" data-bs-toggle="modal" style="text-decoration: none; border: none"> 
												<i class="fa-solid fa-pen-to-square"></i>
											</a> 
                                
                               	
								<button class="btn btn-outline-danger confirm-delete-cliente" data-id="${cliente.id}" style="text-decoration: none; border: none">
										<i class="fa-solid fa-trash-can"></i>
									</button>
                                 
                            </td>
                        </tr>
                        
                        <!-- Modal de edición -->
                        <div class="modal fade" id="exampleModal${cliente.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Editar Cliente</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <h1>Modificar Cliente</h1>
                                        <form id="myForm" action="ControladorGestionProducto" method="POST">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <label>Id:</label>
                                                    <input class="form-control" type="text" name="txtid" value="${cliente.id}" readonly><br>
                                                    <label>Nombre:</label>
                                                    <input class="form-control" type="text" name="txtNom" value="${cliente.nombre}"><br>
                                                </div>
                                                <div class="col-md-6">
                                                    <label>Correo:</label>
                                                    <input class="form-control" type="email" name="txtCorreo" value="${cliente.correo}"><br>
                                                    <label>Telefono:</label>
                                                    <input class="form-control" type="text" name="txtTel" value="${cliente.telefono}"><br>
                                                    <label>Direccion:</label>
                                                    <input class="form-control" type="text" name="txtDirec" value="${cliente.direccion}"><br>
                                                </div>
                                                <input type="hidden" name="txtid" value="">
                                                <!--<input class="btn-primary" type="submit" name="accion" value="Actualizar"><br>-->
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-outline-secondary lk" data-bs-dismiss="modal">Cancelar<i class="fa-solid fa-ban mx-1"></i></button>
                                                <button type="submit" class="btn btn-outline-info lk" name="accion" value="ActualizarCliente">Guardar cambios<i class="fa-solid fa-database mx-2"></i></button>
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
        
        <%@include file="WEB-INF/jspf/foot.jspf" %>  
        <script src="js/scriptdelete.js"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        
    </body>
</html>