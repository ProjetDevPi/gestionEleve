/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.Message;
import javax.mail.Session;
/**
 *
 * @author USER-PC
 */
public class MailSend {
    public void sendMail()
    {
        try{
            String host ="aspmx.l.google.com" ;
            String user = "mouhib.benrhouma@esprit.tn";
            String pass = "Mou200697";
            String to = "mbenrhouma22@gmail.com";
            String from = "Russia";
            String subject = "This is confirmation number for your expert tnt.";
            String messageText = "<h1> sahhit<h1><h2>nah ya whhayed<h2>";
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", true);
            props.put("mail.smtp.ssl.trust","*") ;
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); 
            msg.setSentDate(new Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
    
    }
    public  void sendMail(String from,String to,String Subject,String message)
    {
        try{
            String host ="smtp.gmail.com" ;
            String user = "wejdene.benjeddou@esprit.tn";
            String pass = "esprit student20533633";
            String too = to;
            String fro = from;
            String subject = Subject;
            String messageText = message;
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(fro));
            InternetAddress[] address = {new InternetAddress(too)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
    
    }
    
    /****needs fixing *////
    public  void sendMailWithHTML(){
      // Recipient's email ID needs to be mentioned.
       String to = "jawhar.b.a.96@gmail.com";

      // Sender's email ID needs to be mentioned
      String from = "jawhar.benabdallah@esprit.tn";

      // Assuming you are sending email from localhost
      String host = "smtp.gmail.com";

      // Get system properties
      Properties properties = System.getProperties();
      properties.setProperty("mail.user", "jawhar.b.a.96@gmail.com");
      properties.setProperty("mail.password", "awzxcvy24142710");
      // Setup mail server
      properties.setProperty("mail.smtp.host", host);

      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties);

      try {
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("This is the Subject Line!");

         // Send the actual HTML message, as big as you like
         message.setContent("<h1>This is actual message</h1>", "text/html");

         // Send message
         Transport.send(message);
         System.out.println("Sent message successfully....");
      } catch (MessagingException mex) {
         mex.printStackTrace();
      }
    }
    
}
