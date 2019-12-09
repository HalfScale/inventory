/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

/**
 *
 * @author MacMuffin
 */
@WebServlet(name = "BrandController", urlPatterns = {"/brand.create"})
public class BrandController extends HttpServlet {

   @Resource(name = "jdbc/inventory")
   private DataSource datasource;
   private Connection con;
   
   private static final String SQL_CREATE_BRAND = "insert into `brand` (name, status) VALUES (?, ?)";
   private static final String SQL_GET_BRAND_BY_ID = "select * from `brand` where id = ?";
   private static final String SQL_GET_ALL_BAND = "select * from `brand`";
   private static final String SQL_UPDATE_BRAND = "update brand set name = ?, status =?";
   private static final String SQL_DELETE_PRODUCT = "select * from brand";

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
      response.setContentType("text/html;charset=UTF-8");
      try (PrintWriter out = response.getWriter()) {
         System.out.println("url /brand.create works!");
//         switch (request.getServletPath()) {
//            case "/brand.create":
//               BrandController.createBrand(con, request);
//               break;
//            default:
//               throw new AssertionError();
//         }

      }
   }
   
   public static void createBrand(Connection con, HttpServletRequest request) {
      
      Brand brand = new Brand(request);
      
      try (PreparedStatement pstmt = con.prepareStatement(SQL_CREATE_BRAND, Statement.RETURN_GENERATED_KEYS)) {
         pstmt.setString(1, brand.getName());
         pstmt.setBoolean(2, brand.getStatus());
         pstmt.executeUpdate();

         try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
               brand.setId(generatedKeys.getInt(1));
            }
         }

      } catch (SQLException ex) {
         Logger.getLogger(system.controller.BrandController.class.getName()).log(Level.SEVERE, null, ex);
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
