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
import system.bean.ProductTransactionDetail;

/**
 *
 * @author MacMuffin
 */
public class ProductTransactionDetailDao {
   private static final String SQL_CREATE_PRODUCT_TRANSACTION_DETAIL = "insert into `product_transaction_detail` (product_transaction_id, product_id, quantity, is_reseller_price) VALUES (?, ?, ?, ?)";
   
   public static void create(Connection con, ProductTransactionDetail productTransactionDetail) throws SQLException {
	   System.out.println("Creating product transaction detail");
		try (PreparedStatement pstmt = con.prepareStatement(SQL_CREATE_PRODUCT_TRANSACTION_DETAIL, Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setInt(1, productTransactionDetail.getProductTransaction().getId());
			pstmt.setInt(2, productTransactionDetail.getProduct().getId());
			pstmt.setInt(3, productTransactionDetail.getQuantity());
			pstmt.setBoolean(4, productTransactionDetail.isIsReseller());
			pstmt.executeUpdate();

			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					productTransactionDetail.setId(generatedKeys.getInt(1));
				}
			}
		}
	}
}
