package presentationLayer;

import functionLayer.CustomException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author super
 */
public class UpdateOrderCommand extends Command{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CustomException {
        
        return "employeepage";
    }
}
