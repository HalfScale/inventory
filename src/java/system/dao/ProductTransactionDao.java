/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import system.bean.ProductTransaction;

/**
 *
 * @author MacMuffin
 */
public class ProductTransactionDao {

   private static final String SQL_CREATE_PRODUCT_TRANSACTION = "insert into `product_transaction` (transaction_type_id, timestamp) VALUES (?, ?)";
   
   public static void create(Connection con, ProductTransaction productTransaction) throws SQLException {
      try (PreparedStatement pstmt = con.prepareStatement(SQL_CREATE_PRODUCT_TRANSACTION, Statement.RETURN_GENERATED_KEYS)) {
         pstmt.setInt(1, productTransaction.getTransactionType().getId());
         pstmt.setTimestamp(2, productTransaction.getTimestamp());
         pstmt.executeUpdate();

         try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
               productTransaction.setId(generatedKeys.getInt(1));
            }
         }
      }
   }
}
