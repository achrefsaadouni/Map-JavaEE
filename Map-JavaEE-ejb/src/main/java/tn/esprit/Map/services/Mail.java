package tn.esprit.Map.services;



import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Stateless
public class Mail {
 
	@Resource(mappedName="java:jboss/mail/RedHat")
    private Session session;
 
    public void send(String addresses, String topic, String textMessage) {
 
        try {
 
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses));
            message.setSubject(topic);
            message.setText(textMessage);
 
            Transport.send(message);
 
        } catch (MessagingException e) {
            System.out.println(e);
        }
    }
}