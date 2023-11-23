package Controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import Modelo.CorreoEmail;
import Modelo.ProveedorInfo;
import ModeloDAO.ExistenciasDAO;
import ModeloDAO.UsuarioDAO;

/**
 * Servlet implementation class ControladorReporte
 */
public class ControladorReporte extends HttpServlet {
	UsuarioDAO dao=new UsuarioDAO();
	ExistenciasDAO dax=new ExistenciasDAO();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorReporte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 try {
		        // Establecer conexión a la base de datos
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventarios", "root", "");
		        String tipoInforme = request.getParameter("tipoInforme");

	            // Obtener el número de informe según el tipo
	            String numeroInforme = dao.obtenerProximoNumeroReporte(tipoInforme);

	            // Pasar el tipo y número del informe como parámetros al reporte
	            Map<String, Object> parameters = new HashMap<>();
	            
	            parameters.put("ID_INFORME", numeroInforme);
	            String reportPath = (tipoInforme.equals("usuario")) ? "/ReporteUsuario.jrxml" : "/ReporteExistencias.jrxml";
		        JasperReport jr = JasperCompileManager
		                .compileReport(getClass().getResourceAsStream(reportPath));
		        JasperPrint jp = JasperFillManager.fillReport(jr, parameters, conn);
		        

		        // Generar un nombre único para el archivo PDF (puedes personalizar esto según tus necesidades)
		        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		        String hora = new SimpleDateFormat("HH_mm_ss").format(new Date());
		        String fileName = "Informe " + numeroInforme + " " + fecha + " " + hora + ".pdf";
		        String folderPath = "C:/Users/Luuis/OneDrive/Escritorio/ReportPDF/";
		        String filePath = folderPath + fileName;
		        // Configurar la respuesta del servlet para descargar el archivo
		        response.setContentType("application/pdf");
		        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

		        // Exportar el informe a formato PDF y escribir en el flujo de salida de la respuesta
		        JasperExportManager.exportReportToPdfStream(jp, response.getOutputStream());
		        JasperExportManager.exportReportToPdfFile(jp, filePath);
		        System.out.println("Tipo y número del informe: " + tipoInforme + numeroInforme);
		    } catch (SQLException e) {
		        e.printStackTrace();
		        response.getWriter().println("Error al conectarse a la base de datos");
		    } catch (Exception e) {
		        e.printStackTrace();
		        response.getWriter().println("Error al generar y guardar el informe");
		    }
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String action = request.getParameter("accion");
		HttpSession session=request.getSession();
		String userEmail = (String) session.getAttribute("email");
		String userName = (String) session.getAttribute("usuario");
		System.out.println(userEmail);
		System.out.println(userName);
		if (action.equalsIgnoreCase("enviarCorreoInactivo")) {
			Map<Integer, ProveedorInfo> proveedoresInfo = dax.obtenerDiferenciaDiasYNombreProveedores();
            StringBuilder mensajeProveedores = new StringBuilder();
            for (Map.Entry<Integer, ProveedorInfo> entry : proveedoresInfo.entrySet()) {
            	
                int proveedorId = entry.getKey();
                ProveedorInfo proveedorInfo = entry.getValue();
                

                if (proveedorInfo.getDiferenciaDias() >= 2) {
                	mensajeProveedores.append("Proveedor ID: ").append(proveedorId).append("\n");
                    mensajeProveedores.append("Nombre del Proveedor: ").append(proveedorInfo.getNombre()).append("\n");
                    mensajeProveedores.append("Ultima fecha de actividad: ").append(proveedorInfo.getFecha()).append("\n");
                    mensajeProveedores.append("Dias inactivos: ").append(proveedorInfo.getDiferenciaDias()).append("\n\n");
                    
                }
            }
            
            CorreoEmail.enviarCorreoConfirmacion(userEmail, userName,  mensajeProveedores.toString());
            
        }	
		
		 response.sendRedirect(request.getContextPath() + "/ExistenciaMaterial.jsp");
         return;
	}

}
