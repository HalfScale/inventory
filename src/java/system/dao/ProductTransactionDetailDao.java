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
import java.util.ArrayList;
import java.util.List;
import system.bean.Product;
import system.bean.ProductTransaction;
import system.bean.ProductTransactionDetail;

/**
 *
 * @author MacMuffin
 */
public class ProductTransactionDetailDao {

	private static final String SQL_CREATE_PRODUCT_TRANSACTION_DETAIL = "insert into `product_transaction_detail` (product_transaction_id, product_id, quantity, is_reseller_price) VALUES (?, ?, ?, ?)";
	private static final String SQL_GET_BY_PRODUCT_TRANSACTION_ID = "select * from `product_transaction_detail` where `product_transaction_id` = ?";
	private static final String SQL_GET_ALL_PRODUCT_TRANSACTION_DETAIL = "select * from `product_transaction_detail`";
	private static final String SQL_GET_TABLE_BY_ID = "select * from `product_transaction_detail` where id = ?";

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

	public static ProductTransactionDetail getById(Connection con, int id) throws SQLException {
		ProductTransactionDetail productTransactionDetail = new ProductTransactionDetail();
		ResultSet rs = null;
		
		try (PreparedStatement pstmt = con.prepareStatement(SQL_GET_TABLE_BY_ID)) {
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				ProductTransaction productTransaction = ProductTransactionDao.getById(con, rs.getInt(2));
				Product product = ProductDao.getById(con, rs.getInt(3));

				productTransactionDetail.setId(rs.getInt(1));
				productTransactionDetail.setProductTransaction(productTransaction);
				productTransactionDetail.setProduct(product);
				productTransactionDetail.setQuantity(rs.getInt(4));
				productTransactionDetail.setIsReseller(rs.getBoolean(5));
			}
		}

		return productTransactionDetail;
	}
	
	public static List<ProductTransactionDetail> getAll(Connection con) throws SQLException {
		List<ProductTransactionDetail> transactionDetails = new ArrayList<>();
		try (PreparedStatement pstmt = con.prepareStatement(SQL_GET_ALL_PRODUCT_TRANSACTION_DETAIL);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				ProductTransactionDetail transactionDetail = ProductTransactionDetailDao.getById(con, rs.getInt(1));
				transactionDetails.add(transactionDetail);
			}
		}

		return transactionDetails;
	}

	public static List<ProductTransactionDetail> getByProductTransactionId(Connection con, int id) throws SQLException {
		List<ProductTransactionDetail> productTransactionDetails = new ArrayList<>();
		try (PreparedStatement pstmt = con.prepareStatement(SQL_GET_BY_PRODUCT_TRANSACTION_ID)) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
					
			while (rs.next()) {
				ProductTransactionDetail productTransactionDetail = ProductTransactionDetailDao.getById(con, rs.getInt(1));
				productTransactionDetails.add(productTransactionDetail);
			}
		}
		return productTransactionDetails;
	}

}
