/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentationLayer;

import logicLayer.CustomException;
import logicLayer.LogicFacade;
import logicLayer.Order;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Prints a partlist from a given Order ID
 * @author Hupra
 */
@WebServlet(name = "ServletGetPartlist", urlPatterns = {"/ServletGetPartlist"})
public class ServletGetPartlist extends HttpServlet {

    /**
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {

            int id = Integer.parseInt(request.getParameter("id"));
            Order o = LogicFacade.getOrder(id);

            request.setAttribute("items", o.getPartlist());

            request.getRequestDispatcher("/WEB-INF/parts/partlist.jsp").forward(request, response);

        } catch (IOException | NumberFormatException | ServletException ex) {

            try (PrintWriter out = response.getWriter()) {
                out.println("<p>Der gik noget galt, prøv igen senere.</p>");
        }

        } catch (CustomException ex) {

            try (PrintWriter out = response.getWriter()) {
                out.println("<p>" + ex.getMessage() + "</p>");
            }
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
