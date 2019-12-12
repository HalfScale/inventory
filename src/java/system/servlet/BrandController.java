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
import system.bean.Brand;
import system.dao.BrandDao;

/**
 *
 * @author MacMuffin
 */
@WebServlet(
		name = "BrandController",
		urlPatterns = {"/brand.create", "/brand.get", "/brand.getAll", "/brand.update", "/brand.delete"}
)
public class BrandController extends HttpServlet {

	@Resource(name = "jdbc/inventory")
	private DataSource datasource;
	private Connection con;
	
	private static final Gson GSON = new Gson();

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
		Map result = new HashMap();
		
		try (PrintWriter out = response.getWriter()) {

			switch (request.getServletPath()) {
				case "/brand.create":
					this.createBrand(con, result, request);
					break;
				case "/brand.get":
					this.getBrand(con, result, request);
					break;
				case "/brand.getAll":
					this.getAllBrand(con, result);
					break;
				case "/brand.update":
					this.updateBrand(con, result, request);
					break;
				case "/brand.delete":
					this.deleteBrand(con, result, request);
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

	private void createBrand(Connection con, Map result, HttpServletRequest request) throws SQLException {
		Brand brand = new Brand(request);
		BrandDao.createBrand(con, brand);
		result.put("data", brand);
		result.put("status", 0);
		result.put("response", "Creation successful!");
	}

	private void getBrand(Connection con, Map result, HttpServletRequest request) {

	}

	private void getAllBrand(Connection con, Map result) throws SQLException {
		List<Brand> brands = BrandDao.getAllBrands(con);
		result.put("data", brands);
		result.put("status", 0);
		result.put("response", "Query successful!");
	}

	private void updateBrand(Connection con, Map result, HttpServletRequest request) throws SQLException {
		Brand brand = new Brand(request);
		BrandDao.updateBrand(con, brand);
		result.put("data", brand);
		result.put("status", 0);
		result.put("response", "Update successful!");
	}

	private void deleteBrand(Connection con, Map result, HttpServletRequest request) throws SQLException{
		int id = Integer.parseInt(request.getParameter("id"));
		BrandDao.deleteBrand(con, id);
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
		return "Short description";
	}// </editor-fold>

}
