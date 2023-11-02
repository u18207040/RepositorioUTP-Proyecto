package Controlador;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import Conexion.Conexion;
import ModeloDAO.ExistenciasDAO;
import ModeloDAO.UsuarioDAO;
import Modelo.CorreoEmail;
import Modelo.ProveedorInfo;
import Modelo.Usuario;

public class Controlador extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Usuario u = new Usuario();
	UsuarioDAO dao = new UsuarioDAO();
	ExistenciasDAO dax=new ExistenciasDAO();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
        String action = request.getParameter("accion");
        if (action.equalsIgnoreCase("eliminar")) {
        if (request.getParameter("id") != null && request.getParameter("tabla") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            String tabla = request.getParameter("tabla");
            boolean eliminado = dao.eliminar(id, tabla);
            if (eliminado) {
                if ("materiales".equalsIgnoreCase(tabla)) {
                    response.sendRedirect(request.getContextPath() + "/Material.jsp");
                    return;
                } else if ("proveedores".equalsIgnoreCase(tabla)) {
                    response.sendRedirect(request.getContextPath() + "/Proveedor.jsp");
                    return;
                }else if ("productos".equalsIgnoreCase(tabla)) {
                    response.sendRedirect(request.getContextPath() + "/Producto.jsp");
                    return;
                } else if ("cliente".equalsIgnoreCase(tabla)) {
                    response.sendRedirect(request.getContextPath() + "/Cliente.jsp");
                    return;
                } else if ("empleado".equalsIgnoreCase(tabla)) {
                    response.sendRedirect(request.getContextPath() + "/Usuario.jsp");
                    return;
                }
                else if ("inventario_productos".equalsIgnoreCase(tabla)) {
                    response.sendRedirect(request.getContextPath() + "/ExistenciaProducto.jsp");
                    return;
                }else if ("inventario_material".equalsIgnoreCase(tabla)) {
                    response.sendRedirect(request.getContextPath() + "/ExistenciaMaterial.jsp");
                    return;
                }
            }
        }
    }

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
		String action = request.getParameter("accion");
		if (action != null && action.equalsIgnoreCase("Ingresar")) {
		    String user = request.getParameter("txtuser");
		    String pass = request.getParameter("txtpass");
		    u = dao.validar(user, pass);
		    Conexion conexionDB = new Conexion();
		    String permisos = u.getPermisos();
		    if (u.getUser() != null) {
	
		        if ("BLOQUEADO".equalsIgnoreCase(permisos)) {
		        	request.getSession().setAttribute("mostrarMensajeBloqueo", true);
		            request.getRequestDispatcher("index.jsp").forward(request, response);
		        } else {
		            HttpSession session = request.getSession(true);
		            session.setAttribute("rolUsuario", u.getEstado());
		            session.setAttribute("usuario", u.getNom());
		            session.setAttribute("user", u.getUser());
		            session.setAttribute("userId", u.getId());
		            conexionDB.actualizarEnLineaUsuario(user, 1);
		            conexionDB.actualizarUltimaConexion(user);
		            conexionDB.actualizarUltimaHoraConexion(user);
		            //Date fecha = dax.obtenerFechaUltimaTransaccionProveedor();
		            Map<Integer, ProveedorInfo> proveedoresInfo = dax.obtenerDiferenciaDiasYNombreProveedores();

		            for (Map.Entry<Integer, ProveedorInfo> entry : proveedoresInfo.entrySet()) {
		                int proveedorId = entry.getKey();
		                ProveedorInfo proveedorInfo = entry.getValue();

		                if (proveedorInfo.getDiferenciaDias() >= 2) {
		                    System.out.println("Proveedor ID: " + proveedorId);
		                    System.out.println("Nombre del Proveedor: " + proveedorInfo.getNombre());
		                    System.out.println("Diferencia de días para el proveedor ID " + proveedorId + ": " + proveedorInfo.getDiferenciaDias());
		                }
		            }


		            request.getRequestDispatcher("Fronter.jsp").forward(request, response);
		        }
		    } else {
		    	request.getSession().setAttribute("mostrarMensajeError", true);
		        request.getRequestDispatcher("index.jsp").forward(request, response);
		    }
		}else if (action.equalsIgnoreCase("AgregarUsuario")) {
            String user = request.getParameter("txtUsuario");
            String dni = request.getParameter("txtDni");
            String nom = request.getParameter("txtNom");
            String cargo = request.getParameter("txtCargo");
            String telefono = request.getParameter("txtTelefono");
            String email=request.getParameter("txtEmail");
            u.setDni(dni);
            u.setEstado(cargo);
            u.setNom(nom);
            u.setUser(user);
            u.setTel(telefono);
            u.setEmail(email);
            dao.add(u);
            response.sendRedirect(request.getContextPath() + "/Usuario.jsp");
            return;

        }else if (action.equalsIgnoreCase("ActualizarUsuario")) {
            int id = Integer.parseInt(request.getParameter("txtid"));
            String user = request.getParameter("txtUsuario");
            String dni = request.getParameter("txtDni");
            String nom = request.getParameter("txtNom");
            String cargo = request.getParameter("txtCargo");
            String permiso= request.getParameter("txtPermiso");
            String telefono = request.getParameter("txtTelefono");
            u.setId(id);
            u.setDni(dni);
            u.setEstado(cargo);
            u.setNom(nom);
            u.setUser(user);
            u.setTel(telefono);
            u.setPermisos(permiso);
            dao.edit(u);
            request.getRequestDispatcher("Usuario.jsp").forward(request, response);
		} else if (action.equalsIgnoreCase("BuscarUsuario")) {
			String dato = request.getParameter("txtBuscar");
			if (dato != null && !dato.isEmpty()) {
				List<Usuario> lista = dao.buscar(dato);
				
				request.setAttribute("datos", lista);
				
			} 
			
			request.getRequestDispatcher("Usuario.jsp").forward(request, response);

		} else if (action.equalsIgnoreCase("enviarCorreo")) {
		    int usuarioId = Integer.parseInt(request.getParameter("usuarioId"));
		    // Recupera los detalles del usuario con el ID proporcionado
		    Usuario usuario = dao.obtenerUsuarioPorId(usuarioId);
		    if (usuario != null) {
		        // Envía el correo utilizando los detalles del usuario
		        CorreoEmail correo = new CorreoEmail();
		        correo.enviarCorreoConfirmacion(usuario.getEmail(), usuario.getUser(), usuario.getDni(), usuario.getEstado());
		        response.sendRedirect(request.getContextPath() + "/Usuario.jsp");
	            //CorreoEmail.enviarCorreoConfirmacion(correo, nom, user, cargo);
	            return;
		    }
		    // Redirige o realiza cualquier otra acción necesaria después de enviar el correo.
		}
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String menu = request.getParameter("menu");
		String accion = request.getParameter("accion");

		if (menu != null) {
			if (menu.equalsIgnoreCase("Principal")) {
				response.sendRedirect("Fronter.jsp");
			} else if (menu.equalsIgnoreCase("Material")) {
				switch (accion) {
				case "Mat":
					response.sendRedirect("Material.jsp");
					break;
				case "pro":
					response.sendRedirect("Proveedor.jsp");
					break;
				case "existenciaMaterial":
					response.sendRedirect("ExistenciaMaterial.jsp");
					break;
				default:
					response.sendRedirect("Principal.jsp");
					break;
				}
			} else if (menu.equalsIgnoreCase("Producto")) {
				switch (accion) {
				case "producto":
					response.sendRedirect("Producto.jsp");
					break;
				case "cliente":
					response.sendRedirect("Cliente.jsp");
					break;
				case "existenciaProducto":
					response.sendRedirect("ExistenciaProducto.jsp");
					break;
				default:
					response.sendRedirect("Principal.jsp");
					break;
				}
			} else if (menu.equalsIgnoreCase("Usuario")) {
				switch (accion) {
				case "usuario":
					response.sendRedirect("Usuario.jsp");
					break;
				default:
					response.sendRedirect("Principal.jsp");
					break;
				}
			}

		}
	}
}
