package logicLayer;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author Hupra Laptop
 */
public class DanielsPostHus {


    private DanielsPostHus() {
    }
    
//    public static void main(String[] args) throws MessagingException {
//        sendMail("Ny ordre klar til behandling", "<div>Jeg er en div hihi</div>", "foglyngby@gmail.com");
//    }

    /**
     *
     * @param subject the subject line in the email
     * @param content the content for the email (HTML)
     * @param receiver the email adresse of the receiver
     * @throws AddressException
     * @throws MessagingException
     */
    
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
        msg.setFrom(new InternetAddress("foglyngby@gmail.com"));
        msg.setContent(content, "text/html");

        // send email
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.gmail.com", "foglyngby@gmail.com", "cphbusiness");
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
    }
    
    /**
     *
     * @param o Order, takes details from Order and sends an email to fodlyngby@gmail.com
     * @throws MessagingException
     * @throws CustomException
     */
    public static void newOrder(Order o) throws MessagingException, CustomException {
        
        String subject = "Ny ordre af carport på " + o.getWidth() + "x" + o.getLength();
        String receiver = "foglyngby@gmail.com";
        StringBuilder s = new StringBuilder();

        
        s.append("<h4>Mål:</h4>");
        s.append("<table>");
        
        s.append("<tr><td style='padding-right:10px;box-sizing:border-box;'>Carport bredde:</td><td>").append(o.getWidth()).append("</td></tr>");
        s.append("<tr><td style='padding-right:10px;box-sizing:border-box;'>Carport længde:</td><td>").append(o.getLength()).append("</td></tr>");
        if(o.hasShed()){
            s.append("<tr><td style='padding-right:10px;box-sizing:border-box;'>Redskabsrum bredde:</td><td>").append(o.getShedWidth()).append("</td></tr>");
            s.append("<tr><td style='padding-right:10px;box-sizing:border-box;'>Redskabsrum længde:</td><td>").append(o.getLength()).append("</td></tr>");
        }
        s.append("</table>");
        
        s.append("<h4>Kontakt information:</h4>");
        s.append("<table>");
        s.append("<tr><td style='padding-right:10px;box-sizing:border-box;'>Navn:<td>").append(o.getName()).append("</td></tr>");
        s.append("<tr><td style='padding-right:10px;box-sizing:border-box;'>Telefon:</td><td>").append(o.getPhone()).append("</td></tr>");
        s.append("<tr><td style='padding-right:10px;box-sizing:border-box;'>Email:</td><td>").append(o.getEmail()).append("</td></tr>");
        s.append("<tr><td style='padding-right:10px;box-sizing:border-box;'>Adresse:</td><td>").append(o.getAddress()).append("</td></tr>");
        s.append("<tr><td style='padding-right:10px;box-sizing:border-box;'>Postnummer:</td><td>").append(o.getZipCode()).append("</td></tr>");
        s.append("<tr><td style='padding-right:10px;box-sizing:border-box;'>By:</td><td>").append(o.getCity()).append("</td></tr>");
        
        s.append("</table>");
        
        s.append("<h4>Bemrkning:</h4>");
        s.append("<p>").append(o.getNote()).append("</p>");
        
        sendMail(subject, s.toString(), receiver);

    }
}
