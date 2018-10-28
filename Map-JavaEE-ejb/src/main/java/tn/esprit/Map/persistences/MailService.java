package tn.esprit.Map.persistences;


import javax.ejb.Stateless;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;
@Stateless
public class MailService {
 
 

  @javax.annotation.Resource(name = "java:jboss/mail/RedHat")
  private Session session;
 
  public void send(final String addresses, final String subject, final String text) {
    try {
      Message message = new MimeMessage(session);
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses));
      message.setSubject(subject);
      message.setText(text);
 
      Transport.send(message);
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}