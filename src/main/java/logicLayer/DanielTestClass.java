package logicLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.mail.MessagingException;
import org.mindrot.jbcrypt.BCrypt;
import org.pmw.tinylog.Configurator;
import storageLayer.Connector;
import org.pmw.tinylog.Logger;
import org.pmw.tinylog.writers.FileWriter;

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
        
       
        Logger.warn("DER LOGGES");
        
        
//        for (double i = 0; i < 1000; i+=59.5)
//        {
//            System.out.println(i);
//        }
//        
//        
//        for (int i = 0; i < 1000; i+=30)
//        {
//            System.out.println(i-4.5);
//        }
        
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

