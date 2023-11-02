<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ModeloDAO.GestionProducto" %>
<%@ page import="Modelo.Producto" %>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/miestilo.css">
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 <script src="https://kit.fontawesome.com/65c558f159.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
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
         td, th, .form-control,h5,h1, .lk {
    		font-family: 'Roboto Mono', monospace;
    
		}
    </style>
</head>
<body >
        <jsp:include page="Principal.jsp" />  
        <div class="container-fluid">
            <div class="row "> 
                <div class="col-xl-3 col-sm-12 col-md-12 col-lg-4 pt-5 px-5 " style="background:#ecf7e1">
                    <div class="d-flex align-items-center justify-content-center">
                        <img src="img/estampado-textil.png" style="width:80px" alt="Material Imagen">
                        <h2 class="mx-4">PRODUCTO</h2>
                    </div>
                    <form class="py-5 d-none d-lg-block" action="ControladorGestionProducto" id="miFormulariop" method="POST">
                        <div class="form-group">
                            <label>Tipo de acabado:</label>
                            <select class="form-control form-control-sm" id="tipo" name="txtTipo">
                                <option value="Estampado">Estampado</option>
                                <option value="Bordado">Bordado</option>
                                <option value="Limpieza">Limpieza</option>
                                <option value="Etiquetado">Etiquetado</option>
                                <option value="Empaquetado">Empaquetado</option>
                                <option value="Acabado General">Acabado General</option>
                            </select>
                            <span id="tipoError" class="error"></span>
                        </div>
                        <div class="row">
							<div class="col-md-6 form-group">
								<label>Nombre:</label> 
								<select class="form-control form-control-sm" id="nombre" name="txtNombre">
									<option value="Camisetas">Camisetas</option>
									<option value="Camisas">Camisas</option>
									<option value="Vestidos">Vestidos</option>
									<option value="Faldas">Faldas</option>
									<option value="Pantalones">Pantalones</option>
									<option value="Short">Short</option>
									<option value="Chaquetas">Chaquetas</option>
									<option value="Casacas">Casacas</option>
									<option value="Chompas">Chompas</option>
									<option value="Polos">Polos</option>
								</select>
							</div>
							<div class="col-md-6 form-group">
                            	<label>Modelo: </label> 
                            	<select class="form-control form-control-sm" id="modelo" name="txtModelo">
									<option value="Cuero">Cuero</option>
									<option value="Blazer">Blazer</option>
									<option value="Clásico">Clásico</option>
									<option value="Jogger">Jogger</option>
									<option value="Pitillo">Pitillo</option>
									<option value="Deportivo">Deportivo</option>
									<option value="Leggings">Leggings</option>
									<option value="Mini">Mini</option>
									<option value="Oversize">Oversize</option>
									<option value="Recto">Recto</option>
									<option value="Manga larga">Manga larga</option>
									<option value="Manga corta">Manga corta</option>
									<option value="Sin mangas">Sin mangas</option>
									<option value="Drill">Drill</option>
								</select>
							</div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 form-group">
                                <label>Talla: </label>
                                <input class="form-control form-control-sm" type="text" id="talla" name="txtTalla"><br>
                                <span id="tallaError" class="error"></span>
                            </div><!-- comment -->
                        	<div class="col-md-6 form-group">
                            	<label>Genero: </label>
                            	<select class="form-control form-control-sm" id="sexo" name="txtGenero">
        							<option value="Femenino">Femenino</option>
       								<option value="Masculino">Masculino</option>
        							<option value="Unisex">Unisex</option>
    							</select>
                            	<span id="GeneroError" class="error"></span>
                        	</div>
                        </div>
                        <div class="form-group">
                            <label>Descripcion: </label>
                            <input class="form-control form-control-sm" type="text" id="descripcion" name="txtDescripcion"><br>
                            <span id="descripcionError" class="error"></span>
                        </div>
                        <div class="text-center">
                            <!--<input class="btn btn-outline-success" type="submit" name="accion" value="AgregarMaterial"><br>-->
                            <button type="submit" class="btn btn-outline-info lk" name="accion" value="AgregarProducto">Guardar datos<i class="fa-solid fa-database mx-2"></i></button>
                        </div>
                    </form>
                    <script src="js/scripmat.js"></script>
                </div>
                <div class="col-sm-12 col-md-12 col-lg-8 col-xl-9 bg-white px-5">
                    <div class="navbar mt-5 text-center ">
                        <form action="ControladorGestionProducto" method="POST" class="form-inline mx-auto d-flex" > 
                            <input type="search" name="txtBuscar" placeholder="Search..." class="form-control w-120">
                            <!--<input type="submit" name="accion" value="Buscar Material" class="btn btn-outline-success"> -->
                            <button type="submit" class="btn btn-outline-success lk" name="accion" value="BuscarProducto"><i class="fa-solid fa-magnifying-glass mx-1"></i>Buscar</button>
                        </form>
                    </div>
                    <table class="table table-striped table-bordered  table-hover mt-5 mb-5">
                    <thead class="table-success">
                        <tr>
                            <th class="text-center">ID</th>
                            <th class="text-center">Tipo de acabado</th>
                            <th class="text-center">Nombre</th>
                            <th class="text-center">Descripcion</th>
                            <th class="text-center">Talla</th>
                            <th class="text-center">Modelo</th>
                            <th class="text-center">Genero</th>
                            <th class="text-center">Acciones</th>
                        </tr>
                    </thead>
                    <%
                    GestionProducto dao=new GestionProducto();
                    List<Producto>lista=dao.listarP();
                    List<Producto> datos = (List<Producto>) request.getAttribute("datos"); 

                    %>
                    <c:choose>
						<c:when test="${not empty datos}">
							<c:set var="lista" value="${datos}" />
						</c:when>
						<c:otherwise>
							<c:set var="lista" value="<%=lista%>" />
						</c:otherwise>
					</c:choose>
					<c:forEach var="producto" items="${lista}">
                    
                    <tbody>
                        <tr>
                            <td class="text-center">${producto.id}</td>
                            <td class="text-center">${producto.tipo}</td>
                            <td class="text-center">${producto.nombre}</td>
                            <td class="text-center">${producto.descripcion}</td>
                            <td class="text-center">${producto.talla}</td>
                            <td class="text-center">${producto.modelo}</td>
                            <td class="text-center">${producto.genero}</td>
                            <td class="text-center">
                                
                                
                                <!-- <button type="button" class="btn btn-outline-primary my-1" data-bs-toggle="modal" data-bs-target="#exampleModal${producto.id}">Editar</button>
                                <a class="btn btn-outline-danger confirm-delete-productos" data-id="${producto.id}">Eliminar</a>-->
                                <a href="#exampleModal${producto.id}" class="btn btn-outline-dark" data-bs-toggle="modal" style="text-decoration: none; border: none"> 
												<i class="fa-solid fa-pen-to-square"></i>
											</a> 
                                
                               
								<button class="btn btn-outline-danger confirm-delete-productos" data-id="${producto.id}" style="text-decoration: none; border: none">
										<i class="fa-solid fa-trash-can"></i>
									</button>
                              

                            </td>
                        </tr>

                        <div class="modal fade" id="exampleModal${producto.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog ">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Editar Producto</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <h1>Modificar Producto</h1>
                                        <form id="myForm" action="ControladorGestionProducto" method="POST">
                                            <label>Id:</label>
                                            <input class="form-control" type="text" name="txtid" value="${producto.id}" readonly><br>
                                            <label>Tipo de Acabado:</label>
                                            <select class="form-control" name="txtTipo">
                                                <option value="Estampado"${producto.tipo.equals("Estampado") ? "selected" : "" }>Estampado</option>
                                                <option value="Bordado" ${producto.tipo.equals("Bordado") ? "selected" : "" }>Bordado</option>
                                                <option value="Limpieza" ${producto.tipo.equals("Limpieza") ? "selected" : "" }>Limpieza</option>
                                                <option value="Etiquetado" ${producto.tipo.equals("Etiquetado") ? "selected" : "" }>Etiquetado</option>
                                                <option value="Empaquetado" ${producto.tipo.equals("Empaquetado") ? "selected" : "" }>Empaquetado</option>
                                                <option value="Acabado General" ${producto.tipo.equals("Acabado General") ? "selected" : "" }>Acabado General</option>
                                                
                                            </select>
                                            <label>Nombre:</label>
                                            <select
													class="form-control" name="txtNombre">
													<option value="Camisetas"
														${producto.nombre.equals("Camisetas") ? "selected" : ""}>Camisetas</option>
													<option value="Vestidos"
														${producto.nombre.equals("Vestidos") ? "selected" : ""}>Vestidos</option>
													<option value="Faldas"
														${producto.nombre.equals("Faldas") ? "selected" : ""}>Faldas</option>
													<option value="Pantalones"
														${producto.nombre.equals("Pantalones") ? "selected" : ""}>Pantalones</option>
													<option value="Short"
														${producto.nombre.equals("Short") ? "selected" : ""}>Short</option>
													<option value="Chaquetas"
														${producto.nombre.equals("Chaquetas") ? "selected" : ""}>Chaquetas</option>
													<option value="Casacas"
														${producto.nombre.equals("Casacas") ? "selected" : ""}>Casacas</option>
													<option value="Chompas"
														${producto.nombre.equals("Chompas") ? "selected" : ""}>Chompas</option>
													<option value="Polos"
														${producto.nombre.equals("Polos") ? "selected" : ""}>Polos</option>
											</select>
											
                                            <label>Talla:</label>
                                            <input class="form-control" type="text" name="txtTalla" value="${producto.talla}"><!-- comment -->
                                            <label>Modelo:</label> 
                                            	<select class="form-control" name="txtModelo">
													<option value="Cuero"
														${producto.modelo.equals("Cuero") ? "selected" : ""}>Cuero</option>
													<option value="Blazer"
														${producto.modelo.equals("Blazer") ? "selected" : ""}>Blazer</option>
													<option value="Clásico"
														${producto.modelo.equals("Clásico") ? "selected" : ""}>Clásico</option>
													<option value="Jogger"
														${producto.modelo.equals("Jogger") ? "selected" : ""}>Jogger</option>
													<option value="Pitillo"
														${producto.modelo.equals("Pitillo") ? "selected" : ""}>Pitillo</option>
													<option value="Deportivo"
														${producto.modelo.equals("Deportivo") ? "selected" : ""}>Deportivo</option>
													<option value="Leggings"
														${producto.modelo.equals("Leggings") ? "selected" : ""}>Leggings</option>
													<option value="Mini"
														${producto.modelo.equals("Mini") ? "selected" : ""}>Mini</option>
													<option value="Oversize"
														${producto.modelo.equals("Oversize") ? "selected" : ""}>Oversize</option>
													<option value="Recto"
														${producto.modelo.equals("Recto") ? "selected" : ""}>Recto</option>
													<option value="Manga larga"
														${producto.modelo.equals("Manga larga") ? "selected" : ""}>Manga
														larga</option>
													<option value="Manga corta"
														${producto.modelo.equals("Manga corta") ? "selected" : ""}>Manga
														corta</option>
													<option value="Sin mangas"
														${producto.modelo.equals("Sin mangas") ? "selected" : ""}>Sin
														mangas</option>
													<option value="Drill"
														${producto.modelo.equals("Drill") ? "selected" : ""}>Drill</option>
												</select>
											<label>Genero:</label>
                                            	<select class="form-control form-control-sm" id="sexo" name="txtGenero">
        											<option value="Femenino" ${producto.genero.equals("Femenino") ? "selected" : "" }>Femenino</option>
        											<option value="Masculino" ${producto.genero.equals("Masculino") ? "selected" : "" }>Masculino</option>
        											<option value="Unisex" ${producto.genero.equals("Unisex") ? "selected" : "" }>Unisex</option>
    											</select>
    										<label>Descripcion:</label>
                                            <input class="form-control" type="text" name="txtDescripcion" value="${producto.descripcion}"><br>
                                            <input type="hidden" name="txtid" value="">
                                            <!--<input class="btn-primary" type="submit" name="accion" value="Actualizar"><br>-->
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-outline-secondary lk" data-bs-dismiss="modal">Cancelar<i class="fa-solid fa-ban mx-1"></i></button>
                                                <button type="submit" class="btn btn-outline-info lk" name="accion" value="ActualizarProducto">Guardar cambios<i class="fa-solid fa-database mx-2"></i></button>
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
        <jsp:include page="footer.jsp" />    
        <script src="js/scriptdelete.js"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        

    </body>
</html>