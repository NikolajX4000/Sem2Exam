package functionLayer;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class DanielsPostHus {

    private DanielsPostHus() {
    }
    
//    public static void main(String[] args) throws MessagingException {
//        sendMail("Ny ordre klar til behandling", "<div>Jeg er en div hihi</div>", "foglyngby@gmail.com");
//    }
    
    public static void sendMail(String subject, String content, String receiver) throws AddressException, MessagingException {
        
        // lav propperties til brug i session
        Properties props = System.getProperties();
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // lav session og msg
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage msg = new MimeMessage(session);
        
        // lav content
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
        msg.setSubject(subject);
        msg.setContent(content, "text/html");

        // send email
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.gmail.com", "foglyngby@gmail.com", "cphbusiness");
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
    }
}
