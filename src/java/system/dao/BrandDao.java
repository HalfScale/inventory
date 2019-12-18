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
import java.util.logging.Level;
import java.util.logging.Logger;
import system.bean.Brand;

/**
 *
 * @author Muffin
 */
public class BrandDao {

	private static final String SQL_CREATE_BRAND = "insert into `brand` (name, status) VALUES (?, ?)";
	private static final String SQL_GET_BRAND_BY_ID = "select * from `brand` where id = ?";
	private static final String SQL_GET_ALL_BRAND = "select * from `brand`";
	private static final String SQL_UPDATE_BRAND = "update `brand` set name = ?, status = ? where id = ?";
	private static final String SQL_DELETE_BRAND = "delete from `brand` where id = ?";

	public static void create(Connection con, Brand brand) throws SQLException {
		try (PreparedStatement pstmt = con.prepareStatement(SQL_CREATE_BRAND, Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, brand.getName());
			pstmt.setBoolean(2, brand.getStatus());
			pstmt.executeUpdate();

			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					brand.setId(generatedKeys.getInt(1));
				}
			}
		}
	}

	public static Brand getById(Connection con, int id) throws SQLException {
		Brand brand = new Brand();
		try (PreparedStatement pstmt = con.prepareStatement(SQL_GET_BRAND_BY_ID)) {
			pstmt.setInt(1, id);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					brand.setId(rs.getInt(1));
					brand.setName(rs.getString(2));
					brand.setStatus(rs.getBoolean(3));
				}
			}
		}

		return brand;
	}

	public static List<Brand> getAll(Connection con) throws SQLException {
		List<Brand> brands = new ArrayList<>();
		try (PreparedStatement pstmt = con.prepareStatement(SQL_GET_ALL_BRAND);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				Brand brand = BrandDao.getById(con, rs.getInt(1));
				brands.add(brand);
			}
		}

		return brands;
	}

	public static void update(Connection con, Brand brand) throws SQLException {
		try (PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_BRAND)) {
			pstmt.setString(1, brand.getName());
			pstmt.setBoolean(2, brand.getStatus());
			pstmt.setInt(3, brand.getId());
			pstmt.executeUpdate();
		}
	}

	public static void delete(Connection con, int id) throws SQLException {
		try (PreparedStatement pstmt = con.prepareStatement(SQL_DELETE_BRAND)) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		}
	}

}
