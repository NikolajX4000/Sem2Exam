/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package presentationLayer;

import functionLayer.CustomException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CmdPageMaterial extends Command{
    
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CustomException {
        return "materialpage";
    }


}
