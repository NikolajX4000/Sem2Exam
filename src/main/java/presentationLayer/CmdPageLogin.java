/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentationLayer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CmdPageLogin extends Command
{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response)
    {
        return "login";
    }

}
