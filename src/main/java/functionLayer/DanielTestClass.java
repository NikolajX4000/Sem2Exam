package functionLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        String IP = "159.89.9.144";
        String PORT = "3306";
        String DATABASE = "sem2examTest";
        String USER = "fogTest";
        String USERPW = "password123";
        try {
            Connection c1 = Connector.connection();
            Connection c2 = null;
            System.out.println("1" + c1);
            System.out.println("2" + c2);
            
            Connector.setConnection(DriverManager.getConnection("jdbc:mysql://" + IP + ":" + PORT + "/" + DATABASE, USER, USERPW));
            //c1 = Connector.connection();
            c2 = Connector.connection();
            System.out.println("1" + c1);
            System.out.println("2" + c2);
            
//            System.out.println("1" + c1);
//            System.out.println("2" + c2);
//            c1 = Connector.connection();
//            c2 = Connector.connection();
//            System.out.println("1" + c1);
//            System.out.println("2" + c2);
//        Order order = new Order();
//        order.
//                setName("test").
//                setWidth(600).
//                setLength(780).
//                setShedWidth(530).
//                setShedLength(210);
//        order.
//                setWidth(360).
//                setLength(730).
//                setAngle(20).
//                setShedWidth(320).
//                setShedLength(225).
//                setRoof(7);
//        FlatCarPortList carport = new FlatCarPortList(order);
//        TallCarPortList carport = new TallCarPortList(order);
//        List<PartLine> parts = carport.getParts();
//        for (int i = 0; i < parts.size(); i++)
//        {
//            System.out.println(parts.get(i));
//        }
        } catch (Exception ex) {
            Logger.getLogger(DanielTestClass.class.getName()).log(Level.SEVERE, null, ex);
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
}
