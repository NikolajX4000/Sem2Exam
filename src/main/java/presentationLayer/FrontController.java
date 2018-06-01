package presentationLayer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicLayer.NoAccessException;

/**
 * This controller handles what content to display on the website with the help of different Commands
 * @author super
 */
@WebServlet(name = "FrontController", urlPatterns = {"/"})
public class FrontController extends HttpServlet {

    /**
     * The FrontController finds the desired command from the HttpServletRequest and loads the correct .jsp file.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException if a file (404) or a forbidden location is tried to be accessed (403) a SevletException is thrown
     * @throws IOException is thrown whenever an input or outpur operation fails // trying to read/wrtie you don't have acces to // trying to read a file that is no longer available
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        response.setBufferSize(0);
        Command command = Command.from(request);
        try
        {
            String view = command.execute(request, response);
            request.getRequestDispatcher("/WEB-INF/views/" + view + ".jsp").forward(request, response);
            
        } catch (NoAccessException ex)
        {   
            request.setAttribute("feedback", ex.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>    
}
