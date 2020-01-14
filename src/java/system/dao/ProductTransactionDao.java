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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import system.bean.ProductTransaction;
import system.bean.TransactionType;
import system.bean.User;
import system.logger.Console;
import system.util.Util;

/**
 *
 * @author MacMuffin
 */
public class ProductTransactionDao {

	private static final String SQL_CREATE_PRODUCT_TRANSACTION = "insert into `product_transaction` (user_id, transaction_type_id, timestamp) VALUES (?, ?, ?)";
	private static final String SQL_GET_PRODUCT_TRANSACTION_BY_ID = "select * from `product_transaction` where id = ?";
	private static final String SQL_GET_ALL_PRODUCT_TRANSACTION = "select * from `product_transaction`";

	public static void create(Connection con, ProductTransaction productTransaction) throws SQLException {
		try (PreparedStatement pstmt = con.prepareStatement(SQL_CREATE_PRODUCT_TRANSACTION, Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setInt(1, productTransaction.getUser().getId());
			pstmt.setInt(2, productTransaction.getTransactionType().getId());
			pstmt.setTimestamp(3, productTransaction.getTimestamp());
			pstmt.executeUpdate();
			Console.log("Product Transaction Dao!");
			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					productTransaction.setId(generatedKeys.getInt(1));
				}
			}
		}
	}

	public static ProductTransaction getById(Connection con, int id) throws SQLException {
		ProductTransaction productTransaction = new ProductTransaction();
		ResultSet rs = null;

		try (PreparedStatement pstmt = con.prepareStatement(SQL_GET_PRODUCT_TRANSACTION_BY_ID)) {
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				TransactionType transactionType = TransactionTypeDao.getById(con, rs.getInt(3));
				User user = UserDao.getById(con, rs.getInt(2));
				LocalDateTime dateTime = LocalDateTime.now();

				productTransaction.setId(rs.getInt(1));
				productTransaction.setUser(user);
				productTransaction.setTransactionType(transactionType);
				productTransaction.setTimestamp(Util.toTimestamp(dateTime));
			}
		}

		return productTransaction;
	}

	public static List<ProductTransaction> getAll(Connection con) throws SQLException {
		List<ProductTransaction> productTransactions = new ArrayList<>();
		try (PreparedStatement pstmt = con.prepareStatement(SQL_GET_ALL_PRODUCT_TRANSACTION);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				ProductTransaction productTransaction = ProductTransactionDao.getById(con, rs.getInt(1));
				productTransactions.add(productTransaction);
			}
		}

		return productTransactions;
	}
}
