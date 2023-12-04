<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page import="Modelo.Usuario"%>
<%@page import="Modelo.Proveedor"%>
<%@page import="Modelo.Cliente"%>
<%@page import="Modelo.Material"%>
<%@page import="Modelo.Producto"%>
<%@page import="Modelo.Existencia"%>
<%@page import="ModeloDAO.GestionMaterial"%>
<%@page import="ModeloDAO.GestionProducto"%>
<%@page import="ModeloDAO.ExistenciasDAO"%>
<%@page import="java.util.List"%>
<%@include file="verificaSesion.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Existencia de Productos</title>
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
            
        }
        .form-group {
            margin-bottom: 5px; 
        }
         td, th, h5,h1, .form-control, .lk {
    		font-family: 'Roboto Mono', monospace;
    
		}
    </style>
<body>
        <jsp:include page="Principal.jsp" />   
        <div class="container-fluid">
           	<%
                GestionProducto daoe = new GestionProducto();
                List<Producto> listaProductos = daoe.listarP();
                List<Cliente> listaClientes = daoe.listarC();
            %>
            <div class="row"> 
              	<%
                    String rolUsuario = (String) session.getAttribute("rolUsuario");
                    if (rolUsuario != null && rolUsuario.equalsIgnoreCase("administrador")) {
                %>
                <div class="container col-xl-6 col-lg-6 col-md-12 col-sm-12 pt-5 px-5" style="background:#ecf7e1">
                    <div class="d-flex align-items-center justify-content-center">
                        <img src="img/inventario.png" style="width:80px" alt="Existencia Imagen">
                        <h2 class="ml-4">INVENTARIO DE PRODUCTO</h2>
                    </div>
                    <form class="py-3 d-none d-lg-block" action="ControladorGestionProducto" method="POST" id="miFormularioExistenciap">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>Producto</label>
                                    <select class="form-control form-control-sm" id="producto" name="txtProducto">
                                        <% 
                                            for (Producto producto : listaProductos) {
                                        %>
                                        <option value="<%= producto.getId() %>"><%= producto.getNombre() %></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                    <span id="productoError" class="error"></span>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>Cliente</label>
                                    <select class="form-control form-control-sm" id="cliente" name="txtCliente">
                                        <% 
                                            for (Cliente cliente : listaClientes) {
                                        %>
                                        <option value="<%= cliente.getId() %>"><%= cliente.getNombre() %></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                    <span id="clienteError" class="error"></span>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>Responsable:</label>
                                    <input class="form-control form-control-sm" type="text" id="responsable" name="txtResponsable" value="<%=varNombresMostrar%>" readonly>
                                    <span id="responsableError" class="error"></span>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>Fecha entrada:</label>
                                    <input class="form-control form-control-sm" type="date" id="date" name="txtDate">
                                    <span id="dateError" class="error"></span>
                                </div>
                            </div>
                        </div>
                         <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>Existencia</label>
                                    <input class="form-control form-control-sm" type="number" placeholder="0"  id="inicial" name="txtInicial">
                                    <span id="inicialError" class="error"></span>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>Estado:</label>
                                    <select class="form-control form-control-sm" id="estado" name="txtEstado">
                                    <option value="Ingreso reciente">Ingreso reciente</option>
                                    <option value="En proceso">En proceso</option>
                                    
                            </select>
                                <span id="estadoError" class="error"></span>   
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>P. Servicio:</label>
                                    <input class="form-control form-control-sm" type="text" placeholder="S/.Precio" id="precio" name="txtPrecio">
                                    <span id="precioError" class="error"></span>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <br>
                                <div>
                                    <!--<input class="btn btn-outline-info" type="submit" name="accion" value="AgregarExistencia" id="btnAgregar">-->
                                    <button type="submit" class="btn btn-outline-info lk" name="accion" id="btnAgregar" value="AgregarExistenciaProducto">Guardar<i class="fa-solid fa-database mx-2"></i></button>
                                </div>
                            </div>
                        </div>
                                    
                    </form>
                    <script src="js/scripexi.js"></script>
                </div>
                <%}%> 
                <div class="col-sm-12 col-md-12 col-lg-8 col-xl-12 bg-white px-5">
                    <div class="navbar mt-5 text-center ">
                        <form action="ControladorGestionProducto" method="POST" class="form-inline mx-auto d-flex" > 
                        <input type="search" name="txtBuscar" placeholder="Search..." class="form-control w-120">
                        <!--<input type="submit" name="accion" value="buscar" class="btn btn-outline-success">-->
                        <button type="submit" class="btn btn-outline-success lk" name="accion" value="BuscarExistenciaProducto"><i class="fa-solid fa-magnifying-glass mx-1"></i>Buscar</button>
                        </form>
                    </div>
                    <div class="table-responsive">
                 <form action="ControladorReporte" method="get">
    				<input type="hidden" name="tipoInforme" value="producto">
    				<button class="btn btn-outline-dark lk mb-2" type="submit">Generar Informe Producto <i class="fa-regular fa-file-pdf"></i></button>
				</form>
				<%
                    
                       if (rolUsuario != null && rolUsuario.equalsIgnoreCase("administrador")) {
                %>
				<form id="formularioCorreo" action="ControladorReporte" method="POST">
    				<input type="hidden" name="accion" value="enviarCorreoInactivo">
    				<button class="btn btn-outline-dark lk" type="submit" onclick="confirmarEnvio()">Enviar Clientes Inactivos <i class="fa-solid fa-envelope"></i></button>
				</form>
					<script>
						function confirmarEnvio() {
							var confirmacion = confirm("¿Estás seguro de que deseas enviar el correo?");
							if (confirmacion) {
								document.getElementById("formularioCorreo").submit();
							}
						}
					</script>
					<%
                       }			
				%>
                <table class="table table-sm table-striped table-bordered table-hover my-3">
                    <thead class="table-success">
                        <tr>
                            <th class="text-center">ID</th>
                            <th class="text-center">Producto</th>
                            <th class="text-center">Cliente</th>
                            <th class="text-center">Existencia</th>
                            <th class="text-center">Estado</th>
                            <th class="text-center">P.Servicio</th>
                            <th class="text-center">F.Entrada</th>
                            <th class="text-center">F.Salida</th>
                            <th class="text-center">Responsable</th>
                            <%
                                String rolUser = (String) session.getAttribute("rolUsuario");
                                if (rolUser != null && rolUser.equalsIgnoreCase("administrador")) {
                            %>
                            <th class="text-center">ACCIONES</th>
                            <%
                               }
                            %>  
                        </tr>
                    </thead>
                    <%
                    ExistenciasDAO dao=new ExistenciasDAO();
                    List<Existencia>list=dao.existenciam();
                    List<Existencia> datos = (List<Existencia>) request.getAttribute("datos"); 
                    if (datos != null) {
                    list = datos; 
                    }
                    for (int i = 0; i < list.size(); i++) {
                        Existencia ex = list.get(i);
                                
                    %>
                    <tbody>
                        <tr>
                            <td class="text-center"><%= ex.getIdInventario()%></td>
                            <td class="text-center"><%= ex.getNombreProducto()%></td>
                            <td class="text-center"><%= ex.getNombreCliente()%></td>
                            <td class="text-center"><%= ex.getExistenciaInicial()%></td><!-- comment -->
                            <td class="text-center"><%= ex.getEstado()%></td>
                            <td class="text-center"><%= ex.getPrecioCompra()%></td>
                            <td class="text-center"><%= ex.getFechaEntrada() %></td>
                            <td class="text-center"> <% if (ex.getFechaSalida() != null) { %>
                                <%= ex.getFechaSalida() %>
                                <% } %></td>
                            <td class="text-center"><%= ex.getIngresadoPor()%></td>
                            <%
                                String rol = (String) session.getAttribute("rolUsuario");
                                if (rol != null && rol.equalsIgnoreCase("administrador")) {
                            %>
                            <td class="text-center">
                            
                                <button type="button" class="btn btn-outline-dark" style="text-decoration: none; border: none" data-bs-toggle="modal" data-bs-target="#exampleModal<%= ex.getIdInventario() %>" <%= "Entregado".equals(ex.getEstado()) ? "disabled" : "" %>><i class="fa-solid fa-pen-to-square"></i></button>
                               <!-- <a class="btn btn-outline-danger confirm-delete-inventario-productos" data-id="">Eliminar</a>-->
								<button class="btn btn-outline-danger confirm-delete-inventario-productos" data-id="<%=ex.getIdInventario()%>" style="text-decoration: none; border: none">
										<i class="fa-solid fa-trash-can"></i>
									</button>

                            </td>
                            <%
                               }
                            %>  
                        </tr>
                        
                    <div class="modal fade" id="exampleModal<%=ex.getIdInventario()%>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg"> 
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Editar Existencia de Producto</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <h1>Modificar Existencia</h1>
                                    <form id="myForm" action="ControladorGestionProducto" method="POST">
                                        <div class="row"> 
                                            <div class="col-md-6"> 
                                                <label>IdInventario:</label>
                                                <input class="form-control" type="text" name="txtid" value="<%= ex.getIdInventario()%>" readonly><br>
                                                <label>Producto:</label>
                                                <select class="form-control" name="txtProducto">
                                                    <% 
                                                        for (Producto producto : listaProductos) {
                                                    %>
                                                    <option value="<%= producto.getId() %>" <% if (producto.getId() == ex.getIdProducto()) { %>selected<% } %>><%= producto.getNombre() %></option>
                                                    <%
                                                        }
                                                    %>
                                                </select><br>
                                                <label>Cliente:</label>
                                                <select class="form-control" name="txtCliente">
                                                    <% 
                                                        for (Cliente cliente : listaClientes) {
                                                    %>
                                                    <option value="<%= cliente.getId() %>" <% if (cliente.getId() == ex.getIdCliente()) { %>selected<% } %>><%= cliente.getNombre() %></option>
                                                    <%
                                                        }
                                                    %>
                                                </select><br>
                                            </div>
                                            <div class="col-md-6"> 
                                                <label>Existencia:</label>
                                                <input class="form-control" type="number" name="txtInicial" value="<%= ex.getExistenciaInicial()%>"><br>
                                                <label>Estado:</label>
                                                <select class="form-control" name="txtEstado">
                                                <option value="Recien Ingresado" <%= ex.getEstado().equals("Recien Ingresado") ? "selected" : "" %>>Ingreso reciente</option>
                                                <option value="En proceso" <%= ex.getEstado().equals("En proceso") ? "selected" : "" %>>En proceso</option>
                                                <option value="Entregado" <%= ex.getEstado().equals("Entregado") ? "selected" : "" %>>Entregado</option>
                                            </select>
                                                <label>Precio de servicio:</label>
                                                <input class="form-control" type="text" name="txtPrecio" value="<%= ex.getPrecioCompra()%>"><br>
                                            </div>
                                            <div class="col-md-6"> 
                                                <label>F. Entrada:</label>
                                                <input class="form-control" type="date" name="txtFecha" value="<%= ex.getFechaEntrada()%>"><br>
                                                <label>Responsable:</label>
                                                <input class="form-control" type="text" name="txtResponsable" value="<%=varNombresMostrar%>" readonly><br>
                                                
                                                <input type="hidden" name="txtid" value="<%= ex.getIdInventario()%>">
                                            </div>
                                        </div>
                                        <!--<input class="btn-primary" type="submit" name="accion" value="Actualizar"><br>-->
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-outline-secondary lk" data-bs-dismiss="modal">Cancelar<i class="fa-solid fa-ban mx-1"></i></button>
                                            <button type="submit" class="btn btn-outline-info lk" name="accion" value="ActualizarExistenciaProducto">Guardar cambios<i class="fa-solid fa-database mx-2"></i></button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%}%>
                    </tbody>
                </table> 
                    </div>
              
                </div>  
                 
            </div>
        </div>  
     	<script src="js/scriptdelete.js"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        
    </body>
</html>