package presentationLayer;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicLayer.NoAccessException;

/**
 *
 * @author super
 */
abstract class Command
{

    private static HashMap<String, Command> commands;

    private static void initCommands()
    {
        commands = new HashMap<>();
        commands.put("CmdCreateOrder", new CmdCreateOrder());
        commands.put("CmdShowOrders", new CmdShowOrders());
        commands.put("CmdUpdateOrder", new CmdUpdateOrder());

        commands.put("CmdPageBuildCarport", new CmdPageBuildCarport());
        commands.put("CmdPageAllOrders", new CmdPageAllOrders());
        commands.put("CmdPageMaterial", new CmdPageMaterial());
        commands.put("CmdPageLogin", new CmdPageLogin());

        commands.put("CmdUpdateMaterial", new CmdUpdateMaterial());
        commands.put("CmdUpdateTool", new CmdUpdateTool());
        commands.put("CmdUpdateRoof", new CmdUpdateRoof());

        commands.put("CmdLogin", new CmdLogin());
        commands.put("CmdLogout", new CmdLogout());
    }

    static Command from(HttpServletRequest request)
    {
        if (commands == null)
        {
            initCommands();
        }
        return commands.getOrDefault(request.getParameter("command"), new CmdUnknown());
    }

    abstract String execute(HttpServletRequest request, HttpServletResponse response)throws NoAccessException;

}
