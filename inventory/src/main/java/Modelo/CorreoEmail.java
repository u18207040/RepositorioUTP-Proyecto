package Modelo;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CorreoEmail {
	public static void enviarCorreoConfirmacion(String destinatario, String nombreUsuario, String dni, String cargo) {
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
            message.setFrom(new InternetAddress("Luuis21e@gmail.com")); // Cambia esto al correo del remitente
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(destinatario)  // Correo del destinatario (usuario registrado)
            );
            message.setSubject("Confirmación de Registro en Mi Sitio Web");
            message.setText("Hola " + nombreUsuario + ",\n\n" +
                    "Gracias por registrarte en Mi Sitio Web. Tu cuenta ha sido creada con éxito.\n\n" +
                    "Tus datos de registro:\n" +
                    "Nombre de usuario: " + nombreUsuario + "\n" +
                    "Nombre de Password: " + dni + "\n" +
                    "Nombre de Cargo: " + cargo + "\n" +
                    "Correo electrónico: " + destinatario + "\n\n" +
                    "¡Bienvenido y disfruta de nuestro sitio!");

            Transport.send(message);

            System.out.println("Correo de confirmación enviado a " + destinatario);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
