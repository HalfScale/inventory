/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.bean;

import java.math.BigDecimal;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import system.dao.BrandDao;

/**
 *
 * @author MacMuffin
 */
public class Product {

	private int id;
	private Brand brand;
	private Category category;
	private String name;
	private String code;
	private String description;
	private BigDecimal price;
	private BigDecimal resellerPrice;
	private int stock;
	private boolean status;

	public Product() {
	}

	public Product(HttpServletRequest request, Connection con) {
		this.id = Integer.parseInt(request.getParameter("id") != null ? request.getParameter("id") : "-1");
		this.name = request.getParameter("name");
		this.code = request.getParameter("code");
		this.description = request.getParameter("description");
		this.price = new BigDecimal(request.getParameter("price"));
		this.resellerPrice = new BigDecimal(request.getParameter("resellerPrice"));
		this.stock = Integer.parseInt(request.getParameter("stock"));
		this.status = request.getParameter("status").equals("1");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getResellerPrice() {
		return resellerPrice;
	}

	public void setResellerPrice(BigDecimal resellerPrice) {
		this.resellerPrice = resellerPrice;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
