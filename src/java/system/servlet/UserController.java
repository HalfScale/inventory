package system.servlet;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
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
import org.apache.commons.lang3.StringUtils;
import system.bean.Role;
import system.bean.User;
import system.bean.UserRole;
import system.dao.RoleDao;
import system.dao.UserDao;
import system.dao.UserRoleDao;
import system.logger.Console;
import system.util.Util;

/**
 *
 * @author MacMuffin
 */
@WebServlet(
        name = "UserController",
        urlPatterns = {"/user.create", "/user.get", "/user.getAll", "/user.update", "/user.delete"}
)
public class UserController extends HttpServlet {

    @Resource(name = "jdbc/inventory")
    private DataSource datasource;
    private Connection con;

    private static final Gson GSON = new Gson();

    public void init() throws ServletException {
        super.init();
        try {
            con = datasource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(BrandController.class.getName()).log(Level.SEVERE, null, ex);
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
        Map result = new HashMap();
        System.out.println("request.getServletPath() " + request.getServletPath());
        try (PrintWriter out = response.getWriter()) {

            switch (request.getServletPath()) {
                case "/user.create":
                    this.createUser(con, result, request);
                    break;
                case "/user.get":
                    this.getUser(con, result, request);
                    break;
                case "/user.getAll":
                    this.getAllUser(con, result);
                    break;
                case "/user.update":
                    this.updateUser(con, result, request);
                    break;
                case "/user.delete":
                    this.deleteUser(con, result, request);
                    break;
                default:
                    break;
            }

            out.println(GSON.toJson(result));
        } catch (SQLException ex) {
            Logger.getLogger(BrandController.class.getName()).log(Level.SEVERE, null, ex);
            result.put("status", ex.getErrorCode());
            result.put("response", ex.getMessage());
        } catch (Exception ex) {
            result.put("status", 1);
            result.put("response", ex.getMessage());
        }
    }

    private void createUser(Connection con, Map result, HttpServletRequest request) throws SQLException {
        User user = new User(request);
        UserDao.create(con, user);
		
		int roleId = Integer.parseInt(Util.isBlank(request.getParameter("role"), "-1"));
		Role role = RoleDao.getById(con, roleId);
		
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		
		UserRoleDao.create(con, userRole);
		
        result.put("data", user);
        result.put("status", 0);
        result.put("response", "Creation successful!");
    }

    private void getUser(Connection con, Map result, HttpServletRequest request) throws SQLException {

    }

    private void getAllUser(Connection con, Map result) throws SQLException {
        List<User> users = UserDao.getAll(con);
        result.put("data", users);
        result.put("status", 0);
        result.put("response", "Query successful!");
    }

    private void updateUser(Connection con, Map result, HttpServletRequest request) throws SQLException {
        User user = new User(request);
		int roleId = Integer.parseInt(Util.isBlank(request.getParameter("role"), "-1"));
        //If user didn't update her/his password
        //Then get the previous password
        if (StringUtils.isBlank(user.getPassword())) {
            User previousUserData = UserDao.getById(con, user.getId());
            user.setPassword(previousUserData.getPassword());
        }
        UserDao.update(con, user);
        result.put("data", user);
        result.put("status", 0);
        result.put("response", "Update successful!");
    }

    private void deleteUser(Connection con, Map result, HttpServletRequest request) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        UserDao.delete(con, id);
        result.put("status", 0);
        result.put("response", "Delete successful!");
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
        return "UserController Servlet";
    }// </editor-fold>

}
