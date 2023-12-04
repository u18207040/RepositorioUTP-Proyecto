<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@page import="Modelo.Usuario"%>
<%@page import="Modelo.Proveedor"%>
<%@page import="Modelo.Material"%>
<%@page import="Modelo.Existencia"%>
<%@page import="ModeloDAO.ExistenciasDAO"%>
<%@page import="ModeloDAO.GestionMaterial"%>
<%@page import="java.util.List"%>
<%@include file="verificaSesion.jsp"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Existencia de Materiales</title>
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
         td, th, h1,h5, .form-control, .lk {
    		font-family: 'Roboto Mono', monospace;
    
		}
		

    </style>
 <body>
 		
        <jsp:include page="Principal.jsp" />   
        <div class="container-fluid">
            <%
                GestionMaterial daoe = new GestionMaterial();
                List<Material> listaMateriales = daoe.listarm();
                List<Proveedor> listaProveedores = daoe.listarp();
            %>
            <div class="row">
            	<%
                       String rolUsuario = (String) session.getAttribute("rolUsuario");
                       if (rolUsuario != null && rolUsuario.equalsIgnoreCase("administrador")) {
                %>

                <div class="container  col-xl-6 col-lg-6 col-md-12 col-sm-12 pt-5 px-5" style="background:#ecf7e1">
                    <div class="d-flex align-items-center justify-content-center">
                        <img src="img/gestion-de-materiales.png" style="width:80px" alt="Existencia Imagen">
                        <h2 class="ml-4">INVENTARIO DE MATERIAL</h2>
                    </div>
                    
                    <form class="py-3 d-none d-lg-block" action="ControladorGestionMaterial" method="POST" id="miFormularioExistencia">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>Material</label>
                                    <select class="form-control form-control-sm" id="material" name="txtMaterial">
                                        <% 
                                            for (Material material : listaMateriales) {
                                        %>
                                        <option value="<%= material.getId() %>"><%= material.getNombre() %></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                    <span id="materialError" class="error"></span>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>Proveedor</label>
                                    <select class="form-control form-control-sm" id="proveedor" name="txtProveedor">
                                        <% 
                                            for (Proveedor proveedor : listaProveedores) {
                                        %>
                                        <option value="<%= proveedor.getId() %>"><%= proveedor.getNombre() %></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                    <span id="proveedorError" class="error"></span>
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
                                    <label>E. Inicial:</label>
                                    <input class="form-control form-control-sm" type="number" placeholder="0" id="inicial" name="txtInicial">
                                    <span id="inicialError" class="error"></span>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>E. Final:</label>
                                    <input class="form-control form-control-sm" type="number" placeholder="0" id="final" name="txtFinal">
                                    <span id="finalError" class="error"></span>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>P. compra:</label>
                                    <input class="form-control form-control-sm" type="text" placeholder="S/. Precio" id="precio" name="txtPrecio">
                                    <span id="precioError" class="error"></span>
                                </div>
                            </div>
                             <div class="col-md-3">
                                <br>
                                <div>
                                    <!--<input class="btn btn-outline-info" type="submit" name="accion" value="AgregarExistencia" id="btnAgregar">-->
                                    <button type="submit" class="btn btn-outline-info lk" name="accion" id="btnAgregar" value="AgregarExistencia">Guardar<i class="fa-solid fa-database mx-2"></i></button>
                                </div>
                            </div>
                        </div>           
                        
                    </form>
                    <script src="js/scripexi.js"></script>
                </div>
                <%
                            }
                        %>   
                              
                <div class="col-sm-12 col-md-12 col-lg-8 col-xl-12 bg-white px-5">
                    <div class="navbar mt-5 text-center ">
                        <form action="ControladorGestionMaterial" method="POST" class="form-inline mx-auto d-flex" > 
                        <input type="search" name="txtBuscar" placeholder="Search..." class="form-control w-120">
                        <!--<input type="submit" name="accion" value="buscar" class="btn btn-outline-success">-->
                        <button type="submit" class="btn btn-outline-success lk" name="accion" value="BuscarExistencia"><i class="fa-solid fa-magnifying-glass mx-1"></i>Buscar</button>
                        </form>
                    </div>
                <div class="table-responsive">
				<form action="ControladorReporte" method="get">
    				<input type="hidden" name="tipoInforme" value="existencias">
    				<button class="btn btn-outline-dark lk my-2" type="submit">Generar Informe Existencias <i class="fa-regular fa-file-pdf"></i></button>
				</form>
				
                <table class="table table-sm table-striped table-bordered table-hover mb-5">
                    <thead class="table-success">
                        <tr>
                            <th class="text-center nx">IdInventario</th>
                            <th class="text-center">Material</th>
                            <th class="text-center">Proveedor</th>
                            <th class="text-center">Existencia</th>
                            <th class="text-center">ExistenciaFinal</th>
                            <th class="text-center">P.Compra</th>
                            <th class="text-center">F.Entrada</th><br>
                            <th class="text-center">Responsable</th>
                            <%
                                String rolUser = (String) session.getAttribute("rolUsuario");
                                if (rolUser != null && rolUser.equalsIgnoreCase("administrador")) {
                            %>
                            <th class="text-center">Acciones</th>
                            <%
                             }
                            %>  
                        </tr>
                    </thead>
                     <%
                    ExistenciasDAO dao=new ExistenciasDAO();
                    List<Existencia>list=dao.existencia();
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
                            <td class="text-center"><%= ex.getNombreMaterial()%></td>
                            <td class="text-center"><%= ex.getNombreProveedor()%></td>
                            <td class="text-center"><%= ex.getExistenciaInicial()%></td><!-- comment -->
                            <td class="text-center"><%= ex.getExistenciaFinal()%></td>
                            <td class="text-center"><%= ex.getPrecioCompra()%></td>
                            <td class="text-center"><%= ex.getFechaEntrada() %></td>
                            <td class="text-center"><%= ex.getIngresadoPor()%></td>
                            <%
                                String rol = (String) session.getAttribute("rolUsuario");
                                if (rol != null && rol.equalsIgnoreCase("administrador")) {
                            %>
								<td class="text-center">
									<!-- <button type="button" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#exampleModal"><i class="fa-solid fa-pen-to-square" style="color: #000000;"></i>Editar</button>
                                <button type="button" class="btn btn-outline-success btn-edit-cantidad" data-bs-toggle="modal" data-bs-target="#cantidadModal" data-id="">Cantidad</button>

                                <a class="btn btn-outline-danger confirm-delete-inventario-material" data-id="">Eliminar</a>-->

									<button type="button" class="btn btn-outline-dark" style="text-decoration: none; border: none" data-bs-toggle="modal" data-bs-target="#exampleModal<%=ex.getIdInventario()%>">
										<i class="fa-solid fa-pen-to-square"></i>
									</button>
									<button class="btn btn-outline-danger confirm-delete-inventario-material" data-id="<%=ex.getIdInventario()%>" style="text-decoration: none; border: none">
										<i class="fa-solid fa-trash-can"></i>
									</button>
									<button type="button" class="btn btn-outline-dark lk" style="text-decoration: none; border: none" data-bs-toggle="modal" data-bs-target="#cantidadModal<%=ex.getIdInventario()%>"
										data-id="<%=ex.getIdInventario()%>">
										<i class="fa-solid fa-truck-ramp-box"></i>
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
                                    <h5 class="modal-title" id="exampleModalLabel">Editar Existencia de Material</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <h1>Modificar Existencia</h1>
                                    <form id="myForm" action="ControladorGestionMaterial" method="POST">
                                        <div class="row"> 
                                            <div class="col-md-6"> 
                                                <label>IdInventario:</label>
                                                <input class="form-control" type="text" name="txtid" value="<%= ex.getIdInventario()%>" readonly><br>
                                                <label>Material:</label>
                                                <select class="form-control" name="txtMat">
                                                    <% 
                                                        for (Material material : listaMateriales) {
                                                    %>
                                                    <option value="<%= material.getId() %>" <% if (material.getId() == ex.getIdMaterial()) { %>selected<% } %>><%= material.getNombre() %></option>
                                                    <%
                                                        }
                                                    %>
                                                </select><br>
                                                <label>Proveedor:</label>
                                                <select class="form-control" name="txtProveedor">
                                                    <% 
                                                        for (Proveedor proveedor : listaProveedores) {
                                                    %>
                                                    <option value="<%= proveedor.getId() %>" <% if (proveedor.getId() == ex.getIdProveedor()) { %>selected<% } %>><%= proveedor.getNombre() %></option>
                                                    <%
                                                        }
                                                    %>
                                                </select><br>
                                            </div>
                                            <div class="col-md-6"> 
                                                <label>Existencia:</label>
                                                <input class="form-control" type="number" name="txtInicial" value="<%= ex.getExistenciaInicial()%>"><br>
                                                <label>ExistenciaFinal:</label>
                                                <input class="form-control" type="number" name="txtFinal" value="<%= ex.getExistenciaFinal()%>"><br>
                                                <label>Precio compra:</label>
                                                <input class="form-control" type="text" name="txtPrecio" value="<%= ex.getPrecioCompra()%>"><br>
                                            </div>
                                            <div class="col-md-6"> 
                                                <label>F. Entrada:</label>
                                                <input class="form-control" type="date" name="txtFecha" value="<%= ex.getFechaEntrada()%>"><br>
                                                <label>Responsable:</label>
                                                <input class="form-control form-control-sm" type="text" id="responsable" name="txtResponsable" value="<%=varNombresMostrar%>" readonly><br>
                                                <input type="hidden" name="txtid" value="<%= ex.getIdInventario()%>">
                                            </div>
                                        </div>
                                        <!--<input class="btn-primary" type="submit" name="accion" value="Actualizar"><br>-->
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-outline-secondary lk" data-bs-dismiss="modal">Cancelar<i class="fa-solid fa-ban mx-1"></i></button>
                                            <button type="submit" class="btn btn-outline-info lk" name="accion" value="ActualizarExistencia">Guardar cambios<i class="fa-solid fa-database mx-2"></i></button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal fade" id="cantidadModal<%=ex.getIdInventario()%>" tabindex="-1" aria-labelledby="cantidadModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="cantidadModalLabel">Ingreso/Salida de Existencia</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <form id="cantidadForm<%=ex.getIdInventario()%>" method="POST" action="ControladorGestionMaterial">
                                        IdInventario:<br>
                                        <input class="form-control" type="text" name="txtid" value="<%= ex.getIdInventario()%>" readonly><br>
                                        <div class="mb-3">
                                            <label for="tipo" class="form-label">Tipo (Ingreso/Salida)</label>
                                            <select class="form-control" id="tipo" name="txtTipoMovimiento">
                                                <option value="Ingreso">Ingreso</option>
                                                <option value="Salida">Salida</option>
                                            </select>
                                        </div>
                                        <div class="mb-3">
                                            <label for="cantidad" class="form-label">Cantidad</label>
                                            <input type="number" placeholder="0" class="form-control" id="cantidad" name="txtCantidad" required>
                                            
                                            
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-outline-secondary lk" data-bs-dismiss="modal">Cancelar<i class="fa-solid fa-ban mx-1"></i></button>
                                            <button type="submit" class="btn btn-outline-info lk" form="cantidadForm<%=ex.getIdInventario()%>" name="accion" value="EditarCantidad">Guardar<i class="fa-solid fa-database mx-2"></i></button>
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