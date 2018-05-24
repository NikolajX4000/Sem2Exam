package presentationLayer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CmdPageMaterial extends Command
{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response)
    {
        return "editMaterials";
    }

}
