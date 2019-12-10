/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.servlet;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import system.bean.User;
import system.dao.UserController;

/**
 *
 * @author MacMuffin
 */
@WebServlet(
        name = "SystemServlet",
        urlPatterns = {"/WebLogin"}
)
public class WebLogin extends HttpServlet {

    @Resource(name = "jdbc/inventory")
    private DataSource datasource;
    private Connection con;

    public void init() throws ServletException {
        super.init();
        try {
            con = datasource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(WebLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Content-Type", "application/json");
        try (PrintWriter out = response.getWriter()) {

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

        Map result = new HashMap();
        Gson gson = new Gson();
        response.setHeader("Content-Type", "application/json");

        try (PrintWriter out = response.getWriter()) {

            try {
                String username = request.getParameter("user");
                String password = request.getParameter("pass");
                
                User user = UserController.getUser(con, username, password);
                System.out.println("username");
                System.out.println("password");

                if (user != null) {
                    request.getSession().setAttribute("active_user", user);
                    result.put("status", 0);
                    result.put("response", "login succesful!");
                } else {
                    result.put("status", 1);
                    result.put("response", "Incorrect username and password!");
                }

            } catch (SQLException ex) {
                System.out.println("Exception " + ex.getMessage());
                result.put("status", 1);
                result.put("response", ex.getMessage());
            } catch (Exception ex) {
                System.out.println("Exception " + ex.getMessage());
                result.put("status", 1);
                result.put("response", ex.getMessage());
            }
            
            out.println(gson.toJson(result));
        }
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
