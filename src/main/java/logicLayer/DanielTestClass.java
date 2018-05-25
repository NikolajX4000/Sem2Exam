package logicLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import org.mindrot.jbcrypt.BCrypt;
import storageLayer.Connector;

/**
 *
 * @author super
 */
public class DanielTestClass {

    /**
     *
     * @param args
     */
    public static void main(String[] args) throws CustomException {
        
//        try
//        {
//            //       foglyngby@gmail.com
//            
//            DanielsPostHus.sendMail("dertestes", "yolo", "foglyngby@gmail.com");
//        } catch (MessagingException ex)
//        {
//            System.out.println(ex);
//        }

    }
}

    // PASSWORD HASHING :)    
//    String password = "æqwrwqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqasdasdsadasd aw wa z zcxx z ___:::eøå";        
//    // Hash a password for the first time
//    String hashed = BCrypt.hashpw(password, BCrypt.gensalt(14));
//
//    System.out.println(hashed.length());
//    System.out.println(hashed);
//
//    if (BCrypt.checkpw("test", "$2a$10$TjM12svvl2BDRi/R.QKeQ.ZzPFifCQWmqTIF4OvHQO1krfYoG5bXO"))
//        System.out.println("It matches");
//    else
//        System.out.println("It does not match");
//    }

