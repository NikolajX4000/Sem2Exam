
package presentationLayer;

import functionLayer.CustomException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

public class CmdLogin extends Command{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CustomException
    {
        
        try
        {
            String hashed = BCrypt.hashpw(request.getParameter("pw"), BCrypt.gensalt(14));
            request.setAttribute("test", hashed.length() + " " + hashed);

        } catch (Exception e)
        {
        }
        
        
        return "login";
    }
    
}


//    PASSWORD HASHING :)    
//
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