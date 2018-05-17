
package presentationLayer;

import functionLayer.CustomException;
import functionLayer.LogicFacade;
import functionLayer.Roof;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author super
 */
public class CmdPageBuildCarport extends Command{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CustomException {
        return "order";
    }

}
