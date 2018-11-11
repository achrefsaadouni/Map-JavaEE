package tn.esprit.Map.utilities;

import java.util.Properties;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Mail_API {
	public void sendEmail(String to,String from,String subject,String bodyText){
    // Recipient's email ID needs to be mentioned.
    //String to = "mahdychergui@gmail.com";

    // Sender's email ID needs to be mentioned
    //String from = "rahmabasly20@gmail.com";
    final String username = "rahmabasly20@gmail.com";//change accordingly
    final String password = "24390420rahma***";//change accordingly

    // Assuming you are sending email through relay.jangosmtp.net
    String host = "smtp.gmail.com";

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

    // Get the Session object.
    Session session = Session.getInstance(props,
       new javax.mail.Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
             return new PasswordAuthentication(username, password);
	   }
       });

    try {
	   // Create a default MimeMessage object.
	   Message message = new MimeMessage(session);
	
	   // Set From: header field of the header.
	   message.setFrom(new InternetAddress(from));
	
	   // Set To: header field of the header.
	   message.setRecipients(Message.RecipientType.TO,
             InternetAddress.parse(to));
	
	   // Set Subject: header field
	   message.setSubject(subject);
	
	   // Now set the actual message
	   message.setText(bodyText);

	   // Send message
	   Transport.send(message);

	   System.out.println("Sent message successfully....");

    } catch (MessagingException e) {
       throw new RuntimeException(e);
    }
 }
    
}
