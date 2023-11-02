
<%@ page import="Conexion.Conexion" %>
<%
	String varCodigo = (String) session.getAttribute("user");
	Conexion conexionDB = new Conexion();
	conexionDB.actualizarEnLineaUsuario(varCodigo, 0);
    session.invalidate();
    response.sendRedirect("index.jsp");
%>