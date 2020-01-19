package system.servlet;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import system.bean.Module;
import system.bean.Role;
import system.bean.RoleModule;
import system.dao.ModuleDao;
import system.dao.RoleDao;
import system.dao.RoleModuleDao;
import system.logger.Console;
import system.util.Util;

/**
 *
 * @author MacMuffin
 */
@WebServlet(
		name = "RoleController",
		urlPatterns = {
			"/role.create", 
			"/role.get", 
			"/role.getAll", 
			"/role.update", 
			"/role.delete",
			"/role.getAllWithModules"
		}
)
public class RoleController extends HttpServlet {

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

		try (PrintWriter out = response.getWriter()) {

			switch (request.getServletPath()) {
				case "/role.create":
					this.createRole(con, result, request);
					break;
				case "/role.get":
					this.getRole(con, result, request);
					break;
				case "/role.getAll":
					this.getAllRole(con, result);
					break;
				case "/role.update":
					this.updateRole(con, result, request);
					break;
				case "/role.delete":
					this.deleteRole(con, result, request);
					break;
				case "/role.getAllWithModules":
					this.getAllWithModules(con, result, request);
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

	private void createRole(Connection con, Map result, HttpServletRequest request) throws SQLException {
		Role role = new Role(request);
		RoleDao.create(con, role);
		Console.log("Inside create role");
		String[] modules = request.getParameterValues("modules[]");
		for (String id : modules) {
			Module module = ModuleDao.getById(con, Integer.parseInt(id));

			RoleModule roleModule = new RoleModule();
			roleModule.setRoleId(role.getId());
			roleModule.setModuleId(module.getId());

			RoleModuleDao.create(con, roleModule);
		}

		result.put("data", role);
		result.put("status", 0);
		result.put("response", "Creation successful!");
	}

	private void getRole(Connection con, Map result, HttpServletRequest request) throws SQLException {
		int id = Integer.parseInt(Util.isBlank(request.getParameter("id"), "-1"));
		Role role = RoleDao.getById(con, id);
		result.put("data", role);
		result.put("status", 0);
		result.put("response", "Query successful!");
	}

	private void getAllRole(Connection con, Map result) throws SQLException {
		List<Role> roles = RoleDao.getAll(con);
		result.put("data", roles);
		result.put("status", 0);
		result.put("response", "Query successful!");
	}

	private void updateRole(Connection con, Map result, HttpServletRequest request) throws SQLException {
		Role role = new Role(request);
		RoleDao.update(con, role);
		result.put("data", role);
		result.put("status", 0);
		result.put("response", "Update successful!");
	}

	private void deleteRole(Connection con, Map result, HttpServletRequest request) throws SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		RoleDao.delete(con, id);
		result.put("status", 0);
		result.put("response", "Delete successful!");
	}
	
	private void getAllWithModules(Connection con, Map result, HttpServletRequest request) throws SQLException {
		List<Role> roles = new ArrayList<>();
		
		for (Role role : RoleDao.getAll(con)) {
			Role newRoleObject = RoleDao.getRoleWithModules(con, role.getId());
			roles.add(newRoleObject);
		}
		
		result.put("data", roles);
		result.put("status", 0);
		result.put("response", "Query successful!");
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
		return "RoleController Servlet";
	}// </editor-fold>

}
