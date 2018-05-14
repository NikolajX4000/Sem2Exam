package functionLayer;

import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author super
 */
public class DanielTestClass
{

    /**
     *
     * @param args
     */
    public static void main(String[] args) throws CustomException
    {

        Order order = new Order();
        order.
                setName("test").
                setWidth(600).
                setLength(780).
                setShedWidth(530).
                setShedLength(210);
        FlatCarPortList carport = new FlatCarPortList(order);
        List<PartLine> parts = carport.getParts();
        for (int i = 0; i < parts.size(); i++)
        {
            System.out.println(parts.get(i));
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
