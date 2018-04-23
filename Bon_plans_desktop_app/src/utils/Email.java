/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author KC
 */
public class Email {
    private static final String username = "bonplans.esprit@gmail.com";
    private static final String password = "bonplans123";
    private static final String from = "bonplans.esprit@gmail.com";
    private static final Properties props = new Properties();
    private static Session session;
    
    private static void connect() {
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        session = Session.getInstance(props, 
            new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
    }
    
    public static boolean send(String to, String subject, String text){
        new Thread(() -> {
            connect();
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                message.setSubject(subject);
                message.setText(text);
                Transport.send(message);
            } catch (MessagingException ex) {
                
            }
        }).start();
        return true;
    }
    
}
