package Modelo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class CorreoEmailUser {
	public static void enviarCorreoConfirmacion(String destinatario, String nombreUsuario, String dni, String cargo) {
        Properties prop = new Properties();
        try (InputStream input = CorreoEmailUser.class.getClassLoader().getResourceAsStream("../resources/config.properties")) {
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
            message.setFrom(new InternetAddress("Luuis21e@gmail.com")); 
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(destinatario)
            );
            message.setSubject("Datos de mi Cuenta: Sitio Web - Taller Angelito");
            message.setText("Hola " + nombreUsuario + ",\n\n" +
                    "Cuenta registrada al sitio web de la Empresa. Tu Cuenta ha sido creada con exito.\n\n" +
                    "Tus datos de registro:\n" +
                    "Nombre de usuario: " + nombreUsuario + "\n" +
                    "Nombre de Password: " + dni + "\n" +
                    "Nombre de Cargo: " + cargo + "\n" +
                    "Correo electrónico: " + destinatario + "\n\n" +   
            		"¡Bienvenido al Sistema: Taller Acabados Angelito!");

            Transport.send(message);

            System.out.println("Correo de confirmación enviado a " + destinatario);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
