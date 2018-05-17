
package presentationLayer;

import functionLayer.CustomException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CmdLogin extends Command{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CustomException
    {
        
        try
        {
            
        } catch (Exception e)
        {
        }
        
        
        return "login";
    }
    
}
