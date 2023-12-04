<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pagina Principal</title>
<link rel="stylesheet" href="css/miestilo.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/65c558f159.js" ></script>
<link href="https://fonts.googleapis.com/css2?family=Kanit:ital,wght@0,300;1,900&family=Poppins:wght@400;600;800&family=Source+Sans+3:ital,wght@0,200;0,500;0,600;1,300;1,400&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
<style>
    body {
  background-image: url('img/Marco.jpg');
  background-repeat: no-repeat;
  background-size: cover;
  background-position:center center;
  background-color: rgba(255, 255, 255, 0.5);
  
}</style>
</head>
<body>
<%@include file="Principal.jsp" %>

<div class="container text-center mt-5 pt-5">
    <div class="row my-5 align-items-center">
        <div class="col-lg-6 col-md-6 col-sm-12 col-12 mt-5">
            <div>
                <h2>MATERIAL</h2>
                <a href="Controlador?menu=Material&accion=existenciaMaterial">
                    <img src="img/maquina.png" style="width:50%">
                </a>
            </div>
        </div>
        <div class="col-lg-6 col-md-6 col-sm-12 col-12 mt-5">
            <div>
                <h2>PRODUCTO</h2>
                <a href="Controlador?menu=Producto&accion=existenciaProducto">
                    <img src="img/ropa.png" style="width:50%">
                </a>
            </div>
        </div>
        
    </div>

</div>
</body>
</html>