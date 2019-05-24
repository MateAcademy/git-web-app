package service;

import org.apache.log4j.Logger;
import utils.RandomHelper;

import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import javax.mail.Authenticator;

public class MailService {

    private static final Logger logger = Logger.getLogger(MailService.class);

    public String sendEmailWithCode(String userEmail) {
        final String username = "s.klunniy@gmail.com";
        final String password = "Epic49$erverGl12";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new Authenticator() {
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

            String randomCode = RandomHelper.getRandomeCode();
            message.setText("Ваш код для подтверждения покупки равен " + randomCode);

            Transport.send(message);
            logger.info("Сообщение отправлено" + message);

            return randomCode;
        } catch (MessagingException e) {
            logger.error("Can't send message", e);
            return "";
        }
    }

//    public static void main(String[] args) {
//        MailService mailService = new MailService();
//        mailService.sendEmailWithCode("s.klunniy@gmail.com");
//    }

}

