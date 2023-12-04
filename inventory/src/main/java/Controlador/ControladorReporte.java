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
import Modelo.CliienteInfo;
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

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 try {
		        // Establecer conexión a la base de datos
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventarios", "root", "");
		        String tipoInforme = request.getParameter("tipoInforme");

	            String numeroInforme = dao.obtenerProximoNumeroReporte(tipoInforme);

	            Map<String, Object> parameters = new HashMap<>();
	            
	            parameters.put("ID_INFORME", numeroInforme);
	            String reportPath = (tipoInforme.equals("producto")) ? "/ReporteProducto.jrxml" : "/ReporteExistencias.jrxml";
		        
	            JasperReport jr = JasperCompileManager.compileReport(getClass().getResourceAsStream(reportPath));
		        JasperPrint jp = JasperFillManager.fillReport(jr, parameters, conn);
		        

		        // Generar un nombre unico para el archivo PDF
		        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		        String hora = new SimpleDateFormat("HH_mm_ss").format(new Date());
		        String fileName = "Informe " + numeroInforme + " " + fecha + " " + hora + ".pdf";
		        String folderPath = "C:/Users/Luuis/OneDrive/Escritorio/ReportPDF/";
		        String filePath = folderPath + fileName;
		        response.setContentType("application/pdf");
		        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

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
			Map<Integer, CliienteInfo> clientesInfo = dax.obtenerDiferenciaDiasYNombreClientes();
            StringBuilder mensajeClientes = new StringBuilder();
            for (Map.Entry<Integer, CliienteInfo> entry : clientesInfo.entrySet()) {
            	
                int proveedorId = entry.getKey();
                CliienteInfo clienteInfo = entry.getValue();
                

                if (clienteInfo.getDiferenciaDias() >= 2) {
                	mensajeClientes.append("Cliente ID: ").append(proveedorId).append("\n");
                	mensajeClientes.append("Nombre del Cliente: ").append(clienteInfo.getNombre()).append("\n");
                	mensajeClientes.append("Ultima fecha de actividad: ").append(clienteInfo.getFecha()).append("\n");
                	mensajeClientes.append("Dias inactivos: ").append(clienteInfo.getDiferenciaDias()).append("\n\n");
                    
                }
            }
            
            Thread correoThread = new Thread(() -> {
                CorreoEmail.enviarCorreoConfirmacion(userEmail, userName, mensajeClientes.toString());
            });
            correoThread.start();
           
        }	
		
		 response.sendRedirect(request.getContextPath() + "/ExistenciaProducto.jsp");
         return;
	}

}
