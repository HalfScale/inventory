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
import java.util.ArrayList;
import java.util.List;
import system.bean.TransactionType;
/**
 *
 * @author MacMuffin
 */
public class TransactionTypeDao {
   
   private static final String SQL_GET_ALL_TRANSACTION_TYPE = "select * from `transaction_type`";
   private static final String SQL_GET_TRANSACTION_TYPE_BY_ID = "select * from `transaction_type` where id = ?";
   
   public static TransactionType getById(Connection con, int id) throws SQLException {
      TransactionType transactionType = new TransactionType();
      try (PreparedStatement pstmt = con.prepareStatement(SQL_GET_TRANSACTION_TYPE_BY_ID)) {
         pstmt.setInt(1, id);

         try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
               transactionType.setId(rs.getInt(1));
               transactionType.setName(rs.getString(2));
            }
         }
      }

      return transactionType;
   }
   
   public static List<TransactionType> getAll(Connection con) throws SQLException {
      List<TransactionType> transactionTypes = new ArrayList<>();
      try (PreparedStatement pstmt = con.prepareStatement(SQL_GET_ALL_TRANSACTION_TYPE);
              ResultSet rs = pstmt.executeQuery()) {

         while (rs.next()) {
            TransactionType transactionType = TransactionTypeDao.getById(con, rs.getInt(1));
            transactionTypes.add(transactionType);
         }
      }

      return transactionTypes;
   }
   
}
