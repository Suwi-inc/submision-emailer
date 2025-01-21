package com.suwi.email;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {
    @Value("${spring.mail.username}")
    private String fromEmail;
    @Value("${spring.mail.password}")
    private String emailPassword;
    @Value("${target}")
    private String targetEmail;

    public void sendEmail(String name, String email, String message) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, emailPassword);
            }
        });

        Message mailMessage = new MimeMessage(session);
        mailMessage.setFrom(new InternetAddress(fromEmail));
        mailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(targetEmail));
        mailMessage.setSubject("New Contact Form Submission");
        mailMessage.setText("Name: " + name + "\nEmail: " + email + "\nMessage: " + message);

        Transport.send(mailMessage);
    }
}
