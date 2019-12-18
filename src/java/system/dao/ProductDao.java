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
import system.bean.Brand;
import system.bean.Category;
import system.bean.Product;

/**
 *
 * @author Muffin
 */
public class ProductDao {

	private static final String SQL_CREATE_PRODUCT = "insert into `product` (brand_id, category_id, name, code, description, price, reseller_price, stock, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_GET_PRODUCT_BY_ID = "select * from `product` where id = ?";
	private static final String SQL_GET_ALL_PRODUCT = "select * from `product`";
	private static final String SQL_UPDATE_PRODUCT = "update `product` set brand_id = ?, category_id = ?, name = ?, code = ?, description = ?, price = ?, reseller_price = ?, stock = ?, status = ? where id = ?";
	private static final String SQL_DELETE_PRODUCT = "delete from `product` where id = ?";

	public static void create(Connection con, Product product) throws SQLException {
		try (PreparedStatement pstmt = con.prepareStatement(SQL_CREATE_PRODUCT, Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setInt(1, product.getBrand().getId());
			pstmt.setInt(2, product.getCategory().getId());
			pstmt.setString(3, product.getName());
			pstmt.setString(4, product.getCode());
			pstmt.setString(5, product.getDescription());
			pstmt.setBigDecimal(6, product.getPrice());
			pstmt.setBigDecimal(7, product.getResellerPrice());
			pstmt.setInt(8, product.getStock());
			pstmt.setBoolean(9, product.getStatus());
			pstmt.executeUpdate();

			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					product.setId(generatedKeys.getInt(1));
				}
			}
		}
	}

	public static Product getById(Connection con, int id) throws SQLException {
		Product product = new Product();
		try (PreparedStatement pstmt = con.prepareStatement(SQL_GET_PRODUCT_BY_ID)) {
			pstmt.setInt(1, id);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					product.setId(rs.getInt(1));
					Brand brand = BrandDao.getById(con, rs.getInt(2));
					product.setBrand(brand);
					Category category = CategoryDao.getById(con, rs.getInt(3));
					product.setCategory(category);
					product.setName(rs.getString(4));
					product.setCode(rs.getString(5));
					product.setDescription(rs.getString(6));
					product.setPrice(rs.getBigDecimal(7));
					product.setResellerPrice(rs.getBigDecimal(8));
					product.setStock(rs.getInt(9));
					product.setStatus(rs.getBoolean(10));
				}
			}
		}

		return product;
	}

	public static List<Product> getAll(Connection con) throws SQLException {
		List<Product> list = new ArrayList<>();
		try (PreparedStatement pstmt = con.prepareStatement(SQL_GET_ALL_PRODUCT);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				Product product = ProductDao.getById(con, rs.getInt(1));
				list.add(product);
			}
		}
		return list;
	}

	public static void update(Connection con, Product product) throws SQLException {
		try (PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_PRODUCT)) {
			pstmt.setInt(1, product.getBrand().getId());
			pstmt.setInt(2, product.getCategory().getId());
			pstmt.setString(3, product.getName());
			pstmt.setString(4, product.getCode());
			pstmt.setString(5, product.getDescription());
			pstmt.setBigDecimal(6, product.getPrice());
			pstmt.setBigDecimal(7, product.getResellerPrice());
			pstmt.setInt(8, product.getStock());
			pstmt.setBoolean(9, product.getStatus());
			pstmt.setInt(10, product.getId());
			pstmt.executeUpdate();
		}
	}

	public static void delete(Connection con, int id) throws SQLException {
		try (PreparedStatement pstmt = con.prepareStatement(SQL_DELETE_PRODUCT)) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		}
	}
}
