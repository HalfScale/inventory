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
import system.bean.ProductTransactionDetail;
import system.dao.ProductTransactionDetailDao;

/**
 *
 * @author MacMuffin
 */
@WebServlet(
        name = "ProductTransactionDetailController",
        urlPatterns = {
			"/productTransactionDetail.create", 
			"/productTransactionDetail.get", 
			"/productTransactionDetail.getAll", 
			"/productTransactionDetail.update", 
			"/productTransactionDetail.delete"
		}
)
public class ProductTransactionDetailController extends HttpServlet {

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
            case "/productTransactionDetail.create":
               this.createProductTransactionDetail(con, result, request);
               break;
            case "/productTransactionDetail.get":
               this.getProductTransactionDetail(con, result, request);
               break;
            case "/productTransactionDetail.getAll":
               this.getAllProductTransactionDetail(con, result);
               break;
            case "/productTransactionDetail.update":
               this.updateProductTransactionDetail(con, result, request);
               break;
            case "/productTransactionDetail.delete":
               this.deleteProductTransactionDetail(con, result, request);
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

   private void createProductTransactionDetail(Connection con, Map result, HttpServletRequest request) throws SQLException {
      ProductTransactionDetail productTransactionDetail = new ProductTransactionDetail(request);
      ProductTransactionDetailDao.create(con, productTransactionDetail);
      result.put("data", productTransactionDetail);
      result.put("status", 0);
      result.put("response", "Creation successful!");
   }

   private void getProductTransactionDetail(Connection con, Map result, HttpServletRequest request) throws SQLException {
      int id = Integer.parseInt(Util.isBlank(request.getParameter("id"), "-1"));
      ProductTransactionDetail productTransactionDetail = ProductTransactionDetailDao.getById(con, id);
      result.put("data", productTransactionDetail);
      result.put("status", 0);
      result.put("response", "Query successful!");
   }

   private void getAllProductTransactionDetail(Connection con, Map result) throws SQLException {
      List<ProductTransactionDetail> productTransactionDetails = ProductTransactionDetailDao.getAll(con);
      result.put("data", productTransactionDetails);
      result.put("status", 0);
      result.put("response", "Query successful!");
   }

   private void updateProductTransactionDetail(Connection con, Map result, HttpServletRequest request) throws SQLException {
      ProductTransactionDetail productTransactionDetail = new ProductTransactionDetail(request);
      ProductTransactionDetailDao.update(con, productTransactionDetail);
      result.put("data", productTransactionDetail);
      result.put("status", 0);
      result.put("response", "Update successful!");
   }

   private void deleteProductTransactionDetail(Connection con, Map result, HttpServletRequest request) throws SQLException {
      int id = Integer.parseInt(request.getParameter("id"));
      ProductTransactionDetailDao.delete(con, id);
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
      return "ProductTransactionDetailController Servlet";
   }// </editor-fold>

}
