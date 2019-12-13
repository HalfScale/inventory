/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.dao;

/**
 *
 * @author Muffin
 */
public class ProductDao {
	private static final String SQL_CREATE_BRAND = "insert into `brand` (name, status) VALUES (?, ?)";
	private static final String SQL_GET_BRAND_BY_ID = "select * from `brand` where id = ?";
	private static final String SQL_GET_ALL_BRAND = "select * from `brand`";
	private static final String SQL_UPDATE_BRAND = "update `brand` set name = ?, status = ? where id = ?";
	private static final String SQL_DELETE_PRODUCT = "delete from `brand` where id = ?";
}
