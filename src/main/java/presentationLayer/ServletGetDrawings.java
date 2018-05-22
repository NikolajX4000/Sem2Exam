/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentationLayer;

import functionLayer.Order;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hupra
 */
@WebServlet(name = "ServletGetDrawings", urlPatterns = {"/ServletGetDrawings"})
public class ServletGetDrawings extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //request.setAttribute("test", request.getParameter("id"));
        try {

            int width = Integer.parseInt(request.getParameter("width"));
            int shedWidth = Integer.parseInt(request.getParameter("shed_width"));
            int length = Integer.parseInt(request.getParameter("length"));
            int shedLength = Integer.parseInt(request.getParameter("shed_length"));
            
            Order o = new Order().setWidth(width).setLength(length);
            
            if(request.getParameter("has_shed").equals("true")){
                o.setShedWidth(shedWidth).setShedLength(shedLength);
            }
            
            if(request.getParameter("has_angle").equals("true")){
                o.setAngle(Integer.parseInt(request.getParameter("angle")));
            }

            try (PrintWriter out = response.getWriter()) {
                out.println(o.getDrawingTop());
                out.println(o.getDrawingSide());
                //out.println("ANGLE: " + request.getParameter("has_angle") + " | SHED: " + request.getParameter("has_shed"));
            }

        } catch (Exception e) {
            
            try (PrintWriter out = response.getWriter()) {
                out.println("<p>Der gik noget galt, prøv igen senere.</p>");
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
