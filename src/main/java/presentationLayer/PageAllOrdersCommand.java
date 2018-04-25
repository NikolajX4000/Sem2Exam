
package presentationLayer;

import functionLayer.CustomException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageAllOrdersCommand extends Command{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CustomException {
        return "employeepage";
    }

}
