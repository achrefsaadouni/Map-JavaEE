package tn.esprit.Map.services;


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
 
  public void send(final String addresses, final String subject,final String text) {
    try {
      Message message = new MimeMessage(session);
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses));
      message.setSubject(subject);
      message.setContent(format(text), "text/html; charset=utf-8");
 
      Transport.send(message);
    } catch (Exception e) {
      System.out.println(e);
    }
  }
  
  
  public String format(final String text){
	  return text;
  }
}