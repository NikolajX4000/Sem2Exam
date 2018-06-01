
package presentationLayer;

import logicLayer.CustomException;
import logicLayer.Employee;
import logicLayer.LogicFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This command attempts to log the employee into the website, and puts a refference to their employee reference in the session
 * @author Hupra Laptop
 */
public class CmdLogin extends Command{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response)
    {
        
        try
        {
            Employee emp  = LogicFacade.login(request.getParameter("user"), request.getParameter("pw"));
            
            request.setAttribute("feedback", "Velkommen " + emp.getName());
            request.getSession().setAttribute("user", emp);
            
            
            //content
            try
            {
                request.setAttribute("orders", LogicFacade.getAllOrders());

            } catch (CustomException ex)
            {
                request.setAttribute("feedback", ex.getMessage());
            }
            
            
            return "allOrders";

        } catch (CustomException e)
        {
            request.setAttribute("feedback", e.getMessage());
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