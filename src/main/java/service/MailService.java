package service;

import org.apache.log4j.Logger;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailService {

    private static final Logger logger = Logger.getLogger(MailService.class);

    public void sendMail(String userEmail) {
        final String username = "s.klunniy@gmail.com";
        final String password = "******";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("s.klunniy@gmail.com"));
//            message.setRecipients(
//                    Message.RecipientType.TO,
//                    InternetAddress.parse("userEmail")
//            );

            message.setRecipient(
                    Message.RecipientType.TO, new InternetAddress(userEmail));

            message.setSubject("Одноразовый код подтверждения покупки");
            message.setText("Ваш код равен 208");

            Transport.send(message);

            logger.info("Сообщение отправлено");
        } catch (MessagingException e) {
            logger.error("Can't send message", e);
        }
    }

    public static void main(String[] args) {
        MailService mailService = new MailService();
        mailService.sendMail("s.klunniy@gmail.com");
    }

}

