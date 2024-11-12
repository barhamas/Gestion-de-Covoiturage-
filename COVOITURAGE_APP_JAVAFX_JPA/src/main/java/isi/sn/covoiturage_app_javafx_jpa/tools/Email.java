package isi.sn.covoiturage_app_javafx_jpa.tools;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email {
    public static void sendMail(String email, String username, String ms) {
        String ToEmail = email;
        String FromEmail = "aliou.18.ndour@gmail.com";
        String FromEmailPassword = "lzgt jtok qwld jfsx";
        String Subject = "B' ArtCola Bradley";
        String mes = username + " " + ms;

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FromEmail, FromEmailPassword);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(ToEmail));
            message.setSubject(Subject);
            message.setText(mes);
            Transport.send(message);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Erreur d'envoi de mail: " + ex.getMessage());
        }
    }
}
