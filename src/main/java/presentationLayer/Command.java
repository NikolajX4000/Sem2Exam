package presentationLayer;

import functionLayer.CustomException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author super
 */
abstract class Command {

    private static HashMap<String, Command> commands;

    private static void initCommands() {
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
    }

    static Command from(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        if (commands == null) {
            initCommands();
        }
        return commands.getOrDefault(commandName, new UnknownCommand());
    }

    abstract String execute(HttpServletRequest request, HttpServletResponse response)
            throws CustomException;

}
