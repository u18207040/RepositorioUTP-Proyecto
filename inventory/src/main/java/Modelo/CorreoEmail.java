package Modelo;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CorreoEmail {
	public static void enviarCorreoConfirmacion(String destinatario, String nombreUsuario, String proveedoresInfo) {
        Properties prop = new Properties();
        try (InputStream input = CorreoEmail.class.getClassLoader().getResourceAsStream("../resources/config.properties")) {
            if (input == null) {
                System.out.println("No se pudo encontrar config.properties");
                return;
            }
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        final String username = prop.getProperty("email");
        final String password = prop.getProperty("password");

        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.host", "smtp.gmail.com");
        mailProperties.put("mail.smtp.port", "587");
        mailProperties.put("mail.smtp.auth", "true");
        mailProperties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(mailProperties, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ProyectosTA21@gmail.com")); 
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(destinatario)
            );
            message.setSubject("Tiempo de inactividad de Clientes");
            message.setText("Hola " + nombreUsuario + ",\n\n" +
                    "Datos de Clientes inactivos:\n\n" +   
                    "Información de clientes:\n" + proveedoresInfo + "\n\n" +
            		"¡Bienvenido y disfruta de nuestro sitio!");

            Transport.send(message);

            System.out.println("Correo d e confirmación enviado a " + destinatario);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
