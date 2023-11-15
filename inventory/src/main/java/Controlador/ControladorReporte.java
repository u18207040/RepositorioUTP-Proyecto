package Controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

/**
 * Servlet implementation class ControladorReporte
 */
public class ControladorReporte extends HttpServlet {
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

		        JasperReport jr = JasperCompileManager
		                .compileReport(getClass().getResourceAsStream("/ReporteUsuario.jrxml"));
		        JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);

		        // Generar un nombre único para el archivo PDF (puedes personalizar esto según tus necesidades)
		        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		        String hora = new SimpleDateFormat("HH_mm_ss").format(new Date());
		        String fileName = "Informe " + fecha + " " + hora + ".pdf";
		        String folderPath = "C:/Users/Luuis/OneDrive/Escritorio/ReportPDF/";
		        String filePath = folderPath + fileName;
		        // Configurar la respuesta del servlet para descargar el archivo
		        response.setContentType("application/pdf");
		        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

		        // Exportar el informe a formato PDF y escribir en el flujo de salida de la respuesta
		        JasperExportManager.exportReportToPdfStream(jp, response.getOutputStream());
		        JasperExportManager.exportReportToPdfFile(jp, filePath);
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
