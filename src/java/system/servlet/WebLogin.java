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
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import system.bean.User;
import system.dao.UserDao;
import system.exception.MyException;

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
    private final static Logger logger = LogManager.getLogger(WebLogin.class);

    public void init() throws ServletException {
        super.init();
        System.out.println("logger not started!");
        try {
            con = datasource.getConnection();
        } catch (SQLException ex) {
            logger.debug("SQL Exception", ex);
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
        response.setHeader("Content-Type", "application/json");

        Map result = new HashMap();
        Gson gson = new Gson();
        
        try (PrintWriter out = response.getWriter()) {

            try {
                String username = request.getParameter("user");
                String password = request.getParameter("pass");
                
                User user = UserDao.getByUsernameAndPassword(con, username, password);
                if (user != null) {
                    logger.debug("User " + user.getFirstName() + " " + user.getLastName() + " logging in...");
					
					if (user.getStatus()) {
						request.getSession().setAttribute("active_user", user);
						result.put("status", 0);
						result.put("response", "login succesful!");
					}else {
						throw new MyException("User is inactive. Check with your administrator.", 2);
					}
					
                } else {
                    logger.debug("User does not exist!");
					throw new MyException("Incorrect username or password! Try again.", 3);
                }

            } catch(MyException ex) {
			
                result.put("status", ex.getErrorCode());
                result.put("response", ex.getMessage());
			}catch (SQLException ex) {
				
                result.put("status", ex.getErrorCode());
                result.put("response", ex.getMessage());
            } catch (Exception ex) {
                logger.debug(ex.getMessage(), ex);
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
