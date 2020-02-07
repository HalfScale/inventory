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
import system.bean.ProductTransaction;
import system.bean.ProductTransactionDetail;
import system.dao.ProductTransactionDao;
import system.dao.ProductTransactionDetailDao;
import system.exception.MyException;

/**
 *
 * @author MacMuffin
 */
@WebServlet(
		name = "TransactionController", 
		urlPatterns = {
			"/productTransaction.create", 
			"/productTransaction.get", 
			"/productTransaction.getAll", 
			"/productTransaction.update", 
			"/productTransaction.delete",
			"/productTransaction.getAllDetails"
		}
)
public class ProductTransactionController extends HttpServlet {
	
	@Resource(name = "jdbc/inventory")
	private DataSource datasource;
	private Connection con;

	private static final Gson GSON = new Gson();

	public void init() throws ServletException {
		super.init();
		try {
			con = datasource.getConnection();
		} catch (SQLException ex) {
			Logger.getLogger(ProductTransactionController.class.getName()).log(Level.SEVERE, null, ex);
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
				case "/productTransaction.create":
//					this.createProduct(con, result, request);
					break;
				case "/productTransaction.get":
//					this.getProduct(con, result, request);
					break;
				case "/productTransaction.getAll":
					this.getAllProductTransaction(con, result);
					break;
				case "/productTransaction.getAllDetails":
					this.getAllProductDetails(con, result);
					break;
				case "/productTransaction.update":
//					this.updateProduct(con, result, request);
					break;
				case "/productTransaction.delete":
//					this.deleteProduct(con, result, request);
					break;
				case "/productTransaction.checkout":
//					this.checkout(con, result, request);
				default:
					break;
			}

			result.put("status", 0);
			out.println(GSON.toJson(result));
		} catch (SQLException ex) {
			Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
			result.put("status", ex.getErrorCode());
			result.put("response", ex.getMessage());
		} catch (MyException ex) {
			result.put("status", ex.getErrorCode());
			result.put("response", ex.getMessage());
		} catch (Exception ex) {
			result.put("status", 1);
			result.put("response", ex.getMessage());
		}
	}
	
//	private void createTransaction(Connection con, Map result, HttpServletRequest request) throws SQLException {
//		Brand brand = BrandDao.getById(con, Integer.parseInt(request.getParameter("brand")));
//		Category category = CategoryDao.getById(con, Integer.parseInt(request.getParameter("category")));
//
//		ProductTransaction product = new ProductTransaction(request, con);
//		product.setBrand(brand);
//		product.setCategory(category);
//		ProductTransactionDao.create(con, product);
//		result.put("data", product);
//		result.put("response", "Creation successful!");
//	}

//	private void getProductTransaction(Connection con, Map result, HttpServletRequest request) {
//		
//	}

	private void getAllProductTransaction(Connection con, Map result) throws SQLException {
		List<Map> dataList = new ArrayList<>();
		
		List<ProductTransaction> productTransactions = ProductTransactionDao.getAll(con);
		
		for (ProductTransaction productTransaction : productTransactions) {
			Map dataMap = new HashMap();
			dataMap.put("transaction", productTransaction);
			
			List<ProductTransactionDetail> transactionDetails = ProductTransactionDetailDao.getByProductTransactionId(con, productTransaction.getId());
			dataMap.put("transactionDetails", transactionDetails);
			dataList.add(dataMap);
		}
		
		result.put("data", dataList);
		result.put("response", "Query successful!");
	}

//	private void updateProductTransaction(Connection con, Map result, HttpServletRequest request) throws SQLException {
//		Brand brand = BrandDao.getById(con, Integer.parseInt(request.getParameter("brand")));
//		Category category = CategoryDao.getById(con, Integer.parseInt(request.getParameter("category")));
//
//		ProductTransaction product = new ProductTransaction(request, con);
//		product.setBrand(brand);
//		product.setCategory(category);
//		ProductTransactionDao.update(con, product);
//		result.put("data", product);
//		result.put("response", "Update successful!");
//	}
//
//	private void deleteProductTransaction(Connection con, Map result, HttpServletRequest request) throws SQLException {
//		int id = Integer.parseInt(request.getParameter("id"));
//		ProductTransactionDao.delete(con, id);
//		result.put("response", "Delete successful!");
//	}
	
	private void getAllProductDetails(Connection con, Map result) throws SQLException {
		List<ProductTransactionDetail> details = ProductTransactionDetailDao.getAll(con);
		result.put("data", details);
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
		return "Short description";
	}// </editor-fold>

}
