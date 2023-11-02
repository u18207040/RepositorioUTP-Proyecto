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
import java.util.Map;

import Modelo.Existencia;
import Modelo.Material;
import Modelo.Proveedor;
import ModeloDAO.ExistenciasDAO;
import ModeloDAO.GestionMaterial;
import ModeloDAO.UsuarioDAO;

/**
 * Servlet implementation class ControladorGestionMaterial
 */
public class ControladorGestionMaterial extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String gestion="Material.jsp";
	String proveedor="Proveedor.jsp";
	String existenciaproducto="ExistenciaProducto.jsp";
    String existenciamaterial="ExistenciaMaterial.jsp";
	Material m=new Material();
    Proveedor pro=new Proveedor();
    UsuarioDAO dao=new UsuarioDAO();
    GestionMaterial dam=new GestionMaterial();
    ExistenciasDAO dax=new ExistenciasDAO();
    int id;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorGestionMaterial() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acceso = "";
		String action = request.getParameter("accion");
		if (action.equalsIgnoreCase("AgregarMaterial")) {
            String cat = request.getParameter("txtCat");
            String nom = request.getParameter("txtNom");
            String det = request.getParameter("txtDet");
            m.setCategoria(cat);
            m.setNombre(nom);
            m.setDetalle(det);
            dam.add(m);
            response.sendRedirect(request.getContextPath() + "/Material.jsp");
            return;
        } else if (action.equalsIgnoreCase("Buscar Material")) {
            String dato = request.getParameter("txtBuscar");
            if (dato != null && !dato.isEmpty()) {
                List<Material> lista = dam.buscarM(dato);
                request.setAttribute("datos", lista);
            } else {
                //List<Material> list = dao.listarm();
                //request.setAttribute("datos", list);

            }
            //acceso = this.gestion; 
            request.getRequestDispatcher("Material.jsp").forward(request, response);

        } else if (action.equalsIgnoreCase("ActualizarM")) {
            id = Integer.parseInt(request.getParameter("txtid"));
            String categoria = request.getParameter("txtCat");
            String nombre = request.getParameter("txtNom");
            String detalles = request.getParameter("txtDet");
            m.setId(id);
            m.setCategoria(categoria);
            m.setDetalle(detalles);
            m.setNombre(nombre);
            dam.edit(m);
            response.sendRedirect(request.getContextPath() + "/Material.jsp");
            
        //GESTION DE PROVEEDORES
        } else if (action.equalsIgnoreCase("AgregarProveedor")) {
            String nom = request.getParameter("txtNom");
            String correo = request.getParameter("txtCorreo");
            String telefono = request.getParameter("txtTel");
            String direccion = request.getParameter("txtDirec");
            pro.setNombre(nom);
            pro.setCorreo(correo);
            pro.setTelefono(telefono);
            pro.setDireccion(direccion);
            dam.add(pro);
            response.sendRedirect(request.getContextPath() + "/Proveedor.jsp");
            return;

        } else if (action.equalsIgnoreCase("ActualizarPro")) {
            id = Integer.parseInt(request.getParameter("txtid"));
            String nombre = request.getParameter("txtNom");
            String correo = request.getParameter("txtCorreo");
            String telefono = request.getParameter("txtTel");
            String direccion = request.getParameter("txtDirec");
            pro.setId(id);
            pro.setNombre(nombre);
            pro.setCorreo(correo);
            pro.setTelefono(telefono);
            pro.setDireccion(direccion);
            dam.edit(pro);
           response.sendRedirect(request.getContextPath() + "/Proveedor.jsp");
           return;
        } else if (action.equalsIgnoreCase("Buscar Proveedor")) {
            String dato = request.getParameter("txtBuscar");
            if (dato != null && !dato.isEmpty()) {
                List<Proveedor> lista = dam.buscarP(dato);
                request.setAttribute("datos", lista);
            } else {
                //List<Material> list = dao.listarm();
                //request.setAttribute("datos", list);

            }
            request.getRequestDispatcher("Proveedor.jsp").forward(request, response);
            //request.getRequestDispatcher("Proveedor.jsp").forward(request, response);

        }//GESTION DE EXISTENCIAS
        else if (action.equalsIgnoreCase("AgregarExistencia")) {
            int existenciaInicial = Integer.parseInt(request.getParameter("txtInicial"));
            int existenciaFinal = Integer.parseInt(request.getParameter("txtFinal"));
            BigDecimal precioCompra = new BigDecimal(request.getParameter("txtPrecio"));
            Date fechaEntrada = Date.valueOf(request.getParameter("txtDate"));
            HttpSession session = request.getSession();
            int idUsuario = (int) session.getAttribute("userId");
            int idMaterial = Integer.parseInt(request.getParameter("txtMaterial"));
            int idProveedor = Integer.parseInt(request.getParameter("txtProveedor"));

            Existencia existencia = new Existencia();
            existencia.setExistenciaInicial(existenciaInicial);
            existencia.setExistenciaFinal(existenciaFinal);
            existencia.setPrecioCompra(precioCompra);
            existencia.setFechaEntrada(fechaEntrada);
            existencia.setIdUsuario(idUsuario);
            existencia.setIdMaterial(idMaterial);
            existencia.setIdProveedor(idProveedor);
            
            
            boolean exito = dax.add(existencia);

            if (exito) {
                response.sendRedirect(request.getContextPath() + "/ExistenciaMaterial.jsp");
               
                return;
            } else {
                acceso = "error.jsp";
            }
        }else if (action.equalsIgnoreCase("BuscarExistencia")) {
            String dato = request.getParameter("txtBuscar");
            if (dato != null && !dato.isEmpty()) {
                List<Existencia> lista = dax.buscarE(dato);
                request.setAttribute("datos", lista);
            }
            
            request.getRequestDispatcher("ExistenciaMaterial.jsp").forward(request, response);
        } else if (action.equalsIgnoreCase("ActualizarExistencia")) {
            
            int idInventario = Integer.parseInt(request.getParameter("txtid"));
            int existenciaInicial = Integer.parseInt(request.getParameter("txtInicial"));
            int existenciaFinal = Integer.parseInt(request.getParameter("txtFinal"));
            BigDecimal precioCompra = new BigDecimal(request.getParameter("txtPrecio"));
            Date fechaEntrada = Date.valueOf(request.getParameter("txtFecha"));
            HttpSession session = request.getSession();
            int idUsuario = (int) session.getAttribute("userId");
            int idMaterial = Integer.parseInt(request.getParameter("txtMat"));
            int idProveedor = Integer.parseInt(request.getParameter("txtProveedor"));

            Existencia existencia = new Existencia();
            existencia.setIdInventario(idInventario);
            existencia.setExistenciaInicial(existenciaInicial);
            existencia.setExistenciaFinal(existenciaFinal);
            existencia.setPrecioCompra(precioCompra);
            existencia.setFechaEntrada(fechaEntrada);
            existencia.setIdUsuario(idUsuario);
            existencia.setIdMaterial(idMaterial);
            existencia.setIdProveedor(idProveedor);
            boolean exito = dax.edit(existencia);

            if (exito) {
            	request.getRequestDispatcher("ExistenciaMaterial.jsp").forward(request, response);
            } else {
                
            	System.out.println("error");
            }
        //ELIMINAR EXISTENCIAS DE MAT-PRO-INVENTARIO
        } else if (action.equalsIgnoreCase("EditarCantidad")) {
            int idInventario = Integer.parseInt(request.getParameter("txtid"));
            int cantidadMovimiento = Integer.parseInt(request.getParameter("txtCantidad"));
            String tipoMovimiento = request.getParameter("txtTipoMovimiento"); 
            boolean exito = dao.editarCantidad(idInventario, tipoMovimiento, cantidadMovimiento);

            if (exito) {
            	//request.getRequestDispatcher("ExistenciaMaterial.jsp").forward(request, response);
            	response.sendRedirect("ExistenciaMaterial.jsp");
            } else {
            	System.out.println("error");
            	response.sendRedirect("index.jsp");
            }
        
        } 
      
		
	}
	

}
