/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.bean;

import javax.servlet.http.HttpServletRequest;
import system.util.Util;

/**
 *
 * @author MacMuffin
 */
public class Category {

	private int id;
	private String name;
	private boolean status;

	public Category() {
	}

	public Category(HttpServletRequest request) {
		this.id = Integer.parseInt(Util.isBlank(request.getParameter("id"), "-1"));
		this.name = request.getParameter("name");
		this.status = request.getParameter("status").equals("1");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
