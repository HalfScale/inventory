/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import system.bean.Brand;

/**
 *
 * @author Muffin
 */
public class BrandController {

   private static final String SQL_CREATE_BRAND = "insert into `brand` (name, status) VALUES (?, ?)";
   private static final String SQL_GET_BRAND_BY_ID = "select * from `brand` where id = ?";
   private static final String SQL_GET_ALL_BAND = "select * from `brand`";
   private static final String SQL_UPDATE_BRAND = "update brand set name = ?, status =?";
   private static final String SQL_DELETE_PRODUCT = "select * from brand";

   public static void createBrand(Connection con, Brand brand) {
      try (PreparedStatement pstmt = con.prepareStatement(SQL_CREATE_BRAND, Statement.RETURN_GENERATED_KEYS)) {
         pstmt.setString(1, brand.getName());
         pstmt.setBoolean(2, brand.getStatus());
         pstmt.executeUpdate();
         
         try(ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
               brand.setId(generatedKeys.getInt(1));
            }
         }
         
      } catch (SQLException ex) {
         Logger.getLogger(BrandController.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

}
