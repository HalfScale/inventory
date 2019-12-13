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
import system.bean.Category;

/**
 *
 * @author Muffin
 */
public class CategoryDao {
	private static final String SQL_CREATE_CATEGORY = "insert into `category` (name, status) VALUES (?, ?)";
	private static final String SQL_GET_CATEGORY_BY_ID = "select * from `category` where id = ?";
	private static final String SQL_GET_ALL_CATEGORY = "select * from `category`";
	private static final String SQL_UPDATE_CATEGORY = "update `category` set name = ?, status = ? where id = ?";
	private static final String SQL_DELETE_CATEGORY = "delete from `category` where id = ?";
	
	public static void create(Connection con, Category category) throws SQLException {
		try (PreparedStatement pstmt = con.prepareStatement(SQL_CREATE_CATEGORY, Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, category.getName());
			pstmt.setBoolean(2, category.getStatus());
			pstmt.executeUpdate();

			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					category.setId(generatedKeys.getInt(1));
				}
			}
		}
	}

	public static Category getById(Connection con, int id) throws SQLException {
		Category category = new Category();
		try (PreparedStatement pstmt = con.prepareStatement(SQL_GET_CATEGORY_BY_ID)) {
			pstmt.setInt(1, id);

			try (ResultSet rs = pstmt.executeQuery()) {
				category.setId(rs.getInt(1));
				category.setName(rs.getString(2));
				category.setStatus(rs.getBoolean(3));
			}
		}

		return category;
	}

	public static List<Category> getAll(Connection con) throws SQLException {
		List<Category> categories = new ArrayList<>();
		try (PreparedStatement pstmt = con.prepareStatement(SQL_GET_ALL_CATEGORY);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt(1));
				category.setName(rs.getString(2));
				category.setStatus(rs.getBoolean(3));
				categories.add(category);
			}
		}

		return categories;
	}

	public static void update(Connection con, Category category) throws SQLException {
		try (PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_CATEGORY)) {
			pstmt.setString(1, category.getName());
			pstmt.setBoolean(2, category.getStatus());
			pstmt.setInt(3, category.getId());
			pstmt.executeUpdate();
		}
	}

	public static void delete(Connection con, int id) throws SQLException {
		try (PreparedStatement pstmt = con.prepareStatement(SQL_DELETE_CATEGORY)) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		}
	}
}
