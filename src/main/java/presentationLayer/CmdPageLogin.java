/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentationLayer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This command returns the user to the login page - login.jsp
 * @author Hupra Laptop
 */
public class CmdPageLogin extends Command
{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response)
    {
        
        return "login";
    }

}
