package Controlador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import Modelo.Cliente;
import Modelo.Existencia;
import Modelo.Producto;
import ModeloDAO.ExistenciasDAO;
import ModeloDAO.GestionProducto;


public class ControladorGestionProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String producto="Producto.jsp";
	String cliente="Cliente.jsp";
	String existenciaProducto="ExistenciaProducto.jsp";
	Producto p=new Producto();
	Cliente c=new Cliente();
	GestionProducto dao=new GestionProducto();
	ExistenciasDAO dax=new ExistenciasDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorGestionProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String acceso = "";
		String action = request.getParameter("accion");
		 if (action.equalsIgnoreCase("AgregarProducto")) {
	            String tipo = request.getParameter("txtTipo");
	            String nom = request.getParameter("txtNombre");
	            String desc = request.getParameter("txtDescripcion");
	            String talla= request.getParameter("txtTalla");
	            String modelo=request.getParameter("txtModelo");
	            String genero=request.getParameter("txtGenero");
	            p.setTipo(tipo);
	            p.setNombre(nom);
	            p.setDescripcion(desc);
	            p.setTalla(talla);
	            p.setModelo(modelo);
	            p.setGenero(genero);
	            dao.add(p);
	            response.sendRedirect(request.getContextPath() + "/Producto.jsp");
	            return;
	        } else if (action.equalsIgnoreCase("BuscarProducto")) {
	            String dato = request.getParameter("txtBuscar");
	            if (dato != null && !dato.isEmpty()) {
	                List<Producto> lista = dao.buscarP(dato);
	                request.setAttribute("datos", lista);
	                System.out.println(lista);
	            } else {
	                //List<Material> list = dao.listarm();
	                //request.setAttribute("datos", list);
	                System.out.println("No encontrado....Error");
	            }
	            acceso = this.producto;

	        } 
	        else if (action.equalsIgnoreCase("ActualizarProducto")) {
	            int id = Integer.parseInt(request.getParameter("txtid"));
	            String tipo = request.getParameter("txtTipo");
	            String nom = request.getParameter("txtNombre");
	            String desc = request.getParameter("txtDescripcion");
	            String talla = request.getParameter("txtTalla");
	            String modelo = request.getParameter("txtModelo");
	            String genero=request.getParameter("txtGenero");
	            p.setId(id);
	            p.setTipo(tipo);
	            p.setNombre(nom);
	            p.setDescripcion(desc);
	            p.setTalla(talla);
	            p.setModelo(modelo);
	            p.setGenero(genero);
	            dao.edit(p);
	            response.sendRedirect(request.getContextPath() + "/Producto.jsp");
	            return;
	        }
	        else if (action.equalsIgnoreCase("AgregarCliente")) {
	            String nom = request.getParameter("txtNom");
	            String correo = request.getParameter("txtCorreo");
	            String telefono = request.getParameter("txtTel");
	            String direccion = request.getParameter("txtDirec");
	            c.setNombre(nom);
	            c.setCorreo(correo);
	            c.setTelefono(telefono);
	            c.setDireccion(direccion);
	            dao.add(c);
	            response.sendRedirect(request.getContextPath() + "/Cliente.jsp");
	            return;

	        }else if (action.equalsIgnoreCase("ActualizarCliente")) {
	            int id = Integer.parseInt(request.getParameter("txtid"));
	            String nombre = request.getParameter("txtNom");
	            String correo = request.getParameter("txtCorreo");
	            String telefono = request.getParameter("txtTel");
	            String direccion = request.getParameter("txtDirec");
	            c.setId(id);
	            c.setNombre(nombre);
	            c.setCorreo(correo);
	            c.setTelefono(telefono);
	            c.setDireccion(direccion);
	            dao.edit(c);
	            response.sendRedirect(request.getContextPath() + "/Cliente.jsp");
	            return;
	        }else if (action.equalsIgnoreCase("BuscarCliente")) {
	            String dato = request.getParameter("txtBuscar");
	            if (dato != null && !dato.isEmpty()) {
	                List<Cliente> lista = dao.buscarC(dato);
	                request.setAttribute("datos", lista);
	            } else {
	                //List<Material> list = dao.listarm();
	                //request.setAttribute("datos", list);

	            }
	            acceso = this.cliente;
	            //request.getRequestDispatcher("Cliente.jsp").forward(request, response);

	        } else if (action.equalsIgnoreCase("AgregarExistenciaProducto")) {
	            int existenciaInicial = Integer.parseInt(request.getParameter("txtInicial"));
	            BigDecimal precioCompra = new BigDecimal(request.getParameter("txtPrecio"));
	            Date fechaEntrada = Date.valueOf(request.getParameter("txtDate"));
	            HttpSession session = request.getSession();
	            int idUsuario = (int) session.getAttribute("userId");
	            int idProducto = Integer.parseInt(request.getParameter("txtProducto"));
	            int idCliente = Integer.parseInt(request.getParameter("txtCliente"));
	            String estado= request.getParameter("txtEstado");

	            Existencia existencia = new Existencia();
	            existencia.setExistenciaInicial(existenciaInicial);
	            existencia.setPrecioCompra(precioCompra);
	            existencia.setFechaEntrada(fechaEntrada);
	            existencia.setIdUsuario(idUsuario);
	            existencia.setIdProducto(idProducto);
	            existencia.setIdCliente(idCliente);
	            existencia.setEstado(estado);

	            boolean exito = dax.addx(existencia);
	            

	            if (exito) {
	                response.sendRedirect(request.getContextPath() + "/ExistenciaProducto.jsp");
	                return;
	            } else {
	                acceso = "error.jsp";
	            }
	        } else if (action.equalsIgnoreCase("BuscarExistenciaProducto")) {
	            String dato = request.getParameter("txtBuscar");
	            if (dato != null && !dato.isEmpty()) {
	                List<Existencia> lista = dax.buscarM(dato);
	                request.setAttribute("datos", lista);

	            } else {

	            }
	            acceso = this.existenciaProducto;
	        }else if (action.equalsIgnoreCase("ActualizarExistenciaProducto")) {
	            
	            int idInventario = Integer.parseInt(request.getParameter("txtid"));
	            int existenciaInicial = Integer.parseInt(request.getParameter("txtInicial"));
	            BigDecimal precioCompra = new BigDecimal(request.getParameter("txtPrecio"));
	            Date fechaEntrada = Date.valueOf(request.getParameter("txtFecha"));
	            //String ingresadoPor = request.getParameter("txtResponsable");
	            String estado = request.getParameter("txtEstado");
	            int idProducto = Integer.parseInt(request.getParameter("txtProducto"));
	            int idCliente = Integer.parseInt(request.getParameter("txtCliente"));
	             HttpSession session = request.getSession();
	            int idUsuario = (int) session.getAttribute("userId");
	            Existencia existencia = new Existencia();
	            existencia.setIdInventario(idInventario);
	            existencia.setEstado(estado);
	            existencia.setExistenciaInicial(existenciaInicial);
	            existencia.setPrecioCompra(precioCompra);
	            existencia.setFechaEntrada(fechaEntrada);
	            existencia.setIdUsuario(idUsuario);
	            existencia.setIdProducto(idProducto);
	            existencia.setIdCliente(idCliente);
	            boolean exito = dax.editP(existencia);

	            if (exito) {
	                acceso = this.existenciaProducto;
	            } else {
	                acceso = "error.jsp";
	            }
	        //ELIMINAR EXISTENCIAS DE MAT-PRO-INVENTARIO
	        } 
	        RequestDispatcher vista = request.getRequestDispatcher(acceso);
	        vista.forward(request, response);
		 	
	}

}
